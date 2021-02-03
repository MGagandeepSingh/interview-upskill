package com.sapient.controllers;

import com.sapient.models.User;
import com.sapient.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("userController")
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/user/{userId}")
    @Cacheable(cacheNames = "user", key = "#userId")
    public ResponseEntity<?> getUser(@PathVariable long userId) {

        if (userService.getUser(userId).isPresent()) {
            User user = userService.getUser(userId).get();
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @PostMapping("/user/add")
    @CachePut(cacheNames = "user", key = "#user.id")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok().body("User added successfully.");
    }

    @PutMapping("/user/update")
    @CachePut(cacheNames = "user", key = "#user.id")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok().body("User updated successfully.");
    }

    @DeleteMapping("/user/delete/{userId}")
    @CacheEvict(cacheNames = "user", key = "#userId")
    public ResponseEntity<String> deleteBook(@PathVariable long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().body("User deleted successfully.");
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
