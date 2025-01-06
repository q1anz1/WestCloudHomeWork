package qianz.frequencyspringbootstarter.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import qianz.frequencyspringbootstarter.anno.FrequencyControl;
import qianz.frequencyspringbootstarter.exception.FrequencyControlException;
import qianz.frequencyspringbootstarter.properties.FrequencyControlProperties;
import qianz.frequencyspringbootstarter.spi.FrequencyControlAlgorithm;

import java.lang.reflect.Method;
import java.util.ServiceLoader;

/**
 * 频率控制切面
 */
@Slf4j
@Aspect
public class FrequencyControlAspect {
    private FrequencyControlAlgorithm frequencyControlAlgorithm;

    public FrequencyControlAspect(FrequencyControlProperties frequencyControlProperties) {
        ServiceLoader<FrequencyControlAlgorithm> serviceLoader = ServiceLoader.load(FrequencyControlAlgorithm.class);
        for (FrequencyControlAlgorithm algorithm: serviceLoader) {
            if (algorithm.getName().equals(frequencyControlProperties.getFrequencyControlConfig().getAlgorithmName())) {
                this.frequencyControlAlgorithm = algorithm;
            }
        }
        if (frequencyControlAlgorithm == null) {
            log.error("未找到application中所配置的算法，将使用默认的token-bucket");
            for (FrequencyControlAlgorithm algorithm: serviceLoader) {
                if (algorithm.getName().equals("token-bucket")) {
                    this.frequencyControlAlgorithm = algorithm;
                }
            }
        }
        if (frequencyControlAlgorithm == null) throw new RuntimeException("限流算法(包括默认算法)装配失败");
    }

    @Before("@annotation(qianz.frequencyspringbootstarter.anno.FrequencyControl) || @within(qianz.frequencyspringbootstarter.anno.FrequencyControl)")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        FrequencyControl annotation = method.getAnnotation(FrequencyControl.class);
        if (annotation == null) {
            // 如果annotation为空，这说明注解是加在类上的
            Class<?> declaringClass = method.getDeclaringClass();
            annotation = declaringClass.getAnnotation(FrequencyControl.class);
        }
        // 判断是否被限流
        if (!frequencyControlAlgorithm.tryAcquire()) throw new FrequencyControlException("频率控制禁止了这次访问");
    }
}
