package br.com.alanpcavalcante.araraflyapi.domain.match;

import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;



public class ValidateCustomerVotedOnOwnMatch {

    public ValidateCustomerVotedOnOwnMatch() {}

    public void run(User developer, Project project) {
        if (!developer.getIsDeveloper()) {
            throw new IllegalArgumentException("So pode dar match quem for desenvolvedor");
        }

        if (developer.getLogin() == project.getCustomer().getLogin()) {
            throw new IllegalArgumentException("O cliente não pode dar match no proprio projeto");
        }

    }

}
