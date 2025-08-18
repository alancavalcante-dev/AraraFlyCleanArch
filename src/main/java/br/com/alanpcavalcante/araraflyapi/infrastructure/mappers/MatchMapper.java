package br.com.alanpcavalcante.araraflyapi.infrastructure.mappers;

import br.com.alanpcavalcante.araraflyapi.domain.match.Confirm;
import br.com.alanpcavalcante.araraflyapi.domain.match.Match;
import br.com.alanpcavalcante.araraflyapi.infrastructure.model.MatchEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatchMapper {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private UserMapper userMapper;

    public Match entityToDomain(MatchEntity entity) {
        Match match = new Match();
        match.setIdMatch(entity.getIdMatch());
        match.setProject(projectMapper.entityToDomain(entity.getProject()));
        match.setDeveloper(userMapper.entityToDomain(entity.getDeveloper()));
        match.setConfirmDeveloper(new Confirm(entity.getConfirmDeveloper()));
        match.setConfirmCustomer(new Confirm(entity.getConfirmClient()));
        match.setDateCreated(entity.getDateMatch());
        return match;
    }

    public MatchEntity domainToEntity(Match match) {
        MatchEntity entity = new MatchEntity();
        entity.setIdMatch(match.getIdMatch());
        entity.setProject(projectMapper.domainToEntity(match.getProject()));
        entity.setDeveloper(userMapper.domainToEntity(match.getDeveloper()));
        entity.setConfirmDeveloper(match.getConfirmDeveloper().getConfirm());
        entity.setConfirmClient(match.getConfirmCustomer().getConfirm());
        entity.setDateMatch(match.getDateCreated());
        return entity;
    }

}
