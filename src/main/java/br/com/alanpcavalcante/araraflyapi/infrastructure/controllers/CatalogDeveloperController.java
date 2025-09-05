package br.com.alanpcavalcante.araraflyapi.infrastructure.controllers;


import br.com.alanpcavalcante.araraflyapi.application.usecases.portfolio.FindAllDeveloper;
import br.com.alanpcavalcante.araraflyapi.domain.CustomPage;
import br.com.alanpcavalcante.araraflyapi.domain.portfolio.Portfolio;
import br.com.alanpcavalcante.araraflyapi.domain.technology.Technology;
import br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.dto.CatalogDeveloperResponse;
import br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.dto.ProjectResponse;
import br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.specification.CatalogDeveloperSpecification;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.PortfolioMapper;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.PortfolioDeveloperEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(name="Catalógo de Desenvolvedores - Todos usuários")
@RestController
@RequestMapping("api/catalog/developers")
@RequiredArgsConstructor
public class CatalogDeveloperController {

    private final PortfolioMapper portfolioMapper;
    private final FindAllDeveloper findAllDeveloper;

    @GetMapping
    @Operation(summary = "Pega todos desenvolvedores (apenas públicos) com filtro")
    public ResponseEntity<PageImpl<CatalogDeveloperResponse>> catalogSearch(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "technologies", required = false) List<Technology> technologies,
            Pageable pageable
    ) {
        Specification<PortfolioDeveloperEntity> spec = Specification.where(CatalogDeveloperSpecification.isPortfolioPublic());

        if (technologies != null && !technologies.isEmpty()) {
            spec = spec.and(CatalogDeveloperSpecification.hasTechnologies(technologies));
        }
        if (search != null && !search.isBlank()) {
            spec = spec.and(CatalogDeveloperSpecification.searchByText(search));
        }

        CustomPage<Portfolio> portfolioPage = findAllDeveloper.findAll(spec, pageable);

        List<CatalogDeveloperResponse> responseList = portfolioPage.getContent().stream()
                .map(portfolioMapper::domainToCatalogDeveloperResponse)
                .toList();

        PageImpl<CatalogDeveloperResponse> responsePage = new PageImpl<>(
                responseList,
                pageable,
                portfolioPage.getTotalElements()
        );

        if (responsePage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(responsePage);
    }

}


