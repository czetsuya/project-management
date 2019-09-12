package br.com.greenmile.pms.controller;

import br.com.greenmile.pms.entity.User;
import br.com.greenmile.pms.service.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
@RequestMapping("/api/v1/users")
@Validated
public class UserQueryRestController {

    @Autowired
    private UserQueryService service;

    @GetMapping("/{user-id}")
    @CachePut(value = "users", key = "#id")
    public ResponseEntity<User> findById(@PathVariable("user-id") @Min(value = 1, message = MIN_ID_MESSAGE) Long id) {
        if (!this.service.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = this.service.findById(id);

        if (ObjectUtils.isEmpty(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping
    @CachePut(value = "users")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = this.service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
