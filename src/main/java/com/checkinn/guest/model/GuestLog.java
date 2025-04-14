package com.checkinn.guest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "guest_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUsuario;
    private Long idHospede;

    private String acao; // ex: CREATED, UPDATED, DELETED

    private LocalDateTime dataHora;
}
