package com.abidi.fixture.bean;

import com.abidi.dto.UserDTO;

/**
 * Created by houssemabidi on 02/05/17.
 */
public class UserBean {

    String firstName;
    String lastName;

    public UserBean(UserDTO userDTO) {
        firstName = userDTO.getFirstName();
        lastName = userDTO.getLastName();
    }
}
