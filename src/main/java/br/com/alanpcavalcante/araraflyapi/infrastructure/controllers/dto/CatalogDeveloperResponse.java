package br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.dto;

import br.com.alanpcavalcante.araraflyapi.domain.technology.Technology;

import java.util.List;
import java.util.UUID;

public record CatalogDeveloperResponse(
        UUID portfolioId,
        String name,
        String urlPictureUser,
        List<Technology> technologies
) { }