package com.abidi.service;

import com.abidi.config.AppConfiguration;
import com.abidi.model.Account;
import com.abidi.model.User;
import com.abidi.repository.AccountRepository;
import com.abidi.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.Currency;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by houssemabidi on 19/04/17.
 */
@RunWith(SpringRunner.class)
public class AbstractServiceTest {

    @InjectMocks
    UserService userService;

    @InjectMocks
    AccountService accountService;

    @Mock
    UserRepository userRepository;

    @Mock
    AccountRepository accountRepository;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testInjectionIsOk() {
        assertThat(userService).isNotNull();
        assertThat(accountService).isNotNull();
    }

    public Account fullAccount1(){
        final User user = new User();
        user.setLastName("lastName1");
        final Account account = new Account();
        account.setCurrency(Currency.getInstance("EUR"));
        account.setBalance(1500d);
        account.setUsers(singletonList(user));
        account.setRib("");
        return account;
    }

    public Account fullAccount2(){
        final User user = new User();
        user.setLastName("lastName2");
        final Account account = new Account();
        account.setCurrency(Currency.getInstance("EUR"));
        account.setBalance(2500d);
        account.setUsers(singletonList(user));
        account.setRib("");
        return account;
    }

}
