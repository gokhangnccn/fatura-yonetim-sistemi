package com.dicore.fatura_yonetim_sistemi.repository;

import com.dicore.fatura_yonetim_sistemi.entity.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Integer> {

}
