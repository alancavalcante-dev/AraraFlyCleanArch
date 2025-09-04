package br.com.alanpcavalcante.araraflyapi.domain.match;

public class ValidateCustomerProjectConfirm {

    public ValidateCustomerProjectConfirm() {
    }

    public Match run(Match match) {
        match.getConfirmCustomer().setConfirm(true);
        return match;
    }
}
