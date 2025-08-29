package br.com.alanpcavalcante.araraflyapi.application.usecases.deploy;

import br.com.alanpcavalcante.araraflyapi.application.gateways.deploy.DeployRepository;
import br.com.alanpcavalcante.araraflyapi.domain.deploy.Deploy;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

public class GetDeploy {

    private final DeployRepository deployRepository;

    public GetDeploy(DeployRepository deployRepository) {
        this.deployRepository = deployRepository;
    }

    public Deploy get(User userLogged, Project project) {
        if (project.getStateBusiness() == StateBusiness.CANCELED || project.getStateBusiness() == StateBusiness.FINISHED) {
            throw new IllegalArgumentException("Esse projeto não está em produção");
        }

        if (!(userLogged.getId().equals(project.getCustomer().getId()) || userLogged.getId().equals(project.getDeveloper().getId()))) {
            throw new IllegalArgumentException("Usuário não pertence ao projeto");
        }

        return deployRepository.getDeployByProject(project)
                .orElseThrow(() -> new IllegalArgumentException("Deploy do projeto não encontrado"));

    }
}
