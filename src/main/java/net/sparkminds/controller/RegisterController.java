package net.sparkminds.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.sparkminds.dto.RegisterRequestDto;
import net.sparkminds.entity.Reviewer;
import net.sparkminds.service.RegisterService;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;
    
    @PostMapping
    public ResponseEntity<?> Register(@RequestBody RegisterRequestDto registerRequestDto) {
        
        Reviewer reviewer = registerService.register(registerRequestDto);
        
        return ResponseEntity.ok(reviewer.getEmail());
                
    }
}
