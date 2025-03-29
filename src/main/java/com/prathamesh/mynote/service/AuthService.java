package com.prathamesh.mynote.service;

import com.prathamesh.mynote.dto.LoginRequest;
import com.prathamesh.mynote.dto.RegisterRequest;
import com.prathamesh.mynote.model.User;
import com.prathamesh.mynote.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(RegisterRequest request) {
        // Create a new User entity from request
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // Password should be hashed in real apps

        // Save the user to the database
        userRepository.save(user);
    }

    public boolean loginUser(LoginRequest request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        return user.isPresent() && user.get().getPassword().equals(request.getPassword());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
