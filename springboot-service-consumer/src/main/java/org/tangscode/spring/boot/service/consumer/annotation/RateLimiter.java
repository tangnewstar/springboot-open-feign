package org.tangscode.spring.boot.service.consumer.annotation;

import java.lang.annotation.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2025/2/12
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimiter {
    int capacity() default 1;
    int refillTokens() default 1;
    int refillPeriod() default 1;
    ChronoUnit refillUnit() default ChronoUnit.SECONDS;
}
