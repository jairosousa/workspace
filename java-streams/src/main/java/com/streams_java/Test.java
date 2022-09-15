package com.streams_java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Autor Jairo Nascimento
 * @Created 15/09/2022 - 11:52
 */
public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        Stream<String> stream1 = Stream.of("test1");
        Stream<String> stream2 = Stream.of("test1", "test2", "test3", "test1");

        // filtrar apenas elementos distintos do stream2 para a saída
        stream2.distinct
                ()
                .forEach(System.out::println);

        // A Interface de Streams também fornece a criação de fluxos a partir do padrão Builder.
        Stream<Integer> build1 = Stream.<Integer>builder()
                .add(2)
                .add(1)
                .add(3)
                .add(5)
                .build();

        // Criando Streams de Arrays
        // Array é a estrutura de dados mais comum em qualquer linguagem de programação. Vamos converter a fonte de dados Arrays em fluxos Java.

        String[] domains = {"abc.com", "pqr.net", "tuf.org", "abc.com"};
        Stream<String> domainsStream = Stream.of(domains);

        int[] ints = {1, 2, 3};
        IntStream stream = Arrays.stream(ints);
        Arrays.stream(ints).forEach(System.out::println);

        // Using generate(Supplier<? extends T> s):

        Stream
                .generate(UUID::randomUUID)
                .limit(10)
                .forEach(System.out::println);

        // Using Iterate(T Seed, UnaryOperator<T> f)

        Stream
                .iterate(10, n -> n + 1)
                .limit(10)
                .forEach(System.out::println);

        //Creating From Collection
        Set<Integer> set = new HashSet<>(List.of(3, 2, 1));
        System.out.println(set.
                stream().
                sorted().
                reduce(Integer::max));

        // Creating Empty Streams
        Stream<String> empty = Stream.empty();

        // NIO Files.lines()
        try (Stream<String> lines = Files.lines(Path.of(""))) {
            System.out.println(lines);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        // bufferedreader
        BufferedReader bufferedReader = new BufferedReader(new FileReader(""));
        try (Stream<String> lines = bufferedReader.lines()) {
            lines.forEach(System.out::println);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
