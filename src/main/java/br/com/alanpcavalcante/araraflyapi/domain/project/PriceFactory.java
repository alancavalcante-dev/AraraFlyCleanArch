package br.com.alanpcavalcante.araraflyapi.domain.project;

import java.math.BigDecimal;

public class PriceFactory {

    public Price generate(BigDecimal amount, TypePrice typePrice) {
        if (TypePrice.Hour.equals(typePrice)) {
            return new PriceHour(amount, typePrice);
        }

        if (TypePrice.Day.equals(typePrice)) {
            return new PriceDay(amount, typePrice);
        }

        if (TypePrice.Project.equals(typePrice)) {
            return new PriceProject(amount, typePrice);
        }

        throw new IllegalArgumentException("Type price invalid");
    }

}
