package qianz.frequencyspringbootstarter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import qianz.frequencyspringbootstarter.config.FrequencyControlConfig;
import qianz.frequencyspringbootstarter.config.IpFrequencyControlConfig;

/**
 * 定义配置文件
* */
@Data
@ConfigurationProperties(prefix = "frequency-control")
public class FrequencyControlProperties {
    private IpFrequencyControlConfig ipFrequencyControlConfig = new IpFrequencyControlConfig();
    private FrequencyControlConfig frequencyControlConfig = new FrequencyControlConfig();
}
