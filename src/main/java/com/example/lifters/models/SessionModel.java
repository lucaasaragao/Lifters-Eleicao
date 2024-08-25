package com.example.lifters.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_SESSION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "is_open", nullable = false)
    private boolean open;

    public void openSession() {
        this.open = true;
    }

    public void closeSession() {
        this.open = false;
    }

}
