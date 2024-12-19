package qianz.frequencyspringbootstarter.anno;

import qianz.frequencyspringbootstarter.spi.impl.DefaultFrequencyControlAlgorithm;

import java.lang.annotation.*;

/**
 * 可以自定义算法的频率控制
 * */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FrequencyControl {
    /**
     * 指定算法类，如果没指定，就用默认的
     * */
    Class<? extends DefaultFrequencyControlAlgorithm> frequencyControlAlgorithm() default DefaultFrequencyControlAlgorithm.class;
}
