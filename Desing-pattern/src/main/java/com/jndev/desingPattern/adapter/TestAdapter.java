package com.jndev.desingPattern.adapter;

import com.jndev.desingPattern.adapters.*;

/**
 * @Autor Jairo Nascimento
 * @Created 30/10/2022 - 09:11
 */
public class TestAdapter {
    public static void main(String[] args) {
        Converter usdeuro = new USDEUROConverter();
        Converter usdinr = new USDINRConverter();

        // Adaptadores proprios
        CurrencyConverter converter = new CurrencyConverter(usdinr);
        converter.performConversion();

        // Adaptadores de terceiros
        Converter usdbitcoin = new USDBitcoinAdapter();
        CurrencyConverter converterBi = new CurrencyConverter(usdbitcoin);
        converterBi.performConversion();
    }
}
