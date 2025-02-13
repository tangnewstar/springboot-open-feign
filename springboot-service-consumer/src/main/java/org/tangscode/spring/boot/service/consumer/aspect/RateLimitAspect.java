package org.tangscode.spring.boot.service.consumer.aspect;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.tangscode.spring.boot.service.consumer.annotation.RateLimit;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2025/2/12
 */
@Aspect
@Component
public class RateLimitAspect {
    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Around("@annotation(rateLimit)")
    public Object rateLimit(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        String key = joinPoint.getSignature().toShortString();
        Bucket bucket = buckets.computeIfAbsent(key, k -> createBucket(rateLimit));
        if (bucket.tryConsume(1)) {
            return joinPoint.proceed();
        } else {
            return "rate limit exceeded";
//            throw new RuntimeException("rate limit exceeded");
        }
    }

    private Bucket createBucket(RateLimit rateLimiter) {
        // refill tokens and interval
        Refill refill = Refill.intervally(rateLimiter.refillTokens(),
                Duration.of(rateLimiter.refillPeriod(), rateLimiter.refillUnit()));
        Bandwidth limit = Bandwidth.classic(rateLimiter.capacity(), refill);
        return Bucket4j.builder().addLimit(limit).build();
    }
}
