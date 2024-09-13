package com.dicore.fatura_yonetim_sistemi.mapper;

import com.dicore.fatura_yonetim_sistemi.dtos.request.ServiceProviderRequestDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.ServiceProviderResponseDTO;
import com.dicore.fatura_yonetim_sistemi.entity.ServiceProvider;

public class ServiceProviderMapper {

    public static ServiceProvider toEntity(ServiceProviderRequestDTO serviceProviderRequestDTO) {
        ServiceProvider serviceProvider = new ServiceProvider();
        serviceProvider.setName(serviceProviderRequestDTO.getName());
        serviceProvider.setServiceType(serviceProviderRequestDTO.getServiceType());
        serviceProvider.setContactInfo(serviceProviderRequestDTO.getContactInfo());
        serviceProvider.setUnitPrice(serviceProviderRequestDTO.getUnitPrice());
        return serviceProvider;
    }

    public static ServiceProviderResponseDTO toResponseDTO(ServiceProvider serviceProvider) {
        ServiceProviderResponseDTO dto = new ServiceProviderResponseDTO();
        dto.setId(serviceProvider.getId());
        dto.setName(serviceProvider.getName());
        dto.setServiceType(serviceProvider.getServiceType());
        dto.setContactInfo(serviceProvider.getContactInfo());
        dto.setUnitPrice(serviceProvider.getUnitPrice());
        return dto;
    }
}
