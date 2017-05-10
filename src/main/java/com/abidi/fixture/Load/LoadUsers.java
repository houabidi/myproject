package com.abidi.fixture.load;

import com.abidi.model.User;
import com.abidi.repository.UserRepository;

/**
 * Created by houssemabidi on 02/05/17.
 */
public class LoadUsers extends LoadFixture<User, Long, UserRepository> {

    String firstName;
    String lastName;

    public LoadUsers() {
        super(UserRepository.class);
    }

    public boolean insert() {
        User user = new User.UserBuilder(firstName, lastName).build();
        return save(user);
    }
}
