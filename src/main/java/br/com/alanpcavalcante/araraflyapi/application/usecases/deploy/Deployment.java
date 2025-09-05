package br.com.alanpcavalcante.araraflyapi.application.usecases.deploy;

import br.com.alanpcavalcante.araraflyapi.application.gateways.deploy.DeployRepository;
import br.com.alanpcavalcante.araraflyapi.application.gateways.deploy.FileDeployment;
import br.com.alanpcavalcante.araraflyapi.domain.deploy.Deploy;

import java.io.IOException;
import java.nio.file.Path;

public class Deployment {

    private final FileDeployment fileDeployment;
    private final DeployRepository deployRepository;
    private final WorkspaceFile workspaceFile;
    private final GeneratorBuilderDeployment generatorBuilderDeployment;


    public Deployment(FileDeployment fileDeployment, DeployRepository deployRepository, WorkspaceFile workspaceFile,
                      GeneratorBuilderDeployment generatorBuilderDeployment) {
        this.fileDeployment = fileDeployment;
        this.deployRepository = deployRepository;
        this.workspaceFile = workspaceFile;
        this.generatorBuilderDeployment = generatorBuilderDeployment;
    }


    public boolean upDeploy(Deploy deploy) throws IOException {
        if (deploy.getUp()) {
            return true;
        }

        String username = deploy.getProject().getDeveloper().getLogin().getLogin();

        String file = generatorBuilderDeployment.generate(deploy, username, "batataquente");
        Path workspaceFileSaved = workspaceFile.upSaveBuilderFileWorkspace(file, deploy.getProject().getIdProject());
        fileDeployment.up(workspaceFileSaved);
        workspaceFile.dropBuilderFileWorkspace(workspaceFileSaved);

        deploy.setPath(workspaceFileSaved);
        deploy.setUp(true);
        deployRepository.save(deploy);
        return true;
    }


    public boolean downDeploy(Deploy deploy) {
        if (!deploy.getUp()) {
            return true;
        }

        try {
            Path path = deploy.getPath();
            boolean exist = workspaceFile.findPathBuilderFile(path);
            if (exist) {
                fileDeployment.down(path);

                deploy.setUp(false);
                deploy.setPath(Path.of(""));
                deployRepository.save(deploy);
                return true;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException(e);
        }

        return false;
    }

}
