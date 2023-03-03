package com.yorra.twinkle.security;

import com.yorra.twinkle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.yorra.twinkle.model.entities.User user = userRepository.findByPhone(username);
        if (user != null) {
            return User.withUsername(user.getPhone()).password(user.getPassword()).roles(String.valueOf(user.getAuthorities())).build();
        } else {
            throw new UsernameNotFoundException(username + " does not exist");
        }
    }
}
