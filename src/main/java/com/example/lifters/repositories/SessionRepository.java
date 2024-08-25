package com.example.lifters.repositories;


import com.example.lifters.models.SessionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<SessionModel, UUID> {

    Optional<SessionModel> findById(UUID id);
    Optional<SessionModel> findTopByOrderByIdDesc();
}
