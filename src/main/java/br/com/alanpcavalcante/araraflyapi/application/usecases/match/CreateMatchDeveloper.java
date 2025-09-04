package br.com.alanpcavalcante.araraflyapi.application.usecases.match;

import br.com.alanpcavalcante.araraflyapi.application.gateways.match.MatchRepository;
import br.com.alanpcavalcante.araraflyapi.application.usecases.project.GetProjectById;
import br.com.alanpcavalcante.araraflyapi.application.usecases.project.GetProjectDeveloper;
import br.com.alanpcavalcante.araraflyapi.domain.match.Confirm;
import br.com.alanpcavalcante.araraflyapi.domain.match.Match;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class CreateMatchDeveloper {

    private final MatchRepository matchRepository;
    private final MatchValidateFacade matchValidateFacade;
    private final GetProjectById getProjectById;

    public CreateMatchDeveloper(MatchRepository matchRepository, MatchValidateFacade matchValidateFacade, GetProjectById getProjectById) {
        this.matchRepository = matchRepository;
        this.matchValidateFacade = matchValidateFacade;
        this.getProjectById = getProjectById;
    }

    public Match createMatch(User developer, UUID idProject) {
        Project project = getProjectById.get(idProject);

        List<Match> matches = matchRepository.findAllMatchByDeveloperAndStateBusiness(developer, StateBusiness.OPEN);
        Match match = new Match();

        match.setDeveloper(developer);
        match.setDateCreated(LocalDateTime.now().toLocalDate());
        match.setProject(project);
        match.setConfirmDeveloper(new Confirm(false));
        match.setConfirmCustomer(new Confirm(false));

        matchValidateFacade.insertMatch(match, matches);

        return matchRepository.save(match);
    }
}
