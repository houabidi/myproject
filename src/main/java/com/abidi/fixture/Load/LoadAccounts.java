package com.abidi.fixture.load;

import com.abidi.model.Account;
import com.abidi.model.Account.AccountBuilder;
import com.abidi.repository.AccountRepository;

import static java.lang.Double.valueOf;

/**
 * Created by houssemabidi on 02/05/17.
 *
 * Fixture to initiate accounts
 */
public class LoadAccounts extends LoadFixture<Account, Long, AccountRepository> {

    public String balance;
    public String currency;
    public String rib;

    public LoadAccounts() {
        super(AccountRepository.class);
    }

    public boolean insert() {
        final Account account = new AccountBuilder(rib).balance(valueOf(balance)).currency(currency).build();
        return save(account);
    }
}
