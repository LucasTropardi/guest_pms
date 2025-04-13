package com.checkinn.guest.dto;

import com.checkinn.guest.model.Guest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GuestResponse {

    private Long id;
    private String nome;
    private Long nacionalidade;
    private String email;
    private String telefone;
    private String endereco;
    private Long cidade;
    private String documento;
    private String observacoes;

    public GuestResponse(Guest guest) {
        this.id = guest.getId();
        this.nome = guest.getNome();
        this.nacionalidade = guest.getNacionalidade();
        this.email = guest.getEmail();
        this.telefone = guest.getTelefone();
        this.endereco = guest.getEndereco();
        this.cidade = guest.getCidade();
        this.documento = guest.getDocumento();
        this.observacoes = guest.getObservacoes();
    }
}
