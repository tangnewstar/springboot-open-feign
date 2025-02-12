package org.tangscode.spring.boot.service.consumer.feign;

import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2025/2/8
 */
@Component
public class GithubClientFallbackFactory implements FallbackFactory<GitHubClient> {
    @Override
    public GitHubClient create(Throwable cause) {
        return new GitHubClient() {
            @Override
            public GitHubRepo getRepo(String owner, String repo) {
                if (cause instanceof FeignException) {
                    FeignException fe = (FeignException) cause;
                    if (fe.status() == 404) {
                        GitHubRepo fallbackRepo = new GitHubRepo();
                        fallbackRepo.setName("-");
                        fallbackRepo.setDescription("无效用户或仓库");
                        return fallbackRepo;
                    }
                }
                GitHubRepo fallbackRepo = new GitHubRepo();
                fallbackRepo.setName("-");
                fallbackRepo.setDescription("服务调用失败");
                return fallbackRepo;
            }
        };
    }
}
