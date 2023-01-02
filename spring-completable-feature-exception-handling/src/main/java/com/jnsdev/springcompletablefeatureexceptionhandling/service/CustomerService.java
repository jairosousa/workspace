package com.jnsdev.springcompletablefeatureexceptionhandling.service;

import com.jnsdev.springcompletablefeatureexceptionhandling.client.CustomerClient;
import com.jnsdev.springcompletablefeatureexceptionhandling.client.PurchaseTransactionClient;
import com.jnsdev.springcompletablefeatureexceptionhandling.dto.CustomerResponse;
import com.jnsdev.springcompletablefeatureexceptionhandling.dto.PurchaseTransactionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Autor Jairo Nascimento
 * @Created 30/12/2022 - 09:52
 */

@Slf4j
@Service
public final class CustomerService {
    private final CustomerClient customerClient;
    private final PurchaseTransactionClient purchaseTransactionClient;

    CustomerService(
            CustomerClient customerClient,
            PurchaseTransactionClient purchaseTransactionClient) {
        this.customerClient = customerClient;
        this.purchaseTransactionClient = purchaseTransactionClient;
    }

    public CustomerClient customerClient() {
        return customerClient;
    }

    public PurchaseTransactionClient purchaseTransactionClient() {
        return purchaseTransactionClient;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CustomerService) obj;
        return Objects.equals(this.customerClient, that.customerClient) &&
                Objects.equals(this.purchaseTransactionClient, that.purchaseTransactionClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerClient, purchaseTransactionClient);
    }

    @Override
    public String toString() {
        return "CustomerService[" +
                "customerClient=" + customerClient + ", " +
                "purchaseTransactionClient=" + purchaseTransactionClient + ']';
    }

    public CustomerResponse getCustomerByIdUsingExceptionally(Integer customerId) {
        log.info("Getting customer by id {} using exceptionally.", customerId);
        CompletableFuture<CustomerResponse> customerResponseCF = CompletableFuture.supplyAsync(
                () -> CustomerResponse.valueOf(customerClient.getCustomerById(customerId)));
        CompletableFuture<Set<PurchaseTransactionResponse>> purchaseTransactionsCF = CompletableFuture.supplyAsync(
                () -> purchaseTransactionClient
                        .getPurchaseTransactionsByCustomerId(customerId, isException(customerId))
                        .stream()
                        .map(PurchaseTransactionResponse::valueOf)
                        .collect(Collectors.toSet()))
                .exceptionally(ex -> {
                    log.error("Received exception {}, returning empty list.", ex.getMessage());
                    return Set.of();
                });
        CompletableFuture<CustomerResponse> customerResponseCompletableFuture = customerResponseCF
                .thenCombine(purchaseTransactionsCF, (customerResponse, purchaseTransactions) -> {
                    customerResponse.setPurchaseTransactions(purchaseTransactions);
                    return customerResponse;
                });
        CustomerResponse response = customerResponseCompletableFuture.join();
        return response;
    }

    public CustomerResponse getCustomerByIdUsingExceptionallyRethrow(Integer customerId) {
        log.info("Getting customer by id {} using exceptionally and rethrow.", customerId);
        CompletableFuture<CustomerResponse> customerResponseCF = CompletableFuture.supplyAsync(
                () -> CustomerResponse.valueOf(customerClient.getCustomerById(customerId)));
        CompletableFuture<Set<PurchaseTransactionResponse>> purchaseTransactionsCF = CompletableFuture.supplyAsync(
                () -> purchaseTransactionClient
                        .getPurchaseTransactionsByCustomerId(customerId, isException(customerId))
                        .stream()
                        .map(PurchaseTransactionResponse::valueOf)
                        .collect(Collectors.toSet()))
                .exceptionally(ex -> {
                    log.error("Received exception {}, throwing new exception!", ex.getMessage());
                    throw new IllegalArgumentException();
                });
        CompletableFuture<CustomerResponse> customerResponseCompletableFuture = customerResponseCF
                .thenCombine(purchaseTransactionsCF, (customerResponse, purchaseTransactions) -> {
                    customerResponse.setPurchaseTransactions(purchaseTransactions);
                    return customerResponse;
                });
        CustomerResponse response = customerResponseCompletableFuture.join();
        return response;
    }

    public CustomerResponse getCustomerByIdUsingHandle(Integer customerId) {
        log.info("Getting customer by id {} using handle.", customerId);
        CompletableFuture<CustomerResponse> customerResponseCF = CompletableFuture.supplyAsync(
                () -> CustomerResponse.valueOf(customerClient.getCustomerById(customerId)));
        CompletableFuture<Set<PurchaseTransactionResponse>> purchaseTransactionsCF = CompletableFuture.supplyAsync(
                () -> purchaseTransactionClient
                        .getPurchaseTransactionsByCustomerId(customerId, isException(customerId))
                        .stream()
                        .map(PurchaseTransactionResponse::valueOf)
                        .collect(Collectors.toSet()))
                .handle((response, ex) -> {
                    log.info("Executing exception handler for purchase transaction CF...");
                    if (ex != null) {
                        log.error("Received exception {}, returning empty list.", ex.getMessage());
                        return Collections.EMPTY_SET;
                    }
                    return response;
                });
        CompletableFuture<CustomerResponse> customerResponseCompletableFuture = customerResponseCF
                .thenCombine(purchaseTransactionsCF, (customerResponse, purchaseTransactions) -> {
                    customerResponse.setPurchaseTransactions(purchaseTransactions);
                    return customerResponse;
                });
        CustomerResponse response = customerResponseCompletableFuture.join();
        return response;
    }

    public CustomerResponse getCustomerByIdUsingWhenComplete(Integer customerId) {
        log.info("Getting customer by id {} using when complete.", customerId);
        CompletableFuture<CustomerResponse> customerResponseCF = CompletableFuture.supplyAsync(
                () -> CustomerResponse.valueOf(customerClient.getCustomerById(customerId)));
        CompletableFuture<Set<PurchaseTransactionResponse>> purchaseTransactionsCF = CompletableFuture.supplyAsync(
                () -> purchaseTransactionClient
                        .getPurchaseTransactionsByCustomerId(customerId, isException(customerId))
                        .stream()
                        .map(PurchaseTransactionResponse::valueOf)
                        .collect(Collectors.toSet()))
                .whenComplete((response, ex) -> {
                    log.info("Executing whenComplete for purchase transaction CF...");
                    if (ex != null) {
                        log.error("Received exception {}, throwing exception to consumer", ex.getMessage());
                    }
                });
        CompletableFuture<CustomerResponse> customerResponseCompletableFuture = customerResponseCF
                .thenCombine(purchaseTransactionsCF, (customerResponse, purchaseTransactions) -> {
                    customerResponse.setPurchaseTransactions(purchaseTransactions);
                    return customerResponse;
                });
        CustomerResponse response = customerResponseCompletableFuture.join();
        return response;
    }

    public CustomerResponse getCustomerByIdWithOrTimeout(Integer customerId) {
        log.info("Getting customer by id {} with orTimeout", customerId);
        int timeOut = getTimeOut(customerId);
        log.info("CF timeout is {}", timeOut);
        CompletableFuture<CustomerResponse> customerResponseCF = CompletableFuture.supplyAsync(
                () -> CustomerResponse.valueOf(customerClient.getCustomerById(customerId)));
        CompletableFuture<Set<PurchaseTransactionResponse>> purchaseTransactionsCF = CompletableFuture.supplyAsync(
                () -> purchaseTransactionClient
                        .getPurchaseTransactionsByCustomerId(customerId, false)
                        .stream()
                        .map(PurchaseTransactionResponse::valueOf)
                        .collect(Collectors.toSet()))
                .orTimeout(timeOut, TimeUnit.SECONDS);
        CompletableFuture<CustomerResponse> customerResponseCompletableFuture = customerResponseCF
                .thenCombine(purchaseTransactionsCF, (customerResponse, purchaseTransactions) -> {
                    customerResponse.setPurchaseTransactions(purchaseTransactions);
                    return customerResponse;
                });
        CustomerResponse response = customerResponseCompletableFuture.join();
        return response;
    }

    public CustomerResponse getCustomerByIdWithCompleteOnTimeout(Integer customerId) {
        log.info("Getting customer by id {} with completeOnTimeout.", customerId);
        int timeOut = getTimeOut(customerId);
        log.info("CF timeout is {}", timeOut);
        CompletableFuture<CustomerResponse> customerResponseCF = CompletableFuture.supplyAsync(
                () -> CustomerResponse.valueOf(customerClient.getCustomerById(customerId)));
        CompletableFuture<Set<PurchaseTransactionResponse>> purchaseTransactionsCF = CompletableFuture.supplyAsync(
                () -> purchaseTransactionClient
                        .getPurchaseTransactionsByCustomerId(customerId, false)
                        .stream()
                        .map(PurchaseTransactionResponse::valueOf)
                        .collect(Collectors.toSet()))
                .completeOnTimeout(Set.of(), timeOut, TimeUnit.SECONDS);
        CompletableFuture<CustomerResponse> customerResponseCompletableFuture = customerResponseCF
                .thenCombine(purchaseTransactionsCF, (customerResponse, purchaseTransactions) -> {
                    customerResponse.setPurchaseTransactions(purchaseTransactions);
                    return customerResponse;
                });
        CustomerResponse response = customerResponseCompletableFuture.join();
        return response;
    }

    private static int getTimeOut(Integer customerId) {
        if (customerId % 2 == 0) {
            return 2;
        }
        return 5;
    }

    private static boolean isException(Integer customerId) {
        if (customerId % 2 == 0) {
            return true;
        }
        return false;
    }
}
