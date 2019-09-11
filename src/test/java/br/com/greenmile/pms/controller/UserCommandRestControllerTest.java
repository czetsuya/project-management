package br.com.greenmile.pms.controller;

import br.com.greenmile.pms.common.dto.UserDTO;
import br.com.greenmile.pms.common.dto.UserResultDTO;
import br.com.greenmile.pms.entity.User;
import br.com.greenmile.pms.service.UserCommandService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserCommandRestController.class)
public class UserCommandRestControllerTest {

    private static final String BASE_URI = "/api/v1";

    @Autowired
    private MockMvc mock;

    @MockBean
    private UserCommandService service;

    @Test
    public void testSave() throws Exception {
        User user = new User("Diane Nguyen", "dianenguyen@gmail.com");
        UserDTO dto = UserDTO.builder().name("Diane Nguyen").email("dianenguyen@gmail.com").build();
        UserResultDTO result = UserResultDTO.builder().id(1L).name("Diane Nguyen").email("dianenguyen@gmail.com").build();
        when(this.service.save(dto)).thenReturn(result);

        mock.perform(post(BASE_URI + "/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {
        User user = new User("Diane Silva", "dianesilva@gmail.com");

        when(this.service.save(UserDTO.builder().name("Diane Nguyen").email("dianenguyen@gmail.com").build()))
                .thenReturn(UserResultDTO.builder().id(1L).name("Diane Nguyen").email("dianenguyen@gmail.com").build());

        when(this.service.update(1L, UserDTO.builder().name("Diane Silva").email("dianesilva@gmail.com").build()))
                .thenReturn(UserResultDTO.builder().id(1L).name("Diane Silva").email("dianesilva@gmail.com").build());

        mock.perform(put(BASE_URI + "/users/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteById() throws Exception {
        mock.perform(delete(BASE_URI + "/users/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
