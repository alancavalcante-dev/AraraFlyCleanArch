package br.com.alanpcavalcante.araraflyapi.application.gateways.match;

import br.com.alanpcavalcante.araraflyapi.domain.match.Match;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface MatchRepository {

    Optional<Match> findMatchById(UUID idMatch);
    List<Match> findAllMatchByDeveloperAndStateBusiness(User developer, StateBusiness stateBusiness);
    List<Match> findAllMatchByCustomerAndStateBusiness(User customer, StateBusiness stateBusiness);
    Match save(Match match);
    void delete(Match match);

}
