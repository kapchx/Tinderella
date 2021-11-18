package com.toolsofswprojects.tinderellabackend.controller;

import com.toolsofswprojects.tinderellabackend.model.User_t;
import com.toolsofswprojects.tinderellabackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/all")
    public ResponseEntity<List<User_t>> getAllUsers() {
        List<User_t> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<User_t> getUserById(@PathVariable("id") Long id) {
        User_t user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User_t> registerUser(@RequestBody User_t user) {
        User_t newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<User_t> addUser(@RequestBody User_t user) {
        User_t newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<User_t> updateUser(@PathVariable("id")Long id, @RequestBody User_t user) {
        User_t updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
