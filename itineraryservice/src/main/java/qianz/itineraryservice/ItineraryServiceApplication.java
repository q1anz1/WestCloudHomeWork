package qianz.itineraryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import qianz.itineraryservice.config.FallbackConfiguration;

@SpringBootApplication
@EnableFeignClients(defaultConfiguration = FallbackConfiguration.class)
public class ItineraryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItineraryServiceApplication.class, args);
    }
}
