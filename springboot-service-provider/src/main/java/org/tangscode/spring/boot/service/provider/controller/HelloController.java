package org.tangscode.spring.boot.service.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2025/2/7
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/say")
    public String sayHello() {
        return "Hello, Spring Boot!";
    }
}
