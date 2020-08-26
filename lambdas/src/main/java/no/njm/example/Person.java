package no.njm.example;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
final class Person {
    final String firstName;
    final String lastName;
}
