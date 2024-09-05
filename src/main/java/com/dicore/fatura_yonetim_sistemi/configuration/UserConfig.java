package com.dicore.fatura_yonetim_sistemi.configuration;

import com.dicore.fatura_yonetim_sistemi.entity.User;
import com.dicore.fatura_yonetim_sistemi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User u1 = new User(
                    "Ali Veli",
                    "ali.veli@example.com",
                    "password123",
                    "05330000001",
                    "Ankara, Türkiye");

            User u2 = new User(
                    "Ayşe Yılmaz",
                    "ayse.yilmaz@example.com",
                    "password456",
                    "05330000002",
                    "İstanbul, Türkiye");

            User u3 = new User(
                    "Mehmet Can",
                    "mehmet.can@example.com",
                    "password789",
                    "05330000003",
                    "İzmir, Türkiye");

            List<User> users = List.of(u1, u2, u3);

            for (User user : users) {
                if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
                    userRepository.save(user);
                }
            }
        };
    }
}
