package com.devjns.crudjpa.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Autor Jairo Nascimento
 * @Created 03/11/2022 - 13:38
 */
@Repository
public interface PostJpaRepository extends JpaRepository<Post, String> {
}
