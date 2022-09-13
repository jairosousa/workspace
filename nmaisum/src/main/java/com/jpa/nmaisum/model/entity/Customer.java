package com.jpa.nmaisum.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.isNull;

/**
 * @Autor Jairo Nascimento
 * @Created 13/09/2022 - 08:30
 */
@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    @Fetch(FetchMode.SUBSELECT)
    private Set<Product> products;

    public void addProduct(Product product) {
        if (isNull(products))
            products = new HashSet<>();
        products.add(product);
        product.setCustomer(this);
    }
}
