package org.example.usermanagement.account.service;

import lombok.RequiredArgsConstructor;
import org.example.usermanagement.account.domain.Account;
import org.example.usermanagement.account.domain.Gender;
import org.example.usermanagement.account.infrastructure.IAccountRepository;
import org.example.usermanagement.account.presentation.AccountRequest;
import org.example.usermanagement.account.presentation.AccountResponse;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final IAccountRepository accountRepository;
    @Override
    public Account save(AccountRequest req) {

        Account account = Account.builder()
                .username(req.getUsername())
                .age(req.getAge())
                .gender(Gender.valueOf(req.getGender()))
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();

        return accountRepository.save(account);
    }

    @Override
    public Account findById(UUID id) {
        return accountRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(UUID id) {
        // Delete account by id

    }

    @Override
    public Account update(AccountRequest account) {
        // Update account
        return null;
    }
}
