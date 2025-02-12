package org.tangscode.spring.boot.service.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2025/2/7
 */
@FeignClient(name = "SPRINGBOOT-SERVICE-PROVIDER", fallback = HelloClientFallback.class)
public interface HelloClient {
    @GetMapping("/hello/say")
    public String sayHello();
}
