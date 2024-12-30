package qianz.frequencyspringbootstarter.config.algorithm;

import lombok.Data;

/**
 * 漏桶算法配置类
 */
@Data
public class LeakyBucketConfig {
    private final Integer capacity;
    private final Integer rate; // 每秒多少个

    public LeakyBucketConfig() {
        this.capacity = 1000;
        this.rate = 100;
    }
}
