package com.checkinn.guest.service;

import com.checkinn.guest.client.AuthClient;
import com.checkinn.guest.dto.GuestRequest;
import com.checkinn.guest.dto.UserAuthResponse;
import com.checkinn.guest.model.Guest;
import com.checkinn.guest.model.GuestLog;
import com.checkinn.guest.repository.GuestLogRepository;
import com.checkinn.guest.repository.GuestRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;
    private final GuestLogRepository guestLogRepository;
    private final AuthClient authClient;

    public Guest save(GuestRequest request, String token) {
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

        logAction(token, guest.getId(), "CREATED");
        return guest;
    }

    public Guest update(Long id, GuestRequest request, String token) {
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
        logAction(token, id, "UPDATED");
        return updated;
    }

    public void delete(Long id, String token) {
        Guest guest = findById(id);
        guestRepository.delete(guest);
        logAction(token, id, "DELETED");
    }

    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    public Guest findById(Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hóspede não encontrado."));
    }

    private void logAction(String token, Long idHospede, String acao) {
        Long idUsuario = extractUserId(token);
        guestLogRepository.save(GuestLog.builder()
                .idUsuario(idUsuario)
                .idHospede(idHospede)
                .acao(acao)
                .dataHora(LocalDateTime.now())
                .build());
    }

    private Long extractUserId(String token) {
        UserAuthResponse user = authClient.validateToken(token);
        return user.getId();
    }
}
