package com.abidi.service;

import com.abidi.model.Account;
import com.abidi.model.User;
import com.abidi.repository.AccountRepository;
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
public class AccountService {

    private static final String SUCCESS = "Success! New balance :";
    private static final String ERROR = "Error";

    @Autowired
    AccountRepository accountRepository;

    /**
     * Method to create or update account
     *
     * @param account the account to save or update
     */
    public void createOrUpdate(Account account) {
        accountRepository.save(account);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    /**
     * Method to withdraw money from a given account
     *
     * @param rib           the account rib
     * @param sumToWithdraw the sum to withdraw
     * @return success or error message
     */
    public String withdraw(String rib, Double sumToWithdraw) {
        final Account a = accountRepository.findByRib(rib);
        if (a != null && a.getBalance() - sumToWithdraw > 0) {
            accountRepository.save(a);
            return SUCCESS + (a.getBalance() - sumToWithdraw);
        }
        return ERROR;
    }

    /**
     * Method to deposit money from a given account
     *
     * @param rib          the account rib
     * @param sumToDeposit the sum to deposit
     * @return success or error message
     */
    public String deposit(String rib, Double sumToDeposit) {
        final Account a = accountRepository.findByRib(rib);
        return a != null ? SUCCESS + (a.getBalance() + sumToDeposit) : ERROR;
    }


    public List<Account> findByUser(User user) {
        return accountRepository.findByUsers(singletonList(user));
    }

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
