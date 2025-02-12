package org.tangscode.spring.boot.service.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2025/2/7
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class ServiceConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerApplication.class, args);
    }
}
