package qianz.frequencyspringbootstarter.config.algorithm;

import lombok.Data;
import qianz.frequencyspringbootstarter.spi.impl.TokenBucketAlgorithm;

/**
 * 令牌桶算法配置类
 */
@Data
public class TokenBucketConfig {
    private final Integer bucketCapacity;
    private final Integer refillRate;

    public TokenBucketConfig() {
        this.bucketCapacity = 1000;
        this.refillRate = 1;
    }
}
