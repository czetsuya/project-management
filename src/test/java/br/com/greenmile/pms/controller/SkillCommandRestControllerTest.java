package br.com.greenmile.pms.controller;

import br.com.greenmile.pms.common.dto.SkillDTO;
import br.com.greenmile.pms.common.dto.SkillResultDTO;
import br.com.greenmile.pms.entity.Skill;
import br.com.greenmile.pms.service.SkillCommandService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SkillCommandRestController.class)
public class SkillCommandRestControllerTest {

    private static final String BASE_URI = "/api/v1";

    @Autowired
    private MockMvc mock;

    @MockBean
    private SkillCommandService service;

    @Test
    public void testUpdate() throws Exception {
        Skill skill = new Skill("TDD");

        when(this.service.save(SkillDTO.builder().name("AWS").build()))
                .thenReturn(SkillResultDTO.builder().id(1L).name("AWS").build());

        when(this.service.update(1L, SkillDTO.builder().name("TDD").build()))
                .thenReturn(SkillResultDTO.builder().id(1L).name("TDD").build());

        mock.perform(put(BASE_URI + "/skills/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(skill)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteById() throws Exception {
        mock.perform(delete(BASE_URI + "/skills/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
