package br.com.alanpcavalcante.araraflyapi.infrastructure.controllers;


import br.com.alanpcavalcante.araraflyapi.application.usecases.portfolio.GetPortfolioPublicById;
import br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.dto.PortfolioDeveloperResponse;
import br.com.alanpcavalcante.araraflyapi.infrastructure.mappers.PortfolioMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;



@Tag(name="Portfólio do Desenvolvedor - Autenticados")
@RestController
@RequestMapping("api/catalog")
@RequiredArgsConstructor
public class CatalogPortfolioDeveloperController {

    private final GetPortfolioPublicById getPortfolioPublicById;
    private final PortfolioMapper portfolioMapper;

    @GetMapping("developers/{idPortfolio}")
    public ResponseEntity<PortfolioDeveloperResponse> portfolioByIdPublic(@PathVariable("idPortfolio") String id) {
        PortfolioDeveloperResponse response = portfolioMapper.domainToResponse(getPortfolioPublicById.get(UUID.fromString(id)));
        return ResponseEntity.ok(response);
    }
}