package qianz.frequencyspringbootstarter.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * 频率控制配置类
 */
@Data
@Configuration
@NoArgsConstructor
public class FrequencyControlConfig {
    private final String algorithm = "token bucket";
}
