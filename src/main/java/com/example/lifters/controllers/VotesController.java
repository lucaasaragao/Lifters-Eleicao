package com.example.lifters.controllers;

import com.example.lifters.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/vote")
public class VotesController {

    @Autowired
    private VoteService voteService;

    @PostMapping
    public ResponseEntity<String> voteCandidate(
            @RequestParam String documentNumber,
            @RequestParam int numberCandidate) {
        try {
            voteService.vote(documentNumber, numberCandidate);
            return ResponseEntity.ok("Successfully registered vote.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
