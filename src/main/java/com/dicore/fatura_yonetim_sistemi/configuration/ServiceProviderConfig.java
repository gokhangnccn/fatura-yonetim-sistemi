package com.dicore.fatura_yonetim_sistemi.configuration;

import com.dicore.fatura_yonetim_sistemi.entity.ServiceProvider;
import com.dicore.fatura_yonetim_sistemi.enums.ServiceType;
import com.dicore.fatura_yonetim_sistemi.repository.ServiceProviderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class ServiceProviderConfig {

    @Bean
    public CommandLineRunner initializeServiceProviders(ServiceProviderRepository serviceProviderRepository) {
        return _ -> {
            List<ServiceProvider> providersToSave = new ArrayList<>();

            List<String[]> serviceProviderData = List.of(
                    new String[]{"A Elektrik Firması", "ELECTRICITY", "info@electricity.com", "1.96"},
                    new String[]{"B Su Firması", "WATER", "info@water.com", "1.25"},
                    new String[]{"C İnternet Firması", "INTERNET", "info@internet.com", "2.50"},
                    new String[]{"D Elektrik Firması", "ELECTRICITY", "info@delectricity.com", "2.10"},
                    new String[]{"E Su Firması", "WATER", "info@ewater.com", "1.30"}
            );

            for (String[] providerData : serviceProviderData) {
                String name = providerData[0];
                ServiceType serviceType = ServiceType.valueOf(providerData[1]);
                String contactInfo = providerData[2];
                BigDecimal unitPrice = new BigDecimal(providerData[3]);

                Optional<ServiceProvider> existingProvider = serviceProviderRepository.findServiceProviderByName(name);
                if (existingProvider.isEmpty()) {
                    ServiceProvider provider = new ServiceProvider();
                    provider.setName(name);
                    provider.setServiceType(serviceType);
                    provider.setContactInfo(contactInfo);
                    provider.setUnitPrice(unitPrice);
                    providersToSave.add(provider);
                }
            }

            if (!providersToSave.isEmpty()) {
                serviceProviderRepository.saveAll(providersToSave);
                System.out.println("New service providers added to the database.");
            } else {
                System.out.println("Service providers already exist in the database.");
            }
        };
    }
}
