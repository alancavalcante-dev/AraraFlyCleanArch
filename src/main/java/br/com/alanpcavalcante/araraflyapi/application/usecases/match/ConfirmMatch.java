package br.com.alanpcavalcante.araraflyapi.application.usecases.match;

import br.com.alanpcavalcante.araraflyapi.application.gateways.match.MatchRepository;
import br.com.alanpcavalcante.araraflyapi.application.usecases.project.UpdateProjectToContainerProduction;
import br.com.alanpcavalcante.araraflyapi.domain.match.Match;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.UUID;


public class ConfirmMatch {

    private final MatchValidateFacade matchValidateFacade;
    private final MatchRepository matchRepository;
    private final UpdateProjectToContainerProduction updateProjectToContainerProduction;


    public ConfirmMatch(MatchValidateFacade matchValidateFacade, MatchRepository matchRepository, UpdateProjectToContainerProduction update) {
        this.matchValidateFacade = matchValidateFacade;
        this.matchRepository = matchRepository;
        this.updateProjectToContainerProduction = update;
    }

    public Match confirmMatch(User user, UUID idMatch) {
        Match match = matchRepository.findMatchById(idMatch).orElseThrow(() -> new RuntimeException("Match not found"));

        if (user.getId() != match.getProject().getCustomer().getId() && user.getId() != match.getDeveloper().getId()) {
            throw new RuntimeException("denied!");
        }

        if (match.getProject().getStateBusiness() != StateBusiness.OPEN) {
            throw new RuntimeException("project already start");
        }

        Match matchUpdate = matchValidateFacade.confirm(match, user);

        if (matchUpdate.getConfirmCustomer().getConfirm() && matchUpdate.getConfirmDeveloper().getConfirm()) {
            updateProjectToContainerProduction.updateProject(match.getProject(), match.getDeveloper());
        }

        return matchRepository.save(matchUpdate);
    }
}
