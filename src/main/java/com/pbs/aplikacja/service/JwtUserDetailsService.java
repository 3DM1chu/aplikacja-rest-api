package com.pbs.aplikacja.service;

import com.pbs.aplikacja.model.Student;
import com.pbs.aplikacja.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String lastname) throws UsernameNotFoundException {
        Student user = userRepository.findByLastNameStartsWithIgnoreCase(lastname, null).get().findFirst().get();
        if (user == null) {
            throw new UsernameNotFoundException("User not found with lastname: " + lastname);
        }
        return new org.springframework.security.core.userdetails.User(user.getFirstName(), "admin",
                new ArrayList<>());
    }
}
