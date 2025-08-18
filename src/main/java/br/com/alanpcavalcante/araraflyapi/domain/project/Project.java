package br.com.alanpcavalcante.araraflyapi.domain.project;

import br.com.alanpcavalcante.araraflyapi.domain.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Project {

    private UUID idProject;

    private Title title;

    private Description description;

    private Price price;

    private TypePrice typePrice;

    private LocalDate closingDate;

    private LocalDateTime dateCreated;

    private StateBusiness stateBusiness;

    private User customer;

    private User developer;


    public UUID getIdProject() {
        return idProject;
    }

    public void setIdProject(UUID idProject) {
        this.idProject = idProject;
    }

    public User getUser() {
        return customer;
    }

    public void setUser(User customer) {
        if (customer.getIsDeveloper()) {
            throw new IllegalArgumentException("Apenas clientes podem ter projeto!");
        }
        this.customer = customer;
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

    public void setTypePrice(Price price) {
        this.typePrice = price.getTypePrice();
    }

    public BigDecimal getPrice() {
        return price.getPrice();
    }

    public void setPrice(Price price) {

        if (price.getPrice().compareTo(new BigDecimal("25.00")) < 0) {
            throw new IllegalArgumentException("Invalid field, minimum value of R$ 25.00");
        }

        if (!this.getStateBusiness().equals(StateBusiness.OPEN)) {
            throw new IllegalArgumentException("Cannot change the value while the project is in production");
        }

        this.price.setPrice(price.getPrice());
    }


    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public User getDeveloper() {
        return developer;
    }

    public void setDeveloper(User developer) {
        this.developer = developer;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }
}
