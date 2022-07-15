package net.sparkminds.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import net.sparkminds.Repository.ApplicationRepository;
import net.sparkminds.Repository.PastProjectRepository;
import net.sparkminds.dto.ApplicationRequestDto;
import net.sparkminds.dto.ApplicationResponseDto;
import net.sparkminds.dto.PastProjectResponseDto;
import net.sparkminds.entity.Application;
import net.sparkminds.entity.PastProject;
import net.sparkminds.service.ApplicationService;
import net.sparkminds.service.mapper.PastProjectMapper;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplicationServiceImplement implements ApplicationService {

    private final ApplicationRepository applicationRepsonsitory;

    private final PastProjectRepository pastProjectRepsonsitory;
    
    private final PastProjectMapper projectMapper;

    @Override
    public List<ApplicationResponseDto> getApplicationById(Long id) {
        return applicationRepsonsitory.findByIdAndDeletedFalse(id).stream().map(entity -> {
            List<PastProjectResponseDto> pasProjects = entity.getPastProjects().stream().map(passPr -> {
                return PastProjectResponseDto.builder().pastProjectName(passPr.getPastProjectName())
                        .employment(passPr.getEmployment()).capacity(passPr.getCapacity())
                        .duration(passPr.getDuration()).startYear(passPr.getStartYear()).role(passPr.getRole())
                        .teamSize(passPr.getTeamSize()).linkToRepository(passPr.getLinkToRepository())
                        .linkToLiveUrl(passPr.getLinkToLiveUrl())
//                        .idApplication(passPr.getApplication().getId())
                        .build();
            }).collect(Collectors.toList());
            return ApplicationResponseDto.builder().email(entity.getEmail()).github(entity.getGithub())
                    .name(entity.getName()).pastProjects(pasProjects).build();
        }).collect(Collectors.toList());
//	    return null;
    }

    @Override
    public List<ApplicationResponseDto> getAllApplication() {
        return applicationRepsonsitory.getAllApplication().stream().map(entity -> {
            List<PastProjectResponseDto> pasProjects = entity.getPastProjects().stream().map(passPr -> {
                return PastProjectResponseDto.builder().pastProjectName(passPr.getPastProjectName())
                        .employment(passPr.getEmployment()).capacity(passPr.getCapacity())
                        .duration(passPr.getDuration()).startYear(passPr.getStartYear()).role(passPr.getRole())
                        .teamSize(passPr.getTeamSize()).linkToRepository(passPr.getLinkToRepository())
                        .linkToLiveUrl(passPr.getLinkToLiveUrl())
//						.idApplication(passPr.getApplication().getId())
                        .build();
            }).collect(Collectors.toList());
            return ApplicationResponseDto.builder().email(entity.getEmail()).name(entity.getName())
                    .github(entity.getGithub()).pastProjects(pasProjects).build();
        }).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ApplicationResponseDto updateApplication(ApplicationRequestDto applicationRequestDto, Long id) {
        Application application = applicationRepsonsitory.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Application is not exist"));

        application.setEmail(applicationRequestDto.getEmail());
        application.setGithub(applicationRequestDto.getGithub());
        application.setName(applicationRequestDto.getName());
        applicationRepsonsitory.save(application);

        return ApplicationResponseDto.builder().email(application.getEmail()).github(application.getGithub())
                .name(application.getName()).build();
    }

    @Transactional
    @Override
    public void deleteApplicationWithEmail(String email) {
        Application applicationDelete = applicationRepsonsitory.findByEmailAndDeletedFalse(email)
                .orElseThrow(() -> new EntityNotFoundException("Application is not exist"));
        applicationDelete.setDeleted(true);
        applicationRepsonsitory.save(applicationDelete);
    }

    @Transactional
    @Override
    public void deleteApplicationWithId(Long id) {
        Application applicationDelete = applicationRepsonsitory.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Application is not exist"));
        applicationDelete.setDeleted(true);
        applicationRepsonsitory.save(applicationDelete);
    }

    private PastProject convertToEntity(PastProjectResponseDto dto) {
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

    @Transactional
    @Override
    public ApplicationResponseDto createApplication(ApplicationRequestDto applicationRequestDto) {
        List<PastProject> projects = applicationRequestDto
                .getPastProject()
                .stream()
                .map(projectMapper::toEntity)
                .collect(Collectors.toList());
        
        Application newApplication = new Application();
        newApplication.setEmail(applicationRequestDto.getEmail());
        newApplication.setGithub(applicationRequestDto.getGithub());
        newApplication.setName(applicationRequestDto.getName());
        newApplication.setDeleted(false);
        newApplication.addProjects(projects);

        newApplication = applicationRepsonsitory.save(newApplication);

        return ApplicationResponseDto
                .builder()
                .email(newApplication.getEmail())
                .github(newApplication.getGithub())
                .name(newApplication.getName())
                .build();
    }
}
