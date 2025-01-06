package qianz.frequencyspringbootstarter.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import qianz.frequencyspringbootstarter.aop.FrequencyControlAspect;
import qianz.frequencyspringbootstarter.aop.IpFrequencyControlAspect;

/**
 * 频率控制自动装配类
* */
@EnableConfigurationProperties(value = FrequencyControlProperties.class)
@Configuration
public class FrequencyControlAutoConfiguration {
    /**
     * ip频率控制切面
     * */
    @Bean
    public IpFrequencyControlAspect ipFrequencyControlAspect(FrequencyControlProperties frequencyControlProperties) {
        return new IpFrequencyControlAspect(frequencyControlProperties);
    }

    /**
     * 全局频率控制切面
     * */
    @Bean
    public FrequencyControlAspect frequencyControlAspect(FrequencyControlProperties frequencyControlProperties) {
        return new FrequencyControlAspect(frequencyControlProperties);
    }
}
