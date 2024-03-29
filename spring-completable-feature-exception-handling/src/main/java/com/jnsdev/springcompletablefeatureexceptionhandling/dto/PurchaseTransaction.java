package com.jnsdev.springcompletablefeatureexceptionhandling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Autor Jairo Nascimento
 * @Created 30/12/2022 - 10:00
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseTransaction {

    private String id;

    private String paymentType;

    private BigDecimal amount;

    private LocalDate createdAt;
}
