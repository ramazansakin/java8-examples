package no.njm.example;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

/**
 * Functions accept one argument and produce a result.
 * Default methods can be used to chain multiple functions together (compose, andThen).
 */
@Slf4j
public class Functions {

    public static void main(String[] args) {
        Function<String, Integer> func = x -> x.length();
        Integer apply = func.apply("rsakin");   // 6
        log.info("Size : " + apply);

        Function<String, Integer> func1 = x -> x.length();
        Function<Integer, Integer> func2 = x -> x * 2;
        Integer result = func1.andThen(func2).apply("rsakin");   // 12
        log.info("New size : " + result);
    }
}