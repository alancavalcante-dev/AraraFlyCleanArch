package br.com.alanpcavalcante.araraflyapi.application.usecases.project;

import java.util.List;

public record ListProjectCustomerDto(
        List<ProjectRequest> opens,
        List<ProjectRequest> finishes
) {
    
}
