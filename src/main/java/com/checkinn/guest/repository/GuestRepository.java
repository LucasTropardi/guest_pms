package com.checkinn.guest.repository;

import com.checkinn.guest.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    Optional<Guest> findByDocumento(String documento);

    Optional<Guest> findByNome(String nome);
}
