package org.example.usermanagement.account.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.usermanagement.account.domain.Account;
import org.example.usermanagement.account.service.AccountServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceImpl accountService;
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
    ) {
        Account account = accountService.save(accountRequest);
        AccountResponse response = AccountResponse.builder()
                .id(String.valueOf(account.getId()))
                .username(account.getUsername())
                .gender(account.getGender().name())
                .age(account.getAge())
                .createdAt(account.getCreatedAt().toString())
                .build();
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

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
