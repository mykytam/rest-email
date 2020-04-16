package com.email.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String department;
    private String email;
}
