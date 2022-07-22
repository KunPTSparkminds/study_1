package net.sparkminds.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sparkminds.service.LogoutService;


@RestController 
@RequestMapping("/api/logout")
@RequiredArgsConstructor
public class LogoutController {
    
    private final LogoutService logoutService;
    
    @PostMapping
    public ResponseEntity<?> Logout(@RequestHeader("Authorization") String token) {

        logoutService.logout(token.split(" ")[1]);
        return ResponseEntity.ok().body(logoutService.logout(token.split(" ")[1]));

    }

}
