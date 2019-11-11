package io.hashimati.basiccrud;

import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Person
 */

 @NoArgsConstructor
 @AllArgsConstructor
 @Data
 @ToString
 @EqualsAndHashCode
public class Person {


    private String username, email, fullname; 

    

}