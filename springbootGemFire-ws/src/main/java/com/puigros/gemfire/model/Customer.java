package com.puigros.gemfire.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.Region;

import java.io.Serializable;

/**
 * Created by gpuigros on 28/07/17.
 */
@Region("customer")
public class Customer implements Serializable{
    @Id
    private String firstname;
    private String lastname;
    private int age;

    @PersistenceConstructor
    public Customer(String firstname, String lastname, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String toString(){
        return "firstname: " + firstname + " ,lastname: " + lastname + " ,age: " + age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}