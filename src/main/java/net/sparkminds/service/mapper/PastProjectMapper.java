package net.sparkminds.service.mapper;

import org.springframework.stereotype.Service;

import net.sparkminds.dto.PastProjectRequestDto;
import net.sparkminds.entity.PastProject;

@Service
public class PastProjectMapper {

    public PastProject toEntity(PastProjectRequestDto dto) {
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

}
