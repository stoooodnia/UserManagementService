package org.example.usermanagement.account.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.usermanagement.account.domain.Account;
import org.example.usermanagement.account.service.AccountServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServiceImpl accountService;
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getById (
            @PathVariable UUID id
    ) {
        try {
            Account account = accountService.findById(id);
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
        try {
            Account account = accountService.create(accountRequest);
            AccountResponse response = AccountResponse.builder()
                    .id(String.valueOf(account.getId()))
                    .username(account.getUsername())
                    .gender(account.getGender().name())
                    .age(account.getAge())
                    .createdAt(account.getCreatedAt().toString())
                    .build();
            return ResponseEntity.created(URI.create("/accounts/" + account.getId())).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(409).build();
        }
    }


    // This is a global exception handler for validation errors just for making the response prettier :>
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

    @PatchMapping("/{id}") ResponseEntity<AccountResponse> put (
            @PathVariable UUID id,
            @Valid
            @RequestBody AccountUpdateRequest updateRequest
    ) {
        try {
            Account account = accountService.update(id, updateRequest);
            AccountResponse response = AccountResponse.builder()
                    .id(String.valueOf(account.getId()))
                    .username(account.getUsername())
                    .gender(account.getGender().name())
                    .age(account.getAge())
                    .createdAt(account.getCreatedAt().toString())
                    .build();
//            return ResponseEntity.noContent().build();  // generally update should return 204 noContent,
            return ResponseEntity.ok(response); // but I choose a convention to make life easier for user, and return the updated resource
                                                //  so that user don't need to make an additional request to get the updated resource
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}") ResponseEntity<Void> delete (
            @PathVariable UUID id
    )
    {
        try {
            accountService.delete(id);
            // could return ok with deleted entity but choose to stay with noContent.
            // as in update, it depends on convention.
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
