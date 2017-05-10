package com.abidi.fixture;

import com.abidi.fixture.bean.UserBean;
import fit.RowFixture;

import java.util.List;

import static com.abidi.fixture.SearchUsers.users;
import static java.util.stream.Collectors.toList;

/**
 * Created by houssemabidi on 02/05/17.
 */
public class DisplayUsers extends RowFixture {

    @Override
    public Object[] query() throws Exception {
        List<UserBean> result = users.stream().map(UserBean::new).collect(toList());
        return result.toArray();
    }

    @Override
    public Class<?> getTargetClass() {
        return UserBean.class;
    }
}
