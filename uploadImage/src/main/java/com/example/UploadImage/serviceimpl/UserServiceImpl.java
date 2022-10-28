package com.example.UploadImage.serviceimpl;

import com.example.UploadImage.entity.User;
import com.example.UploadImage.repository.UserRepository;
import com.example.UploadImage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void updateProfilePicture(String userId, MultipartFile multipartFile) throws Exception {
        User user = userRepository.findByUserId(userId);
        if(user!= null && multipartFile!=null) {
            user.setProfilePicBytes(multipartFile.getBytes());
            userRepository.save(user);
        } else {
            log.debug("User not found for userId : {}", userId);
            throw new Exception("User not found for { userId = " + userId + "}");
        }
    }

    @Override
    public User getUserDetailsByEmailId(String emailId) {
        User user = userRepository.findByEmailId(emailId);
        return user;
    }
}
