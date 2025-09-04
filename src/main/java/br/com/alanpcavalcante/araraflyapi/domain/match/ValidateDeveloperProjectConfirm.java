package br.com.alanpcavalcante.araraflyapi.domain.match;

public class ValidateDeveloperProjectConfirm {

    public ValidateDeveloperProjectConfirm() {
    }

    public Match run(Match match) {
        match.getConfirmDeveloper().setConfirm(true);
        return match;
    }
}
