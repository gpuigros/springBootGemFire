package com.puigros.ws.controller;

import com.puigros.gemfire.model.Customer;
import com.puigros.gemfire.model.CustomerAddress;
import com.puigros.gemfire.repository.CustomerRepository;
import com.puigros.model.service.SampleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * This is a sample rest controller class.
 * <p>
 * <p>
 *
 * @author Guillem
 * APIresourcesdefinition-Examples</a>
 * @see
 * @since JDK1.8
 */
@Slf4j
@RestController
@RequestMapping(value = "/springbootGemFire/1.0/")
@Api(value="Example System")
public class GemFireController {

    @Autowired
    private SampleService sampleService;

    @Autowired
    CustomerRepository customerRepository;
    
    /**
     * Retrieve All Samples
     * @return All samples
     */

    @ApiOperation(
            tags = "SAMPLE",
            value = "Get a list of SampleDTO",
            notes = "The Samples endpoint returns a list of SampleDTO objects",
            response = Boolean.class,
            responseContainer = "List"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @RequestMapping(value = "/createData", produces = {MediaType.APPLICATION_JSON_VALUE},
                    headers = "Accept=application/json", method = RequestMethod.GET)
    public ResponseEntity<Void> createData() {

        CustomerAddress adr=new CustomerAddress();
        adr.setStreet("Sessamy Street");
        adr.setNumber(8);
        Customer peter = new Customer("Peter", "Williams", 20);
        peter.setAddress(adr);

        adr=new CustomerAddress();
        adr.setStreet("Sessamy Street 2");
        adr.setNumber(88);
        Customer mary = new Customer("Mary", "Diaz", 25);
        mary.setAddress(adr);
        // SAVE customer to Gemfire
        customerRepository.save(peter);
        customerRepository.save(mary);

        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    /**
     * Retrieve All Samples
     * @return All samples
     */

    @ApiOperation(
            tags = "SAMPLE",
            value = "Get a list of SampleDTO",
            notes = "The Samples endpoint returns a list of SampleDTO objects",
            response = Boolean.class,
            responseContainer = "List"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @RequestMapping(value = "/getData", produces = {MediaType.APPLICATION_JSON_VALUE},
            headers = "Accept=application/json", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getData() {

        List<Customer> customers=new ArrayList<>();
        // FindAll Customers
        log.info("-------Find All Customers-------");
        Iterable<Customer> custList = customerRepository.findAll();
        for(Customer c: custList){
            log.info(c.toString());
            customers.add(c);
        }

        // Find a Customer by firstname
        log.info("-------Find Customer by FirstName-------");
        Customer c1 = customerRepository.findByFirstname("Peter");
        log.info(c1.toString());

        // Find a Customer by lastname
        log.info("-------Find Customer by LastName-------");
        Customer c2 = customerRepository.findByLastname("Diaz");
        log.info(c2.toString());


        // find customer by age
        log.info("-------Find Customers have age greater than 22-------");
        Iterable<Customer> custLstByAgeGreaterThan22 = customerRepository.findByAgeGreaterThan(22);
        for(Customer c: custLstByAgeGreaterThan22){
            log.info(c.toString());
        }


        log.info("-------Find Customers have age less than 30-------");
        Iterable<Customer> custLstByAgeLessThan30 = customerRepository.findByAgeLessThan(30);
        for(Customer c: custLstByAgeLessThan30){
            log.info(c.toString());
        }
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);

    }
}
