package com.projectmanager.projectmanager.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.projectmanager.projectmanager.models.LoginUser;
import com.projectmanager.projectmanager.models.User;
import com.projectmanager.projectmanager.repositories.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public User register(User newUser, BindingResult result) {
        Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
        if (potentialUser.isPresent()) {
            result.rejectValue("email", "Unique", "Email is already taken");
        }
        if (!newUser.getPassword().equals(newUser.getConfirm())) {
            result.rejectValue("confirm", "Matches", "The Confirm Password must match Password");
        }
        if (result.hasErrors()) {
            return null;
        }
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);
        return userRepo.save(newUser);
    }

    public User login(@Valid LoginUser newLoginObject, BindingResult result) {
        Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
        if (!potentialUser.isPresent()) {
            result.rejectValue("email", "Unique", "Unknown email!");
            return null;
        }
        User user = potentialUser.get();
        if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Password!");
            return null;
        }
        return user;
    }

    public User findById(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

}
