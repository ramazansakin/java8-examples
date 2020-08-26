package no.njm.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Foo {
    String name;
    List<Bar> bars = new ArrayList<>();

    public Foo(String name) {
        this.name = name;
    }
}
