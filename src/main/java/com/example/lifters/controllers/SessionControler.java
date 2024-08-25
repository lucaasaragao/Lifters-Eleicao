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
        //Regra de negocio 5 -> Pode abrir ou encerrar a secao sem tempo entre elas
        return ResponseEntity.ok("Session opened");
    }

    @PatchMapping("/close")
    public ResponseEntity<String> closeSession() {
        //Regra de negocio 6 -> Lembrar de implementar a varificacao de votos
        sessionService.closeSession();
        return ResponseEntity.ok("Session closed");
    }
}
