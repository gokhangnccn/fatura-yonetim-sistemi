package com.dicore.fatura_yonetim_sistemi.repository;

import com.dicore.fatura_yonetim_sistemi.entity.ServiceProvider;
import com.dicore.fatura_yonetim_sistemi.entity.User;
import com.dicore.fatura_yonetim_sistemi.enums.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {

    List<ServiceProvider> findByServiceType(ServiceType serviceType);

    List<ServiceProvider> findByNameContainingIgnoreCase(String name);

    List<ServiceProvider> findByUnitPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);


    @Query("SELECT s FROM ServiceProvider s WHERE s.name = ?1")
    Optional<ServiceProvider> findServiceProviderByName(String name);

    @Query("SELECT DISTINCT u FROM User u " +
            "JOIN Bill b ON u.id = b.user.id " +
            "WHERE b.serviceProvider.id = :serviceProviderId")
    List<User> findUsersByServiceProviderId(@Param("serviceProviderId") Long serviceProviderId);


}
