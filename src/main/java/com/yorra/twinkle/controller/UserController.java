package com.yorra.twinkle.controller;

import com.yorra.twinkle.model.dtos.Login;
import com.yorra.twinkle.model.entities.User;
import com.yorra.twinkle.repository.UserRepository;
import com.yorra.twinkle.security.AuthoritiesConstants;
import com.yorra.twinkle.security.jwt.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1")
@Slf4j
public class UserController {

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;
    private final JwtProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid Login login) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                login.getPhone(),
                login.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("AUTHORIZATION_HEADER", "Bearer " + jwt);
        return new ResponseEntity<>(jwt, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid Login login) {
        User user = modelMapper.map(login, User.class);
        user.setId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(login.getPassword()));
        user.setRole(AuthoritiesConstants.USER);
        userRepository.save(user);
    }
}
