package com.abidi.service;

import com.abidi.model.Account;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by houssemabidi on 19/04/17.
 */
public class AccountServiceTest extends AbstractServiceTest {

    @Test
    public void testFindAll() {
        Account account1 = fullAccount1();
        Account account2 = fullAccount1();
        when(accountRepository.findAll()).thenReturn(asList(account1, account2));
        final List<Account> accounts = accountService.findAll();
        assertThat(accounts.size()).isEqualTo(2);
    }

    @Test
    public void testWithdraw() {
        when(accountRepository.findByRib("rib")).thenReturn(fullAccount1());
        final String success = accountService.withdraw("rib", 1400d);
        final String error = accountService.withdraw("rib", 1600d);
        assertThat(success).isEqualTo("Success! New balance :100.0");
        assertThat(error).isEqualTo("Error");

    }

    @Test
    public void testDeposit() {
        when(accountRepository.findByRib("rib")).thenReturn(fullAccount1());
        final String success = accountService.deposit("rib", 100d);
        assertThat(success).isEqualTo("Success! New balance :1600.0");

    }

    @Test
    public void testFindByUser(){


    }

}
