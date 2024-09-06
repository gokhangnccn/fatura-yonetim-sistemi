package com.dicore.fatura_yonetim_sistemi.service;

import com.dicore.fatura_yonetim_sistemi.dtos.UserDTO;
import com.dicore.fatura_yonetim_sistemi.entity.User;
import com.dicore.fatura_yonetim_sistemi.exception.CustomException;
import com.dicore.fatura_yonetim_sistemi.mapper.UserMapper;
import com.dicore.fatura_yonetim_sistemi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        if (optionalUser.isPresent()) {
            throw new CustomException("Email already taken!", HttpStatus.CONFLICT);
        }
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new CustomException("User with id: " + userId + " does not exist.", HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserMapper.toEntity(userDTO, user);
            userRepository.save(user);
        } else {
            throw new CustomException("User with id: " + userId + " does not exist.", HttpStatus.NOT_FOUND);
        }
    }
}
