package net.sparkminds.service;

import net.sparkminds.dto.LoginRequestDto;
import net.sparkminds.dto.LoginResponseDto;

public interface LoginService {

    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
