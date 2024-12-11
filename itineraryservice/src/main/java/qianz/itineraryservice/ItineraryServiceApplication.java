package qianz.itineraryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import qianz.cloudapicommon.service.DestinationService;

@SpringBootApplication
@EnableFeignClients
public class ItineraryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItineraryServiceApplication.class, args);
    }
}
