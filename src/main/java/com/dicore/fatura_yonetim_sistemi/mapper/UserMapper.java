package com.dicore.fatura_yonetim_sistemi.mapper;

import com.dicore.fatura_yonetim_sistemi.dtos.UserDTO;
import com.dicore.fatura_yonetim_sistemi.entity.User;

public class UserMapper {

    public static void toEntity(UserDTO userDTO, User user) {
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
    }
}
