package com.abidi.controller;

import com.abidi.dto.AccountDTO;
import com.abidi.dto.AccountDTO;
import com.abidi.model.Account;
import com.abidi.model.Account.AccountBuilder;
import com.abidi.model.Account;
import org.junit.Test;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;


/**
 * Created by houssemabidi on 25/04/17.
 */
public class AccountControllerTest extends AbstractControllerTest {

    @Override
    public void setUp() {
        super.setUp();
        this.mockMvc = standaloneSetup(accountController).build();
    }

    @Test
    public void testFindAll() throws Exception {

        final Account first = new AccountBuilder("rib1")
                .balance(1000d)
                .currency("EUR")
                .build();

        final Account second = new AccountBuilder("rib2")
                .balance(1050d)
                .currency("EUR")
                .build();

        when(accountService.findAll()).thenReturn(asList(first, second));
        when(modelMapper.map(first, AccountDTO.class)).thenReturn(new AccountDTO());
        when(modelMapper.map(second, AccountDTO.class)).thenReturn(new AccountDTO());
        mockMvc.perform(get("/accounts")).andExpect(status().isOk());

    }

    @Test
    public void testCreate() throws Exception {

        final Account account = new Account.AccountBuilder("rib").build();

        when(accountService.createOrUpdate(any())).thenReturn(account);
        when(modelMapper.map(account, AccountDTO.class)).thenReturn(new AccountDTO());
        mockMvc.perform(post("/account/add")
                .requestAttr("accountDTO",new AccountDTO()))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {

        final Account account = new Account.AccountBuilder("rib").build();

        when(accountService.createOrUpdate(any())).thenReturn(account);
        when(modelMapper.map(account, AccountDTO.class)).thenReturn(new AccountDTO());
        mockMvc.perform(put("/account/update")
                .requestAttr("accountDTO",new AccountDTO()))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/1"))
                .andExpect(status().isOk());

    }
}
