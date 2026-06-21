package com.sportappuserservice.repository;

import com.sportappuserservice.model.UserLocations;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLocationRepository extends JpaRepository<@NonNull UserLocations,@NonNull Long> {
}
