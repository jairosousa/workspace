package com.jnsdev.springcompletablefeatureexceptionhandling.client;

import com.jnsdev.springcompletablefeatureexceptionhandling.configuration.DataLoader;
import com.jnsdev.springcompletablefeatureexceptionhandling.dto.PurchaseTransaction;
import com.jnsdev.springcompletablefeatureexceptionhandling.utils.SleepUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Autor Jairo Nascimento
 * @Created 30/12/2022 - 17:02
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PurchaseTransactionClient {

    private final DataLoader dataLoader;

    public Set<PurchaseTransaction> getPurchaseTransactionsByCustomerId(Integer customerId, boolean isException) {
        log.info("Getting purchase transactions by customerId {}", customerId);
        SleepUtils.loadingSimulator(4);
        if (isException) {
            log.error("The error occurred while trying to retrieve purchase transactions!");
            throw new RuntimeException();
        }
        return dataLoader.getPurchaseTransactionResponses().get(customerId);
    }
}
