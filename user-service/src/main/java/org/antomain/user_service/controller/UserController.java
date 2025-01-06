package org.antomain.user_service.controller;

import lombok.RequiredArgsConstructor;
import org.antomain.user_service.model.dto.clientside.TokenDto;
import org.antomain.user_service.model.dto.clientside.UserAuthDto;
import org.antomain.user_service.model.dto.clientside.UserDto;
import org.antomain.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<TokenDto> registerUser(UserAuthDto userAuthDto) {
        return ResponseEntity.ok(userService.registerUser(userAuthDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> loginUser(UserAuthDto userAuthDto) {
        return ResponseEntity.ok(userService.loginUser(userAuthDto));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

}
