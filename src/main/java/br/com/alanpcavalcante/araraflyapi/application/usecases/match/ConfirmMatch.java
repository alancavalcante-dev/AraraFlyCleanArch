package br.com.alanpcavalcante.araraflyapi.application.usecases.match;

import br.com.alanpcavalcante.araraflyapi.application.gateways.match.MatchRepository;
import br.com.alanpcavalcante.araraflyapi.application.usecases.project.UpdateProjectToContainerProduction;
import br.com.alanpcavalcante.araraflyapi.domain.match.Match;
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
        Match match = matchRepository.findMatchById(idMatch).orElseThrow(() -> new RuntimeException("Match não encontrado"));

        Match matchUpdate = matchValidateFacade.confirm(match, user.getIsDeveloper());

        if (matchUpdate.getConfirmCustomer().getConfirm() && matchUpdate.getConfirmDeveloper().getConfirm()) {
            updateProjectToContainerProduction.updateProject(match.getProject(), matchUpdate.getDeveloper());
        }
        return matchRepository.save(matchUpdate);
    }
}
