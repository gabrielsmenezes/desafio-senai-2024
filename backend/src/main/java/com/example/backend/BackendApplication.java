package com.example.backend;

import com.example.backend.domain.Category;
import com.example.backend.domain.FinancialItem;
import com.example.backend.domain.Type;
import com.example.backend.domain.UserSecurity;
import com.example.backend.repository.FinancialItemRepository;
import com.example.backend.repository.UserSecurityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner saveOutcomes(UserSecurityRepository userSecurityRepository, FinancialItemRepository repository, PasswordEncoder passwordEncoder) {
        return (args) -> {
            UserSecurity user = userSecurityRepository.save(new UserSecurity(null, "gabriel", passwordEncoder.encode("123456"), "Gabriel", "mrgabrielsantana@hotmail.com", null));
            repository.save(new FinancialItem(null, "Salary", 6000.0, LocalDate.now(), Category.SALARY, Type.INCOME, user));
            repository.save(new FinancialItem(null, "Buy some grocery", 11.52, LocalDate.now(), Category.FOOD, Type.OUTCOME, user));
            repository.save(new FinancialItem(null, "Dividends", 59.63, LocalDate.now(), Category.DIVIDEND, Type.INCOME, user));
            repository.save(new FinancialItem(null, "Uber", 31.36, LocalDate.now(), Category.TRANSPORT, Type.OUTCOME, user));
            repository.save(new FinancialItem(null, "Medical exam", 350.0, LocalDate.now(), Category.HEALTH, Type.OUTCOME, user));
            repository.save(new FinancialItem(null, "Rent", 666.0, LocalDate.now(), Category.RENT, Type.OUTCOME, user));
            repository.save(new FinancialItem(null, "English class", 89.99, LocalDate.now(), Category.EDUCATION, Type.OUTCOME, user));
            repository.save(new FinancialItem(null, "Cover Bon Jovi", 25.0, LocalDate.now(), Category.LOUNGE, Type.OUTCOME, user));
            repository.save(new FinancialItem(null, "Car fix", 600.0, LocalDate.now(), Category.OTHER, Type.OUTCOME, user));
        };
    }

}
