package com.jpa.nmaisum.model.repository;

import com.jpa.nmaisum.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Autor Jairo Nascimento
 * @Created 13/09/2022 - 08:35
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
