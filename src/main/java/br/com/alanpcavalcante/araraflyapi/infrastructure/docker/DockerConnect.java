package br.com.alanpcavalcante.araraflyapi.infrastructure.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.transport.DockerHttpClient;
import com.github.dockerjava.zerodep.ZerodepDockerHttpClient;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class DockerConnect {

    @Value("${docker.host}")
    private String dockerHost;

    public DockerClient connectDockerLocal() {
        DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(dockerHost)
                .build();

        DockerHttpClient httpClient = new ZerodepDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .sslConfig(config.getSSLConfig())
                .build();

        return DockerClientImpl.getInstance(config, httpClient);
    }

    @PostConstruct
    public void testConnection() {
        try (DockerClient dockerClient = connectDockerLocal()) {
            System.out.println("Docker version: " + dockerClient.versionCmd().exec().getVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}