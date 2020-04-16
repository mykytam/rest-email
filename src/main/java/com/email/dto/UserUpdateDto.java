package com.email.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateDto {
    private String firstName;
    private String lastName;
    private String department;
    private String email;
}
