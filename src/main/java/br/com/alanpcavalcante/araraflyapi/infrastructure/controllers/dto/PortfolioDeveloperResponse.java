package br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.dto;

public record PortfolioDeveloperResponse(
    String nameDeveloper,
    String emailDeveloper,
    String urlPictureUser,

    String presentation,
    String resume,
    String description,
    String urlPictureBanner
) {
}
