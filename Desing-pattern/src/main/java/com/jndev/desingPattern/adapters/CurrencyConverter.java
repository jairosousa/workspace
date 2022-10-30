package com.jndev.desingPattern.adapters;

/**
 * @Autor Jairo Nascimento
 * @Created 30/10/2022 - 09:07
 */
public class CurrencyConverter {

    private Converter converter;

    public CurrencyConverter(Converter converter) {
        this.converter = converter;
    }

    public void performConversion() {
        converter.converter();
    }
}
