package br.com.alanpcavalcante.araraflyapi.application.usecases.project;

import br.com.alanpcavalcante.araraflyapi.application.gateways.project.ProjectRepository;
import br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions.ProjectNotStating;
import br.com.alanpcavalcante.araraflyapi.application.usecases.exceptions.UserDoesNotBelong;
import br.com.alanpcavalcante.araraflyapi.domain.project.Price;
import br.com.alanpcavalcante.araraflyapi.domain.project.PriceFactory;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.UUID;

public class UpdateProjectCustomer {

    private final ProjectRepository projectRepository;
    private final PriceFactory priceFactory;

    public UpdateProjectCustomer(ProjectRepository projectRepository, PriceFactory priceFactory) {
        this.projectRepository = projectRepository;
        this.priceFactory = priceFactory;
    }

    public Project update(UUID idProject, ProjectRequest dto, User customer) {
        Project project = projectRepository.getProjectByIdProjectAndCustomer(idProject, customer)
                 .orElseThrow(() -> new UserDoesNotBelong("User does not belong"));

        StateBusiness state = project.getStateBusiness();
        if (!(state == StateBusiness.OPEN)) {
            throw new ProjectNotStating("You can only edit when the project has not been started");
        }

        Price price = priceFactory.generate(dto.price(), dto.typePrice());

        project.getTitle().setTitle(dto.title());
        project.getDescription().setDescription(dto.description());
        project.setPrice(price);
        project.setTypePrice(price);
        project.setClosingDate(dto.closingDate());
        project.setDateCreated(dto.dateCreated());

        return projectRepository.save(project);
    }

}
