package br.com.alanpcavalcante.araraflyapi.application.usecases.match;

import br.com.alanpcavalcante.araraflyapi.domain.match.*;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;


import java.util.List;

public class MatchValidateFacade {

    private final ValidateCustomerVotedOnOwnMatch validateCustomerProjectMatch;
    private final ValidateDeveloperProjectMatch validateDeveloperProjectMatch;
    private final ValidateCustomerProjectConfirm validateCustomerProjectConfirm;
    private final ValidateDeveloperProjectConfirm validateDeveloperProjectConfirm;


    public MatchValidateFacade(
            ValidateCustomerVotedOnOwnMatch validateCustomerProjectMatch,
            ValidateCustomerProjectConfirm validateCustomerProjectConfirm,
            ValidateDeveloperProjectMatch validateDeveloperProjectMatch,
            ValidateDeveloperProjectConfirm validateDeveloperProjectConfirm
    )
    {
        this.validateCustomerProjectMatch = validateCustomerProjectMatch;
        this.validateCustomerProjectConfirm = validateCustomerProjectConfirm;
        this.validateDeveloperProjectMatch = validateDeveloperProjectMatch;
        this.validateDeveloperProjectConfirm = validateDeveloperProjectConfirm;
    }

    public void match(Match match, List<Match> matches) {
        User developer = match.getDeveloper();
        Project project = match.getProject();
        validateCustomerProjectMatch.run(developer, project);
        validateDeveloperProjectMatch.run(match, matches);
    }

    public Match confirm(Match match, boolean isDeveloper) {
        if (isDeveloper) {
            return validateDeveloperProjectConfirm.run(match);
        } else return validateCustomerProjectConfirm.run(match);

    }

}
