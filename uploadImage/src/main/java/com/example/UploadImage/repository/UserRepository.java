package com.example.UploadImage.repository;

import com.example.UploadImage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmailId(String email);

    User findByUserId(String id);

}

