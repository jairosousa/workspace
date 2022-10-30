package com.jndev.desingPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 30/10/2022 - 08:40
 */
public class NotificationService {

    public void subscribe(Customer customer, DeliveryPackage deliveryPackage) {
        List<Customer> preCustomer = new ArrayList<>();
        preCustomer.addAll(deliveryPackage.getCustomers() != null
                ? deliveryPackage.getCustomers() : new ArrayList<>());
        preCustomer.add(customer);
        deliveryPackage.setCustomers(preCustomer);

    }

    public void unSubscribe(Customer customer, DeliveryPackage deliveryPackage) {
        List<Customer> preCustomer = new ArrayList<>();
        preCustomer.addAll(deliveryPackage.getCustomers() != null ?
                deliveryPackage.getCustomers() : new ArrayList<>());
        preCustomer.remove(customer);
        deliveryPackage.setCustomers(preCustomer);
    }

    public void notifyCustomer(DeliveryPackage deliveryPackage) {
        List<Customer> customers = new ArrayList<>();
        customers.addAll(deliveryPackage.getCustomers() != null ?
                deliveryPackage.getCustomers() : new ArrayList<>());
        for (Customer customer : customers) {
            System.out.println("Olá " + customer.getName()
                    + "  status de sua entrega do o seu pacote foi alterado para:- " + deliveryPackage.getStatus());
        }

    }
}
