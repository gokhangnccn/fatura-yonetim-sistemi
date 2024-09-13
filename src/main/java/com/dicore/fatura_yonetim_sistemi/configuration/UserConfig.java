package com.dicore.fatura_yonetim_sistemi.configuration;

import com.dicore.fatura_yonetim_sistemi.entity.User;
import com.dicore.fatura_yonetim_sistemi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;


@Configuration
public class UserConfig {
    @Bean
    public CommandLineRunner initializeUsers(UserRepository userRepository) {
        return _ -> {

            Optional<User> u1 = userRepository.findByEmail("ali.yilmaz@mail.com");
            Optional<User> u2 = userRepository.findByEmail("ayse.demir@mail.com");
            Optional<User> u3 = userRepository.findByEmail("mehmet.kaya@mail.com");

            if (u1.isEmpty()) {
                User user1 = new User();
                user1.setName("Ali Yılmaz");
                user1.setEmail("ali.yilmaz@mail.com");
                user1.setPassword("aayilmaz123");
                user1.setPhoneNumber("05550004567");
                user1.setAddress("Ankara, Türkiye");
                userRepository.save(user1);
            }

            if (u2.isEmpty()) {
                User user2 = new User();
                user2.setName("Ayşe Demir");
                user2.setEmail("ayse.demir@mail.com");
                user2.setPassword("aydemir123");
                user2.setPhoneNumber("05320001578");
                user2.setAddress("İstanbul, Türkiye");
                userRepository.save(user2);
            }

            if (u3.isEmpty()) {
                User user3 = new User();
                user3.setName("Mehmet Kaya");
                user3.setEmail("mehmet.kaya@mail.com");
                user3.setPassword("mkaya123");
                user3.setPhoneNumber("05300003210");
                user3.setAddress("İzmir, Türkiye");
                userRepository.save(user3);
            }

            if (u1.isPresent() && u2.isPresent() && u3.isPresent()) {
                System.out.println("Users already exist in the database.");
            } else {
                System.out.println("New users added to the database.");
            }
        };
    }
}

