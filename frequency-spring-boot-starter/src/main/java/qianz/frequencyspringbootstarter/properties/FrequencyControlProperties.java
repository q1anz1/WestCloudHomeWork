package qianz.frequencyspringbootstarter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 定义配置文件
* */
@Data
@ConfigurationProperties(prefix = "frequency-control")
public class FrequencyControlProperties {
    private IpFrequencyControlConfig ipFrequencyControlConfig = new IpFrequencyControlConfig();
    private FrequencyControlConfig frequencyControlConfig = new FrequencyControlConfig();

    @Data
    public static class FrequencyControlConfig {
        private String AlgorithmName = "token-bucket";
        private TokenBucketConfig tokenBucketConfig = new TokenBucketConfig();
        private LeakyBucketConfig leakyBucketConfig = new LeakyBucketConfig();

        /**
         * 漏桶算法配置类
         */
        @Data
        public static class LeakyBucketConfig {
            private Integer capacity = 1000;
            private Integer rate = 100; // 每秒多少个
        }

        /**
         * 令牌桶算法配置类
         */
        @Data
        public static class TokenBucketConfig {
            private Integer bucketCapacity = 1000;
            private Integer refillRate = 1;
        }
    }

    @Data
    public static class IpFrequencyControlConfig {
        /**
         * 默认时间
         * */
        private Integer defaultTime = 10;
        /**
         * 默认频率
         * */
        private Integer defaultFrequency = 5;
        /**
         * 默认时间单位 second, minute, hour, day
         * */
        private String defaultTimeUnit = "second";
    }
}
