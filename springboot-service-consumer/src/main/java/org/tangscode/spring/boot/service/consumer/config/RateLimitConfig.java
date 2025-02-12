package org.tangscode.spring.boot.service.consumer.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2025/2/10
 */
@Configuration
public class RateLimitConfig {

    @Bean
    public Bucket bucket() {
        Bandwidth limit = Bandwidth.classic(1, Refill.intervally(1, Duration.ofSeconds(1)));
        return Bucket4j.builder().addLimit(limit).build();
    }
}
