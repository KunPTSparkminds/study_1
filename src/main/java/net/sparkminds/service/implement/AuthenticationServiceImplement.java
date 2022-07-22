package net.sparkminds.service.implement;

import java.util.Date;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.sparkminds.config.JwtTokenUtil;
import net.sparkminds.dto.LoginRequestDto;
import net.sparkminds.dto.LoginResponseDto;
import net.sparkminds.dto.RegisterRequestDto;
import net.sparkminds.entity.Reviewer;
import net.sparkminds.repository.ReviewerRepository;
import net.sparkminds.service.AuthenticationService;
import net.sparkminds.service.JwtUserDetailsService;
import net.sparkminds.service.RedisService;
import net.sparkminds.service.mapper.ReviewerMapper;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImplement implements AuthenticationService {

	private final ReviewerRepository reviewerRepository;

	private final JwtTokenUtil jwtTokenUtil;

	private final BCryptPasswordEncoder bcryptEncoder;

	private final JwtUserDetailsService jwtUserDetailsService;

	private final ReviewerMapper reviewerMapper;

	private final RedisService redisService;

	private final RedisTemplate<String, Object> template;

	@Override
	public LoginResponseDto login(LoginRequestDto loginRequestDto) {
		Optional<Reviewer> user = reviewerRepository.findByEmail(loginRequestDto.getEmail());

		boolean a = bcryptEncoder.matches(loginRequestDto.getPassword(), user.get().getPassword());

		if (a == false) {
			throw new EntityNotFoundException("Sai tai khoan hoac mat khau");
		}

		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(loginRequestDto.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		LoginResponseDto x = new LoginResponseDto();

		x.setJwtToken(token);
		return x;
	}

	@Override
	@Transactional
	public Reviewer register(RegisterRequestDto registerRequestDto) {

		if (reviewerRepository.existsByEmail(registerRequestDto.getEmail()))
			return null;

		return reviewerRepository.save(reviewerMapper.registerRequestToEntity(registerRequestDto));
	}

	@Override
	public String logout(String jwt) {
		Long timeout = jwtTokenUtil.getExpirationDateFromToken(jwt).getTime() - new Date().getTime();

		redisService.cacheJwt(jwt, timeout);

		return "Success Logout";
	}


}
