package br.com.greenmile.pms.controller;

import br.com.greenmile.pms.common.dto.SkillDTO;
import br.com.greenmile.pms.common.dto.SkillResultDTO;
import br.com.greenmile.pms.common.dto.UserDTO;
import br.com.greenmile.pms.common.dto.UserResultDTO;
import br.com.greenmile.pms.service.UserCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
@RequestMapping("/api/v1/users")
@Validated
public class UserCommandRestController {

    @Autowired
    private UserCommandService service;

    @PostMapping
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity<UserResultDTO> save(@Valid @RequestBody UserDTO dto) {
        UserResultDTO result = this.service.save(dto);

        if (ObjectUtils.isEmpty(result)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/{user-id}/skills")
    @CacheEvict(value = "skils", allEntries = true, key = "#id")
    public ResponseEntity<SkillResultDTO> save(@PathVariable("user-id") @Min(value = 1, message = MIN_ID_MESSAGE) Long id,
                                              @Valid @RequestBody SkillDTO dto) {
        SkillResultDTO result = this.service.saveSkill(id, dto);

        if (ObjectUtils.isEmpty(result)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{user-id}")
    public ResponseEntity<UserResultDTO> update(@PathVariable("user-id") @Min(value = 1, message = MIN_ID_MESSAGE) Long id,
                                                @Valid @RequestBody UserDTO dto) {
        UserResultDTO result = this.service.update(id, dto);

        if (ObjectUtils.isEmpty(result)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<?> delete(@PathVariable("user-id") @Min(value = 1, message = MIN_ID_MESSAGE) Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.service.delete(id);
        return ResponseEntity.ok().build();
    }
}
