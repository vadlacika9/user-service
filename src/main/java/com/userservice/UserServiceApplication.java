package com.userservice;

import com.userservice.model.User;
import com.userservice.model.UserLocations;
import com.userservice.repository.UserLocationRepository;
import com.userservice.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLocationRepository userLocationsRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @PostConstruct
    void init() {
        try {
            System.out.println(userRepository.findById(2L).orElse(null));



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
