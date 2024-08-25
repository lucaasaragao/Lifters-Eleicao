package com.example.lifters.services;

import com.example.lifters.models.CandidateModel;
import com.example.lifters.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CandidateService {

    final CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public boolean getCandidateByDocumentNumber(String documentNumber) {
        return candidateRepository.existsByDocumentNumber(documentNumber);
    }

    public CandidateModel save(CandidateModel candidateModel) {
        return candidateRepository.save(candidateModel);
    }

    public List<CandidateModel> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Optional<CandidateModel> getCandidateById(UUID id) {
        return candidateRepository.findById(id);
    }

    public void deleteCandidate(CandidateModel candidateModel) {
        candidateRepository.delete(candidateModel);
    }

    public Optional<CandidateModel> getCandidateByNumber(Integer numberOfCandidate) {
        return candidateRepository.findByNumberOfCandidate(numberOfCandidate);
    }
}
