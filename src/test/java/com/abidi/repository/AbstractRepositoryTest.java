package com.abidi.repository;

import com.abidi.config.AppConfiguration;
import com.abidi.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by houssemabidi on 19/04/17.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfiguration.class, TestConfig.class})
public class AbstractRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void testInjectionIsOk() {
        assertThat(userRepository).isNotNull();
        assertThat(accountRepository).isNotNull();
    }

}
