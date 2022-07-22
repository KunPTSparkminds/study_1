package net.sparkminds.service;

import net.sparkminds.dto.LoginRequestDto;
import net.sparkminds.dto.LoginResponseDto;
import net.sparkminds.dto.RegisterRequestDto;
import net.sparkminds.entity.Reviewer;

public interface AuthenticationService {
	
	LoginResponseDto login(LoginRequestDto loginRequestDto);
	
	Reviewer register(RegisterRequestDto registerRequestDto);
	
	String logout(String jwt);
    
}
