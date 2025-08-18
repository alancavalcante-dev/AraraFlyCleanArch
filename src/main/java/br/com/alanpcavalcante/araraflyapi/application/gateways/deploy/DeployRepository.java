package br.com.alanpcavalcante.araraflyapi.application.gateways.deploy;

import br.com.alanpcavalcante.araraflyapi.domain.deploy.Deploy;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;

import java.util.Optional;


public interface DeployRepository {

    Deploy save(Deploy deploy);
    Optional<Deploy> getDeployByProject(Project project);

}
