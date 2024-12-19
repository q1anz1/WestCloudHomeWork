package qianz.frequencyspringbootstarter.anno;

import org.springframework.context.annotation.Import;
import qianz.frequencyspringbootstarter.properties.FrequencyControlAutoConfiguration;

import java.lang.annotation.*;

/**
 * 开启频率控制
* */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({FrequencyControlAutoConfiguration.class})
public @interface EnableFrequencyControl {
}
