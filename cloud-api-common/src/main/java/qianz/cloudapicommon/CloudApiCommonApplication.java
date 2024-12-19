package qianz.cloudapicommon;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class CloudApiCommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudApiCommonApplication.class, args);
    }
}
