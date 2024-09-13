package com.dicore.fatura_yonetim_sistemi.controller;

import com.dicore.fatura_yonetim_sistemi.dtos.request.ServiceProviderRequestDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.ServiceProviderResponseDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.UserResponseDTO;
import com.dicore.fatura_yonetim_sistemi.enums.ServiceType;
import com.dicore.fatura_yonetim_sistemi.service.ServiceProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/service-providers")
@Tag(name = "Hizmet Sağlayıcı Yönetimi")
public class ServiceProviderController {

    private final ServiceProviderService serviceProviderService;

    @Autowired
    public ServiceProviderController(ServiceProviderService serviceProviderService) {
        this.serviceProviderService = serviceProviderService;
    }

    @Operation(summary = "Tüm hizmet sağlayıcıları getir", description = "Sistemde kayıtlı tüm hizmet sağlayıcıları listeler.")
    @GetMapping
    public ResponseEntity<List<ServiceProviderResponseDTO>> getAllServiceProviders() {
        List<ServiceProviderResponseDTO> serviceProviders = serviceProviderService.getAllServiceProviders();
        return ResponseEntity.ok(serviceProviders);
    }

    @Operation(summary = "ID'ye göre hizmet sağlayıcıyı getir", description = "Belirtilen ID'ye sahip hizmet sağlayıcıyı getirir.")
    @GetMapping("/{id}")
    public ResponseEntity<ServiceProviderResponseDTO> getServiceProviderById(@PathVariable Long id) {
        ServiceProviderResponseDTO serviceProvider = serviceProviderService.getServiceProviderById(id);
        return ResponseEntity.ok(serviceProvider);
    }

    @Operation(summary = "Yeni hizmet sağlayıcı oluştur", description = "Yeni bir hizmet sağlayıcı kaydı oluşturur.")
    @PostMapping
    public ResponseEntity<ServiceProviderResponseDTO> createServiceProvider(@RequestBody ServiceProviderRequestDTO serviceProviderRequestDTO) {
        ServiceProviderResponseDTO createdServiceProvider = serviceProviderService.createServiceProvider(serviceProviderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdServiceProvider);
    }

    @Operation(summary = "Hizmet sağlayıcıyı güncelle", description = "Belirtilen ID'ye sahip hizmet sağlayıcıyı günceller.")
    @PutMapping("/{id}")
    public ResponseEntity<ServiceProviderResponseDTO> updateServiceProvider(@PathVariable Long id, @RequestBody ServiceProviderRequestDTO serviceProviderRequestDTO) {
        ServiceProviderResponseDTO updatedServiceProvider = serviceProviderService.updateServiceProvider(id, serviceProviderRequestDTO);
        return ResponseEntity.ok(updatedServiceProvider);
    }

    @Operation(summary = "Hizmet sağlayıcıyı sil", description = "Belirtilen ID'ye sahip hizmet sağlayıcıyı siler.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteServiceProvider(@PathVariable Long id) {
        serviceProviderService.deleteServiceProvider(id);
        return ResponseEntity.ok("Service provider created successfully.");
    }

    @Operation(summary = "Hizmet türüne göre hizmet sağlayıcıları getir", description = "Belirtilen hizmet türüne göre hizmet sağlayıcıları getirir (Elektrik, Su, İnternet).")
    @GetMapping("/by-type")
    public ResponseEntity<List<ServiceProviderResponseDTO>> getServiceProvidersByType(@RequestParam ServiceType serviceType) {
        List<ServiceProviderResponseDTO> serviceProviders = serviceProviderService.getServiceProvidersByType(serviceType);
        return ResponseEntity.ok(serviceProviders);
    }

    @Operation(summary = "İsimle hizmet sağlayıcı arama", description = "Girilen isme göre hizmet sağlayıcıları arar ve isimle eşleşen sonuçları döndürür.")
    @GetMapping("/search")
    public ResponseEntity<List<ServiceProviderResponseDTO>> searchServiceProvidersByName(@RequestParam String name) {
        List<ServiceProviderResponseDTO> serviceProviders = serviceProviderService.searchServiceProvidersByName(name);
        return ResponseEntity.ok(serviceProviders);
    }

    @Operation(summary = "İsme göre tek hizmet sağlayıcı getir", description = "Belirtilen isme göre tek bir hizmet sağlayıcı getirir.")
    @GetMapping("/by-name")
    public ResponseEntity<ServiceProviderResponseDTO> getServiceProviderByName(@RequestParam String name) {
        ServiceProviderResponseDTO serviceProvider = serviceProviderService.getServiceProviderByName(name);
        return ResponseEntity.ok(serviceProvider);
    }

    @Operation(summary = "Belirli bir hizmet sağlayıcıya abone olan kullanıcıları getir", description = "Belirli bir hizmet sağlayıcıya abone olan kullanıcıları listeler.")
    @GetMapping("/{serviceProviderId}/subscribers")
    public ResponseEntity<List<UserResponseDTO>> getSubscribersByServiceProvider(@PathVariable Long serviceProviderId) {
        List<UserResponseDTO> subscribers = serviceProviderService.getSubscribersByServiceProvider(serviceProviderId);
        return ResponseEntity.ok(subscribers);
    }

    @Operation(summary = "Fiyat aralığına göre hizmet sağlayıcıları getir", description = "Belirtilen birim fiyat aralığında olan hizmet sağlayıcıları listeler.")
    @GetMapping("/by-unit-price")
    public ResponseEntity<List<ServiceProviderResponseDTO>> getServiceProvidersByUnitPrice(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        List<ServiceProviderResponseDTO> serviceProviders = serviceProviderService.getServiceProvidersByUnitPrice(minPrice, maxPrice);
        return ResponseEntity.ok(serviceProviders);
    }
}
