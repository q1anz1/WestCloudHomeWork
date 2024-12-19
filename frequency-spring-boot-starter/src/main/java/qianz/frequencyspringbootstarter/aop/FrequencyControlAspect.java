package qianz.frequencyspringbootstarter.aop;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import qianz.frequencyspringbootstarter.config.FrequencyControlConfig;

/**
 * 频率控制切面
 */
@Slf4j
@Aspect
@AllArgsConstructor
public class FrequencyControlAspect {
    private FrequencyControlConfig frequencyControlConfig;
    @Before("@annotation(qianz.frequencyspringbootstarter.anno.FrequencyControl)")
    public void before(JoinPoint joinPoint) {

    }
}
