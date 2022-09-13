package com.jpa.nmaisum.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Autor Jairo Nascimento
 * @Created 13/09/2022 - 08:33
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private String description;

    @ManyToOne
    private Customer customer;

    public Product(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }
}
