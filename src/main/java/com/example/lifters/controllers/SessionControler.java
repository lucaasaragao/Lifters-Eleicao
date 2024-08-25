package com.example.lifters.controllers;

import com.example.lifters.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/session")
public class SessionControler {

    @Autowired
    private SessionService sessionService;

    @PostMapping("/open")
    public ResponseEntity<String> openSession() {
        sessionService.openSession();
        return ResponseEntity.ok("Session opened");
    }

    @PatchMapping("/close")
    public ResponseEntity<String> closeSession() {
        sessionService.closeSession();
        return ResponseEntity.ok("Session closed");
    }
}
