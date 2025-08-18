package br.com.alanpcavalcante.araraflyapi.application.usecases.match;

import br.com.alanpcavalcante.araraflyapi.application.gateways.match.MatchRepository;
import br.com.alanpcavalcante.araraflyapi.domain.match.Confirm;
import br.com.alanpcavalcante.araraflyapi.domain.match.Match;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;

public class CreateMatchDeveloper {

    private final MatchRepository matchRepository;
    private final MatchValidateFacade matchValidateFacade;
    private final Match match;

    public CreateMatchDeveloper(MatchRepository matchRepository, MatchValidateFacade matchValidateFacade, Match match) {
        this.matchRepository = matchRepository;
        this.matchValidateFacade = matchValidateFacade;
        this.match = match;
    }

    public Match createMatch(User user, Project project) {
        List<Match> matches = matchRepository.findAllMatchByUserAndStateBusiness(user, StateBusiness.OPEN);

        match.setDeveloper(user);
        match.setDateCreated(LocalDateTime.now().toLocalDate());
        match.setProject(project);
        match.setConfirmDeveloper(new Confirm(false));
        match.setConfirmCustomer(new Confirm(false));

        matchValidateFacade.match(match, matches);

        return matchRepository.save(match);
    }
}
