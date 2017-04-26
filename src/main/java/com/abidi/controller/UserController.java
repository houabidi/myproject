package com.abidi.controller;

import com.abidi.dto.UserDTO;
import com.abidi.model.User;
import com.abidi.service.UserService;
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
public class UserController {

    final static Logger LOGGER = getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @RequestMapping(value = "/users", method = GET)
    @ResponseBody
    public List<UserDTO> findAll() {
        LOGGER.info("Getting all users");
        List<User> users = userService.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(toList());
    }

    @RequestMapping(value = "/user/add" , method = POST)
    @ResponseStatus(CREATED)
    @ResponseBody
    public UserDTO create(@RequestAttribute(name = "userDTO") UserDTO userDTO) {
        LOGGER.info("Creating user");
        final User userCreated = userService.createOrUpdate(modelMapper.map(userDTO, User.class));
        return modelMapper.map(userCreated, UserDTO.class);
    }

    @RequestMapping(value = "/user/update", method = PUT)
    @ResponseStatus(OK)
    public UserDTO update(@RequestAttribute(name = "userDTO") UserDTO userDTO) {
        LOGGER.info("Updating user");
        final User userUpdated = userService.createOrUpdate(modelMapper.map(userDTO, User.class));
        return modelMapper.map(userUpdated, UserDTO.class);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public String delete(@PathVariable(name = "id") Long id) {
        LOGGER.info("Deleting user");
        try {
            userService.delete(id);
            return "success";
        } catch (Exception e) {
            return "Error";
        }
    }
}
