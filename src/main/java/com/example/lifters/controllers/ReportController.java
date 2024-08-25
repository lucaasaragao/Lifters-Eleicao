package com.example.lifters.controllers;

import com.example.lifters.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private RecordService recordService;

    @GetMapping("/resultOfElection")
    public ResponseEntity<String> getCandidateReport() {
        //Regra de negocio 10 -> Imprime relatorio
        String report = recordService.generateReport();
        return ResponseEntity.ok(report);
    }

}
