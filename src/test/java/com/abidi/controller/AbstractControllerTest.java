package com.abidi.controller;

import com.abidi.service.AccountService;
import com.abidi.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by houssemabidi on 25/04/17.
 */
@RunWith(SpringRunner.class)
public class AbstractControllerTest {

    @Mock
    AccountService accountService;

    @Mock
    UserService userService;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    UserController userController;

    @InjectMocks
    AccountController accountController;

    MockMvc mockMvc;


    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testInjectionIsOk() {
        assertThat(accountController).isNotNull();
        assertThat(userController).isNotNull();
    }

}
