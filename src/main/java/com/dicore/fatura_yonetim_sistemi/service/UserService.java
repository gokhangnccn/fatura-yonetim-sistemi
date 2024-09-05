package com.dicore.fatura_yonetim_sistemi.service;

import com.dicore.fatura_yonetim_sistemi.entity.User;
import com.dicore.fatura_yonetim_sistemi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getALlUsers(){
        return userRepository.findAll();
    }

    public void addNewUser(User user){
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if(optionalUser.isPresent()){
            throw new IllegalStateException("email already taken!");
        }
        userRepository.save(user);
    }

    public void deleteUser(Long userId){
        boolean exists = userRepository.existsById(userId);
        if(!exists){
            throw new IllegalStateException("User with id: " + userId + " does not exists.");
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String name, String email, String phoneNumber, String address){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException(
                "User with id " + userId + " does not exists!"
        ));

        if (name != null && name.length() > 0 && !name.equals(user.getName())) {
            user.setName(name);
        }

        if (email != null && email.length() > 0 && !email.equals(user.getEmail())) {
            Optional<User> userOptional = userRepository.findByEmail(email);
            if (userOptional.isPresent()) {
                throw new IllegalStateException("Email is already taken!");
            }
            user.setEmail(email);
        }

        if (phoneNumber != null && phoneNumber.length() > 0 && !phoneNumber.equals(user.getPhoneNumber())) {
            user.setPhoneNumber(phoneNumber);
        }

        if (address != null && address.length() > 0 && !address.equals(user.getAddress())) {
            user.setAddress(address);
        }
    }




}
