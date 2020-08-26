package no.njm.example;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;

/**
 * Comparators are well known from older versions of Java. Java 8 adds various default methods to the interface
 */
@Slf4j
public class Comparators {

    public static void main(String[] args) {
        Person person1 = new Person("First", "Last");
        Person person2 = new Person("Second", "Third");

        Comparator<Person> firstNameComparator = Comparator.comparing(p -> p.firstName);
        log.debug("Comparing firstname returns {}", firstNameComparator.compare(person1, person2));
        log.debug("Comparing lastname reversed returns {}", firstNameComparator.reversed().compare(person1, person2));

        // Chaining comparators
        log.debug("Comparing firstname and lastname returns {}", firstNameComparator
                .thenComparing(p -> p.lastName)
                .compare(person1, person2));
    }
}
