package com.abidi.service;

import com.abidi.model.Account;
import com.abidi.model.User;
import com.abidi.repository.AccountRepository;
import com.abidi.repository.UserRepository;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

/**
 * Created by houssemabidi on 18/04/17.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    public void createOrUpdate(User user) {
        userRepository.save(user);
    }

    public String linkAccountsToUser(User user, List<Account> accounts) {
        accounts.forEach(user::linkAccount);
        try {
            userRepository.save(user);
            return "Success";
        } catch (Exception e) {
            return "Error" + e.getMessage();
        }
    }

    public double getUserWealth(User user) {
        final List<Account> userAccounts = accountRepository.findByUsers(singletonList(user));
        double wealth = 0;
        for (Account account : userAccounts) {
            wealth += account.getBalance();
        }
        return wealth;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}