package org.tangscode.spring.boot.service.consumer.config;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Duration;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2025/2/10
 */
@Configuration
public class RateLimitConfig {

//    @Bean
//    public RateLimiter rateLimiter() {
//        RateLimiterConfig config = RateLimiterConfig.custom()
//                .limitForPeriod(1)
//                .limitRefreshPeriod(Duration.ofSeconds(1))
//                .timeoutDuration(Duration.ofMillis(0))
//                .build();
//        return RateLimiterRegistry.of(config).rateLimiter("custom");
//    }

    // 只有注入了RateLimiterRegistry的bean才能使用自定义的RateLimiter，
    // 推荐还是在application.yml中配置，更简单
    @Bean
    public RateLimiterRegistry rateLimiterRegistry() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(1)
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .timeoutDuration(Duration.ZERO)
                .build();

        // 输出当前配置以确认它是否是你自定义的配置
        System.out.println("Custom RateLimiterConfig: " + config);

        RateLimiterRegistry registry = RateLimiterRegistry.of(config);
        return registry;
    }

    @Bean
    public RateLimiter rateLimiter(RateLimiterRegistry rateLimiterRegistry) {
        return rateLimiterRegistry.rateLimiter("custom");
    }
}
