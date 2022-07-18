package net.sparkminds.service.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.dto.RegisterRequestDto;
import net.sparkminds.entity.Reviewer;

@Service
@RequiredArgsConstructor
public class ReviewerMapper {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Reviewer registerRequestToEntity(RegisterRequestDto dto) {
        if (dto == null)
            return null;
        Reviewer reviewer = new Reviewer();
        reviewer.setName(dto.getName());
        reviewer.setEmail(dto.getEmail());
        reviewer.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
//        reviewer.setPassword(dto.getPassword());
        reviewer.setPicture(dto.getPicture());
        return reviewer;
    }
    
    
}
