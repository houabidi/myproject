package com.abidi.fixture;

import com.abidi.controller.AccountController;
import com.abidi.dto.AccountDTO;
import fitlibrary.DoFixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Created by houssemabidi on 02/05/17.
 */
public class SearchAccounts extends DoFixture{

    @Autowired
    private ApplicationContext applicationContext;

    final AccountController accountController;

    static List<AccountDTO> accounts;

    public SearchAccounts() {
        this.accountController = applicationContext.getBean(AccountController.class);
    }

    /**
     * Method to search accounts
     *
     * @return true if there's no exception
     */
    public boolean search() {
        try {
            accounts = accountController.findAll();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
