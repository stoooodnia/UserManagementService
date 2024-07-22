package org.example.usermanagement.account.presentation;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdateRequest {
    
    @Pattern(regexp = "MALE|FEMALE|OTHER", message = "Gender must be one of MALE, FEMALE, OTHER")
    private String gender;

    @Min(value = 13, message = "Age must be greater than 13")
    @Max(value = 150, message = "Age must be less than 150")
    private Integer age;

    // depends on the implementation
    // private String createdAt;
}