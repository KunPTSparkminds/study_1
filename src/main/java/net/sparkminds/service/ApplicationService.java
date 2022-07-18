package net.sparkminds.service;

import java.util.List;

import net.sparkminds.dto.ApplicationRequestDto;
import net.sparkminds.dto.ApplicationResponseDto;

public interface ApplicationService {
    
    List<ApplicationResponseDto> getApplicationById(Long id);
	
	List<ApplicationResponseDto> getAllApplication();
	
	void deleteApplicationWithEmail(String email);
	
	void deleteApplicationWithId(Long id);

	ApplicationResponseDto createApplication(ApplicationRequestDto applicationRequestDto);
	
	ApplicationResponseDto updateApplication(ApplicationRequestDto applicationRequestDto, Long id);
}
