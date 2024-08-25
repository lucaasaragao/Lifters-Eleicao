package com.example.lifters.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_CONSTITUENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConstituentModel implements Serializable {

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

    @Column(name = "votes", nullable = false)
    private Integer votes = 1;  // Inicializa com 1 por padrão

    public void setVotes(Integer votes) {
        if (votes != null) {
            this.votes = votes;
        } else {
            this.votes = 1;  // Garantir que nunca seja nulo
        }
    }
}
