package com.abidi.service;

import com.abidi.model.Account;
import com.abidi.model.User;
import org.junit.Test;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by houssemabidi on 19/04/17.
 */
public class UserServiceTest extends AbstractServiceTest {

    @Test
    public void testLinkAccountsToUser() {
        /**
         * Tested in UserRepositoryTest
         */
    }

    @Test
    public void testGetUserWealth() {
        User user = new User();
        Account account1 = fullAccount1();
        Account account2 = fullAccount2();
        when(accountRepository.findByUsers(singletonList(user))).thenReturn(asList(account1, account2));
        double wealth = userService.getUserWealth(user);
        assertThat(wealth).isEqualTo(4000d);

    }
}
