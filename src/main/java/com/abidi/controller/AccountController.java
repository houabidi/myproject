package com.abidi.controller;

import com.abidi.dto.AccountDTO;
import com.abidi.model.Account;
import com.abidi.service.AccountService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.util.CollectionUtils.isEmpty;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by houssemabidi on 25/04/17.
 */
@RestController
public class AccountController {

    private final static Logger LOGGER = getLogger(AccountController.class);

    @Autowired
    AccountService accountService;

    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(value = "/accounts", method = GET)
    @ResponseBody
    public ResponseEntity<List<AccountDTO>> findAll() {
        LOGGER.info("Getting all accounts");
        List<Account> accounts = accountService.findAll();
        List<AccountDTO> accountDTOList = accounts.stream().map(account -> modelMapper.map(account, AccountDTO.class))
                .collect(toList());
        return isEmpty(accountDTOList) ? new ResponseEntity<>(NO_CONTENT) : new ResponseEntity<>(accountDTOList, OK);

    }

    @RequestMapping(value = "/accountByRib/{rib}", method = GET)
    @ResponseBody
    public ResponseEntity<AccountDTO> findByRib(@PathVariable(name = "rib") String rib) {
        final Account account = accountService.findByRib(rib);
        return account != null ? new ResponseEntity<>(modelMapper.map(account, AccountDTO.class), OK) :
                new ResponseEntity<>(NOT_FOUND);
    }

    @RequestMapping(value = "/account/add", method = POST)
    @ResponseBody
    public ResponseEntity<AccountDTO> create(@RequestBody AccountDTO accountDTO) {
        LOGGER.info("Creating account");
        final Account accountCreated = accountService.createOrUpdate(modelMapper.map(accountDTO, Account.class));
        return new ResponseEntity<>(modelMapper.map(accountCreated, AccountDTO.class), CREATED);
    }

    @RequestMapping(value = "/account/update", method = PUT)
    public ResponseEntity<AccountDTO> update(@RequestBody AccountDTO accountDTO) {
        LOGGER.info("Updating account");
        final Account accountUpdated = accountService.createOrUpdate(modelMapper.map(accountDTO, Account.class));
        return new ResponseEntity<>(modelMapper.map(accountUpdated, AccountDTO.class), OK);
    }

    @RequestMapping(value = "/deleteAccount/{id}", method = DELETE)
    public ResponseEntity<AccountDTO> delete(@PathVariable(name = "id") Long id) {
        LOGGER.info("Deleting account");
        accountService.delete(id);
        return new ResponseEntity<>(OK);
    }
}
