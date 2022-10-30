package com.jndev.desingPattern.observer;

import com.jndev.desingPattern.model.Customer;
import com.jndev.desingPattern.model.DeliveryPackage;
import com.jndev.desingPattern.model.PackageStatus;
import com.jndev.desingPattern.service.NotificationService;

import java.util.UUID;

/**
 * @Autor Jairo Nascimento
 * @purpose: Observer Design pattern main class
 * @Created 30/10/2022 - 08:42
 */
public class TestObserver {

    public static void main(String[] args) {

        //Adding New Customer
        Customer customerJa = new Customer(UUID.randomUUID().toString(), "Jairo Nascimento");
        Customer customerCa = new Customer(UUID.randomUUID().toString(), "Caio Luz");

        //Criar pacote para Assinatura
        DeliveryPackage deliveryPackage = new DeliveryPackage(UUID.randomUUID().toString(),
                "LAPTOP", PackageStatus.STARTED.name());

        //Serviço para Assinatura
        NotificationService notificationService = new NotificationService();

        //Cliente assinando o pacote
        notificationService.subscribe(customerJa, deliveryPackage);
        notificationService.subscribe(customerCa, deliveryPackage);

        //Cliente Desassinando o pacote
//        notificationService.unSubscribe(customerCa, deliveryPackage);

        //Notificando os clientes...
        notificationService.notifyCustomer(deliveryPackage);

        //Alterando o status do pacote
        deliveryPackage.setStatus(PackageStatus.OUT_FOR_DELIVERY.name());

        //Notificando os clientes...
        notificationService.notifyCustomer(deliveryPackage);

    }
}
