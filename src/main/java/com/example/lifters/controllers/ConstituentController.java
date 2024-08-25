package com.example.lifters.controllers;


import com.example.lifters.dtos.ConstituentDto;
import com.example.lifters.models.ConstituentModel;
import com.example.lifters.services.ConstituentService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/constituent")
public class ConstituentController {

    @Autowired
    ConstituentService constituentService;

    @PostMapping
    public ResponseEntity<ConstituentModel> saveConstituent(
            @RequestBody @Valid ConstituentDto constituentDto) {
        if (constituentService.getConstituentByDocumentNumber(constituentDto.getDocumentNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        ConstituentModel constituentModel = new ConstituentModel();
        BeanUtils.copyProperties(constituentDto, constituentModel);
        ConstituentModel savedConstituent = constituentService.save(constituentModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedConstituent);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ConstituentModel>> getAllConstituents() {
        if (constituentService.getAllConstituents().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(constituentService.getAllConstituents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConstituentModel> getConstituent(@PathVariable("id") UUID id) {
        ConstituentModel constituent = constituentService.getConstituentById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Constituent not found"));
        return ResponseEntity.ok(constituent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConstituentModel> updateConstituent(
            @PathVariable("id") UUID id,
            @RequestBody @Valid ConstituentDto constituentDto) {
        ConstituentModel existingConstituent = constituentService.getConstituentById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Constituent not found"));
        BeanUtils.copyProperties(constituentDto, existingConstituent, "id", "documentNumber");
        ConstituentModel updatedConstituent = constituentService.save(existingConstituent);
        return ResponseEntity.ok(updatedConstituent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeConstituent(@PathVariable("id") UUID id) {
        ConstituentModel constituent = constituentService.getConstituentById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Constituent not found"));
        constituentService.deleteConstituent(constituent);
        return ResponseEntity.noContent().build();
    }
}
