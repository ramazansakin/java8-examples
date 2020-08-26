package no.njm.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * An Optional is a class that encapsulates an optional value, and can be viewed as
 * a single-value container that either contains a value or doesn't.
 * <p>
 * Unfortunately, due to backwards-compatibility, none of the existing Java collections will be
 * retrofitted into returning Optionals.
 * <pre>
 * List<MyObject> list = getList();
 * Optional<E> optional = list.get(index);
 * </pre>
 */
public class Optionals {

    private static final Logger log = LoggerFactory.getLogger(Optionals.class);

    public static void main(String[] args) {
        Optionals optionals = new Optionals();
        plainOptional(optionals);
        lambdaOptional(optionals);
        fallbackOptional(optionals);
        nullWrapped(optionals);
        filterOptional(optionals);
        mapOptional(optionals);
    }

    private static void plainOptional(Optionals optionals) {
        Optional<String> optional = optionals.upperCaseFirstLetter("source");
        optional.ifPresent(s -> log.debug("Optional is {}", s));
    }

    private static void lambdaOptional(Optionals optionals) {
        Optional<String> optional = optionals.upperCaseFirstLetter("source");
        optional.ifPresent(log::debug);
    }

    private static void fallbackOptional(Optionals optionals) {
        Optional<String> optional = optionals.upperCaseFirstLetter("");
        optional.ifPresent(log::debug);
    }

    private static void nullWrapped(Optionals optionals) {
        Optional<String> optional = optionals.wrapNull();
        optional.ifPresent(log::debug);
    }

    private static void filterOptional(Optionals optionals) {
        Optional<String> optional = optionals.upperCaseFirstLetter("source");
        optional.filter(s -> s.length() > 4).ifPresent(log::debug);
    }

    private static void mapOptional(Optionals optionals) {
        Optional<String> optional = optionals.upperCaseFirstLetter("source");
        int size = -1;
        if (optional.isPresent()) {
            size = countLetters(optional.get());
        }
        log.debug("String length is {}", size);
    }

    static int countLetters(String input) {
        return input.length() - 1;
    }

    Optional<String> upperCaseFirstLetter(String source) {
        if (source.isEmpty()) {
            return Optional.empty();
        }
        String uppercase = upperCase(firstLetter(source)) + lowerCase(allButFirstLetter(source));
        return Optional.of(uppercase);
    }

    Optional<String> wrapNull() {
        return Optional.empty();
    }

    private String upperCase(String source) {
        return source.toUpperCase();
    }

    private String firstLetter(String source) {
        return source.substring(0, 1);
    }

    private String lowerCase(String source) {
        return source.toLowerCase();
    }

    private String allButFirstLetter(String source) {
        return source.substring(1);
    }
}
