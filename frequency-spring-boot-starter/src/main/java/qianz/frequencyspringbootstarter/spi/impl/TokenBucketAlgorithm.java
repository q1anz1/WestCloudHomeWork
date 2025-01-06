package qianz.frequencyspringbootstarter.spi.impl;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import qianz.frequencyspringbootstarter.properties.FrequencyControlProperties;
import qianz.frequencyspringbootstarter.spi.FrequencyControlAlgorithm;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 频率控制算法实现类
 */
@Data
@Slf4j
public class TokenBucketAlgorithm implements FrequencyControlAlgorithm {
    private final FrequencyControlProperties frequencyControlProperties;
    private static AtomicInteger tokens = new AtomicInteger(0);

    public TokenBucketAlgorithm() {
        this.frequencyControlProperties = SpringUtil.getBean(FrequencyControlProperties.class);
        startRefilling();
    }

    @Override
    public String getName() {
        return "token-bucket";
    }

    @Override
    public boolean tryAcquire() {
        int currentTokens = tokens.get();
        if (currentTokens > 0) {
            if (tokens.compareAndSet(currentTokens, currentTokens - 1)) {
                System.out.println("请求获得令牌，允许通过");
                return true;
            }
        }
        System.out.println("请求被拒绝：无可用令牌");
        return false;
    }

    private void startRefilling() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            int currentTokens = tokens.get();
            int bucketCapacity = frequencyControlProperties.getFrequencyControlConfig().getTokenBucketConfig().getBucketCapacity();
            int refillRate = frequencyControlProperties.getFrequencyControlConfig().getTokenBucketConfig().getRefillRate();
            if (currentTokens < bucketCapacity) {
                int newTokens = Math.min(bucketCapacity, currentTokens + refillRate);
                tokens.set(newTokens);
                log.info("新增令牌，当前令牌数：{}", newTokens);
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}
