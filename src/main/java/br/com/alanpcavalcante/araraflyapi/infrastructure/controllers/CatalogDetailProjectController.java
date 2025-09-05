package br.com.alanpcavalcante.araraflyapi.infrastructure.controllers;


import br.com.alanpcavalcante.araraflyapi.application.usecases.portfolio.GetPortfolioPublicById;
import br.com.alanpcavalcante.araraflyapi.application.usecases.project.GetProjectById;
import br.com.alanpcavalcante.araraflyapi.application.usecases.project.ListProjectOpenSearch;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.dto.PortfolioDeveloperResponse;
import br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.dto.ProjectResponse;
import br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.specification.CatalogProjectSpecification;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.PortfolioMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.ProjectMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@Tag(name="Detalhes do Projeto - Todos usuários")
@RestController
@RequestMapping("api/catalog")
@RequiredArgsConstructor
public class CatalogDetailProjectController {

    private final ProjectMapper projectMapper;
    private final GetProjectById getProjectById;

    @GetMapping("projects/{idProject}")
    public ResponseEntity<ProjectResponse> projectByIdOpen(@PathVariable("idProject") String id) {
        Project project = getProjectById.get(UUID.fromString(id));
        return ResponseEntity.ok(projectMapper.domainToResponse(project));
    }
}
