package qianz.frequencyspringbootstarter.anno;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;
/**
 * 频率控制注解
 * */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface IpFrequencyControl {
    /**
     * 标识前缀，用于让几个url共享限流，此时所有包含同一个key的请求共享同一个计数器
    * */
    String[] key() default {};

    /**
     * 单位时间，-1为使用默认值
    * */
    int time() default -1;

    /**
     * 在单位时间内的频率，-1为使用默认值
    * */
    int frequency() default -1;

    /**
     * 时间单位，TimeUnit.NANOSECONDS为使用默认值
    * */
    TimeUnit timeUnit() default TimeUnit.NANOSECONDS;
}
