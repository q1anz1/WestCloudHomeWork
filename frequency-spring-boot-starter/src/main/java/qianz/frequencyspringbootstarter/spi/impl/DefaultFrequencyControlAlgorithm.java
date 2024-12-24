package qianz.frequencyspringbootstarter.spi.impl;

import qianz.frequencyspringbootstarter.spi.FrequencyControlAlgorithm;

/**
 *
 */
public class DefaultFrequencyControlAlgorithm implements FrequencyControlAlgorithm {
    @Override
    public String getName() {
        return "default";
    }

    @Override
    public boolean tryAcquire() {
        return false;
    }
}
