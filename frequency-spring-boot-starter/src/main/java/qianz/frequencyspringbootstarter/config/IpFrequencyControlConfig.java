package qianz.frequencyspringbootstarter.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
/**
 * ip频率控制配置类
* */
@Data
@Configuration
@NoArgsConstructor
public class IpFrequencyControlConfig {
    /**
     * 默认时间
    * */
    private final Integer defaultTime = 10;
    /**
     * 默认频率
     * */
    private final Integer defaultFrequency = 5;
    /**
     * 默认时间单位 second, minute, hour, day
     * */
    private final String defaultTimeUnit = "second";
}
