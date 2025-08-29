package br.com.alanpcavalcante.araraflyapi.infrastructure.controllers;


import br.com.alanpcavalcante.araraflyapi.application.usecases.project.*;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.dto.ProjectResponse;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.ProjectMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.security.UserLogged;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    @Operation(summary = "Lista os projetos do cliente", description = "Lista os projetos com base nos status: open, working e finished")
    public ResponseEntity<List<ProjectResponse>> listProjects(@RequestParam StateBusiness state) {
        if (!(state == StateBusiness.OPEN || state == StateBusiness.WORKING || state == StateBusiness.FINISHED)) {
            return ResponseEntity.badRequest().build();
        }
        List<ProjectResponse> list = listProjectCustomer.listStateBusiness(userLogged.load(), state)
                     .stream().map(projectMapper::domainToResponse).toList();

        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }


    @PostMapping
    @Operation(summary = "Cria o projeto do cliente", description = "Após a criação, ficará online para outros dev darem Match")
    public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest projectRequest) throws Exception {
        ProjectResponse projectSaved = projectMapper.domainToResponse(
                createProjectCustomer.create(projectRequest, userLogged.load()));
        return ResponseEntity.ok(projectSaved);
    }


    @PutMapping("{idProject}")
    @Operation(summary = "Altera o projeto do cliente", description = "Consegue atualizar apenas se o projeto não estiver em produção")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable("idProject") String id, @RequestBody @Valid ProjectRequest request) {
        Project projectSaved = updateProjectCustomer.update(UUID.fromString(id), request, userLogged.load());
        ProjectResponse response = projectMapper.domainToResponse(projectSaved);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("{idProject}")
    @Operation(summary = "Deleta o projeto do cliente", description = "Consegue deletar apenas se o projeto não estiver em produção")
    public ResponseEntity<Void> deleteProject(@PathVariable("idProject") String id) {
        deleteProjectCustomer.delete(UUID.fromString(id), userLogged.load());
        return ResponseEntity.noContent().build();
    }


}
