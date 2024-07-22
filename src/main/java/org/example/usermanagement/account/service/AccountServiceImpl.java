package org.example.usermanagement.account.service;

import lombok.RequiredArgsConstructor;
import org.example.usermanagement.account.domain.Account;
import org.example.usermanagement.account.infrastructure.IAccountRepository;
import org.example.usermanagement.account.presentation.AccountResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private IAccountRepository accountRepository;
    @Override
    public Account save(Account account) {
        // Save account
        return null;
    }

    @Override
    public Account findById(UUID id) {
        // Find account by id
        return null;
    }

    @Override
    public void delete(UUID id) {
        // Delete account by id

    }

    @Override
    public Account update(Account account) {
        // Update account
        return null;
    }
}
