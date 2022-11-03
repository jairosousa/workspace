package com.devjns.crudjpa.model;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Autor Jairo Nascimento
 * @Created 03/11/2022 - 15:10
 */
@DisplayName("Crud Repository")
@ExtendWith(SpringExtension.class)
@DataJpaTest
class PostJpaRepositoryTest implements WithAssertions {

    @Autowired
    private PostCrudRepository repository;

    @Test
    @DisplayName("Retrieve all")
    @Sql("/data.sql")
    void findAll() {
        assertThat(repository.findAll()).isNotEmpty().hasSize(10);
    }

    @Test
    @DisplayName("Retrieve all by user")
    @Sql("/data.sql")
    void findAllByUser() {
        var user = "luizcosta";
        List<Post> allPostsByUser = this.repository.findAllByUsuario(user);
        assertThat(allPostsByUser).extracting("usuario").contains(user);
    }

    @Test
    @DisplayName("Retrieve all by user contains word")
    @Sql("/data.sql")
    void findAllByUserLike() {
        var user = "t";
        List<Post> allPostsByUser = this.repository.findAllByUsuarioContains(user);
        assertThat(allPostsByUser).hasSize(4);
    }

    @Test
    @DisplayName("Retrieve all posts by user and post word")
    @Sql("/data.sql")
    void findAllByUserAndPost() {
        var user = "luizcosta";
        var post = "#studying";
        List<Post> allPostsByUser = this.repository.findAllByUsuarioAndContentContaining(user, post);
        assertThat(allPostsByUser).hasSize(2);
    }

    @Test
    @DisplayName("Count all posts having the content")
    @Sql("/data.sql")
    void countAllPostsHavingTheContent() {
        var post = "Rock";
        Long occurrences = this.repository.countPostContentWithContentLowerCase(post.toLowerCase());
        assertThat(occurrences).isEqualTo(2);
    }

    @DisplayName("Retrieve all between dates")
    @Sql("/data.sql")
    void findAllBetweenDates() {
        ZoneId zoneId = ZoneId.of("Europe/Madrid");
        var localDateTime = LocalDateTime.of(2015, Month.JANUARY, 1, 0, 0, 0, 0);
        var start = ZonedDateTime.of(localDateTime, zoneId);
        var end = ZonedDateTime.of(localDateTime.plusYears(5), zoneId);

        List<Post> allByCreatedAtBetween = this.repository.findAllByCreatedAtBetween(start, end);
        assertThat(allByCreatedAtBetween).isNotEmpty().hasSize(6);
    }
}