package net.sparkminds.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.Repository.ApplicationRepository;
import net.sparkminds.Repository.PastProjectRepository;
import net.sparkminds.dto.PastProjectRequestDto;
import net.sparkminds.dto.PastProjectResponseDto;
import net.sparkminds.entity.Application;
import net.sparkminds.entity.PastProject;
import net.sparkminds.service.PastProjectService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PastProjectServiceImplement implements PastProjectService {
    
    @Autowired
    private final PastProjectRepository passProjectRepository;
    @Autowired
    private final ApplicationRepository applicationRepsonsitory;
    
    
    @Override
    public List<PastProjectResponseDto> getAllPastProject() {
        return passProjectRepository.findAll().stream().map(entity -> {
            return PastProjectResponseDto
                    .builder()
                    .pastProjectName(entity.getPastProjectName())
                    .employment(entity.getEmployment())
                    .capacity(entity.getCapacity())
                    .duration(entity.getDuration())
                    .startYear(entity.getStartYear())
                    .role(entity.getRole())
                    .teamSize(entity.getTeamSize())
                    .linkToRepository(entity.getLinkToRepository())
                    .linkToLiveUrl(entity.getLinkToLiveUrl())
                    .idApplication(entity.getApplication().getId())
                    .build();
        }).collect(Collectors.toList());
    }
    
    @Override
    public List<PastProjectResponseDto> getPastProjectById(Long id) {
        return passProjectRepository.findById(id).stream().map(entity -> {
            return PastProjectResponseDto
                    .builder()
                    .pastProjectName(entity.getPastProjectName())
                    .employment(entity.getEmployment())
                    .capacity(entity.getCapacity())
                    .duration(entity.getDuration())
                    .startYear(entity.getStartYear())
                    .role(entity.getRole())
                    .teamSize(entity.getTeamSize())
                    .linkToRepository(entity.getLinkToRepository())
                    .linkToLiveUrl(entity.getLinkToLiveUrl())
                    .idApplication(entity.getApplication().getId())
                    .build();
        }).collect(Collectors.toList());
    }
    

    @Transactional
    @Override
    public PastProjectResponseDto createPassProject(PastProjectRequestDto pastProjectRequestDto) {
        Application application = applicationRepsonsitory.findById(pastProjectRequestDto.getIdApplication()).orElse(null);
        PastProject passProject = new PastProject();
        passProject.setPastProjectName(pastProjectRequestDto.getPastProjectName());
        passProject.setEmployment(pastProjectRequestDto.getEmployment());
        passProject.setCapacity(pastProjectRequestDto.getCapacity());
        passProject.setDuration(pastProjectRequestDto.getDuration());
        passProject.setStartYear(pastProjectRequestDto.getStartYear());
        passProject.setRole(pastProjectRequestDto.getRole());
        passProject.setTeamSize(pastProjectRequestDto.getTeamSize());
        passProject.setLinkToRepository(pastProjectRequestDto.getLinkToRepository());
        passProject.setLinkToLiveUrl(pastProjectRequestDto.getLinkToLiveUrl());
        passProject.setApplication(application);
        passProjectRepository.save(passProject);
        return PastProjectResponseDto.builder()
                .pastProjectName(passProject.getPastProjectName())
                .employment(passProject.getEmployment())
                .capacity(passProject.getCapacity())
                .duration(passProject.getDuration())
                .startYear(passProject.getStartYear())
                .role(passProject.getRole())
                .teamSize(passProject.getTeamSize())
                .linkToRepository(passProject.getLinkToRepository())
                .linkToLiveUrl(passProject.getLinkToLiveUrl())
                .idApplication(passProject.getApplication().getId())
                .build();
    }



    
}
