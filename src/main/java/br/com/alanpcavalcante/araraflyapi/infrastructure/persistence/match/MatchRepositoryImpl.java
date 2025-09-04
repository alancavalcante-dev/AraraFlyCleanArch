package br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.match;

import br.com.alanpcavalcante.araraflyapi.application.gateways.match.MatchRepository;
import br.com.alanpcavalcante.araraflyapi.domain.match.Match;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.MatchMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.UserMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.MatchEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class MatchRepositoryImpl implements MatchRepository {

    private final MatchRepositoryJpa matchRepositoryJpa;
    private final MatchMapper matchMapper;
    private final UserMapper userMapper;

    @Override
    public Optional<Match> findMatchById(UUID idMatch) {
        Optional<MatchEntity> match = matchRepositoryJpa.findById(idMatch);
        return match.map(matchMapper::entityToDomain);
    }

    @Override
    public Optional<Match> findMatchByCustomerOrDeveloper(UUID uuid) {
        return matchRepositoryJpa.getMatchByCustomerOrDeveloper(uuid).map(matchMapper::entityToDomain);
    }

    @Override
    public List<Match> findAllMatchByDeveloperAndStateBusiness(User developer, StateBusiness stateBusiness) {
        return matchRepositoryJpa.getAllMatchByDeveloperAndStateBusiness(
                developer.getId(),
                stateBusiness
        ).stream().map(matchMapper::entityToDomain).toList();
    }

    @Override
    public List<Match> findAllMatchByCustomerAndStateBusiness(User customer, StateBusiness stateBusiness) {
        return matchRepositoryJpa.getAllMatchByCustomerAndStateBusiness(
                customer.getId(),
                stateBusiness
        ).stream().map(matchMapper::entityToDomain).toList();
    }

    @Override
    public Match save(Match match) {
        MatchEntity saved = matchRepositoryJpa.save(matchMapper.domainToEntity(match));
        return matchMapper.entityToDomain(saved);
    }

    @Override
    public void delete(Match match) {
        matchRepositoryJpa.delete(matchMapper.domainToEntity(match));
    }
}
