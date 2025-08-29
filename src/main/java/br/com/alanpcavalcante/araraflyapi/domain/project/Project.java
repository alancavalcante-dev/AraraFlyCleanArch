package br.com.alanpcavalcante.araraflyapi.domain.project;

import br.com.alanpcavalcante.araraflyapi.domain.exceptions.*;
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


    public StateBusiness getStateBusiness() {
        return stateBusiness;
    }

    public void setStateBusiness(StateBusiness state) {
        if (StateBusiness.OPEN == state || StateBusiness.DIDNTSTART == state ||
            StateBusiness.FINISHED == state || StateBusiness.WORKING == state || StateBusiness.CANCELED == state) {
            this.stateBusiness = state;
        }
        throw new StateBusinessInvalid("Invalid state business");
    }


    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        if (dateCreated.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Data de criação não pode ser maior que a data atual");
        }
        this.dateCreated = dateCreated;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        if (closingDate.isBefore(LocalDate.now().minusDays(3))) {
            throw new ClosingDateDaysMin("Minimum of 3 days to close the project");
        }
        this.closingDate = closingDate;
    }

    public TypePrice getTypePrice() {
        return typePrice;
    }

    public void setTypePrice(Price price) {
        TypePrice type = price.getTypePrice();
        if (type == TypePrice.Hour || type == TypePrice.Project || type == TypePrice.Day) {
            this.typePrice = price.getTypePrice();
        }
        throw new TypePriceInvalid("Invalid type price");
    }

    public BigDecimal getPrice() {
        return price.getPrice();
    }

    public void setPrice(Price price) {
        if (price.getPrice().compareTo(new BigDecimal("25.00")) < 0) {
            throw new PriceValueMinInvalid("Invalid field, minimum value of R$ 25.00");
        }
        this.price = price;
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
        if (developer.getIsDeveloper()) {
            this.developer = developer;
        }
        throw new CustomerCannotProgram("Customer cannot program systems");
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        if (customer.getIsDeveloper()) {
            throw new DeveloperCannotHaveProject("Developer cannot have a project");
        }
        this.customer = customer;
    }
}
