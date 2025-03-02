package qianz.frequencyspringbootstarter.spi.impl;

import cn.hutool.extra.spring.SpringUtil;
import lombok.Data;
import qianz.frequencyspringbootstarter.properties.FrequencyControlProperties;
import qianz.frequencyspringbootstarter.spi.FrequencyControlAlgorithm;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 漏桶算法
 */
@Data
public class LeakyBucketAlgorithm implements FrequencyControlAlgorithm {
    private final FrequencyControlProperties frequencyControlProperties;
    private  final LinkedBlockingQueue<String> que;

    public LeakyBucketAlgorithm() {
        this.frequencyControlProperties = SpringUtil.getBean(FrequencyControlProperties.class);
        this.que = new LinkedBlockingQueue<>(this.frequencyControlProperties.getFrequencyControlConfig().getLeakyBucketConfig().getCapacity());
        start();
    }

    @Override
    public String getName() {
        return "leaky-bucket";
    }

    @Override
    public boolean tryAcquire() {
        try {
            que.offer(" ",1000,TimeUnit.MILLISECONDS); // 如果一秒内进不了桶就丢弃
        } catch (InterruptedException e) {
            return false;
        }
        return true;
    }

    private void start() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(que::poll, 0, 1000/frequencyControlProperties.getFrequencyControlConfig().getLeakyBucketConfig().getRate(), TimeUnit.MILLISECONDS);
    }
}
