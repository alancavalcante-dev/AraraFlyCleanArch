package br.com.alanpcavalcante.araraflyapi.domain.match;

public class ValidateDeveloperProjectConfirm {

    public ValidateDeveloperProjectConfirm() {
    }

    public Match run(Match match) {
        if (!match.getProject().getCustomer().getIsDeveloper()) {
            throw new IllegalArgumentException("Apenas desenvolvedor pode confirmar este Match");
        }

        if (!(match.getProject().getDeveloper().getId() == match.getDeveloper().getId())) {
            throw new IllegalArgumentException("Apenas o desenvoledor do match pode confirmar o match");
        }
        match.getConfirmDeveloper().setConfirm(true);
        return match;
    }
}
