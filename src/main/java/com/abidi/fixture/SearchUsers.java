package com.abidi.fixture;

import com.abidi.controller.UserController;
import com.abidi.dto.UserDTO;
import fitlibrary.DoFixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by houssemabidi on 02/05/17.
 */
public class SearchUsers extends DoFixture{

    @Autowired
    private ApplicationContext applicationContext;

    final UserController userController;

    static List<UserDTO> users;

    public SearchUsers() {
        this.userController = applicationContext.getBean(UserController.class);
    }

    /**
     * Method to search users
     *
     * @return true if there's no exception
     */
    public boolean search() {
        try {
            final ResponseEntity<List<UserDTO>> responseEntity = userController.findAll();
            users = responseEntity.getBody();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
