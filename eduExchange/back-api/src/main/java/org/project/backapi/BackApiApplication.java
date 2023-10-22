package org.project.backapi;

import org.project.backapi.domain.Student;
import org.project.backapi.domain.User;
import org.project.backapi.dto.RegisterRequest;
import org.project.backapi.enums.UserRole;
import org.project.backapi.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.project.backapi.enums.UserRole.ADMIN;
import static org.project.backapi.enums.UserRole.EDU;

@SpringBootApplication
public class BackApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackApiApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*")
                ;
            }
        };
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthService service
    ) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .fullname("Admin")
                    .email("admin@mail.com")
                    .password("password")
                    .role(String.valueOf(ADMIN))
                    .pseudo("first user")
                    .build();
            System.out.println("Admin token: " + service.register(admin).getToken());

            var teacher = RegisterRequest.builder()
                    .fullname("Teacher")
                    .email("teacher@mail.com")
                    .password("password")
                    .role(String.valueOf(EDU))
                    .pseudo("first teacher second user")
                    .build();
            System.out.println("User token: " + service.register(teacher).getToken());

            var student =RegisterRequest.builder() //Student.builder()
                    .fullname("Student")
                    .email("student@mail.com")
                    .password("password")
                    .role(String.valueOf(EDU))
                    .pseudo("first student third  user")
                    .build();
            //how to handle specific user request creation ?
            System.out.println("Student token: " + service.register(student).getToken());
        };
    }
}
