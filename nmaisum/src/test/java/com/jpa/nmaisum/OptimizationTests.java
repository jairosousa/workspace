package com.jpa.nmaisum;

import com.jpa.nmaisum.model.entity.Customer;
import com.jpa.nmaisum.model.entity.Product;
import com.jpa.nmaisum.model.repository.CustomerRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Autor Jairo Nascimento
 * @Created 13/09/2022 - 08:36
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OptimizationTests {

    @Autowired
    CustomerRepository customerRepository;


    @Test
    @Order(1)
    @Rollback(value = false)
    void createRecords() {
        IntStream.range(0, 50000)
                .forEach(v1 -> {
                    Customer customer = new Customer();
                    customer.setName("Name " + v1);
                    customer.setSurname("Surname " + v1);
                    IntStream.range(0, 3).forEach(v2 -> {
                                Product product = generateProduct(v2);
                                customer.addProduct(product);
                            }
                    );
                    customerRepository.save(customer);
                });
    }

    private Product generateProduct(int value) {
        return new Product("Product " + value,
                "Code " + value,
                "Desc " + value);
    }
    @Test
    @Order(2)
    void fetchRecords() {
        customerRepository.findAll()
                .stream()
                .flatMap(customer -> customer.getProducts().stream())
                .collect(Collectors.toSet());
    }

}
