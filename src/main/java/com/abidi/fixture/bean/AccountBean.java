package com.abidi.fixture.bean;

import com.abidi.dto.AccountDTO;

/**
 * Created by houssemabidi on 02/05/17.
 */
public class AccountBean {

    String balance;
    String currency;
    String rib;

    public AccountBean(AccountDTO accountDTO) {
        balance = accountDTO.getBalance().toString();
        currency = accountDTO.getCurrency();
        rib = accountDTO.getRib();
    }
}
