package com.toolsofswprojects.tinderellabackend.service;

import com.toolsofswprojects.tinderellabackend.exception.BadRequestException;
import com.toolsofswprojects.tinderellabackend.exception.UserNotFoundException;
import com.toolsofswprojects.tinderellabackend.model.User_t;
import com.toolsofswprojects.tinderellabackend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User_t addUser(User_t user){
        Boolean isEmailExists = userRepo.selectExistsEmail(user.getEmail());
        if (isEmailExists){
            throw new BadRequestException("Eamil" + user.getEmail() + " taken");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User_t.UserRole.ROLE_USER);
        user.setUserCode(UUID.randomUUID().toString());
        return userRepo.save(user);

    }

    public List<User_t> findAllUsers(){
        return userRepo.findAll();
    }

    public User_t updateUser(Long id, User_t user){

        User_t userToBeUpdated = userRepo.findUserById(id).
                orElseThrow(() -> new UserNotFoundException(" User by id " + id + " was not found"));
        if (id != null) userToBeUpdated.setId(id);
        if (user.getName() != null) userToBeUpdated.setName(user.getName());
        if (user.getEmail() != null) userToBeUpdated.setEmail(user.getEmail());
        if (user.getPhone() != null) userToBeUpdated.setPhone(user.getPhone());
        if (user.getImageUrl() != null) userToBeUpdated.setImageUrl(user.getImageUrl());
        if (user.getUserCode() != null) userToBeUpdated.setUserCode(user.getUserCode());

        return userRepo.save(userToBeUpdated);
    }

    public User_t findUserById(Long id){
        return userRepo.findUserById(id).
                orElseThrow(() -> new UserNotFoundException(" User by id " + id + " was not found"));
    }


    public void deleteUser(Long id){
        User_t userToBeDeleted = userRepo.findUserById(id).
                orElseThrow(() -> new UserNotFoundException(" User by id " + id + " was not found"));
        userRepo.deleteUserById(id);
    }
}
