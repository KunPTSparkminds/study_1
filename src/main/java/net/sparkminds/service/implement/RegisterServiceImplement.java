package net.sparkminds.service.implement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.Repository.ApplicationRepository;
import net.sparkminds.Repository.PastProjectRepository;
import net.sparkminds.Repository.ReviewerRepository;
import net.sparkminds.dto.RegisterRequestDto;
import net.sparkminds.entity.Reviewer;
import net.sparkminds.service.RegisterService;
import net.sparkminds.service.mapper.PastProjectMapper;
import net.sparkminds.service.mapper.ReviewerMapper;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegisterServiceImplement implements RegisterService {
    
    private final ReviewerRepository reviewerRepository;
    
    private final ReviewerMapper reviewerMapper;
    
    @Transactional
    public Reviewer register(RegisterRequestDto registerRequestDto) {
        
//        if (emailExist(registerRequestDto.getEmail())) {
//            throw new EmailExistsException(
//              "There is an account with that email adress:" + registerRequestDto.getEmail());
//        }
        
        return reviewerRepository.save(reviewerMapper.registerRequestToEntity(registerRequestDto));
    }
}
