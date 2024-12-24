package qianz.frequencyspringbootstarter.spi;

/**
 * 频率控制算法接口
 */
public interface FrequencyControlAlgorithm {
    String getName();
    boolean tryAcquire();
}
