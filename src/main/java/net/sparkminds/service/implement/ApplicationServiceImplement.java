package net.sparkminds.service.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.dto.ApplicationRequestDto;
import net.sparkminds.dto.ApplicationResponseDto;
import net.sparkminds.entity.Application;
import net.sparkminds.entity.PastProject;
import net.sparkminds.repository.ApplicationRepository;
import net.sparkminds.repository.PastProjectRepository;
import net.sparkminds.service.ApplicationService;
import net.sparkminds.service.mapper.ApplicationMapper;
import net.sparkminds.service.mapper.PastProjectMapper;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplicationServiceImplement implements ApplicationService {

    private final ApplicationRepository applicationRepsonsitory;
    
    private final PastProjectRepository pastProjectRepository; 

    private final PastProjectMapper projectMapper;
    
    private final ApplicationMapper applicationMapper;
    

    @Override
    public Optional<ApplicationResponseDto> getApplicationById(Long id) {
        return applicationRepsonsitory.findByIdAndDeletedFalse(id).map(applicationMapper::entityToResponse);
        
    }

    @Override
    public List<ApplicationResponseDto> getAllApplication() {
        return applicationRepsonsitory.findAllByDeletedFalse().stream().map(applicationMapper::entityToResponse)
                .collect(Collectors.toList());
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

        return applicationMapper.entityToResponse(application);

    }

    @Transactional
    @Override
    public void deleteApplicationWithEmail(String email) {
        Application applicationDelete = applicationRepsonsitory.findByEmailAndDeletedFalse(email)
                .orElseThrow(() -> new EntityNotFoundException("Application is not exist"));
        applicationDelete.setDeleted(true);
        List<PastProject> pastProjects = pastProjectRepository.findAllByApplicationId(applicationDelete.getId());
        pastProjects.forEach(pasrPr -> {
            pasrPr.setDeleted(true);
        });
        pastProjectRepository.saveAll(pastProjects);
        applicationRepsonsitory.save(applicationDelete);
    }

    @Transactional
    @Override
    public void deleteApplicationWithId(Long id) {
        Application applicationDelete = applicationRepsonsitory.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Application is not exist"));
        applicationDelete.setDeleted(true);
        List<PastProject> pastProjects = pastProjectRepository.findAllByApplicationId(id);
        pastProjects.forEach(pasrPr -> {
            pasrPr.setDeleted(true);
        });
        pastProjectRepository.saveAll(pastProjects);
        applicationRepsonsitory.save(applicationDelete);
    }


    @Transactional
    @Override
    public ApplicationResponseDto createApplication(ApplicationRequestDto applicationRequestDto) {
        if (applicationRepsonsitory.existsByEmailAndDeletedFalse(applicationRequestDto.getEmail())) return null;
        List<PastProject> projects = applicationRequestDto
                .getPastProject()
                .stream()
                .map(projectMapper::requestToEntity)
                .collect(Collectors.toList());
        
        Application newApplication = new Application();
        newApplication.setEmail(applicationRequestDto.getEmail());
        newApplication.setGithub(applicationRequestDto.getGithub());
        newApplication.setName(applicationRequestDto.getName());
        newApplication.setDeleted(false);
        newApplication.addProjects(projects);

        applicationRepsonsitory.save(newApplication);
        
        return applicationMapper.entityToResponse(newApplication);

    }
}
