package com.abidi.controller;

import com.abidi.dto.AccountDTO;
import com.abidi.model.Account;
import com.abidi.service.AccountService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by houssemabidi on 25/04/17.
 */
public class AccountController {

    final static Logger LOGGER = getLogger(AccountController.class);

    @Autowired
    AccountService accountService;

    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(value = "/accounts", method = GET)
    @ResponseBody
    public List<AccountDTO> findAll() {
        LOGGER.info("Getting all accounts");
        List<Account> accounts = accountService.findAll();
        return accounts.stream().map(account -> modelMapper.map(account, AccountDTO.class)).collect(toList());
    }

    @RequestMapping(value = "/account/add" , method = POST)
    @ResponseStatus(CREATED)
    @ResponseBody
    public AccountDTO create(@RequestAttribute(name = "accountDTO") AccountDTO accountDTO) {
        LOGGER.info("Creating account");
        final Account accountCreated = accountService.createOrUpdate(modelMapper.map(accountDTO, Account.class));
        return modelMapper.map(accountCreated, AccountDTO.class);
    }

    @RequestMapping(value = "/account/update", method = PUT)
    @ResponseStatus(OK)
    public AccountDTO update(@RequestAttribute(name = "accountDTO") AccountDTO accountDTO) {
        LOGGER.info("Updating account");
        final Account accountUpdated = accountService.createOrUpdate(modelMapper.map(accountDTO, Account.class));
        return modelMapper.map(accountUpdated, AccountDTO.class);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public String delete(@PathVariable(name = "id") Long id) {
        LOGGER.info("Deleting account");
        try {
            accountService.delete(id);
            return "success";
        } catch (Exception e) {
            return "Error";
        }
    }
}
