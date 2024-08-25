package com.example.lifters.repositories;


import com.example.lifters.models.ConstituentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface ConstituentRepository extends JpaRepository<ConstituentModel, UUID> {

    boolean existsByDocumentNumber(String documentNumber);
    Optional<ConstituentModel> findByDocumentNumber(String documentNumber);

}
