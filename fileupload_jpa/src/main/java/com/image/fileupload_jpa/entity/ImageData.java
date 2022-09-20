package com.image.fileupload_jpa.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * @Autor Jairo Nascimento
 * @Created 20/09/2022 - 11:07
 */
@Entity
@Table(name = "image_data")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

//    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(name = "image_data")
    private byte[] imageData;
}
