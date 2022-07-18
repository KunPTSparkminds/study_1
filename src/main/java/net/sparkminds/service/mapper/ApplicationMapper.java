package net.sparkminds.service.mapper;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.dto.ApplicationRequestDto;
import net.sparkminds.dto.ApplicationResponseDto;
import net.sparkminds.entity.Application;

@Service
@RequiredArgsConstructor
public class ApplicationMapper {
    

    public Application requestToEntity (ApplicationRequestDto dto) {
        if(dto == null) return null;
        Application application = new Application();
        application.setEmail(dto.getEmail());
        application.setGithub(dto.getGithub());
        application.setName(dto.getName());
        return application;
    }
    
    public ApplicationResponseDto entityToResponse (Application application) {
        if(application == null) return null;
        ApplicationResponseDto dto = new ApplicationResponseDto();
        application.setEmail(application.getEmail());
        application.setGithub(application.getGithub());
        application.setName(application.getName());
        application.setPastProjects(application.getPastProjects());
        return dto;
    }
    
}
