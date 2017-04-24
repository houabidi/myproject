package com.abidi.repository;

import com.abidi.model.Account;
import com.abidi.model.User;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by houssemabidi on 18/04/17.
 */
public class UserRepositoryTest extends AbstractRepositoryTest {

    @Test
    public void testFindAllUsers() {
        final List<User> users = userRepository.findAll();
        assertThat(users).isNotNull();
    }

    @Test
    public void testSaveUser() {
        final User user = new User();
        user.setFirstName("firstName4");
        user.setLastName("lastName4");
        assertThat(userRepository.save(user)).isNotNull();
    }

    @Test
    public void testSaveWithAccount() {
        final User user = userRepository.findOne(2L);
        final Account account = accountRepository.findOne(3L);
        user.linkAccount(account);
        userRepository.save(user);
        final User userWithAccount = userRepository.findOne(2L);
        final List<Account> accounts = userWithAccount.getAccounts();
        assertThat(accounts.size()).isEqualTo(1);
    }

    @Test
    public void testUpdateUser() {
        final User user = userRepository.findOne(2L);
        user.setFirstName("updatedFirstName");
        userRepository.save(user);
        final User updatedUser = userRepository.findOne(2L);
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getFirstName()).isEqualTo("updatedFirstName");
    }

    @Test
    public void testDeleteUser() {
        userRepository.delete(3L);
        final User user = userRepository.findOne(3L);
        assertThat(user).isNull();
    }
}
