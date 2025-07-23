package br.com.alanpcavalcante.araraflyapi.domain.project;

import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Project {

    private UUID idProject;

    private String title;

    private String description;

    private BigDecimal price;

    private TypePrice typePrice;

    private LocalDate closingDate;


    private LocalDateTime dateCreated;

    private StateBusiness stateBusiness;

    private User user;


    public UUID getIdProject() {
        return idProject;
    }

    public void setIdProject(UUID idProject) {
        this.idProject = idProject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StateBusiness getStateBusiness() {
        return stateBusiness;
    }

    public void setStateBusiness(StateBusiness stateBusiness) {
        this.stateBusiness = stateBusiness;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        if (LocalDateTime.now().isAfter(dateCreated)) {
            throw new IllegalArgumentException("Data criação é maios do que a data atual");
        }
        this.dateCreated = dateCreated;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        if (closingDate.isAfter(LocalDate.now().minusDays(3))) {
            throw new IllegalArgumentException("Minimum of 3 days to close the project");
        }
        this.closingDate = closingDate;
    }

    public TypePrice getTypePrice() {
        return typePrice;
    }

    public void setTypePrice(TypePrice typePrice) {
        this.typePrice = typePrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price.compareTo(new BigDecimal("10.00")) < 0) {
            throw new IllegalArgumentException("Invalid field, minimum value of R$ 10.00");
        }
        this.price = price;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        lengthMinChars(description);
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        lengthMinChars(title);
        this.title = title;
    }

    private void lengthMinChars(String field) {
        if (field.length() < 3) {
            throw new IllegalArgumentException("Invalid field, must have at least 3 characters");
        }

        if (field.length() > 100) {
            throw new IllegalArgumentException("Invalid field, cannot exceed 100 characters");
        }
    }
}
