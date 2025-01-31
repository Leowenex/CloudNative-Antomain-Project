package org.antomain.user_service.controller;

import lombok.RequiredArgsConstructor;
import org.antomain.user_service.model.dto.clientside.TokenDto;
import org.antomain.user_service.model.dto.clientside.UserAuthDto;
import org.antomain.user_service.model.dto.clientside.UserDto;
import org.antomain.user_service.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<TokenDto> registerUser(@RequestBody UserAuthDto userAuthDto) {
        return ResponseEntity.ok(userService.registerUser(userAuthDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> loginUser(@RequestBody UserAuthDto userAuthDto) {
        return ResponseEntity.ok(userService.loginUser(userAuthDto));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/whoami")
    public ResponseEntity<UserDto> getCurrentUser() {
        return ResponseEntity.ok(new UserDto(userService.getCurrentUser()));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, path = "/profile-picture")
    public ResponseEntity<String> uploadProfilePicture(@RequestParam("image") final MultipartFile image) {
        return ResponseEntity.ok(userService.uploadProfilePicture(image));
    }
}
