package io.hashimati.basiccrud;

import java.util.HashSet;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Prototype;

/**
 * PersonFacotry
 */
@Factory
public class PersonFacotry {
    
    @Prototype
    private HashSet<Person> personsRepository(){
        return new HashSet<Person>(); 
    }
}