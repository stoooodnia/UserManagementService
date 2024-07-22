package org.example.usermanagement.account.service;

import org.example.usermanagement.account.domain.Account;
import org.example.usermanagement.account.presentation.AccountRequest;

import java.util.UUID;

public interface IAccountService {
    Account save(AccountRequest account);
    Account findById(UUID id);
    void delete(UUID id);
    Account update(AccountRequest account);
}
