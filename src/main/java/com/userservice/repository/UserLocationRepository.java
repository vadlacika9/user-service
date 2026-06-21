package com.userservice.repository;

import com.userservice.model.UserLocations;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLocationRepository extends JpaRepository<@NonNull UserLocations,@NonNull Long> {
}
