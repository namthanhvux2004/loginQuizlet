package com.example.demo.Model;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MyAppUserService implements UserDetailsService {

    @Autowired
    private MyAppUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyAppUser> myAppUser = repository.findByUsername(username);
        if (myAppUser.isPresent()) {
            var UserObj = myAppUser.get();
            return User.builder()
                    .username(UserObj.getUsername())
                    .password(UserObj.getPassword())
                    .build();
        }
        else
        {
            throw new UsernameNotFoundException(username);
        }
    }
}
