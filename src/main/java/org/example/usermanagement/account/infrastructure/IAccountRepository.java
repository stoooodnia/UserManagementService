package org.example.usermanagement.account.infrastructure;

import org.example.usermanagement.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAccountRepository extends JpaRepository<Account, UUID> {
}
