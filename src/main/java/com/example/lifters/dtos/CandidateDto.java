package com.example.lifters.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;


@Data
public class CandidateDto {

    @NotBlank(message = "First name is required.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    private String lastName;

    @NotBlank(message = "Document number is required.")
    private String documentNumber;

    @NotBlank(message = "Position is required.")
    private String position;

    @NotNull(message = "Candidate number cannot be null.")
    private Integer numberOfCandidate;

    @PositiveOrZero(message = "Votes must be zero or positive.")
    private int votes = 1;

    @PositiveOrZero(message = "Number of votes received must be zero or positive.")
    private int numberOfVotesReceived = 0;
}
