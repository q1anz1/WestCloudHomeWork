package qianz.frequencyspringbootstarter.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import qianz.frequencyspringbootstarter.config.algorithm.LeakyBucketConfig;
import qianz.frequencyspringbootstarter.config.algorithm.TokenBucketConfig;

/**
 * 频率控制配置类
 */
@Data
@Configuration
@NoArgsConstructor
public class FrequencyControlConfig {
    private final String AlgorithmName = "token-bucket";
    private final TokenBucketConfig tokenBucketConfig = new TokenBucketConfig();
    private final LeakyBucketConfig leakyBucketConfig = new LeakyBucketConfig();
}
