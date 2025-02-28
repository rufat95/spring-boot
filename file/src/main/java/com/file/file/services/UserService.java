package com.file.file.services;

import com.file.file.entities.User;
import com.file.file.repositories.UserRepository;
import com.file.file.requests.UserRequest;
import com.file.file.utils.FileUploadValidations;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FileUploadValidations fileUpload;

    public User saveUser(MultipartFile profilePicture, String username, String password)
            throws IOException {
        fileUpload.userFile(profilePicture);

        final String UPLOAD_DIR = "uploads/";
        String fileName = UUID.randomUUID() + "_" + profilePicture.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.copy(profilePicture.getInputStream(), filePath);

        UserRequest userRequest = new UserRequest(username, password);

        User user1 = new User();
        user1.setUsername(userRequest.getUsername());
        user1.setPassword(userRequest.getPassword());
        user1.setProfilePicture(filePath.toString());
        return userRepository.save(user1);
    }
}