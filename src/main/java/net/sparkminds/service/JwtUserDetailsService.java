package net.sparkminds.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.sparkminds.entity.Reviewer;
import net.sparkminds.repository.ReviewerRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService {
    
    @Autowired
    private ReviewerRepository reviewerRepository;

//    @Autowired
//    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Reviewer> user = reviewerRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), new ArrayList<>());
    }

//    public DAOUser save(UserDTO user) {
//        DAOUser newUser = new DAOUser();
//        newUser.setUsername(user.getUsername());
//        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
//        return userDao.save(newUser);
//    }

}
