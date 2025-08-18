package br.com.alanpcavalcante.araraflyapi.infrastructure.docker;

import br.com.alanpcavalcante.araraflyapi.application.gateways.deploy.FileDeployment;

import java.nio.file.Path;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.exception.NotFoundException;
import java.io.File;
import java.util.Set;

/**
 * Implementação responsável pelo ciclo de vida de um deployment de contêiner.
 * Utiliza a biblioteca docker-java para construir imagens a partir de um Dockerfile local
 * e para iniciar e parar os contêineres correspondentes.
 */
public class DeploymentImpl implements FileDeployment {

    private final DockerClient dockerClient;

    public DeploymentImpl(DockerConnect dockerConnect) {
        this.dockerClient = dockerConnect.connectDockerLocal();
    }

    @Override
    public void up(Path dockerfilePath) {
        if (dockerfilePath == null || dockerfilePath.getParent() == null) {
            throw new IllegalArgumentException("O caminho do Dockerfile é inválido.");
        }

        Path buildContext = dockerfilePath.getParent();
        String uniqueTag = buildContext.getFileName().toString();
        String imageName = "deployment-" + uniqueTag + ":latest";
        String containerName = "container-" + uniqueTag;

        try {
            System.out.println("Iniciando a construção da imagem: " + imageName);
            String imageId = buildImage(buildContext, imageName);
            System.out.println("Imagem construída com sucesso! ID: " + imageId);

            System.out.println("Iniciando o contêiner: " + containerName);
            runContainer(imageName, containerName);
            System.out.println("Contêiner " + containerName + " iniciado com sucesso.");

        } catch (Exception e) {
            System.err.println("Falha no processo de deployment: " + e.getMessage());
            throw new RuntimeException("Falha ao realizar o deploy do contêiner.", e);
        }
    }

    @Override
    public void down(Path dockerfilePath) {
        if (dockerfilePath == null || dockerfilePath.getParent() == null) {
            System.err.println("Caminho inválido para realizar o 'down'.");
            return;
        }

        String uniqueTag = dockerfilePath.getParent().getFileName().toString();
        String containerName = "container-" + uniqueTag;
        String imageName = "deployment-" + uniqueTag + ":latest";

        try {
            System.out.println("Parando e removendo o contêiner: " + containerName);
            dockerClient.stopContainerCmd(containerName).exec();
            dockerClient.removeContainerCmd(containerName).exec();
            System.out.println("Contêiner removido com sucesso.");
        } catch (NotFoundException e) {
            System.out.println("Contêiner " + containerName + " não encontrado. Pode já ter sido removido.");
        } catch (Exception e) {
            System.err.println("Erro ao parar/remover o contêiner " + containerName + ": " + e.getMessage());
        }

        try {
            System.out.println("Removendo a imagem: " + imageName);
            dockerClient.removeImageCmd(imageName).withForce(true).exec();
            System.out.println("Imagem removida com sucesso.");
        } catch (NotFoundException e) {
            System.out.println("Imagem " + imageName + " não encontrada. Pode já ter sido removida.");
        } catch (Exception e) {
            System.err.println("Erro ao remover a imagem " + imageName + ": " + e.getMessage());
        }
    }

    private String buildImage(Path buildContext, String imageName) {
        File baseDir = buildContext.toFile();

        BuildImageResultCallback callback = new BuildImageResultCallback() {
            @Override
            public void onNext(com.github.dockerjava.api.model.BuildResponseItem item) {
                if (item.getStream() != null) {
                    System.out.print(item.getStream());
                }
                super.onNext(item);
            }
        };

        return dockerClient.buildImageCmd(baseDir)
                .withTags(Set.of(imageName))
                .exec(callback)
                .awaitImageId();
    }

    private void runContainer(String imageName, String containerName) {
        CreateContainerResponse container = dockerClient.createContainerCmd(imageName)
                .withName(containerName)
                .exec();

        dockerClient.startContainerCmd(container.getId()).exec();
    }
}
