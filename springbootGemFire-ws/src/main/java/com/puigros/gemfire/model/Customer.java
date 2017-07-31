package com.puigros.gemfire.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.Region;

import java.io.Serializable;

/**
 * Created by gpuigros on 28/07/17.
 */
@Region("customer")
@Data
public class Customer implements Serializable{
    @Id
    private String firstname;
    private String lastname;
    private int age;
    private CustomerAddress address;

    @PersistenceConstructor
    public Customer(String firstname, String lastname, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }



    public String toString(){
        return "firstname: " + firstname + " ,lastname: " + lastname + " ,age: " + age;
    }



}