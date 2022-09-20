package com.image.fileupload_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class FileuploadJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileuploadJpaApplication.class, args);
    }

}
