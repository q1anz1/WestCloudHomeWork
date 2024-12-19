package qianz.frequencyspringbootstarter.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import qianz.frequencyspringbootstarter.aop.IpFrequencyControlAspect;
import qianz.frequencyspringbootstarter.spi.impl.DefaultFrequencyControlAlgorithm;

/**
 * 频率控制自动装配类
* */
@EnableConfigurationProperties(value = FrequencyControlProperties.class)
@Configuration
public class FrequencyControlAutoConfiguration {
    @Bean
    public IpFrequencyControlAspect frequencyControlAspect(FrequencyControlProperties frequencyControlProperties) {
        return new IpFrequencyControlAspect(frequencyControlProperties.getIpFrequencyControlConfig());
    }

    @Bean
    public DefaultFrequencyControlAlgorithm defaultFrequencyControlAlgorithm() {
        return new DefaultFrequencyControlAlgorithm();
    }
}
