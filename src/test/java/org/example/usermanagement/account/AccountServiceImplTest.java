package org.example.usermanagement.account;

import org.example.usermanagement.account.domain.Account;
import org.example.usermanagement.account.domain.Gender;
import org.example.usermanagement.account.presentation.AccountRequest;
import org.example.usermanagement.account.presentation.AccountUpdateRequest;
import org.example.usermanagement.account.service.AccountServiceImpl;
import org.example.usermanagement.account.infrastructure.IAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AccountServiceImplTest {

    @Mock
    private IAccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAccount() {
        AccountRequest request = AccountRequest.builder()
                .username("testuser")
                .age(30)
                .gender("MALE")
                .build();

        UUID id = UUID.randomUUID();
        Account account = Account.builder()
                .username("testuser")
                .age(30)
                .gender(Gender.MALE)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .id(id)
                .build();

        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account createdAccount = accountService.create(request);

        assertNotNull(createdAccount);
        assertEquals("testuser", createdAccount.getUsername());
        assertEquals(Gender.MALE, createdAccount.getGender());
        assertEquals(30, createdAccount.getAge());
        verify(accountRepository, times(1)).save(any(Account.class));
    }


    @Test
    public void testFindById() {
        UUID id = UUID.randomUUID();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Account account = Account.builder()
                .username("testuser")
                .age(30)
                .gender(Gender.FEMALE)
                .createdAt(timestamp)
                .id(id)
                .build();

        when(accountRepository.findById(id)).thenReturn(Optional.of(account));

        Account foundAccount = accountService.findById(id);

        assertNotNull(foundAccount);
        assertEquals(id, foundAccount.getId());
        assertEquals("testuser", foundAccount.getUsername());
        verify(accountRepository, times(1)).findById(id);
    }

    @Test
    public void testFindByIdNonExistingAccount() {
        UUID id = UUID.randomUUID();

        when(accountRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            accountService.findById(id);
        });

        verify(accountRepository, times(1)).findById(id);
    }

    @Test
    public void testDeleteAccount() {
        UUID id = UUID.randomUUID();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Account account = Account.builder()
                .username("testuser")
                .age(30)
                .gender(Gender.FEMALE)
                .createdAt(timestamp)
                .id(id)
                .build();

        when(accountRepository.findById(id)).thenReturn(Optional.of(account));
        doNothing().when(accountRepository).deleteById(id);

        accountService.delete(id);

        verify(accountRepository, times(1)).findById(id);
        verify(accountRepository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteAccountWithNullId() {
        assertThrows(NoSuchElementException.class, () -> {
            accountService.delete(null);
        });

        verify(accountRepository, never()).findById(any(UUID.class));
        verify(accountRepository, never()).deleteById(any(UUID.class));
    }

    @Test
    public void testDeleteNonExistentAccount() {
        UUID id = UUID.randomUUID();

        when(accountRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            accountService.delete(id);
        });

        verify(accountRepository, times(1)).findById(id);
        verify(accountRepository, never()).deleteById(id);
    }

    @Test
    public void testUpdateAccountAll() {
        UUID id = UUID.randomUUID();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        AccountUpdateRequest updateRequest = AccountUpdateRequest.builder()
                .age(25)
                .gender("FEMALE")
                .build();

        Account existingAccount = Account.builder()
                .username("testuser")
                .age(30)
                .gender(Gender.MALE)
                .createdAt(timestamp)
                .id(id)
                .build();

        Account updatedAccount = Account.builder()
                .username("newuser")
                .age(25)
                .gender(Gender.FEMALE)
                .createdAt(timestamp)
                .id(id)
                .build();

        when(accountRepository.findById(id)).thenReturn(Optional.of(existingAccount));
        when(accountRepository.save(any(Account.class))).thenReturn(updatedAccount);

        Account result = accountService.update(id, updateRequest);

        assertNotNull(result);
        assertEquals("newuser", result.getUsername());
        assertEquals(Gender.FEMALE, result.getGender());
        assertEquals(25, result.getAge());
        assertEquals(timestamp, result.getCreatedAt());
        verify(accountRepository, times(1)).findById(id);
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    public void testUpdateAccountOneField() {
        UUID id = UUID.randomUUID();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        AccountUpdateRequest updateRequest = AccountUpdateRequest.builder()
                .gender("FEMALE")
                .build();

        Account existingAccount = Account.builder()
                .username("testuser")
                .age(30)
                .gender(Gender.MALE)
                .createdAt(timestamp)
                .id(id)
                .build();

        Account updatedAccount = Account.builder()
                .username("newuser")
                .age(30)
                .gender(Gender.FEMALE)
                .createdAt(timestamp)
                .id(id)
                .build();

        when(accountRepository.findById(id)).thenReturn(Optional.of(existingAccount));
        when(accountRepository.save(any(Account.class))).thenReturn(updatedAccount);

        Account result = accountService.update(id, updateRequest);

        assertNotNull(result);
        assertEquals("newuser", result.getUsername());
        assertEquals(Gender.FEMALE, result.getGender());
        assertEquals(30, result.getAge());
        assertEquals(timestamp, result.getCreatedAt());
        verify(accountRepository, times(1)).findById(id);
        verify(accountRepository, times(1)).save(any(Account.class));
    }
};
