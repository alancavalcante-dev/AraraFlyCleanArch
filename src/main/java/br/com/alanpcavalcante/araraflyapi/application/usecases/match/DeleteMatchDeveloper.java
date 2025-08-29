package br.com.alanpcavalcante.araraflyapi.application.usecases.match;

import br.com.alanpcavalcante.araraflyapi.application.gateways.match.MatchRepository;
import br.com.alanpcavalcante.araraflyapi.domain.match.Match;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.UUID;


public class DeleteMatchDeveloper {

    private final MatchRepository matchRepository;

    public DeleteMatchDeveloper(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void delete(User developer, UUID idMatch) {
        Match match = matchRepository.findMatchById(idMatch).orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
        if (match.getDeveloper().getId() != developer.getId()) {
            throw new RuntimeException("Programor não pertencente");
        }

        if (match.getProject().getStateBusiness() != StateBusiness.OPEN) {
            throw new RuntimeException("Não pode deletar projeto que já está em produção");
        }
        matchRepository.delete(match);
    }
}
