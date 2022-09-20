package com.image.fileupload_jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Autor Jairo Nascimento
 * @Created 20/09/2022 - 11:29
 */
@NoArgsConstructor
public class ImageUploadResponse {

    private String mensagem;

    public ImageUploadResponse(String mensagem) {
        this.mensagem = mensagem;
    }
}
