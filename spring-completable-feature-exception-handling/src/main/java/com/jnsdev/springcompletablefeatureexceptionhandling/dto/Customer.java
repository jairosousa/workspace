package com.jnsdev.springcompletablefeatureexceptionhandling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @Autor Jairo Nascimento
 * @Created 30/12/2022 - 09:57
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Integer id;

    private String fullName;

    private String phoneNumber;

    private String address;

    private LocalDate createdAt;
}
