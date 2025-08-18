package br.com.alanpcavalcante.araraflyapi.application.usecases.pointmarking;

import br.com.alanpcavalcante.araraflyapi.application.gateways.pointmarking.PointMarkingRepository;
import br.com.alanpcavalcante.araraflyapi.application.gateways.project.ProjectRepository;
import br.com.alanpcavalcante.araraflyapi.domain.pointmarking.PointMarking;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.time.LocalDateTime;
import java.util.Optional;


public class CreatePointMarking {

    private final PointMarkingRepository pointMarkingRepository;
    private final ProjectRepository projectRepository;
    private final PointMarking pointMarking;


    public CreatePointMarking(PointMarkingRepository pointMarkingRepository, ProjectRepository projectRepository,  PointMarking pointMarking) {
        this.pointMarkingRepository = pointMarkingRepository;
        this.projectRepository = projectRepository;
        this.pointMarking = pointMarking;
    }

    public PointMarking create(User developer, Project project, PointMarkingRequest dto) {
        Optional<Project> projectOpt = projectRepository.getProjectById(developer, project.getIdProject());
        if (projectOpt.isEmpty()) {
            throw new IllegalArgumentException("Desenvolvedor não está associado ao projeto ou projeto inexistente");
        }

        Project pro = projectOpt.get();

        if (!(pro.getStateBusiness() == StateBusiness.WORKING || pro.getStateBusiness() == StateBusiness.DIDNTSTART)) {
            throw new IllegalArgumentException("O projeto não está em andamento para criar uma marcação de ponto");
        }

        pointMarking.setDateCreated(LocalDateTime.now());
        pointMarking.setDeveloper(developer);
        pointMarking.setProject(project);

        pointMarking.setComment(dto.comment());
        pointMarking.setCommentsGeneratedIA(dto.comment());
        pointMarking.setDateStarting(dto.dateStarting());
        pointMarking.setDateClosing(dto.dateClosing());

        return pointMarkingRepository.save(pointMarking);
    }

}
