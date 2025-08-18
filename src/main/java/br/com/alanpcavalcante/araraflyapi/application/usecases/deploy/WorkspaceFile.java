package br.com.alanpcavalcante.araraflyapi.application.usecases.deploy;

import br.com.alanpcavalcante.araraflyapi.application.gateways.deploy.Workspace;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Implementação de Workspace que opera diretamente no sistema de arquivos local.
 * Cria diretórios de trabalho temporários para armazenar arquivos de build (ex: Dockerfile)
 * e gerencia sua criação e exclusão.
 */
public class WorkspaceFile implements Workspace {

    private static final String BUILDER_FILE_NAME = "Dockerfile";

    /**
     * Cria um diretório de trabalho único e temporário para um deployment.
     * A estrutura será: {diretório_temporário_do_sistema}/deployments/{uuid}/
     *
     * @return O caminho (Path) para o diretório do workspace criado.
     */
    private Path createWorkspace(UUID idProject) {
        String uniqueTag = idProject.toString();
        Path workspacePath;

        try {
            String tempDir = System.getProperty("java.io.tmpdir");
            Path fullPath = Paths.get(tempDir, "deployments", uniqueTag);
            workspacePath = Files.createDirectories(fullPath);
            System.out.printf("Workspace criado em: %s%n", workspacePath);
            return workspacePath;

        } catch (IOException e) {
            throw new RuntimeException("Falha ao criar diretório de trabalho.", e);
        }
    }

    /**
     * Verifica se o arquivo de build (ex: Dockerfile) existe dentro de um workspace específico.
     *
     * @param workspacePath O caminho para o diretório do workspace a ser verificado.
     * @return `true` se o arquivo de build for encontrado, `false` caso contrário.
     */
    @Override
    public boolean findPathBuilderFile(Path workspacePath) {
        if (workspacePath == null || !Files.isDirectory(workspacePath)) {
            return false;
        }
        Path builderFile = workspacePath.resolve(BUILDER_FILE_NAME);
        return Files.exists(builderFile) && Files.isRegularFile(builderFile);
    }

    /**
     * Cria um novo workspace e salva o conteúdo do arquivo de build (ex: Dockerfile) dentro dele.
     *
     * @param buildContent O conteúdo (em String) a ser escrito no arquivo de build.
     * @return O caminho (Path) para o arquivo de build recém-criado.
     * @throws IOException se ocorrer um erro de E/S ao escrever o arquivo.
     */
    @Override
    public Path upSaveBuilderFileWorkspace(String buildContent, UUID idProject) throws IOException {
        Path workspacePath = createWorkspace(idProject);
        Path builderFile = workspacePath.resolve(BUILDER_FILE_NAME);
        return Files.writeString(builderFile, buildContent);
    }

    /**
     * Exclui de forma segura e recursiva todo o diretório do workspace e seu conteúdo.
     * Essencial para limpeza e para evitar o acúmulo de lixo no disco.
     *
     * @param workspacePath O caminho para o diretório do workspace a ser excluído.
     * @return `true` se a exclusão for bem-sucedida, `false` caso contrário.
     */
    @Override
    public boolean dropBuilderFileWorkspace(Path workspacePath) {
        if (workspacePath == null || !Files.exists(workspacePath)) {
            System.out.println("Workspace não encontrado para exclusão (ou já foi excluído): " + workspacePath);
            return true;
        }

        try (Stream<Path> walk = Files.walk(workspacePath)) {
            walk.sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            throw new RuntimeException("Falha ao excluir o arquivo/diretório: " + path, e);
                        }
                    });
            System.out.printf("Workspace excluído com sucesso: %s%n", workspacePath);
            return true;
        } catch (IOException | RuntimeException e) {
            System.err.printf("Erro ao tentar excluir o workspace %s: %s%n", workspacePath, e.getMessage());
            return false;
        }
    }
}