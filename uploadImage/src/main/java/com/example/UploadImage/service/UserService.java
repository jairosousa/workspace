package com.example.UploadImage.service;

import com.example.UploadImage.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    void updateProfilePicture(String id, MultipartFile multipartFile) throws Exception;

    User getUserDetailsByEmailId(String emailId);
}
