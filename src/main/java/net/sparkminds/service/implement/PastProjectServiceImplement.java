package net.sparkminds.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.dto.PastProjectResponseDto;
import net.sparkminds.repository.PastProjectRepository;
import net.sparkminds.service.PastProjectService;
import net.sparkminds.service.mapper.PastProjectMapper;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PastProjectServiceImplement implements PastProjectService {
    
    @Autowired
    private final PastProjectRepository passProjectRepository;
    
    private final PastProjectMapper projectMapper;
    
    @Override
    public List<PastProjectResponseDto> getAllPastProject() {
        return passProjectRepository.findAll().stream().map(projectMapper::EntitytoResponse).collect(Collectors.toList());
    }
    
    @Override
    public List<PastProjectResponseDto> getPastProjectById(Long id) {
        return passProjectRepository.findById(id).stream().map(projectMapper::EntitytoResponse).collect(Collectors.toList());
    }
    

//    @Transactional
//    @Override
//    public PastProjectResponseDto createPassProject(PastProjectRequestDto pastProjectRequestDto) {
////        Application application = applicationRepsonsitory.findById(pastProjectRequestDto.getIdApplication()).orElse(null);
//        PastProject passProject = new PastProject();
//        passProject.setPastProjectName(pastProjectRequestDto.getPastProjectName());
//        passProject.setEmployment(pastProjectRequestDto.getEmployment());
//        passProject.setCapacity(pastProjectRequestDto.getCapacity());
//        passProject.setDuration(pastProjectRequestDto.getDuration());
//        passProject.setStartYear(pastProjectRequestDto.getStartYear());
//        passProject.setRole(pastProjectRequestDto.getRole());
//        passProject.setTeamSize(pastProjectRequestDto.getTeamSize());
//        passProject.setLinkToRepository(pastProjectRequestDto.getLinkToRepository());
//        passProject.setLinkToLiveUrl(pastProjectRequestDto.getLinkToLiveUrl());
////        passProject.setApplication(application);
//        passProjectRepository.save(passProject);
////        return PastProjectResponseDto.builder()
////                .pastProjectName(passProject.getPastProjectName())
////                .employment(passProject.getEmployment())
////                .capacity(passProject.getCapacity())
////                .duration(passProject.getDuration())
////                .startYear(passProject.getStartYear())
////                .role(passProject.getRole())
////                .teamSize(passProject.getTeamSize())
////                .linkToRepository(passProject.getLinkToRepository())
////                .linkToLiveUrl(passProject.getLinkToLiveUrl())
//////                .idApplication(passProject.getApplication().getId())
////                .build();
//        return projectMapper.EntitytoResponse(passProject);
//    }



    
}
