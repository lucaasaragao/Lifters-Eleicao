package com.example.lifters.repositories;

import com.example.lifters.models.CandidateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateModel, UUID> {

    boolean existsByDocumentNumber(String documentNumber);
    Optional<CandidateModel> findByNumberOfCandidate(Integer numberOfCandidate);
}
