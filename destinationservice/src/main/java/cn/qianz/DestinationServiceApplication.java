package cn.qianz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DestinationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DestinationServiceApplication.class, args);
    }
}
