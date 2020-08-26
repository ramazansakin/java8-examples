package no.njm.example;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Slf4j
public class FlatMapped {

    public static void main(String[] args) {
        flatMap();
        optionals(new Outer());
    }

    /**
     * FlatMap transforms one object into multiple others, so each object will be transformed into zero,
     * one or multiple other objects backed by streams
     */
    private static void flatMap() {
        List<Foo> fooList = new ArrayList<>();

        IntStream.range(1, 4)
                .forEach(i -> fooList.add(new Foo("Foo" + i)));

        fooList.forEach(foo -> IntStream.range(1, 4)
                .forEach(i -> foo.bars.add(new Bar(foo.name + "Bar" + i))));

        // FlatMap accepts a function which has to return a stream of objects
        fooList.stream()
                .flatMap(foo -> foo.bars.stream())
                .forEach(bar -> log.debug("{}", bar.name));

        // Single pipeline
        IntStream.range(1, 4)
                .mapToObj(i -> new Foo("Foo" + i))
                .flatMap(foo -> foo.bars.stream())
                .forEach(bar -> log.debug("{}", bar.name));
    }

    /**
     * FlatMap is also available for the Optional class and can be utilized to prevent nasty null checks.
     */
    private static void optionals(Outer outer) {
        if (outer != null && outer.nested != null && outer.nested.inner != null && outer.nested.inner.foo != null) {
            log.debug("Foo is {}", outer.nested.inner.foo);
        }

        // Optional FlatMap
        Optional<String> result = Optional.ofNullable(outer)
                .flatMap(o -> Optional.ofNullable(o.nested))
                .flatMap(n -> Optional.ofNullable(n.inner))
                .flatMap(i -> Optional.ofNullable(i.foo));

        result.ifPresent(s -> log.debug("Foo is {}", s));
    }

    private static class Outer {
        Nested nested;
    }

    private static class Nested {
        Inner inner;
    }

    private static class Inner {
        String foo;
    }

}
