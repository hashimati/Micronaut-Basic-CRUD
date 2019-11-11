package io.hashimati.basiccrud;

import java.util.HashSet;

import io.micronaut.context.annotation.Prototype;
import io.micronaut.runtime.Micronaut;

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class);
    }
}