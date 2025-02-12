package org.tangscode.spring.boot.service.consumer.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2025/2/6
 */
@Component
public class GitHubClientFallback implements GitHubClient {

    private static final Logger logger = LoggerFactory.getLogger(GitHubClientFallback.class);

    @Override
    public GitHubRepo getRepo(String owner, String repo) {
        logger.error("Fallback method called for getRepo with owner: {} and repo: {}", owner, repo);
        GitHubRepo fallbackRepo = new GitHubRepo();
        fallbackRepo.setName("-");
        fallbackRepo.setDescription("无效用户或仓库");
        return fallbackRepo;
    }
}
