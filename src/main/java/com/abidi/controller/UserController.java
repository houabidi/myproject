package com.abidi.controller;

import com.abidi.dto.UserDTO;
import com.abidi.model.User;
import com.abidi.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.util.CollectionUtils.isEmpty;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by houssemabidi on 25/04/17.
 */
@RestController
public class UserController {

    private final static Logger LOGGER = getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(value = "/users", method = GET)
    @ResponseBody
    public ResponseEntity<List<UserDTO>> findAll() {
        LOGGER.info("Getting all users");
        List<User> users = userService.findAll();
        List<UserDTO> userDTOList = users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(toList());
        return isEmpty(userDTOList) ? new ResponseEntity<>(NO_CONTENT) : new ResponseEntity<>(userDTOList, responseHeaders(), OK);
    }

    @RequestMapping(value = "/user/add", method = POST)
    @ResponseBody
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        LOGGER.info("Creating user");
        final User userCreated = userService.createOrUpdate(modelMapper.map(userDTO, User.class));
        return new ResponseEntity<>(modelMapper.map(userCreated, UserDTO.class), CREATED);
    }

    @RequestMapping(value = "/user/update", method = PUT)
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) {
        LOGGER.info("Updating user");
        final User userUpdated = userService.createOrUpdate(modelMapper.map(userDTO, User.class));
        return new ResponseEntity<>(modelMapper.map(userUpdated, UserDTO.class), OK);
    }

    @RequestMapping(value = "deleteUser/{id}", method = DELETE)
    public ResponseEntity<UserDTO> delete(@PathVariable(name = "id") Long id) {
        LOGGER.info("Deleting user with id" + id);
        userService.delete(id);
        return new ResponseEntity<>(OK);
    }

    private HttpHeaders responseHeaders() {
        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(APPLICATION_JSON);
        responseHeaders.add("Access-Control-Allow-Origin", "*");
        responseHeaders.add("Access-Control-Allow-Methods", "*");
        return responseHeaders;
    }
}
