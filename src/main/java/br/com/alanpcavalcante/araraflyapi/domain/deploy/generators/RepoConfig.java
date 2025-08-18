package br.com.alanpcavalcante.araraflyapi.domain.deploy.generators;

public class RepoConfig {
    private final String repoHost;
    private final String repoOwner;
    private final String repoName;

    public RepoConfig(String getHost, String repoOwner, String repoName) {
        this.repoHost = getHost;
        this.repoOwner = repoOwner;
        this.repoName = repoName;
    }

    public String getHost() {
        return repoHost;
    }

    public String getOwner() {
        return repoOwner;
    }

    public String getRepoName() {
        return repoName;
    }
}
