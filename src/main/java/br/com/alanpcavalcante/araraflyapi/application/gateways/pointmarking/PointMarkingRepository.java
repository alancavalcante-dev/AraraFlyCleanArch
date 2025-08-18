package br.com.alanpcavalcante.araraflyapi.application.gateways.pointmarking;

import br.com.alanpcavalcante.araraflyapi.domain.pointmarking.PointMarking;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PointMarkingRepository {

    Optional<PointMarking> getPointMarkingByUserAndIdPointMarking(User user, UUID idPointMarking);

    List<PointMarking> getPointMarkingByUserAndProject(User user, Project project);

    PointMarking save(PointMarking pointMarking);

}
