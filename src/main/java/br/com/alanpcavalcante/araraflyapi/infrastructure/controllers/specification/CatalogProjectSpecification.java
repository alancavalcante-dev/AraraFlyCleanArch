package br.com.alanpcavalcante.araraflyapi.infrastructure.controllers.specification;

import br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model.ProjectEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CatalogProjectSpecification {


    public Specification<ProjectEntity> stateBusinessOpen() {
        return (root, query, cb) -> cb.equal(root.get("stateBusiness"), "OPEN");
    }


    public Specification<ProjectEntity> hasTitle(String title){
        return (root, query, cb) -> {
            if (title == null || title.isBlank()) return null;
            return cb.like(root.get("title"), "%" + title + "%");
        };
    }

    public Specification<ProjectEntity> hasDescription(String description) {
        return (root, query, cb) -> {
            if (description == null || description.isBlank()) return null;
            return cb.like(root.get("description"), "%" + description + "%");
        };
    }

    public Specification<ProjectEntity> gtaOrEqualPriceDay(BigDecimal price) {
        return (root, query, cb) -> {
            if (price == null) return null;
            return cb.greaterThanOrEqualTo(root.get("price"), price);
        };
    }


    public Specification<ProjectEntity> gtaOrEqualClosingDate(LocalDate closingDate) {
        return (root, query, cb) -> {
            if (closingDate == null) return null;
            return cb.greaterThanOrEqualTo(root.get("closingDate"), closingDate);
        };
    }

}
