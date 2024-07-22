package org.example.usermanagement.account.service;

import org.example.usermanagement.account.domain.Account;

import java.util.UUID;

public interface IAccountService {
    Account save(Account account);
    Account findById(UUID id);
    void delete(UUID id);
    Account update(Account account);
}
