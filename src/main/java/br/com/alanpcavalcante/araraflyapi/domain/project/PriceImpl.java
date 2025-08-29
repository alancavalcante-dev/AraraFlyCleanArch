package br.com.alanpcavalcante.araraflyapi.domain.project;

import br.com.alanpcavalcante.araraflyapi.domain.exceptions.PriceValueMinInvalid;

import java.math.BigDecimal;

public class PriceImpl implements Price {

    private BigDecimal price;
    private TypePrice typePrice;

    public PriceImpl(BigDecimal price, TypePrice typePrice) {
        validate(price);
        this.price = price;
        this.typePrice = typePrice;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        validate(price);
        this.price = price;
    }

    @Override
    public TypePrice getTypePrice() {
        return this.typePrice;
    }

    @Override
    public void setTypePrice(TypePrice typePrice) {
        this.typePrice = typePrice;
    }

    @Override
    public void validate(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.valueOf(25.00)) < 0) {
            throw new PriceValueMinInvalid("Price must be greater than 25.00");
        }
    }

}
