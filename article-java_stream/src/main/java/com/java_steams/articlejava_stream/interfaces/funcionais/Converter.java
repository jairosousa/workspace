package com.java_steams.articlejava_stream.interfaces.funcionais;

@FunctionalInterface
public interface Converter<T, U> {
      U convert(T from);
}
