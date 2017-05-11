package com.abidi.controller;

import com.abidi.dto.UserDTO;
import com.abidi.model.User;
import com.abidi.model.User.UserBuilder;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by houssemabidi on 25/04/17.
 */
public class UserControllerTest extends AbstractControllerTest {

    @Override
    public void setUp() {
        super.setUp();
        this.mockMvc = standaloneSetup(userController).build();
    }

    @Test
    public void testFindAll() throws Exception {

        final User first = new UserBuilder("first1", "last1").build();
        final User second = new UserBuilder("first2", "last2").build();

        when(userService.findAll()).thenReturn(asList(first, second));
        when(modelMapper.map(first, UserDTO.class)).thenReturn(new UserDTO());
        when(modelMapper.map(second, UserDTO.class)).thenReturn(new UserDTO());
        mockMvc.perform(get("/users")).andExpect(status().isOk());
    }

    @Test
    public void testCreate() throws Exception {

        final User user = new UserBuilder("first1", "last1").build();
        final UserDTO userDTO = new UserDTO();
        final String jsonInString = convertObjectToJson(userDTO);
        when(userService.createOrUpdate(any())).thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);
        mockMvc.perform(post("/user/add")
                .contentType(APPLICATION_JSON)
                .content(jsonInString))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {
        final User user = new UserBuilder("first1", "last1").build();
        final UserDTO userDTO = new UserDTO();
        final String jsonInString = convertObjectToJson(userDTO);
        when(userService.createOrUpdate(any())).thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);
        mockMvc.perform(put("/user/update")
                .contentType(APPLICATION_JSON)
                .content(jsonInString))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/deleteUser/1"))
                .andExpect(status().isOk());

    }
}
