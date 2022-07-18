package net.sparkminds.service.mapper;

import org.springframework.stereotype.Service;

import net.sparkminds.dto.PastProjectRequestDto;
import net.sparkminds.dto.PastProjectResponseDto;
import net.sparkminds.entity.PastProject;

@Service
public class PastProjectMapper {

    public PastProject RequesttoEntity(PastProjectRequestDto dto) {
        if (dto == null) return null;
        PastProject pastProject = new PastProject();
        pastProject.setPastProjectName(dto.getPastProjectName());
        pastProject.setEmployment(dto.getEmployment());
        pastProject.setCapacity(dto.getCapacity());
        pastProject.setDuration(dto.getDuration());
        pastProject.setStartYear(dto.getStartYear());
        pastProject.setTeamSize(dto.getTeamSize());
        pastProject.setLinkToLiveUrl(dto.getLinkToLiveUrl());
        pastProject.setLinkToRepository(dto.getLinkToRepository());
        pastProject.setRole(dto.getRole());
        return pastProject;
    }
    
    
    public PastProjectResponseDto EntitytoResponse(PastProject pastProject) {
        if (pastProject == null) return null;
        PastProjectResponseDto pastProjectDto = new PastProjectResponseDto();
        pastProjectDto.setPastProjectName(pastProject.getPastProjectName());
        pastProjectDto.setEmployment(pastProject.getEmployment());
        pastProjectDto.setCapacity(pastProject.getCapacity());
        pastProjectDto.setDuration(pastProject.getDuration());
        pastProjectDto.setStartYear(pastProject.getStartYear());
        pastProjectDto.setTeamSize(pastProject.getTeamSize());
        pastProjectDto.setLinkToLiveUrl(pastProject.getLinkToLiveUrl());
        pastProjectDto.setLinkToRepository(pastProject.getLinkToRepository());
        pastProjectDto.setRole(pastProject.getRole());
        return pastProjectDto;
    }

}
