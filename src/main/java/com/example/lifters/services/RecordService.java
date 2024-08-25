package com.example.lifters.services;

import com.example.lifters.models.CandidateModel;
import com.example.lifters.models.SessionModel;
import com.example.lifters.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Service
public class RecordService {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private SessionService sessionService;

    public String generateReport(){
        //Regra de negocio 9 -> Verifica se a sessao esta fechada
        SessionModel session = sessionService.getCurrentSession();
        if(session.isOpen()){
            throw new IllegalStateException("Section is open, impossible to print the report");
        }
        // Recupera todos os candidatos
        List<CandidateModel> candidates = candidateRepository.findAll();

        if(candidates.isEmpty()){
            return "No candidates found";
        }

        // Ordena os candidatos por votos
        candidates.sort(Comparator.comparingInt(CandidateModel::getNumberOfVotesReceived).reversed());

        // Calcula os votos dos candidatos
        int totalVotes = candidates.stream()
                .mapToInt(CandidateModel::getNumberOfVotesReceived)
                .sum();
        //Resultado
        CandidateModel winner = candidates.get(0);

        //Regra de negocio 7 -> gerar um relatorio
        // TODO: LEMBRAR DE INPUTAR O CARGO DEPOIS DE TESTAR
        StringBuilder report = new StringBuilder();
        report.append("----------------------------------------\n");
        report.append("Data relatório: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))).append("\n");
        report.append("Cargo ----------------------- PRESIDENTE\n");
        report.append("Candidatos  -----------------------  Votos\n");

        for (CandidateModel candidate : candidates) {
            report.append(String.format("%-30s %d\n", candidate.getFirstName(), candidate.getNumberOfVotesReceived()));
        }

        report.append(String.format("Total de votos %d\n", totalVotes));
        report.append("Vencedor: ").append(winner.getFirstName()).append("\n");
        report.append("----------------------------------------\n");

        return report.toString();

    }
}
