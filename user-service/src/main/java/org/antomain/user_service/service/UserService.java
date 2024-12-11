package org.antomain.user_service.service;

import lombok.RequiredArgsConstructor;
import org.antomain.user_service.model.dto.clientside.TokenDto;
import org.antomain.user_service.model.dto.clientside.UserAuthDto;
import org.antomain.user_service.model.entity.User;
import org.antomain.user_service.repository.UserRepository;
import org.antomain.user_service.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public TokenDto registerUser(UserAuthDto userAuthDto) {
        if (userRepository.existsByUsername(userAuthDto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The given username is already in use!");
        }
        userRepository.save(userAuthDto.toUser(passwordEncoder));
        return loginUser(userAuthDto);
    }

    public TokenDto loginUser(UserAuthDto userAuthDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    userAuthDto.toAuthenticationToken()
            ); // This will throw an exception if the credentials are invalid

            if (!authentication.isAuthenticated()) {
                throw new BadCredentialsException("The given credentials are invalid!");
            }

            String token = jwtService.generateToken(userAuthDto.getUsername());
            return new TokenDto(token);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials", e);
        }
    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByUsername(username);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
    }
}
