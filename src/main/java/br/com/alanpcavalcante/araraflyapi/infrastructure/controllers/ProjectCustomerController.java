package br.com.alanpcavalcante.araraflyapi.infrastructure.controllers;


import br.com.alanpcavalcante.araraflyapi.application.usecases.project.*;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.ProjectMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.UserMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.model.ProjectEntity;
import br.com.alanpcavalcante.araraflyapi.infrastructure.security.UserLogged;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Projetos - Clientes")
@RestController
@RequestMapping("api/customer/projects")
@RequiredArgsConstructor
public class ProjectCustomerController {

    private final UserLogged userLogged;
    private final ProjectMapper projectMapper;

    private final ListProjectCustomer listProjectCustomer;
    private final CreateProjectCustomer createProjectCustomer;
    private final UpdateProjectCustomer updateProjectCustomer;
    private final DeleteProjectCustomer deleteProjectCustomer;


    @GetMapping
    public ResponseEntity<List<ProjectEntity>> listProjects(@RequestParam StateBusiness state) {
        if (!(state == StateBusiness.OPEN || state == StateBusiness.WORKING || state == StateBusiness.FINISHED)) {
            return ResponseEntity.badRequest().build();
        }

        List<ProjectEntity> list = listProjectCustomer.listStateBusiness(userLogged.load(), state)
                     .stream().map(projectMapper::domainToEntity).toList();

        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }


    @PostMapping
    public ResponseEntity<ProjectEntity> createProject(@RequestBody @Valid ProjectDto projectDto) throws Exception {
        ProjectEntity projectSaved = projectMapper.domainToEntity(
                createProjectCustomer.create(projectDto, userLogged.load()));
        return ResponseEntity.ok(projectSaved);
    }


    @PutMapping("{idProject}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable("idProject") String id, @RequestBody @Valid ProjectDto request) {
        Project projectSaved = updateProjectCustomer.update(UUID.fromString(id), request, userLogged.load());
        ProjectDto response = projectMapper.domainToResponse(projectSaved);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("{idProject}")
    public ResponseEntity<Void> deleteProject(@PathVariable("idProject") String id) {
        deleteProjectCustomer.delete(UUID.fromString(id), userLogged.load());
        return ResponseEntity.noContent().build();
    }
}
