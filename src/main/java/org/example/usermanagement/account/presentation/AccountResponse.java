package org.example.usermanagement.account.presentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private String id;
    private String username;
    private String gender;
    private Integer age;
    private String createdAt;
}
