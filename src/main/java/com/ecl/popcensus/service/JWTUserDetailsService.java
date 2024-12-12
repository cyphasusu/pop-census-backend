package com.ecl.popcensus.service;

import com.ecl.popcensus.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JWTUserDetailsService implements  UserDetailsService{

    private final UserRepository userRepository;

    public JWTUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        com.ecl.popcensus.model.User jwtUser = userRepository.findUserByEmail(email).orElseThrow(() -> new IllegalStateException("This user does not exist"));

        if(jwtUser==null)
            throw new UsernameNotFoundException("Email not found:"+email);

        return new User(jwtUser.getEmail(), jwtUser.getPassword(), new ArrayList());
    }
}
