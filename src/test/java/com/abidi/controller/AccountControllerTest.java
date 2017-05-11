package com.abidi.controller;

import com.abidi.dto.AccountDTO;
import com.abidi.model.Account;
import com.abidi.model.Account.AccountBuilder;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


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
        final AccountDTO accountDTO = new AccountDTO();
        final String jsonInString = convertObjectToJson(accountDTO);

        when(accountService.createOrUpdate(any())).thenReturn(account);
        when(modelMapper.map(account, AccountDTO.class)).thenReturn(new AccountDTO());
        mockMvc.perform(post("/account/add")
                .contentType(APPLICATION_JSON)
                .content(jsonInString))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {

        final Account account = new Account.AccountBuilder("rib").build();
        final AccountDTO accountDTO = new AccountDTO();
        final String jsonInString = convertObjectToJson(accountDTO);

        when(accountService.createOrUpdate(any())).thenReturn(account);
        when(modelMapper.map(account, AccountDTO.class)).thenReturn(accountDTO);
        mockMvc.perform(put("/account/update")
                .contentType(APPLICATION_JSON)
                .content(jsonInString))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/deleteAccount/1"))
                .andExpect(status().isOk());

    }

    @Test
    public void findByRib() {
        // TODO
    }
}
