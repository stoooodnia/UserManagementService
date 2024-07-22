package org.example.usermanagement.account.service;

import lombok.RequiredArgsConstructor;
import org.example.usermanagement.account.domain.Account;
import org.example.usermanagement.account.infrastructure.IAccountRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private IAccountRepository accountRepository;
    @Override
    public void save(Account account) {
        // Save account
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
    public void update(Account account) {
        // Update account
    }
}
