package net.sparkminds.service.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.dto.ApplicationRequestDto;
import net.sparkminds.dto.ApplicationResponseDto;
import net.sparkminds.entity.Application;

@Service
@RequiredArgsConstructor
public class ApplicationMapper {
    
    private final PastProjectMapper projectMapper;
    
    public Application requestToEntity(ApplicationRequestDto dto) {
        if (dto == null) return null;
        
        Application application = new Application();
        application.setEmail(dto.getEmail());
        application.setGithub(dto.getGithub());
        application.setName(dto.getName());
        
        return application;
    }
    
    public ApplicationResponseDto entityToResponse(Application entity) {
        if (entity == null) return null;
        
        ApplicationResponseDto application = new ApplicationResponseDto();
        application.setEmail(entity.getEmail());
        application.setGithub(entity.getGithub());
        application.setName(entity.getName());
        application.setPastProjects(
                entity.getPastProjects()
                .stream()
                .map(projectMapper::entityToResponse)
                .collect(Collectors.toList()));
        return application;
    }
    
}
