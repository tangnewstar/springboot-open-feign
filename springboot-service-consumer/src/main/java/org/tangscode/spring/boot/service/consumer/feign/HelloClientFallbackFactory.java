package org.tangscode.spring.boot.service.consumer.feign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2025/2/8
 */
@Component
public class HelloClientFallbackFactory implements FallbackFactory<HelloClient> {
    @Override
    public HelloClient create(Throwable throwable) {
        return new HelloClient() {
            @Override
            public String sayHello() {
                return "Hello, Spring Boot! (fallback factory)";
            }
        };
    }
}
