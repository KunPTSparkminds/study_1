package net.sparkminds.service.implement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.dto.RegisterRequestDto;
import net.sparkminds.entity.Reviewer;
import net.sparkminds.repository.ReviewerRepository;
import net.sparkminds.service.RegisterService;
import net.sparkminds.service.mapper.ReviewerMapper;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegisterServiceImplement implements RegisterService {
    
    private final ReviewerRepository reviewerRepository;
    
    private final ReviewerMapper reviewerMapper;
    
    @Transactional
    public Reviewer register(RegisterRequestDto registerRequestDto) {
           
        if (reviewerRepository.existsByEmail(registerRequestDto.getEmail())) return null;
        return reviewerRepository.save(reviewerMapper.registerRequestToEntity(registerRequestDto));
    }
}
