package com.dicore.fatura_yonetim_sistemi.service;

import com.dicore.fatura_yonetim_sistemi.dtos.request.UserRequestDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.UserResponseDTO;
import com.dicore.fatura_yonetim_sistemi.entity.User;
import com.dicore.fatura_yonetim_sistemi.exception.EntityAlreadyExistsException;
import com.dicore.fatura_yonetim_sistemi.exception.EntityNotFoundException;
import com.dicore.fatura_yonetim_sistemi.mapper.UserMapper;
import com.dicore.fatura_yonetim_sistemi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void addNewUser(UserRequestDTO userRequestDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(userRequestDTO.getEmail());
        if (optionalUser.isPresent()) {
            throw new EntityAlreadyExistsException("User", userRequestDTO.getEmail());
        }

        User user = new User();
        UserMapper.toEntity(userRequestDTO, user);
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User", userId);
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User", userId));

        if (!user.getEmail().equals(userRequestDTO.getEmail())) {
            Optional<User> existingUserWithEmail = userRepository.findByEmail(userRequestDTO.getEmail());
            if (existingUserWithEmail.isPresent()) {
                throw new EntityAlreadyExistsException("User", userRequestDTO.getEmail());
            }
        }

        UserMapper.toEntity(userRequestDTO, user);
        userRepository.save(user);
    }

    public UserResponseDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User", userId));
        return UserMapper.toResponseDTO(user);
    }
}
