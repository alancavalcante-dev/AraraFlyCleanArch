package br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.specification;


import br.com.alanpcavalcante.araraflyapi.domain.technology.Technology;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.PortfolioDeveloperEntity;
import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.TechnologyEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class CatalogDeveloperSpecification {

    public static Specification<PortfolioDeveloperEntity> isPortfolioPublic() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isPortfolioPublic"), true);
    }

    /**
     * Filtra portfólios que tenham PELO MENOS UMA das tecnologias da lista.
     */
    public static Specification<PortfolioDeveloperEntity> hasTechnologies(List<Technology> technologies) {
        return (root, query, criteriaBuilder) -> {
            // Navegação simplificada: Portfolio -> Set<TechnologyEntity>
            Join<PortfolioDeveloperEntity, TechnologyEntity> technologyJoin = root.join("technologies");

            Predicate predicate = technologyJoin.get("name").in(technologies);
            query.distinct(true);
            return predicate;
        };
    }

    public static Specification<PortfolioDeveloperEntity> searchByText(String text) {
        return (root, query, criteriaBuilder) -> {
            String likePattern = "%" + text.toLowerCase() + "%";
            Predicate presentationMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("presentation")), likePattern);
            Predicate resumeMatch = criteriaBuilder.like(criteriaBuilder.lower(root.get("resume")), likePattern);
            return criteriaBuilder.or(presentationMatch, resumeMatch);
        };
    }
}