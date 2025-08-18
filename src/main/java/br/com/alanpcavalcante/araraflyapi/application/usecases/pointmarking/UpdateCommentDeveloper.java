package br.com.alanpcavalcante.araraflyapi.application.usecases.pointmarking;

import br.com.alanpcavalcante.araraflyapi.application.gateways.pointmarking.PointMarkingRepository;
import br.com.alanpcavalcante.araraflyapi.domain.pointmarking.PointMarking;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.UUID;

public class UpdateCommentDeveloper {

    private final PointMarkingRepository pointMarkingRepository;

    public UpdateCommentDeveloper(PointMarkingRepository pointMarkingRepository) {
        this.pointMarkingRepository = pointMarkingRepository;
    }

    public PointMarking updateComment(User developer, UUID pointMarking, String comment) {
        if (!developer.getIsDeveloper()) {
            throw new IllegalArgumentException("Você não é o usuário");
        }

        PointMarking point = pointMarkingRepository.getPointMarkingByUserAndIdPointMarking(developer, pointMarking)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não pertence ou marcação de ponto inexistente"));

        point.setComment(comment);
        return pointMarkingRepository.save(point);
    }

}
