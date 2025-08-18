package br.com.alanpcavalcante.araraflyapi.infrastructure.mappers;

import br.com.alanpcavalcante.araraflyapi.domain.pointmarking.PointMarking;
import br.com.alanpcavalcante.araraflyapi.infrastructure.model.PointMarkingEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class PointMarkingMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProjectMapper projectMapper;

    public PointMarking entityToDomain(PointMarkingEntity entity) {
        PointMarking domain = new PointMarking();
        domain.setIdPointMarking(entity.getIdPointMarking());
        domain.setProject(projectMapper.entityToDomain(entity.getProject()));
        domain.setComment(entity.getComment());
        domain.setCommentsGeneratedIA(entity.getCommentsGeneratedIA());
        domain.setDateStarting(entity.getDateStarting());
        domain.setDateClosing(entity.getDateClosing());
        domain.setDateCreated(entity.getDateCreated());
        domain.setDeveloper(userMapper.entityToDomain(entity.getDeveloper()));
        return domain;
    }

    public PointMarkingEntity domainToEntity(PointMarking domain) {
        PointMarkingEntity entity = new PointMarkingEntity();
        entity.setIdPointMarking(domain.getIdPointMarking());
        entity.setProject(projectMapper.domainToEntity(domain.getProject()));
        entity.setComment(domain.getComment());
        entity.setCommentsGeneratedIA(domain.getCommentsGeneratedIA());
        entity.setDateStarting(domain.getDateStarting());
        entity.setDateClosing(domain.getDateClosing());
        entity.setDateCreated(domain.getDateCreated());
        entity.setDeveloper(userMapper.domainToEntity(domain.getDeveloper()));
        return entity;
    }

}
