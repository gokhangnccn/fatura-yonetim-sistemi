package com.dicore.fatura_yonetim_sistemi.configuration;

import com.dicore.fatura_yonetim_sistemi.dtos.request.BillRequestDTO;
import com.dicore.fatura_yonetim_sistemi.entity.Bill;
import com.dicore.fatura_yonetim_sistemi.entity.ServiceProvider;
import com.dicore.fatura_yonetim_sistemi.entity.User;
import com.dicore.fatura_yonetim_sistemi.enums.BillStatus;
import com.dicore.fatura_yonetim_sistemi.mapper.BillMapper;
import com.dicore.fatura_yonetim_sistemi.repository.BillRepository;
import com.dicore.fatura_yonetim_sistemi.repository.ServiceProviderRepository;
import com.dicore.fatura_yonetim_sistemi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Configuration
public class BillConfig {

    @Bean
    public CommandLineRunner initializeBills(BillRepository billRepository, UserRepository userRepository, ServiceProviderRepository serviceProviderRepository) {
        return _ -> {
            Optional<User> user1 = userRepository.findByEmail("ali.yilmaz@mail.com");
            Optional<User> user2 = userRepository.findByEmail("ayse.demir@mail.com");
            Optional<ServiceProvider> provider1 = serviceProviderRepository.findServiceProviderByName("A Elektrik Firması");
            Optional<ServiceProvider> provider2 = serviceProviderRepository.findServiceProviderByName("B Su Firması");

            if (billRepository.count() == 0 && user1.isPresent() && user2.isPresent() && provider1.isPresent() && provider2.isPresent()) {

                BillRequestDTO billRequest1 = new BillRequestDTO();
                billRequest1.setDueDate(LocalDate.now().plusDays(30));
                billRequest1.setStatus(BillStatus.UNPAID);
                billRequest1.setUnitUsage(new BigDecimal("142"));
                billRequest1.setUserId(user1.get().getId());
                billRequest1.setServiceProviderId(provider1.get().getId());

                Bill bill1 = BillMapper.toEntity(billRequest1, user1.get(), provider1.get());

                BillRequestDTO billRequest2 = new BillRequestDTO();
                billRequest2.setDueDate(LocalDate.now().plusDays(15));
                billRequest2.setStatus(BillStatus.UNPAID);
                billRequest2.setUnitUsage(new BigDecimal("42"));
                billRequest2.setUserId(user2.get().getId());
                billRequest2.setServiceProviderId(provider2.get().getId());

                Bill bill2 = BillMapper.toEntity(billRequest2, user2.get(), provider2.get());

                billRepository.saveAll(List.of(bill1, bill2));


                System.out.println("New bills added to the database.");
            } else {
                System.out.println("Bills already exist in the database.");
            }
        };
    }
}
