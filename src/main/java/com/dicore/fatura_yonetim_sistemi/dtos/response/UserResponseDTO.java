package com.dicore.fatura_yonetim_sistemi.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String address;
}
