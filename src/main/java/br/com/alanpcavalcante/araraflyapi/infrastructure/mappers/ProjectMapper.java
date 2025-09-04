package br.com.alanpcavalcante.araraflyapi.infrastructure.mappers;

import br.com.alanpcavalcante.araraflyapi.domain.project.*;
import br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.dto.ProjectResponse;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProjectMapper {

    @Autowired
    private PriceFactory priceFactory;

    @Autowired
    private UserMapper userMapper;


    public Project  entityToDomain(ProjectEntity entity) {
        if (entity == null) return null;

        Price price = priceFactory.generate(new BigDecimal(String.valueOf(entity.getPrice())), entity.getTypePrice());

        Project project = new Project();
        project.setIdProject(entity.getIdProject());

        project.setTitle(new Title(entity.getTitle()));
        project.setDescription(new Description(entity.getDescription()));
        project.setPrice(price);
        project.setTypePrice(price);

        project.setClosingDate(entity.getClosingDate());
        project.setDateCreated(entity.getDateCreated());
        project.setStateBusiness(entity.getStateBusiness());

        project.setCustomer(userMapper.entityToDomain(entity.getCustomer()));

        if (entity.getDeveloper() != null) {
            project.setDeveloper(userMapper.entityToDomain(entity.getDeveloper()));
        }
        return project;
    }

    public ProjectEntity domainToEntity(Project domain) {
        if (domain == null) return null;

        ProjectEntity entity = new ProjectEntity();
        entity.setIdProject(domain.getIdProject());

        entity.setTitle(domain.getTitle() != null ? domain.getTitle().getTitle() : null);
        entity.setDescription(domain.getDescription() != null ? domain.getDescription().getDescription() : null);

        entity.setPrice(domain.getPrice());
        entity.setTypePrice(domain.getTypePrice());

        entity.setClosingDate(domain.getClosingDate());
        entity.setDateCreated(domain.getDateCreated());
        entity.setStateBusiness(domain.getStateBusiness());

        entity.setCustomer(userMapper.domainToEntity(domain.getCustomer()));

        if (domain.getDeveloper() != null) {
            entity.setDeveloper(userMapper.domainToEntity(domain.getDeveloper()));
        }
        return entity;
    }

    public ProjectResponse domainToResponse(Project project) {
        return new ProjectResponse(
            project.getIdProject().toString(), project.getTitle().getTitle(), project.getDescription().getDescription(), project.getPrice(),
            project.getTypePrice(), project.getClosingDate(), project.getStateBusiness()
        );
    }

}


