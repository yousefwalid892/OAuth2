package com.example.Authorization.server.service;

import com.example.Authorization.server.entities.Client;
import com.example.Authorization.server.entities.User;
import com.example.Authorization.server.model.SecurityUser;
import com.example.Authorization.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(SecurityUser::new).orElseThrow(()-> new UsernameNotFoundException("User not exist"));
    }
}
