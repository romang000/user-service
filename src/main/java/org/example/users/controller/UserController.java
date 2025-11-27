package org.example.users.controller;

import jakarta.validation.Valid;
import org.example.users.controller.dto.UserDto.UserDto;
import org.example.users.controller.dto.UserDto.UserToSave;
import org.example.users.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.findAll();
    }

    @GetMapping("{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public UserDto saveUser(@Valid @RequestBody UserToSave userToSave) {
        return userService.save(userToSave);
    }
}
