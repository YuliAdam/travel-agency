package org.example.service;

import org.example.entity.Users;
import org.example.entity.characteristic.Role;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

@Service
    public class CustomUserDetailsService implements UserDetailsService {
        @Autowired
        private UserRepository userRepository;
        @Override
        public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
            Users user= userRepository.findByLogin(userName);
            if (user == null) {
                throw new UsernameNotFoundException("Unknown user: "+userName);
            }
            UserDetails userDetails = User.builder()
                    .username(user.getLogin())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();
            return userDetails;
        }
    }
