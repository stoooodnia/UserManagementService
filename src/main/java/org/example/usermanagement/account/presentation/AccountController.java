package org.example.usermanagement.account.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.usermanagement.account.domain.Account;
import org.example.usermanagement.account.service.IAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private IAccountService accountService;
    @GetMapping("/{Id}")
    public ResponseEntity<Account> getById (
            @PathVariable String Id
    ) {
        try {
            return ResponseEntity.ok(accountService.findById(UUID.fromString(Id)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("")
    public ResponseEntity<Account> post (
            @Valid
            @RequestBody AccountRequest accountRequest
    ) {}
    @PutMapping("/{Id}") ResponseEntity<Account> put (
            @PathVariable String Id
            @Valid
            @RequestBody AccountRequest accountRequest
    ) {}
    @DeleteMapping("/{Id}") ResponseEntity<Void> delete (
            @PathVariable String Id
    )
    {}
}
