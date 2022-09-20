package com.java_steams.articlejava_stream.interfaces.funcional;

public class Tester {
      public static void main(String[] args) {
            /**
             * Antes do Java 8
             */
            Converter<String, Integer> converter = new Converter<String, Integer>() {

                  @Override
                  public Integer convert(String from) {
                        return Integer.valueOf(from);
                  }

            };
            System.out.println(converter.convert("10"));

            /**
             * expressão lambda
             */
            Converter<String, Integer> fnConvert = (String from) -> Integer.valueOf(from);
            System.out.println(fnConvert.convert("100"));

            /**
             * referência de método
             */
            Converter<String, Integer> rfConverter = Integer::valueOf;
            System.out.println(rfConverter.convert("1000"));

            
      }
}
