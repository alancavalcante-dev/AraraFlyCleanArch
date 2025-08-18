package br.com.alanpcavalcante.araraflyapi.application.usecases.project;

import java.util.List;

public record ListProjectCustomerDto(
        List<ProjectDto> opens,
        List<ProjectDto> finishes
) {
    
}
