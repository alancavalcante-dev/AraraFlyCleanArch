package br.com.alanpcavalcante.araraflyapi.domain.match;

import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;



public class ValidateCustomerVotedOnOwnMatch {

    public ValidateCustomerVotedOnOwnMatch() {}

    public void run(User user, Project project) {
        if (!user.getIsDeveloper()) {
            throw new IllegalArgumentException("So pode dar match quem for desenvolvedor");
        }

        if (user.getLogin() == project.getCustomer().getLogin()) {
            throw new IllegalArgumentException("O cliente não pode dar match no proprio projeto");
        }

    }

}
