package org.example.usermanagement.account.presentation;

import jakarta.validation.constraints.*;
import lombok.*;

// Requested data for creating an account can be made strict, so no additional data can be added,
// but I wanted to keep it simple.
// It doesn't change the way the application is working but can make life easier for
// frontend developers and can save time on debugging.


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    @NotBlank(message = "First name is mandatory")
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters long")
    private String username;

    @NotNull(message = "Gender is mandatory")
    @Pattern(regexp = "MALE|FEMALE|OTHER", message = "Gender must be one of MALE, FEMALE, OTHER")
    private String gender;

    @NotNull(message = "Age is mandatory")
    @Min(value = 13, message = "Age must be greater than 13")
    @Max(value = 150, message = "Age must be less than 150")
    private Integer age;

    // depends on the implementation
    // private String createdAt;
}
