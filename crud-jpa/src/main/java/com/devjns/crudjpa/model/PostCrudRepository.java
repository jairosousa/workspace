package com.devjns.crudjpa.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 03/11/2022 - 13:35
 */
@Repository
public interface PostCrudRepository extends CrudRepository<Post, String> {

    List<Post> findAllByUsuario(String user);
    List<Post> findAllByCreatedAtBetween(ZonedDateTime start, ZonedDateTime end);
    List<Post> findAllByUsuarioContains(String user);

    //NamedQuery
    List<Post> findAllByUsuarioAndContentContaining(String user, String post);

    //Native Query
    @Query(value = "SELECT count(1) FROM posts p WHERE lower(p.content) like CONCAT('%',?1,'%')", nativeQuery = true)
    Long countPostContentWithContentLowerCase(String content);

}
