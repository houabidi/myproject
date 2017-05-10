package com.abidi.fixture;

import com.abidi.fixture.bean.AccountBean;
import fit.RowFixture;

import java.util.List;

import static com.abidi.fixture.SearchAccounts.accounts;
import static java.util.stream.Collectors.toList;

/**
 * Created by houssemabidi on 02/05/17.
 */
public class DisplayAccounts extends RowFixture {

    @Override
    public Object[] query() throws Exception {
        List<AccountBean> result = accounts.stream().map(AccountBean::new).collect(toList());
        return result.toArray();
    }

    @Override
    public Class<?> getTargetClass() {
        return AccountBean.class;
    }
}
