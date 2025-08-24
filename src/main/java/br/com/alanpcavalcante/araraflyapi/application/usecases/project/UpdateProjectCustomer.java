package br.com.alanpcavalcante.araraflyapi.application.usecases.project;

import br.com.alanpcavalcante.araraflyapi.application.gateways.project.ProjectRepository;
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

    public Project update(UUID idProject, ProjectDto dto, User customer) {
        Project project = projectRepository.getProjectByIdProjectAndCustomer(idProject, customer)
                 .orElseThrow(() -> new RuntimeException("Projeto não pertence ao cliente"));

        StateBusiness state = project.getStateBusiness();
        if (!(state == StateBusiness.OPEN)) {
            throw new RuntimeException("So pode editar quando o projeto nao foi iniciado.");
        }

        Price price = priceFactory.generate(dto.price(), dto.typePrice());

        project.getTitle().setTitle(dto.title());
        project.getDescription().setDescription(dto.description());
        project.setPrice(price);
        project.setTypePrice(price);
        project.setClosingDate(dto.closingDate());
        project.setDateCreated(dto.dateCreated());
        project.setStateBusiness(dto.stateBusiness());

        return projectRepository.save(project);
    }

}
