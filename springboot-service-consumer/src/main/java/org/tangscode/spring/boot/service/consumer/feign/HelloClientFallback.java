package org.tangscode.spring.boot.service.consumer.feign;

import org.springframework.stereotype.Component;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2025/2/7
 */
@Component
public class HelloClientFallback implements HelloClient {
    @Override
    public String sayHello() {
        return "Hello, this is fallback method.";
    }
}
