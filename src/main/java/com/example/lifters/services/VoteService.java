package com.example.lifters.services;

import com.example.lifters.models.CandidateModel;
import com.example.lifters.models.ConstituentModel;
import com.example.lifters.models.SessionModel;
import com.example.lifters.repositories.CandidateRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VoteService {

    @Autowired
    private final CandidateRepository candidateRepository;

    @Autowired
    private ConstituentService constituentService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private SessionService sessionService;

    public VoteService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    /**
     * Permite que um eleitor vote em um candidato.
     *
     * O método verifica se o eleitor existe e possui votos disponíveis.
     * Também verifica se o candidato existe. Se ambos forem válidos,
     * o voto é registrado e o número de votos do candidato é incrementado
     * em 1. Após votar, o número de votos do eleitor é zerado.
     *
     * @param documentNumber o número do documento do eleitor
     * @param numberOfCandidate o número do candidato em que o eleitor deseja votar
     *
     * @throws EntityNotFoundException se o eleitor ou o candidato não for encontrado
     * @throws IllegalStateException se o eleitor não possui votos disponíveis para votar
     */

    @Transactional
    public void vote(String documentNumber, int numberOfCandidate) {
        //Verifica se a sessao esta aberrtaa
        SessionModel session = sessionService.getCurrentSession();
        if(!session.isOpen()){
            throw new IllegalStateException("Voting session is not open");
        }

        // Verifica se o eleitor existe e se ele tem votos disponíveis
        ConstituentModel constituent = constituentService.findConstituentByDocumentNumber(documentNumber)
                .orElseThrow(() -> new EntityNotFoundException("Voter not found"));

        if (constituent.getVotes() <= 0) {
            throw new IllegalStateException("Voter does not have votes available to vote");
        }

        // Verifica se o candidato existe
        CandidateModel candidate = candidateService.getCandidateByNumber(numberOfCandidate)
                .orElseThrow(() -> new EntityNotFoundException("Candidate not found"));

        // Atualiza os votos do candidato
        candidate.setNumberOfVotesReceived(candidate.getNumberOfVotesReceived() + 1);
        candidateRepository.save(candidate);

        // Zera os votos do eleitor após votar
        constituent.setVotes(0);
        constituentService.save(constituent);
    }
}
