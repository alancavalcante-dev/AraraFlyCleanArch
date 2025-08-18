package br.com.alanpcavalcante.araraflyapi.domain.project;

import java.math.BigDecimal;

public interface Price {

    TypePrice getTypePrice();
    void setTypePrice(TypePrice typePrice);

    BigDecimal getPrice();
    void setPrice(BigDecimal price);

    void validate(BigDecimal price);


}
