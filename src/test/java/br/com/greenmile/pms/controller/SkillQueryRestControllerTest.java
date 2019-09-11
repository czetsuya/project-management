package br.com.greenmile.pms.controller;

import br.com.greenmile.pms.entity.Skill;
import br.com.greenmile.pms.service.SkillQueryService;
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
@WebMvcTest(SkillQueryRestController.class)
public class SkillQueryRestControllerTest {

    private static final String BASE_URI = "/api/v1";

    @Autowired
    private MockMvc mock;

    @MockBean
    private SkillQueryService service;

    @Test
    public void testFindById() throws Exception {
        Skill skill = new Skill(3L, "Linux");
        when(this.service.existsById(3L)).thenReturn(true);
        when(this.service.findById(3L)).thenReturn(skill);

        mock.perform(get(BASE_URI + "/skills/{id}", 3)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(skill.getId().intValue())))
                .andExpect(jsonPath("name", is(skill.getName())));
    }

    @Test
    public void testGetAllSkills() throws Exception {
        Skill skill1 = new Skill("AWS");
        Skill skill2 = new Skill("SQL");
        Skill skill3 = new Skill("iText");
        Skill skill4 = new Skill("Scrum");
        List<Skill> skills = Arrays.asList(skill1, skill2, skill3, skill4);
        given(this.service.getAll()).willReturn(skills);

        mock.perform(get(BASE_URI + "/skills")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].name", is(skill1.getName())))
                .andExpect(jsonPath("$[1].name", is(skill2.getName())))
                .andExpect(jsonPath("$[2].name", is(skill3.getName())))
                .andExpect(jsonPath("$[3].name", is(skill4.getName())));
    }
}
