package com.puigros.gemfire.repository;


import com.puigros.gemfire.model.Customer;
import org.springframework.data.repository.CrudRepository;



/**
 * Created by gpuigros on 28/07/17.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByFirstname(String firstname);
    Customer findByLastname(String lastname);
    Iterable<Customer> findByAgeGreaterThan(int age);
    Iterable<Customer> findByAgeLessThan(int age);
}