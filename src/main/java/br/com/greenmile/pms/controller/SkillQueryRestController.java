package br.com.greenmile.pms.controller;

import br.com.greenmile.pms.entity.Skill;
import br.com.greenmile.pms.service.SkillQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.Min;
import java.util.List;

import static br.com.greenmile.pms.common.util.MessageUtils.MIN_ID_MESSAGE;

@Controller
@RequestMapping("/api/v1/skills")
@Validated
public class SkillQueryRestController {

    @Autowired
    private SkillQueryService service;

    @GetMapping("/{skill-id}")
    public ResponseEntity<Skill> findById(@PathVariable("skill-id") @Min(value = 1, message = MIN_ID_MESSAGE) Long id) {
        if (!this.service.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Skill skill = this.service.findById(id);

        if (ObjectUtils.isEmpty(skill)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body(skill);
    }

    @GetMapping
    public ResponseEntity<List<Skill>> getAll() {
        List<Skill> skills = this.service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(skills);
    }
}
