package com.jnsdev.springcompletablefeatureexceptionhandling.client;

import com.jnsdev.springcompletablefeatureexceptionhandling.configuration.DataLoader;
import com.jnsdev.springcompletablefeatureexceptionhandling.dto.Customer;
import com.jnsdev.springcompletablefeatureexceptionhandling.utils.SleepUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Autor Jairo Nascimento
 * @Created 30/12/2022 - 17:00
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerClient {

    private final DataLoader dataLoader;

    public Customer getCustomerById(Integer customerId) {
        log.info("Getting customer by id {}", customerId);
        SleepUtils.loadingSimulator(2);
        return dataLoader.getCustomers().get(customerId);
    }
}
