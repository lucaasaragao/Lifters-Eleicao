package com.example.lifters.services;

import com.example.lifters.models.ConstituentModel;
import com.example.lifters.repositories.ConstituentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConstituentService {

    private final ConstituentRepository constituentRepository;

    @Autowired
    public ConstituentService(ConstituentRepository constituentRepository) {
        this.constituentRepository = constituentRepository;
    }

    @Transactional
    public ConstituentModel save(ConstituentModel constituentModel) {
        return constituentRepository.save(constituentModel);
    }

    public List<ConstituentModel> getAllConstituents() {
        return constituentRepository.findAll();
    }

    public Optional<ConstituentModel> getConstituentById(UUID id) {
        return constituentRepository.findById(id);
    }

    public boolean getConstituentByDocumentNumber(String documentNumber) {
        return constituentRepository.existsByDocumentNumber(documentNumber);
    }

    public Optional<ConstituentModel> findConstituentByDocumentNumber(String documentNumber) {
        return constituentRepository.findByDocumentNumber(documentNumber);
    }

    @Transactional
    public void deleteConstituent(ConstituentModel constituentModel) {
        constituentRepository.delete(constituentModel);
    }
}
