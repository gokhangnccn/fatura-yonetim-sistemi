package com.dicore.fatura_yonetim_sistemi.service;

import com.dicore.fatura_yonetim_sistemi.dtos.request.ServiceProviderRequestDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.ServiceProviderResponseDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.UserResponseDTO;
import com.dicore.fatura_yonetim_sistemi.entity.ServiceProvider;
import com.dicore.fatura_yonetim_sistemi.entity.User;
import com.dicore.fatura_yonetim_sistemi.enums.ServiceType;
import com.dicore.fatura_yonetim_sistemi.exception.EntityAlreadyExistsException;
import com.dicore.fatura_yonetim_sistemi.exception.EntityNotFoundException;
import com.dicore.fatura_yonetim_sistemi.mapper.ServiceProviderMapper;
import com.dicore.fatura_yonetim_sistemi.mapper.UserMapper;
import com.dicore.fatura_yonetim_sistemi.repository.ServiceProviderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceProviderService {

    private final ServiceProviderRepository serviceProviderRepository;

    @Autowired
    public ServiceProviderService(ServiceProviderRepository serviceProviderRepository) {
        this.serviceProviderRepository = serviceProviderRepository;
    }

    public List<ServiceProviderResponseDTO> getAllServiceProviders() {
        List<ServiceProvider> serviceProviders = serviceProviderRepository.findAll();
        return serviceProviders.stream()
                .map(ServiceProviderMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ServiceProviderResponseDTO getServiceProviderById(Long id) {
        ServiceProvider serviceProvider = serviceProviderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ServiceProvider", id));
        return ServiceProviderMapper.toResponseDTO(serviceProvider);
    }

    @Transactional
    public ServiceProviderResponseDTO createServiceProvider(ServiceProviderRequestDTO serviceProviderRequestDTO) {
        Optional<ServiceProvider> existingProvider = serviceProviderRepository.findServiceProviderByName(serviceProviderRequestDTO.getName());
        if (existingProvider.isPresent()) {
            throw new EntityAlreadyExistsException("ServiceProvider", serviceProviderRequestDTO.getName());
        }

        ServiceProvider serviceProvider = ServiceProviderMapper.toEntity(serviceProviderRequestDTO);
        ServiceProvider savedServiceProvider = serviceProviderRepository.save(serviceProvider);
        return ServiceProviderMapper.toResponseDTO(savedServiceProvider);
    }

    @Transactional
    public ServiceProviderResponseDTO updateServiceProvider(Long id, ServiceProviderRequestDTO serviceProviderRequestDTO) {
        ServiceProvider existingServiceProvider = serviceProviderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ServiceProvider", id));

        if (!existingServiceProvider.getName().equals(serviceProviderRequestDTO.getName())) {
            Optional<ServiceProvider> existingProviderWithName = serviceProviderRepository.findServiceProviderByName(serviceProviderRequestDTO.getName());
            if (existingProviderWithName.isPresent()) {
                throw new EntityAlreadyExistsException("ServiceProvider", serviceProviderRequestDTO.getName());
            }
        }

        existingServiceProvider.setName(serviceProviderRequestDTO.getName());
        existingServiceProvider.setServiceType(serviceProviderRequestDTO.getServiceType());
        existingServiceProvider.setContactInfo(serviceProviderRequestDTO.getContactInfo());
        existingServiceProvider.setUnitPrice(serviceProviderRequestDTO.getUnitPrice());

        ServiceProvider updatedServiceProvider = serviceProviderRepository.save(existingServiceProvider);
        return ServiceProviderMapper.toResponseDTO(updatedServiceProvider);
    }

    @Transactional
    public void deleteServiceProvider(Long id) {
        if (!serviceProviderRepository.existsById(id)) {
            throw new EntityNotFoundException("ServiceProvider", id);
        }
        serviceProviderRepository.deleteById(id);
    }

    public List<ServiceProviderResponseDTO> getServiceProvidersByType(ServiceType serviceType) {
        List<ServiceProvider> serviceProviders = serviceProviderRepository.findByServiceType(serviceType);
        return serviceProviders.stream()
                .map(ServiceProviderMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<ServiceProviderResponseDTO> searchServiceProvidersByName(String name) {
        List<ServiceProvider> serviceProviders = serviceProviderRepository.findByNameContainingIgnoreCase(name);
        return serviceProviders.stream()
                .map(ServiceProviderMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<UserResponseDTO> getSubscribersByServiceProvider(Long id) {
        List<User> usersByServiceProviderId = serviceProviderRepository.findUsersByServiceProviderId(id);
        return usersByServiceProviderId.stream()
                .map(UserMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ServiceProviderResponseDTO getServiceProviderByName(String name) {
        ServiceProvider serviceProvider = serviceProviderRepository.findServiceProviderByName(name)
                .orElseThrow(() -> new EntityNotFoundException("ServiceProvider", name));
        return ServiceProviderMapper.toResponseDTO(serviceProvider);
    }

    public List<ServiceProviderResponseDTO> getServiceProvidersByUnitPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        List<ServiceProvider> serviceProviders = serviceProviderRepository.findByUnitPriceBetween(minPrice, maxPrice);
        return serviceProviders.stream()
                .map(ServiceProviderMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}