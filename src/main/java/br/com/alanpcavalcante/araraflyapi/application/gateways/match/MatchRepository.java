package br.com.alanpcavalcante.araraflyapi.application.gateways.match;

import br.com.alanpcavalcante.araraflyapi.domain.match.Match;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface MatchRepository {

    Optional<Match> findMatchById(Long idMatch);
    List<Match> findAllMatchByUserAndStateBusiness(User user, StateBusiness stateBusiness);
    Match save(Match match);

}
