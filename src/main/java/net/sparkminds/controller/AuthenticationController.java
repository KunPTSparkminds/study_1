package net.sparkminds.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sparkminds.dto.LoginRequestDto;
import net.sparkminds.dto.RegisterRequestDto;
import net.sparkminds.entity.Reviewer;
import net.sparkminds.service.AuthenticationService;


@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;
	

	@PostMapping("/common/login")
    public ResponseEntity<?> Login(@RequestBody @Valid LoginRequestDto loginRequestDto) {

		authenticationService.login(loginRequestDto);
        return ResponseEntity.ok(authenticationService.login(loginRequestDto));
    }
	
	@PostMapping("/common/register")
    public ResponseEntity<?> Register(@RequestBody @Valid RegisterRequestDto registerRequestDto) {

        Reviewer reviewer = authenticationService.register(registerRequestDto);

        return ResponseEntity.ok(reviewer.getEmail());

    }
	
	@PostMapping("/public/logout")
    public ResponseEntity<?> Logout(@RequestHeader("Authorization") String token) {

		authenticationService.logout(token.split(" ")[1]);
        return ResponseEntity.ok().body(authenticationService.logout(token.split(" ")[1]));

    }
}
