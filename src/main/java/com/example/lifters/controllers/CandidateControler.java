package com.example.lifters.controllers;


import com.example.lifters.dtos.CandidateDto;
import com.example.lifters.models.CandidateModel;
import com.example.lifters.services.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/candidate")
public class CandidateControler {

    @Autowired
    CandidateService candidateService;

    @PostMapping
    public ResponseEntity<String> createCandidate(
            @RequestBody @Valid CandidateDto candidateDto) {

        if (candidateService.getCandidateByDocumentNumber(candidateDto.getDocumentNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: A candidate with this document number already exists.");
        }
        CandidateModel candidate = new CandidateModel();
        BeanUtils.copyProperties(candidateDto, candidate);
        candidateService.save(candidate);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Candidate created successfully.");
    }

    @GetMapping("/list")
    public ResponseEntity<List<CandidateModel>> getAllCandidates() {
        List<CandidateModel> candidates = candidateService.getAllCandidates();
        if (candidates.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
        }
        return ResponseEntity.ok(candidates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateModel> getCandidateById(@PathVariable("id") UUID id) {
        return candidateService.getCandidateById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<CandidateModel> updateCandidate(
            @PathVariable("id") UUID id,
            @RequestBody @Valid CandidateDto candidateDto) {
        CandidateModel existingCandidate = candidateService.getCandidateById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate not found"));
        BeanUtils.copyProperties(candidateDto, existingCandidate, "idCandidate", "documentNumber");
        CandidateModel updatedCandidate = candidateService.save(existingCandidate);
        return ResponseEntity.ok(updatedCandidate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeCandidate(@PathVariable("id") UUID id) {
        CandidateModel candidate = candidateService.getCandidateById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate not found"));
        if (candidate.getNumberOfVotesReceived() > 0){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Candidate cannot be excluded because he already has votes");
        }
        candidateService.deleteCandidate(candidate);
        return ResponseEntity.noContent().build();
    }


}
