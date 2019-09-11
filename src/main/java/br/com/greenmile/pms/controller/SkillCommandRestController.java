package br.com.greenmile.pms.controller;

import br.com.greenmile.pms.common.dto.SkillDTO;
import br.com.greenmile.pms.common.dto.SkillResultDTO;
import br.com.greenmile.pms.service.SkillCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import static br.com.greenmile.pms.common.util.MessageUtils.MIN_ID_MESSAGE;

@Controller
@RequestMapping("/api/v1/skills")
@Validated
public class SkillCommandRestController {

    @Autowired
    private SkillCommandService service;

    @PutMapping("/{skill-id}")
    public ResponseEntity<SkillResultDTO> update(@PathVariable("skill-id") @Min(value = 1, message = MIN_ID_MESSAGE) Long id,
                                                 @Valid @RequestBody SkillDTO dto) {
        SkillResultDTO result = this.service.update(id, dto);

        if (ObjectUtils.isEmpty(result)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{skill-id}")
    public ResponseEntity<?> delete(@PathVariable("skill-id") @Min(value = 1, message = MIN_ID_MESSAGE) Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.service.delete(id);
        return ResponseEntity.ok().build();
    }
}
