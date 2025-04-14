package com.checkinn.guest.service;

import com.checkinn.guest.dto.GuestRequest;
import com.checkinn.guest.model.Guest;
import com.checkinn.guest.model.GuestLog;
import com.checkinn.guest.repository.GuestLogRepository;
import com.checkinn.guest.repository.GuestRepository;
import com.checkinn.guest.security.AuthenticatedUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;
    private final GuestLogRepository guestLogRepository;

    public Guest save(GuestRequest request, Authentication authentication) {
        if (guestRepository.findByDocumento(request.getDocumento()).isPresent()) {
            throw new RuntimeException("Documento já cadastrado.");
        }

        Guest guest = guestRepository.save(Guest.builder()
                .nome(request.getNome())
                .nacionalidade(request.getNacionalidade())
                .email(request.getEmail())
                .telefone(request.getTelefone())
                .endereco(request.getEndereco())
                .cidade(request.getCidade())
                .documento(request.getDocumento())
                .observacoes(request.getObservacoes())
                .build());

        logAction(authentication, guest.getId(), "CREATED");

        return guest;
    }

    public Guest update(Long id, GuestRequest request, Authentication authentication) {
        Guest guest = findById(id);

        guest.setNome(request.getNome());
        guest.setNacionalidade(request.getNacionalidade());
        guest.setEmail(request.getEmail());
        guest.setTelefone(request.getTelefone());
        guest.setEndereco(request.getEndereco());
        guest.setCidade(request.getCidade());
        guest.setDocumento(request.getDocumento());
        guest.setObservacoes(request.getObservacoes());

        Guest updated = guestRepository.save(guest);
        logAction(authentication, id, "UPDATED");
        return updated;
    }

    public void delete(Long id, Authentication authentication) {
        Guest guest = findById(id);
        guestRepository.delete(guest);
        logAction(authentication, id, "DELETED");
    }

    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    public Guest findById(Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hóspede não encontrado."));
    }

    private void logAction(Authentication authentication, Long idHospede, String acao) {
        if (authentication.getPrincipal() instanceof AuthenticatedUser user) {
            guestLogRepository.save(GuestLog.builder()
                    .idUsuario(user.getId())
                    .idHospede(idHospede)
                    .acao(acao)
                    .dataHora(LocalDateTime.now())
                    .build());
        }
    }
}
