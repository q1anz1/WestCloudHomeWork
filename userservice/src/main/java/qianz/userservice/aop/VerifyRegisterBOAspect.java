package qianz.userservice.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import qianz.cloudapicommon.pojo.BO.RegisterBO;
import qianz.userservice.util.ValidUtil;

/**
* 检查注册用户时的参数的aop
* */
@Slf4j
@Aspect
@Component
public class VerifyRegisterBOAspect {
    /**
    * 对打上@VerifyRegisterParam注解的方法检测是否包含RegisterBO，如果有则检查其中的参数是否合法
    * */
    @Before("@annotation(qianz.userservice.anno.VerifyRegisterBO)")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg: args) {
            if (arg instanceof RegisterBO registerBO) {
                ValidUtil.isRegisterBOValid(registerBO);
            }
        }
    }
}
