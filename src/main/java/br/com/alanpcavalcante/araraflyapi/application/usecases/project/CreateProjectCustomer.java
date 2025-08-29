package br.com.alanpcavalcante.araraflyapi.application.usecases.project;

import br.com.alanpcavalcante.araraflyapi.application.gateways.project.ProjectRepository;
import br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions.LimitProjectExceeded;
import br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions.ProjectDuplicateWithNameAndDescription;
import br.com.alanpcavalcante.araraflyapi.domain.project.*;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.List;

public class CreateProjectCustomer {

    private final ProjectRepository projectRepository;

    public CreateProjectCustomer(ProjectRepository repository) {
        this.projectRepository = repository;
    }

    public Project create(ProjectRequest dto, User user) throws Exception {
        Price price = new PriceFactory().generate(dto.price(), dto.typePrice());

        Project project = new Project();
        project.setTitle(new Title(dto.title()));
        project.setDescription(new Description(dto.description()));
        project.setPrice(price);
        project.setTypePrice(price);
        project.setClosingDate(dto.closingDate());
        project.setDateCreated(dto.dateCreated());
        project.setStateBusiness(StateBusiness.OPEN);
        project.setCustomer(user);

        List<Project> projects = projectRepository.getProjectsByCustomerAndStateBusiness(user, StateBusiness.OPEN);

        if (projects.size() == 3) {
            throw new LimitProjectExceeded("Limit projects exceeded, limit: 3 by developer and customer");
        }

        for (Project projectList : projects) {
            if (project.getTitle().equals(projectList.getTitle()) || project.getDescription().equals(projectList.getDescription())) {
                throw new ProjectDuplicateWithNameAndDescription("A project with the same name or description already exists");
            }
        }
        return projectRepository.save(project);
    }

}
