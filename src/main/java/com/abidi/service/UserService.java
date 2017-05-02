package com.abidi.service;

import com.abidi.model.Account;
import com.abidi.model.User;
import com.abidi.repository.AccountRepository;
import com.abidi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Collections.singletonList;

/**
 * Created by houssemabidi on 18/04/17.
 */
@Service
@Transactional
public class UserService {

    private static final String SUCCESS = "Success";
    private static final String ERROR = "Error";

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    /**
     * Method to create or update a given user
     *
     * @param user the user to save or update
     * @return the user
     */
    public User createOrUpdate(User user) {
        return userRepository.save(user);
    }

    /**
     * Method to find all users
     *
     * @return all users
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Method to delete user
     *
     * @param id the user id
     */
    public void delete(Long id) {
        userRepository.delete(id);
    }

    /**
     * Method to link accounts to users
     *
     * @param user     the user to link
     * @param accounts the list of accounts to link
     * @return a message indicating the link status
     */
    public String linkAccountsToUser(User user, List<Account> accounts) {
        accounts.forEach(user::linkAccount);
        try {
            userRepository.save(user);
            return SUCCESS;
        } catch (Exception e) {
            return ERROR + e.getMessage();
        }
    }

    /**
     * Method to get user's wealth
     *
     * @param user the user to calculate wealth
     * @return the wealth of a given user
     */
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
