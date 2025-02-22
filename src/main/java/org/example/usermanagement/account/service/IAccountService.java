package org.example.usermanagement.account.service;

import org.example.usermanagement.account.domain.Account;
import org.example.usermanagement.account.presentation.AccountRequest;
import org.example.usermanagement.account.presentation.AccountUpdateRequest;

import java.util.UUID;

public interface IAccountService {
    Account create(AccountRequest account);
    Account findById(UUID id);
    void delete(UUID id);
    Account update(UUID id, AccountUpdateRequest newAccount);
}
