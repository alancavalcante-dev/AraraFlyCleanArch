package br.com.alanpcavalcante.araraflyapi.domain.match;

public class ValidateCustomerProjectConfirm {

    public ValidateCustomerProjectConfirm() {
    }

    public Match run(Match match) {
        if (match.getProject().getUser().getIsDeveloper()) {
            throw new IllegalArgumentException("Apenas o cliente pode este confirmar Match");
        }

        if (match.getProject().getUser().getId() == match.getDeveloper().getId()) {
            throw new IllegalArgumentException("Apenas o cliente do match pode confirmar o match");
        }
        match.getConfirmCustomer().setConfirm(true);
        return match;
    }
}
