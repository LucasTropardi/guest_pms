package com.checkinn.guest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Nacionalidade é obrigatória")
    private Long nacionalidade;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;

    @NotNull(message = "Cidade é obrigatória")
    private Long cidade;

    @NotBlank(message = "Documento é obrigatório")
    private String documento;

    private String observacoes;
}
