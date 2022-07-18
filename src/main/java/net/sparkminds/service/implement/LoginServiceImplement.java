package net.sparkminds.service.implement;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.sparkminds.config.JwtTokenUtil;
import net.sparkminds.dto.LoginRequestDto;
import net.sparkminds.dto.LoginResponseDto;
import net.sparkminds.entity.Reviewer;
import net.sparkminds.repository.ReviewerRepository;
import net.sparkminds.service.JwtUserDetailsService;
import net.sparkminds.service.LoginService;


@Service
@RequiredArgsConstructor
public class LoginServiceImplement implements LoginService {

    
    @Autowired
    private ReviewerRepository reviewerRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
//    private JwtUtils jwtUtils;
    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;
    
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        

        Optional<Reviewer> user =  reviewerRepository.findByEmail(loginRequestDto.getEmail());
        
        boolean a = bcryptEncoder.matches(loginRequestDto.getPassword(), user.get().getPassword());
        
        if(a == false) {
            throw new EntityNotFoundException("Sai tai khoan hoac mat khau");
        } 
        
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(loginRequestDto.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        LoginResponseDto x = new LoginResponseDto();
        
        x.setJwtToken(token);
        return x;
        
    }
    
}
