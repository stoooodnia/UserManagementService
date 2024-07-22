package org.example.usermanagement.account.infrastructure;

import org.example.usermanagement.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAccountRepository extends JpaRepository<Account, UUID> {
}
