package com.example.lifters.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_CANDIDATE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Document number is required")
    @Column(name = "document_number", unique = true, nullable = false)
    private String documentNumber;

    @NotBlank(message = "Position is required")
    @Column(name = "position", unique = true, nullable = false)
    private String position;

    @NotNull(message = "Number of candidate cannot be null")
    @Column(name = "number_of_candidate", nullable = false)
    private Integer numberOfCandidate;

    @Column(name = "votes", updatable = false)
    private Integer votes = 1;

    @Column(name = "number_of_votes_received")
    private Integer numberOfVotesReceived = 0;

    @PrePersist
    protected void onCreate() {
        this.votes = 1;
    }

}
