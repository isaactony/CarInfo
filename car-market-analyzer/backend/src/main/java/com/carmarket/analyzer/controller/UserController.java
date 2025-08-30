package com.carmarket.analyzer.controller;

import com.carmarket.analyzer.model.User;
import com.carmarket.analyzer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    // User registration
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody Map<String, String> request) {
        try {
            String username = request.get("username");
            String email = request.get("email");
            String password = request.get("password");
            
            if (username == null || email == null || password == null) {
                return ResponseEntity.badRequest().build();
            }
            
            User user = userService.createUser(username, email, password);
            // Don't return password in response
            user.setPassword(null);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // User authentication
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody Map<String, String> request) {
        try {
            String username = request.get("username");
            String password = request.get("password");
            
            if (username == null || password == null) {
                return ResponseEntity.badRequest().build();
            }
            
            boolean isAuthenticated = userService.authenticateUser(username, password);
            
            if (isAuthenticated) {
                User user = userService.getUserByUsername(username).orElse(null);
                if (user != null) {
                    user.setPassword(null); // Don't return password
                    return ResponseEntity.ok(Map.of(
                            "authenticated", true,
                            "user", user
                    ));
                }
            }
            
            return ResponseEntity.ok(Map.of("authenticated", false));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            user.setPassword(null); // Don't return password
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            users.forEach(user -> user.setPassword(null)); // Don't return passwords
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Update user information
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id, 
            @RequestBody Map<String, String> request) {
        try {
            String username = request.get("username");
            String email = request.get("email");
            
            if (username == null || email == null) {
                return ResponseEntity.badRequest().build();
            }
            
            User updatedUser = userService.updateUser(id, username, email);
            updatedUser.setPassword(null); // Don't return password
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Update user password
    @PutMapping("/{id}/password")
    public ResponseEntity<User> updatePassword(
            @PathVariable Long id, 
            @RequestBody Map<String, String> request) {
        try {
            String newPassword = request.get("newPassword");
            
            if (newPassword == null) {
                return ResponseEntity.badRequest().build();
            }
            
            User updatedUser = userService.updatePassword(id, newPassword);
            updatedUser.setPassword(null); // Don't return password
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Check if username exists
    @GetMapping("/check-username/{username}")
    public ResponseEntity<Map<String, Boolean>> checkUsernameExists(@PathVariable String username) {
        try {
            boolean exists = userService.usernameExists(username);
            return ResponseEntity.ok(Map.of("exists", exists));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Check if email exists
    @GetMapping("/check-email/{email}")
    public ResponseEntity<Map<String, Boolean>> checkEmailExists(@PathVariable String email) {
        try {
            boolean exists = userService.emailExists(email);
            return ResponseEntity.ok(Map.of("exists", exists));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
