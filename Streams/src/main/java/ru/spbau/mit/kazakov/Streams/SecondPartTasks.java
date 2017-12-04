package ru.spbau.mit.kazakov.Streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SecondPartTasks {

    private SecondPartTasks() {
    }

    /**
     * Найти строки из переданных файлов, в которых встречается указанная подстрока.
     */
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) throws java.io.IOException {
        return paths.stream()
                .map(Paths::get)
                .flatMap(SecondPartTasks::safeLines)
                .filter(s -> s.contains(sequence))
                .collect(Collectors.toList());
    }

    /**
     * Converts specified path to stream of String. Throws unchecked RuntimeException, if couldn't read from file.
     *
     * @param path specified path
     * @return stream of Strings
     */
    private static Stream<String> safeLines(Path path) {
        try {
            return Files.lines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * В квадрат с длиной стороны 1 вписана мишень.
     * Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
     * Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
     */
    public static double piDividedBy4() {
        return Stream.generate(new Supplier<Double>() {
            Random random = new Random();

            @Override
            public Double get() {
                return Math.pow(random.nextDouble() - 0.5, 2) + Math.pow(random.nextDouble() - 0.5, 2);
            }
        })
                .limit(1000000)
                .filter(d -> d <= 0.25)
                .count() / 1000000.0;
    }

    /**
     * Дано отображение из имени автора в список с содержанием его произведений.
     * Надо вычислить, чья общая длина произведений наибольшая.
     */
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions.entrySet().stream()
                .max(Comparator
                        .comparingInt(e -> e.getValue().stream()
                                .mapToInt(String::length)
                                .sum()))
                .map(Map.Entry::getKey).orElse("");
    }

    /**
     * Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
     * Необходимо вычислить, какой товар и в каком количестве надо поставить.
     */
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders.stream()
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.summingInt(Map.Entry::getValue)
                ));
    }
}
