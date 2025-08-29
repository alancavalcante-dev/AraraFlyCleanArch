package br.com.alanpcavalcante.araraflyapi.application.usecases.match;

import br.com.alanpcavalcante.araraflyapi.application.gateways.match.MatchRepository;
import br.com.alanpcavalcante.araraflyapi.domain.match.Match;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.List;

public class GetListMatchDeveloper {

    private final MatchRepository matchRepository;

    public GetListMatchDeveloper(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> get(User developer) {
         return matchRepository.findAllMatchByDeveloperAndStateBusiness(developer, StateBusiness.OPEN);
    }
}
