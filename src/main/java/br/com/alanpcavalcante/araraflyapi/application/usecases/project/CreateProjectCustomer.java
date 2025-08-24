package br.com.alanpcavalcante.araraflyapi.application.usecases.project;

import br.com.alanpcavalcante.araraflyapi.application.gateways.project.ProjectRepository;
import br.com.alanpcavalcante.araraflyapi.domain.project.*;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.List;

public class CreateProjectCustomer {

    private final ProjectRepository projectRepository;
    private final Project project;

    public CreateProjectCustomer(ProjectRepository repository, Project project) {
        this.projectRepository = repository;
        this.project = project;
    }

    public Project create(ProjectDto dto, User user) throws Exception {
        Price price = new PriceFactory().generate(dto.price(), dto.typePrice());

        project.setTitle(new Title(dto.title()));
        project.setDescription(new Description(dto.description()));
        project.setPrice(price);
        project.setTypePrice(price);
        project.setClosingDate(dto.closingDate());
        project.setDateCreated(dto.dateCreated());
        project.setStateBusiness(StateBusiness.OPEN);
        project.setUser(user);

        List<Project> projects = projectRepository.getProjectsByCustomerAndStateBusiness(user, StateBusiness.OPEN);
        if (projects.size() == 3) {
            throw new Exception("Não pode ter mais de 3 projetos");
        }
        for (Project projectList : projects) {
            if (project.getTitle().equals(projectList.getTitle()) || project.getDescription().equals(projectList.getDescription())) {
                throw new RuntimeException("Já existe um projeto com o mesmo nome ou descrição");
            }
        }
        return projectRepository.save(project);
    }

}
