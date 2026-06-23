package com.userservice.service;

import com.userservice.dto.incoming.UserRegisterDto;
import com.userservice.mappers.UserMapper;
import com.userservice.model.User;
import com.userservice.repository.UserRepository;
import org.jspecify.annotations.NullMarked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder encoder, UserMapper userMapper) {
        this.repository = repository;
        this.encoder = encoder;
        this.userMapper = userMapper;
    }

    // Method to load user details by username (email)
    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database by email (username)
        Optional<User> userInfo = repository.findByEmail(username);

        if (userInfo.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        // Convert UserInfo to UserDetails (UserInfoDetails)
        User user = userInfo.get();
        Collection<? extends GrantedAuthority> authorities =
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    // Add any additional methods for registering or managing users
    public String addUser(UserRegisterDto user) {
        User dbUser = userMapper.userRegistrationDtoToUser(user);
        dbUser.setPassword(encoder.encode(user.getPassword()));

        repository.save(dbUser);
        return "User added successfully!";
    }

    public User getUserByEmail(String email) {
        Optional<User> userInfo = repository.findByEmail(email);
        return userInfo.orElse(null);

    }
}
