package com.checkinn.guest.service;

import com.checkinn.guest.dto.GuestRequest;
import com.checkinn.guest.model.Guest;
import com.checkinn.guest.repository.GuestRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;

    public Guest save(GuestRequest request) {
        if (guestRepository.findByDocumento(request.getDocumento()).isPresent()) {
            throw new RuntimeException("Documento já cadastrado.");
        }

        Guest guest = Guest.builder()
                .nome(request.getNome())
                .nacionalidade(request.getNacionalidade())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .endereco(request.getEndereco())
                .cidade(request.getCidade())
                .documento(request.getDocumento())
                .observacoes(request.getObservacoes())
                .build();

        return guestRepository.save(guest);
    }

    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    public Guest findById(Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hóspede não encontrado."));
    }

    public Guest update(Long id, GuestRequest request) {
        Guest guest = findById(id);

        guest.setNome(request.getNome());
        guest.setNacionalidade(request.getNacionalidade());
        guest.setEmail(request.getEmail());
        guest.setTelefone(request.getTelefone());
        guest.setEndereco(request.getEndereco());
        guest.setCidade(request.getCidade());
        guest.setDocumento(request.getDocumento());
        guest.setObservacoes(request.getObservacoes());

        return guestRepository.save(guest);
    }

    public void delete(Long id) {
        Guest guest = findById(id);
        guestRepository.delete(guest);
    }
}
