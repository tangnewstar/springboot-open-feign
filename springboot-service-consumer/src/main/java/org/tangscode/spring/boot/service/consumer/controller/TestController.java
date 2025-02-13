package org.tangscode.spring.boot.service.consumer.controller;

import io.github.bucket4j.Bucket;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.tangscode.spring.boot.service.consumer.annotation.RateLimit;
import org.tangscode.spring.boot.service.consumer.feign.GitHubClient;
import org.tangscode.spring.boot.service.consumer.feign.HelloClient;

import javax.annotation.Resource;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2025/2/7
 */
@RestController
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Resource
    private HelloClient helloClient;

    @Resource
    private GitHubClient gitHubClient;

    @Autowired
    private io.github.resilience4j.ratelimiter.RateLimiter rateLimiter;

    @RateLimiter(name = "custom", fallbackMethod = "fallback")
    @GetMapping("/hello")
    public ResponseEntity hello() {
        logger.info("hello() called");
        return ResponseEntity.ok(helloClient.sayHello());
    }

    @RateLimit(capacity = 1, refillTokens = 1, refillPeriod = 1)
    @GetMapping("/github/{owner}/{repo}")
    public GitHubClient.GitHubRepo getRepo(@PathVariable String owner, @PathVariable String repo) {
        logger.info("getRepo() called with owner: {} and repo: {}", owner, repo);
        return gitHubClient.getRepo(owner, repo);
    }

    public ResponseEntity fallback(Throwable t) {
        logger.error("Fallback method called with exception: {}", t.toString());
        return ResponseEntity.ok("request limit exceeded");
    }
}
