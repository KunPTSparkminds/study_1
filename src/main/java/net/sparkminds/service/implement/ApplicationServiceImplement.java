package net.sparkminds.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.dto.ApplicationRequestDto;
import net.sparkminds.dto.ApplicationResponseDto;
import net.sparkminds.entity.Application;
import net.sparkminds.entity.PastProject;
import net.sparkminds.repository.ApplicationRepository;
import net.sparkminds.service.ApplicationService;
import net.sparkminds.service.mapper.ApplicationMapper;
import net.sparkminds.service.mapper.PastProjectMapper;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplicationServiceImplement implements ApplicationService {

    private final ApplicationRepository applicationRepsonsitory;

//    private final PastProjectRepository pastProjectRepsonsitory;
    
    private final PastProjectMapper projectMapper;
    
    private final ApplicationMapper applicationMapper;

    @Override
    public ApplicationResponseDto getApplicationById(Long id) {
        Application application = applicationRepsonsitory.findByIdAndDeletedFalse(id).orElseThrow(() -> new EntityNotFoundException("Application is not exist"));
        return applicationMapper.entityToResponse(application);
    }

    @Override
    public List<ApplicationResponseDto> getAllApplication() {
        return applicationRepsonsitory.getAllApplication().stream().map(applicationMapper::entityToResponse).collect(Collectors.toList());
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

        return null;
//        return applicationMapper.entityToResponse(application);
    }

    @Transactional
    @Override
    public void deleteApplicationWithEmail(String email) {
        List<Application> applicationDelete = applicationRepsonsitory.findByEmailAndDeletedFalse(email);
//        applicationDelete.stream().map(a -> {
//            a.setDeleted(true);
//            applicationRepsonsitory.save(a);
//        })
        applicationDelete.forEach(entity -> {
            entity.setDeleted(true);
        });
        applicationRepsonsitory.saveAll(applicationDelete);
    }

    @Transactional
    @Override
    public void deleteApplicationWithId(Long id) {
        Application applicationDelete = applicationRepsonsitory.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Application is not exist"));
        applicationDelete.setDeleted(true);
        applicationRepsonsitory.save(applicationDelete);
    }


    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Transactional
    @Override
    public ApplicationResponseDto createApplication(ApplicationRequestDto applicationRequestDto) {
        List<PastProject> projects = applicationRequestDto
                .getPastProject()
                .stream()
                .map(projectMapper::RequesttoEntity)
                .collect(Collectors.toList());
        
        Application newApplication = new Application();
        newApplication.setEmail(bCryptPasswordEncoder.encode(applicationRequestDto.getEmail()));
        newApplication.setGithub(applicationRequestDto.getGithub());
        newApplication.setName(applicationRequestDto.getName());
        newApplication.setDeleted(false);
        newApplication.addProjects(projects);

        newApplication = applicationRepsonsitory.save(newApplication);

        return applicationMapper.entityToResponse(newApplication);
//        return null;
    }
}
