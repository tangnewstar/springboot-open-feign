package org.tangscode.spring.boot.service.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author tangxinxing
 * @version 1.0
 * @description
 * @date 2025/2/6
 */
@FeignClient(name = "githubClient", url = "${feign.client.github.url}",
        fallback = GitHubClientFallback.class, fallbackFactory = GithubClientFallbackFactory.class)
public interface GitHubClient {

    @GetMapping("/repos/{owner}/{repo}")
    GitHubRepo getRepo(@PathVariable("owner") String owner, @PathVariable("repo") String repo);

    class GitHubRepo {
        private String name;
        private String description;

        // Getters and setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
