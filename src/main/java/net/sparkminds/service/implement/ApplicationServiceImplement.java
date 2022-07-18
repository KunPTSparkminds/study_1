package net.sparkminds.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.dto.ApplicationRequestDto;
import net.sparkminds.dto.ApplicationResponseDto;
import net.sparkminds.dto.PastProjectResponseDto;
import net.sparkminds.entity.Application;
import net.sparkminds.entity.PastProject;
import net.sparkminds.repository.ApplicationRepository;
import net.sparkminds.service.ApplicationService;
import net.sparkminds.service.mapper.PastProjectMapper;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplicationServiceImplement implements ApplicationService {

    private final ApplicationRepository applicationRepsonsitory;

    
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
                        .build();
            }).collect(Collectors.toList());
            return ApplicationResponseDto.builder().email(entity.getEmail()).github(entity.getGithub())
                    .name(entity.getName()).pastProjects(pasProjects).build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<ApplicationResponseDto> getAllApplication() {
        return applicationRepsonsitory.findAll().stream().map(entity -> {
            List<PastProjectResponseDto> pasProjects = entity.getPastProjects().stream().map(passPr -> {
                return PastProjectResponseDto.builder().pastProjectName(passPr.getPastProjectName())
                        .employment(passPr.getEmployment()).capacity(passPr.getCapacity())
                        .duration(passPr.getDuration()).startYear(passPr.getStartYear()).role(passPr.getRole())
                        .teamSize(passPr.getTeamSize()).linkToRepository(passPr.getLinkToRepository())
                        .linkToLiveUrl(passPr.getLinkToLiveUrl())
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


    @Transactional
    @Override
    public ApplicationResponseDto createApplication(ApplicationRequestDto applicationRequestDto) {
        if (applicationRepsonsitory.existsByEmail(applicationRequestDto.getEmail())) return null;
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
