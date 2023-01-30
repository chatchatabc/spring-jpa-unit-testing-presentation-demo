package org.spring.unit.testing.presentation.user.application.common.dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String email;
}
