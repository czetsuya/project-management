package br.com.greenmile.pms.controller;

import br.com.greenmile.pms.entity.User;
import br.com.greenmile.pms.service.UserQueryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserQueryRestController.class)
public class UserQueryRestControllerTest {

    private static final String BASE_URI = "/api/v1";

    @Autowired
    private MockMvc mock;

    @MockBean
    private UserQueryService service;

    @Test
    public void testFindById() throws Exception {
        User user = new User(1L, "Herb Kazzaz", "herbkazzaz@gamil.com");
        when(this.service.existsById(1L)).thenReturn(true);
        when(this.service.findById(1L)).thenReturn(user);

        mock.perform(get(BASE_URI + "/users/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(user.getId().intValue())))
                .andExpect(jsonPath("name", is(user.getName())))
                .andExpect(jsonPath("email", is(user.getEmail())));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        User user1 = new User("Herb Kazzaz", "herbkazzaz@gamil.com");
        User user2 = new User("Jessi Glaser", "jessiglaser@gamil.com");
        User user3 = new User("Maurice", "maurice@gamil.com");
        List<User> users = Arrays.asList(user1, user2, user3);
        given(this.service.getAll()).willReturn(users);

        mock.perform(get(BASE_URI + "/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is(user1.getName())))
                .andExpect(jsonPath("$[1].name", is(user2.getName())))
                .andExpect(jsonPath("$[2].name", is(user3.getName())));
    }
}
