package com.jnsdev.interception.controller;

import com.jnsdev.interception.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Autor Jairo Nascimento
 * @Created 28/10/2022 - 12:00
 */

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id ){
        return ResponseEntity.ok(new Student(id,"Jairo"));
    }
}
