package com.abidi.repository;

import com.abidi.model.Account;
import com.abidi.model.User;
import org.junit.Test;

import java.util.Currency;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by houssemabidi on 19/04/17.
 */
public class AccountRepoitoryTest extends AbstractRepositoryTest {

    @Test
    public void testFindAllAccounts() {
        final List<Account> accounts = accountRepository.findAll();
        assertThat(accounts).isNotNull();
    }

    @Test
    public void testSaveAccount() {
        final Account account = new Account();
        account.setBalance(1500d);
        account.setCurrency(Currency.getInstance("EUR"));
        assertThat(accountRepository.save(account)).isNotNull();
    }

    @Test
    public void testUpdateAccount() {
        final Account account = accountRepository.findOne(1L);
        account.setBalance(1500d);
        accountRepository.save(account);
        final Account updatedAccount = accountRepository.findOne(1L);
        assertThat(updatedAccount).isNotNull();
        assertThat(updatedAccount.getBalance()).isEqualTo(1500d);
    }

    @Test
    public void testDeleteAccount() {
        accountRepository.delete(1L);
        final Account account = accountRepository.findOne(1L);
        assertThat(account).isNull();
    }

    @Test
    public void testFindByRib() {
        final Account account = accountRepository.findByRib("FR7630001007941234567890187");
        assertThat(account).isNotNull();
    }

    @Test
    public void testFindByUsers(){
        final User user = userRepository.findOne(1L);
        final List<Account> accountsByUsers = accountRepository.findByUsers(singletonList(user));
        assertThat(accountsByUsers.size()).isEqualTo(2);
    }

}
