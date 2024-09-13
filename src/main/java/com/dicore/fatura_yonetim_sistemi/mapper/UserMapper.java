package com.dicore.fatura_yonetim_sistemi.mapper;

import com.dicore.fatura_yonetim_sistemi.dtos.request.UserRequestDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.UserResponseDTO;
import com.dicore.fatura_yonetim_sistemi.entity.User;

public class UserMapper {

    public static void toEntity(UserRequestDTO userRequestDTO, User user) {
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        user.setAddress(userRequestDTO.getAddress());
    }

    public static UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setAddress(user.getAddress());
        return dto;
    }
}
