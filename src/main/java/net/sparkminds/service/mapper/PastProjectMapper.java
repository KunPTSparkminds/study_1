package net.sparkminds.service.mapper;

import org.springframework.stereotype.Service;

import net.sparkminds.dto.PastProjectRequestDto;
import net.sparkminds.dto.PastProjectResponseDto;
import net.sparkminds.entity.PastProject;

@Service
public class PastProjectMapper {

    public PastProject requestToEntity(PastProjectRequestDto dto) {
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
    
    public PastProjectResponseDto entityToResponse(PastProject entity) {
        if (entity == null) return null;
        PastProjectResponseDto pastProject = new PastProjectResponseDto();
        pastProject.setPastProjectName(entity.getPastProjectName());
        pastProject.setEmployment(entity.getEmployment());
        pastProject.setCapacity(entity.getCapacity());
        pastProject.setDuration(entity.getDuration());
        pastProject.setStartYear(entity.getStartYear());
        pastProject.setTeamSize(entity.getTeamSize());
        pastProject.setLinkToLiveUrl(entity.getLinkToLiveUrl());
        pastProject.setLinkToRepository(entity.getLinkToRepository());
        pastProject.setRole(entity.getRole());
        return pastProject;
    }

}
