package br.com.alanpcavalcante.araraflyapi.domain.project;

import java.math.BigDecimal;

public class PriceProject extends PriceImpl {

    public PriceProject(BigDecimal price, TypePrice typePrice) {
        super(price, typePrice);
    }
}
