package org.tangscode.spring.boot.service.consumer.controller;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.tangscode.spring.boot.service.consumer.annotation.RateLimiter;
import org.tangscode.spring.boot.service.consumer.feign.GitHubClient;
import org.tangscode.spring.boot.service.consumer.feign.HelloClient;

import javax.annotation.Resource;
import javax.cache.Cache;
import javax.cache.CacheManager;

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

    @Resource
    private Bucket bucket;

    @RateLimiter(capacity = 1, refillTokens = 1, refillPeriod = 1)
    @GetMapping("/hello")
    public String hello() {
        logger.info("hello() called");
        return helloClient.sayHello();
    }

    @RateLimiter(capacity = 1, refillTokens = 1, refillPeriod = 1)
    @GetMapping("/github/{owner}/{repo}")
    public GitHubClient.GitHubRepo getRepo(@PathVariable String owner, @PathVariable String repo) {
        logger.info("getRepo() called with owner: {} and repo: {}", owner, repo);
        return gitHubClient.getRepo(owner, repo);
    }

}
