package com.userservice.controller;

import com.userservice.dto.incoming.UserInDto;
import com.userservice.dto.incoming.UserRegisterDto;
import com.userservice.dto.outgoing.UserOutDto;
import com.userservice.mappers.UserMapper;
import com.userservice.service.JwtService;
import com.userservice.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@RestController
@RequestMapping(path = "/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserRegisterDto user) {
        return userService.addUser(user);
    }

    @PostMapping("/generateToken")
    public ResponseEntity<?> generateToken(@RequestBody UserInDto user, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );

            String token = jwtService.generateToken(user.getEmail());
            UserOutDto userDto = userMapper.userToUserOutDto(userService.getUserByEmail(user.getEmail()));

            Cookie cookie = jwtService.createHttpOnlyCookie("auth_token", token, 86400);
            String userJson = "{\"email\":\"" + userDto.getEmail() +
                    "\",\"role\":\"" + userDto.getRole() +
                    "\",\"id\":\"" + userDto.getId() +
                    "\",\"uuid\":\"" + userDto.getUuid() +
                    "\",\"username\":\"" + userDto.getUsername() + "\"}";
            Cookie userCookie = jwtService.createPublicCookie("user_info", URLEncoder.encode(userJson, StandardCharsets.UTF_8), 86400);

            response.addCookie(cookie);
            response.addCookie(userCookie);

            System.out.println(cookie.getValue());
            System.out.println(userCookie.getValue());

            return ResponseEntity.ok(userDto);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Hibás felhasználónév vagy jelszó!");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        ResponseCookie cookie = ResponseCookie.from("auth_token", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Collections.singletonMap("message", "Logout finished"));
    }
}