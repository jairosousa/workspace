package com.devjns.crudjpa.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * @Autor Jairo Nascimento
 * @Created 03/11/2022 - 13:33
 */

@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@NamedQuery(name = "Post.findAllByUserAndPost",
        query = "select post from Post post where post.usuario = ?1 and post.content like CONCAT('%',?2,'%')")
public class Post {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String usuario;
    private String content;
    @Column(name = "created_at")
    private ZonedDateTime createdAt;
}
