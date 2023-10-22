package org.project.backapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project.backapi.enums.UserRole;

import java.time.LocalDate;

@Getter @AllArgsConstructor
@Setter @NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private UserRole userRole;
    private LocalDate createdAt;
}
