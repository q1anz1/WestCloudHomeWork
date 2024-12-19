package qianz.frequencyspringbootstarter.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import qianz.frequencyspringbootstarter.anno.IpFrequencyControl;
import qianz.frequencyspringbootstarter.config.IpFrequencyControlConfig;
import qianz.frequencyspringbootstarter.exception.FrequencyControlException;
import qianz.frequencyspringbootstarter.util.RedisUtil;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 频率控制切面
 * */
@Slf4j
@Aspect
@AllArgsConstructor
public class IpFrequencyControlAspect {
    private final IpFrequencyControlConfig ipFrequencyControlConfig;
    @Before("@annotation(qianz.frequencyspringbootstarter.anno.IpFrequencyControl)")
    public void before(JoinPoint joinPoint) throws Exception {
        // 获取请求参数
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) throw new Exception("该方法不是一个该被标记的方法");
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
//        String ip = request.getHeader("X-Forwarded-For").split(",")[0];
        String ip = request.getRemoteAddr();
        // 获取注解中的参数
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        IpFrequencyControl annotation = method.getAnnotation(IpFrequencyControl.class);
        // 赋值：是要用application中的默认值还是注解中指定的值
        String[] keys = annotation.key();
        int time = annotation.time();
        if (time == -1) time = ipFrequencyControlConfig.getDefaultTime();
        int frequency = annotation.frequency();
        if (frequency == -1) frequency = ipFrequencyControlConfig.getDefaultFrequency();
        TimeUnit timeUnit = annotation.timeUnit();
        if (timeUnit == TimeUnit.NANOSECONDS) {
            timeUnit = switch (ipFrequencyControlConfig.getDefaultTimeUnit()) {
                case "minute" -> TimeUnit.MINUTES;
                case "hour" -> TimeUnit.HOURS;
                case "day" -> TimeUnit.DAYS;
                default -> TimeUnit.SECONDS;
            };
        }
        // 检查是否超过频率
        if (keys.length == 0) {
            // 根据url限流
            // 查访问次数
            String count = RedisUtil.readIpFrequencyControlUri(uri, ip);
            if (count != null && Integer.parseInt(count) >= frequency) throw new FrequencyControlException("访问频率过高");
            // 没超,计数器+1
            RedisUtil.increaseFrequencyUrl(uri, ip, time, timeUnit);
        } else {
            // 根据key进行限流
            for (String key: keys) {
                // 查访问次数
                String count = RedisUtil.readIpFrequencyControl(key, ip);
                if (count != null && Integer.parseInt(count) >= frequency) throw new FrequencyControlException("访问频率过高");
                // 没超,计数器+1
                RedisUtil.increaseFrequency(key, ip, time, timeUnit);
            }
        }
    }

}
