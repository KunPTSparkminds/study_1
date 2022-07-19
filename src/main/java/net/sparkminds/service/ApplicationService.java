package net.sparkminds.service;

import java.util.List;
import java.util.Optional;

import net.sparkminds.dto.ApplicationRequestDto;
import net.sparkminds.dto.ApplicationResponseDto;

public interface ApplicationService {
    
    Optional<ApplicationResponseDto> getApplicationById(Long id);
	
	List<ApplicationResponseDto> getAllApplication();
	
	void deleteApplicationWithEmail(String email);
	
	void deleteApplicationWithId(Long id);

	ApplicationResponseDto createApplication(ApplicationRequestDto applicationRequestDto);
	
	ApplicationResponseDto updateApplication(ApplicationRequestDto applicationRequestDto, Long id);
}
