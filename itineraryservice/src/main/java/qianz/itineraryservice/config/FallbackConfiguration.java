package qianz.itineraryservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import qianz.itineraryservice.client.DestinationClientHystrix;

@Configuration
public class FallbackConfiguration {
    @Bean
    public DestinationClientHystrix destinationClientHystrix() {
        return new DestinationClientHystrix();
    }
}
