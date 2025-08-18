package br.com.alanpcavalcante.araraflyapi.application.usecases.pointmarking;

import br.com.alanpcavalcante.araraflyapi.application.gateways.neuralnetwork.GeneratorIA;
import br.com.alanpcavalcante.araraflyapi.application.gateways.pointmarking.PointMarkingRepository;
import br.com.alanpcavalcante.araraflyapi.application.usecases.commit.LastCommit;
import br.com.alanpcavalcante.araraflyapi.domain.pointmarking.PointMarking;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

public class GenerateCommentIA {

    private final PointMarkingRepository pointMarkingRepository;
    private final GeneratorIA generatorIA;
    private final LastCommit lastCommit;

    public GenerateCommentIA(PointMarkingRepository pointMarkingRepository, GeneratorIA generatorIA, LastCommit lastCommit) {
        this.pointMarkingRepository = pointMarkingRepository;
        this.generatorIA = generatorIA;
        this.lastCommit = lastCommit;
    }

    public void generate(User user, PointMarking pointMarking) {
        PointMarking point = pointMarkingRepository.getPointMarkingByUserAndIdPointMarking(user, pointMarking.getIdPointMarking())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não pertence ou marcação de ponto inexistente"));

        if (!point.getCommentsGeneratedIA().isEmpty()) {
            throw new IllegalArgumentException("Comentário da IA já estava gerado!");
        }

        Object commit = lastCommit.commit(user, pointMarking.getProject());
        String generatedComment = String.valueOf(generatorIA.generatedCommentIA(
                commit.toString(),
                pointMarking.getComment(),
                pointMarking.getDateStarting(),
                pointMarking.getDateClosing()
        ));
        point.setCommentsGeneratedIA(generatedComment);
        pointMarkingRepository.save(point);
    }
}
