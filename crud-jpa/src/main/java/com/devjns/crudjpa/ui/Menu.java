package com.devjns.crudjpa.ui;

import com.devjns.crudjpa.model.Post;
import com.devjns.crudjpa.model.PostCrudRepository;
import com.devjns.crudjpa.model.PostJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @Autor Jairo Nascimento
 * @Created 03/11/2022 - 13:39
 */
@Component
@Slf4j
public class Menu {

    private static PostCrudRepository crudRepository;
    private static PostJpaRepository jpaRepository;

    private static final List<String> USERS = List.of("messi", "mbappe", "neymar");

    @Autowired
    public Menu(PostCrudRepository crudRepository, PostJpaRepository jpaRepository) {
        Menu.crudRepository = crudRepository;
        Menu.jpaRepository = jpaRepository;
    }

    public static List<Post> findAllCrud() {
        return StreamSupport.stream(crudRepository.findAll().spliterator(), false)
                .collect(Collectors.toUnmodifiableList());
    }

    public static long countPosts() {
        return crudRepository.count();
    }

    public static void createARandomPost() {
        Post post = jpaRepository.save(Post.builder()
                .usuario(getRandomUser())
                .content("I'm a random content using an ID " + UUID.randomUUID().toString().substring(0, 10))
                .createdAt(ZonedDateTime.now())
                .build());
        log.info("New post created by {}", post.getUsuario());
    }

    /**
     * // For a multithread env int index = ThreadLocalRandom.current().nextInt(USERS.size()) % USERS.size();
     * @return user {@link String}
     */
    private static String getRandomUser() {
        return USERS.get(new Random().nextInt(USERS.size()));
    }
}
