package com.jnsdev.springcompletablefeatureexceptionhandling.controller;

import com.jnsdev.springcompletablefeatureexceptionhandling.dto.CustomerResponse;
import com.jnsdev.springcompletablefeatureexceptionhandling.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Autor Jairo Nascimento
 * @Created 30/12/2022 - 09:51
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/customers")
public class CustumerController {

    private final CustomerService customerService;

    @GetMapping("/using-exceptionally/{customerId}")
    public CustomerResponse getCustomerByIdUsingExceptionally(@PathVariable Integer customerId) {
        return customerService.getCustomerByIdUsingExceptionally(customerId);
    }

    @GetMapping("/using-exceptionally-rethrow/{customerId}")
    public CustomerResponse getCustomerByIdUsingException(@PathVariable Integer customerId) {
        return customerService.getCustomerByIdUsingExceptionallyRethrow(customerId);
    }

    @GetMapping("/using-when-complete/{customerId}")
    public CustomerResponse getCustomerByIdUsingWhenComplete(@PathVariable Integer customerId) {
        return customerService.getCustomerByIdUsingWhenComplete(customerId);
    }

    @GetMapping("/timeout/{customerId}")
    public CustomerResponse getCustomerByIdWithTimeout(@PathVariable Integer customerId) {
        return customerService.getCustomerByIdWithOrTimeout(customerId);
    }

    @GetMapping("/complete-on-timeout/{customerId}")
    public CustomerResponse getCustomerByIdWithCompleteOnTimeout(@PathVariable Integer customerId) {
        return customerService.getCustomerByIdWithCompleteOnTimeout(customerId);
    }
}
