package no.njm.example;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * Suppliers produce a result of a given generic type. Unlike Functions, Suppliers don't accept arguments.
 */
@Slf4j
public class Suppliers {

    public static void main(String[] args) {
        Supplier<Person> personSupplier = Person::new;
        Person person = personSupplier.get();
        person.setId(100);
        log.debug("Person has id {}", person.getId());
    }

    @Data
    private static class Person {
        private int id;
    }
}