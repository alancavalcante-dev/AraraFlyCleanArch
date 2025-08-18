package br.com.alanpcavalcante.araraflyapi.domain.match;

import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;

import java.util.List;

public class ValidateDeveloperProjectMatch {

    public ValidateDeveloperProjectMatch() {}

    public void run(Match match, List<Match> matches) {

        if (!(match.getProject().getStateBusiness() == StateBusiness.OPEN)) {
            throw new IllegalArgumentException("Nao pode dar match em projetos que nao estao abertos para negociacao");
        }


        int cont = 0;
        for (Match matchList: matches) {
            if (matchList.getConfirmDeveloper().getConfirm()) {
                if (cont == 3) {
                    throw new IllegalArgumentException("Não pode dar match em mais de 3 projetos");
                }

                if (matchList.getProject().getIdProject() == match.getProject().getIdProject()) {
                    throw new IllegalArgumentException("Não pode dar match no mesmo projeto");
                }

                cont++;
            }
        }

    }

}
