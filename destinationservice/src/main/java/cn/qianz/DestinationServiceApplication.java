package cn.qianz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableHystrix
@EnableFeignClients
public class DestinationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DestinationServiceApplication.class, args);
    }
}
