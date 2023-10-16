package br.com.gabrielsousa.todolist.controllers;

import br.com.gabrielsousa.todolist.entities.User;
import br.com.gabrielsousa.todolist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody User user) {
        User userCreated = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

}
