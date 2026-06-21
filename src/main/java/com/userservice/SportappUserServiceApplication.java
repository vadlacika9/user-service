package com.sportappuserservice;

import com.sportappuserservice.model.User;
import com.sportappuserservice.model.UserLocations;
import com.sportappuserservice.repository.UserLocationRepository;
import com.sportappuserservice.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SportappUserServiceApplication {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLocationRepository userLocationsRepository;

    public static void main(String[] args) {
        SpringApplication.run(SportappUserServiceApplication.class, args);
    }

    @PostConstruct
    void init() {
        try {
           User user = userRepository.findById(2L).orElse(null);
           UserLocations userLocations = new UserLocations();
           userLocations.setLocationName("OTTHON");
           userLocations.setCity("Budapest");
           userLocations.setIsPrimary(true);
           userLocations.setPostalCode("12345");
            assert user != null;
            userLocations.setUserId(user.getId());

            userLocationsRepository.save(userLocations);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
