package br.com.alanpcavalcante.araraflyapi.infrastructure.controllers;

import br.com.alanpcavalcante.araraflyapi.application.usecases.project.CustomPage;
import br.com.alanpcavalcante.araraflyapi.application.usecases.project.ListProjectOpenSearch;
import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.dto.ProjectResponse;
import br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.specification.CatalogProjectSpecification;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.ProjectMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.ProjectEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.PageImpl;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Tag(name="Catalógo de Projetos Abertos - Todos usuários")
@RestController
@RequestMapping("api/catalog/projects")
@RequiredArgsConstructor
public class CatalogProjectOpenController {

    private final ProjectMapper projectMapper;
    private final CatalogProjectSpecification catalog;
    private final ListProjectOpenSearch listProjectOpenSearch;

    @GetMapping
    @Operation(summary = "Pega todos projetos estão abertos para desenvolver")
    public ResponseEntity<PageImpl<ProjectResponse>> catalogSearch(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "price", required = false) BigDecimal price,
            @RequestParam(value = "type-price", required = false) BigDecimal typePrice,
            @RequestParam(value = "closing-date", required = false) LocalDate closingDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(value = "sortField", required = false) String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection
    ) {
        Specification<ProjectEntity> spec = catalog.stateBusinessOpen();

        if (title != null && !title.isBlank()) {
            spec = spec.and(catalog.hasTitle(title));
        }
        if (description != null && !description.isBlank()) {
            spec = spec.and(catalog.hasDescription(description));
        }
        if (price != null) {
            spec = spec.and(catalog.gtaOrEqualPriceDay(price));
        }
        if (typePrice != null) {
            spec = spec.and(catalog.gtaOrEqualPriceDay(typePrice));
        }
        if (closingDate != null) {
            spec = spec.and(catalog.gtaOrEqualClosingDate(closingDate));
        }

        PageRequest pageable;
        if (sortField != null && !sortField.isBlank()) {
            Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        } else {
            pageable = PageRequest.of(page, size);
        }

        CustomPage<Project> customPageResult = listProjectOpenSearch.listProjects(spec, pageable);

        List<ProjectResponse> responseList = customPageResult.getContent().stream()
                .map(projectMapper::domainToResponse)
                .toList();

        PageImpl<ProjectResponse> responsePage = new PageImpl<>(
                responseList,
                pageable,
                customPageResult.getTotalElements()
        );

        if (responsePage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(responsePage);

    }



}
