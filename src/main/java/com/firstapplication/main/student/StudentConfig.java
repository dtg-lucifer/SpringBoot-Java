package com.firstapplication.main.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student mariam = new Student("Mariam","student1@gmail.com", LocalDate.of(2005, Month.JANUARY, 19));
            Student alex = new Student("Alex","student2@gmail.com", LocalDate.of(2004, Month.JANUARY, 19));
            Student piush = new Student("Piush","student3@gmail.com", LocalDate.of(2005, Month.JANUARY, 19));
            repository.saveAll(List.of(mariam, alex, piush));
        };
    }
}
