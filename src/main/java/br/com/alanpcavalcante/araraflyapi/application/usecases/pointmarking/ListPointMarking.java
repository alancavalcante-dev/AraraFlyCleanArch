package br.com.alanpcavalcante.araraflyapi.application.usecases.pointmarking;

import br.com.alanpcavalcante.araraflyapi.application.gateways.pointmarking.PointMarkingRepository;
import br.com.alanpcavalcante.araraflyapi.domain.pointmarking.PointMarking;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.util.List;

public class ListPointMarking {

    private final PointMarkingRepository pointMarkingRepository;

    public ListPointMarking(PointMarkingRepository pointMarkingRepository) {
        this.pointMarkingRepository = pointMarkingRepository;
    }

    public List<PointMarking> list(User user, Project project) {
        if (!(project.getStateBusiness() == StateBusiness.DIDNTSTART || project.getStateBusiness() == StateBusiness.WORKING)) {
            throw new IllegalArgumentException();
        }

        return pointMarkingRepository.getPointMarkingByUserAndProject(user, project);

    }
}
