package org.example.usermanagement.account.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.usermanagement.account.domain.Account;
import org.example.usermanagement.account.domain.Gender;
import org.example.usermanagement.account.infrastructure.IAccountRepository;
import org.example.usermanagement.account.presentation.AccountRequest;
import org.example.usermanagement.account.presentation.AccountResponse;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final IAccountRepository accountRepository;
    @Override
    public Account create(AccountRequest req) {

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
    @Transactional
    public void delete(UUID id) {
        accountRepository.findById(id).orElseThrow();
        accountRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Account update(UUID id, AccountRequest newAccount) {

        // Update account
        Optional<Account> optional = accountRepository.findById(id);
        System.out.println(id);
        System.out.println(optional);
        if (optional.isEmpty()) {
            return this.create(newAccount);
        }
        Account a = optional.get();
        a.setUsername(newAccount.getUsername());
        a.setAge(newAccount.getAge());
        a.setGender(Gender.valueOf(newAccount.getGender()));

        return accountRepository.save(a);
    }
}
