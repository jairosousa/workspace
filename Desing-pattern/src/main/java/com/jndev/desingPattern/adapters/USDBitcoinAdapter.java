package com.jndev.desingPattern.adapters;

/**
 * @Autor Jairo Nascimento
 * @Created 30/10/2022 - 09:28
 */
public class USDBitcoinAdapter implements Converter {

    private USDBitcoinConverter converter;

    public USDBitcoinAdapter() {
        converter = new USDBitcoinConverter();
    }

    @Override
    public void converter() {
        System.out.println("Convertendo para Bitcoin: " + converter.processConversion());
    }
}
