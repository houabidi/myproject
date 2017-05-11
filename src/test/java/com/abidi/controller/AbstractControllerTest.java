package com.abidi.controller;

import com.abidi.service.AccountService;
import com.abidi.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

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

    /**
     * Method to convert object to json
     *
     * @param obj the object to convert
     * @return the json string
     * @throws JsonProcessingException the exception
     */
    String convertObjectToJson(Object obj) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
