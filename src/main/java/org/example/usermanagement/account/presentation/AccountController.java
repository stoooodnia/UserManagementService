package org.example.usermanagement.account.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.usermanagement.account.domain.Account;
import org.example.usermanagement.account.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private IAccountService accountService;
    @GetMapping("/{Id}")
    public ResponseEntity<AccountResponse> getById (
            @PathVariable UUID Id
    ) {
        try {
            Account account = accountService.findById(Id);
            AccountResponse response = AccountResponse.builder()
                    .id(String.valueOf(account.getId()))
                    .username(account.getUsername())
                    .gender(account.getGender().name())
                    .age(account.getAge())
                    .createdAt(account.getCreatedAt().toString())
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("")
    public ResponseEntity<AccountResponse> post (
            @Valid
            @RequestBody AccountRequest accountRequest
    ) {return null;}
    @PutMapping("/{Id}") ResponseEntity<AccountResponse> put (
            @PathVariable UUID Id,
            @Valid
            @RequestBody AccountRequest accountRequest
    ) {return null;}
    @DeleteMapping("/{Id}") ResponseEntity<Void> delete (
            @PathVariable UUID Id
    )
    {return null;}
}
