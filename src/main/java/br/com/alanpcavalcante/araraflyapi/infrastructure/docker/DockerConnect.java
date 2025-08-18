package br.com.alanpcavalcante.araraflyapi.infrastructure.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.transport.DockerHttpClient;
import com.github.dockerjava.zerodep.ZerodepDockerHttpClient;
import org.springframework.stereotype.Component;


@Component
public class DockerConnect {

    public DockerClient connectDockerLocal() {
        // Carrega a configuração padrão do Docker (DOCKER_HOST do ambiente, etc.)
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();

        // Cria o cliente HTTP usando o transporte Zerodep que está no seu pom.xml
        DockerHttpClient httpClient = new ZerodepDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .sslConfig(config.getSSLConfig())
                .build();

        // Retorna a instância final do DockerClient, pronta para uso
        return DockerClientImpl.getInstance(config, httpClient);
    }
}