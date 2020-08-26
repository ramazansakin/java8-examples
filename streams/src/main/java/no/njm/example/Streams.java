package no.njm.example;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A java.util.Stream represents a sequence of elements on which one or more operations can be performed.
 * Collections in Java 8 are extended so you can simply create streams either by calling Collection.stream().
 * <p>
 * Java 8 streams cannot be reused. As soon as you call any terminal operation the stream is closed.
 */
@Slf4j
public class Streams {

    static List<Person> persons = Arrays.asList(
            new Person("Alfa", 20),
            new Person("Bravo", 30),
            new Person("Charlie", 40),
            new Person("Delta", 50),
            new Person("Echo", 60));

    public static void main(String[] args) {
        filter();
        sort();
        match();
        count();
        map();
        reduce();
        toList();
        kindOfStreams();
    }

    /**
     * Filter accepts a Predicate functional interface to filter all elements of the stream.
     */
    private static void filter() {
        List<String> list = Arrays.asList("alfa", "bravo", "charlie");
        list.stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(log::debug); // Consumer

        list.stream()
                .filter((s) -> s.contains("a"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(log::debug);
    }

    /**
     * The elements are sorted in natural order unless you pass a custom Comparator.
     * Keep in mind that sorted does only create a sorted view of the stream.
     * The ordering of stringCollection is untouched.
     */
    private static void sort() {
        List<String> list = Arrays.asList("alfa", "bravo", "charlie");
        list.stream()
                .sorted()
                .filter((s) -> s.startsWith("a"))
                .forEach(log::debug);
    }

    /**
     * Various matching operations can be used to check whether a certain predicate matches the stream.
     */
    private static void match() {
        // True if one element matches the Predicate
        boolean oneMatches = persons.stream()
                .anyMatch((p) -> p.name.startsWith("A"));
        log.debug("anyMatch is {}", oneMatches);

        // True if all elements matches the Predicate
        boolean allMatches = persons.stream()
                .allMatch((p) -> p.name.startsWith("A"));
        log.debug("allMatch is {}", allMatches);

        // True if no element matches the Predicate
        boolean noneMatches = persons.stream()
                .noneMatch((p) -> p.name.startsWith("A"));
        log.debug("noneMatches is {}", noneMatches);
    }

    /**
     * Count is a terminal operation returning the number of elements in the stream as a long.
     */
    private static void count() {
        long count = persons.stream().count();
        log.debug("Count is {}", count);
    }

    /**
     * The intermediate operation map converts each element into another object via the given function.
     */
    private static void map() {
        List<String> lowerCases = Arrays.asList("alfa", "bravo", "charlie");
        List<String> upperCases = lowerCases.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        upperCases.forEach(log::debug);
    }

    /**
     * This terminal operation performs a reduction on the elements of the stream with the given function.
     * The result is an Optional holding the reduced value.
     */
    private static void reduce() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        Optional<Integer> reduced = numbers.stream()
                .sorted()
                .reduce((n1, n2) -> n1 + n2); // Accumulator
        log.debug("Reduced numbers is {}", reduced.orElse(0));
    }

    /**
     * Convert a Map to a List.
     */
    private static void toList() {
        Map<Integer, Person> map = new HashMap<>();
        map.put(1, persons.get(0));
        map.put(2, persons.get(1));
        map.put(3, persons.get(2));

        // Manual
        List<Person> list = new ArrayList<>(map.size());
        map.entrySet()
                .stream()
                .forEach(entry -> list.add(entry.getValue()));
        log.debug("List has {} elements", list.size());
    }

    /**
     * Streams can be created from various data sources.
     */
    private static void kindOfStreams() {

        // From List
        Arrays.asList("alfa", "bravo", "charlie")
                .stream()
                .findFirst()
                .ifPresent(log::debug);

        // From object references
        Stream.of("alfa", "bravo", "charlie")
                .findFirst()
                .ifPresent(log::debug);

        // From primitive type
        log.debug("Sum range is {}", IntStream.range(1, 4)
                .sum());

        // From arrays
        OptionalDouble doubleResult = Arrays.stream(new int[]{1, 2, 3})
                .map(n -> 2 * n + 1)
                .average();
        log.debug("Average is {}", doubleResult.orElse(0));

        // Mapping stream values
        OptionalInt intResult = Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max();
        log.debug("Max is {}", intResult.orElse(0));
    }
}
