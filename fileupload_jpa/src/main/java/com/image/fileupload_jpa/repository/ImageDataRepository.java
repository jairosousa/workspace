package com.image.fileupload_jpa.repository;

import com.image.fileupload_jpa.entity.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Autor Jairo Nascimento
 * @Created 20/09/2022 - 11:13
 */
@Repository
public interface ImageDataRepository extends JpaRepository<ImageData, Long> {
    Optional<ImageData> findByName(String name);
}
