package net.sparkminds.service;

import net.sparkminds.dto.RegisterRequestDto;
import net.sparkminds.entity.Reviewer;

public interface RegisterService {
    Reviewer register(RegisterRequestDto registerRequestDto);
}
