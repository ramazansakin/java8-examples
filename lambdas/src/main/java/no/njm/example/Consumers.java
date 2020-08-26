package no.njm.example;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/**
 * Consumers represents operations to be performed on a single input argument.
 */
@Slf4j
public class Consumers {

    public static void main(String[] args) {
        Person person = new Person("First", "Last");
        Consumer<Person> printFirstName = p -> log.info(p.firstName);
        printFirstName.accept(person);

        // Chaining consumers
        Consumer<Person> printName = printFirstName.andThen(p -> log.info(p.lastName));
        printName.accept(person);
    }
}