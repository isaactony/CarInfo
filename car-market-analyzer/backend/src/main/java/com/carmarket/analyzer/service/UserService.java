package com.carmarket.analyzer.service;

import com.carmarket.analyzer.model.User;
import com.carmarket.analyzer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    // Create a new user
    public User createUser(String username, String email, String password) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists: " + username);
        }
        
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists: " + email);
        }
        
        // Encode password and create user
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, email, encodedPassword);
        
        return userRepository.save(user);
    }
    
    // Get user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    
    // Get user by username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    // Get user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    // Update user information
    public User updateUser(Long id, String username, String email) {
        User user = getUserById(id);
        
        // Check if new username conflicts with existing users
        if (!username.equals(user.getUsername()) && userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists: " + username);
        }
        
        // Check if new email conflicts with existing users
        if (!email.equals(user.getEmail()) && userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists: " + email);
        }
        
        user.setUsername(username);
        user.setEmail(email);
        
        return userRepository.save(user);
    }
    
    // Update user password
    public User updatePassword(Long id, String newPassword) {
        User user = getUserById(id);
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        
        return userRepository.save(user);
    }
    
    // Delete user
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
    
    // Authenticate user
    public boolean authenticateUser(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        
        return false;
    }
    
    // Check if username exists
    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }
    
    // Check if email exists
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
