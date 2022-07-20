package net.sparkminds.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sparkminds.dto.LoginRequestDto;
import net.sparkminds.service.LoginService;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<?> Login(@RequestBody @Valid LoginRequestDto loginRequestDto) {

        loginService.login(loginRequestDto);

        return ResponseEntity.ok(loginService.login(loginRequestDto));
    }

}
