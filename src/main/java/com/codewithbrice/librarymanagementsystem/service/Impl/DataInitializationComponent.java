package com.codewithbrice.librarymanagementsystem.service.Impl;

import com.codewithbrice.librarymanagementsystem.domain.AuthProvider;
import com.codewithbrice.librarymanagementsystem.domain.UserRole;
import com.codewithbrice.librarymanagementsystem.modal.User;
import com.codewithbrice.librarymanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializationComponent implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        initializeAdminUser();
    }

    private void initializeAdminUser() {
        String adminEmail = "briceguifo@admin.com";
        String adminPassword = "codewithbrice";

        if(userRepository.findByEmail(adminEmail)==null) {
            User user= User.builder()
                    .password(passwordEncoder.encode(adminPassword))
                    .email(adminEmail)
                    .fullName("Code With Brice")
                    .role(UserRole.ROLE_ADMIN)
                    .authProvider(AuthProvider.LOCAL)
                    .build();

            User admin=userRepository.save(user);
        }
    }
}
