package net.sparkminds.service;

import java.util.List;

import net.sparkminds.dto.PastProjectResponseDto;

public interface PastProjectService {
    
    List<PastProjectResponseDto> getAllPastProject();
    
    List<PastProjectResponseDto> getPastProjectById(Long id);
    
//    PastProjectResponseDto createPassProject(PastProjectRequestDto passProjectRequestDto);

}
