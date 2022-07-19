package net.sparkminds.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.dto.PastProjectRequestDto;
import net.sparkminds.dto.PastProjectResponseDto;
import net.sparkminds.entity.PastProject;
import net.sparkminds.repository.PastProjectRepository;
import net.sparkminds.service.PastProjectService;
import net.sparkminds.service.mapper.ApplicationMapper;
import net.sparkminds.service.mapper.PastProjectMapper;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PastProjectServiceImplement implements PastProjectService {
    
    @Autowired
    private final PastProjectRepository passProjectRepository;

    private final PastProjectMapper projectMapper;

    private final ApplicationMapper applicationMapper;
    
    @Override
    public List<PastProjectResponseDto> getAllPastProject() {
        
        return passProjectRepository.findAll().stream().map(projectMapper::entityToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<PastProjectResponseDto> getPastProjectByApplicationId(Long id) {
        
        return passProjectRepository.findAllByApplicationId(id).stream().map(projectMapper::entityToResponse)
                .collect(Collectors.toList());
        
//        return projectMapper.entityToResponse(passProjectRepository.findAllByApplicationId(id).get());
    }
    

    @Transactional
    @Override
    public PastProjectResponseDto createPassProject(PastProjectRequestDto pastProjectRequestDto) {
//        Application application = applicationRepsonsitory.findById(pastProjectRequestDto.getIdApplication()).orElse(null);
        PastProject passProject = new PastProject();
        
        passProject = projectMapper.requestToEntity(pastProjectRequestDto);
        

        passProjectRepository.save(passProject);
        return projectMapper.entityToResponse(passProject);

    }



    
}
