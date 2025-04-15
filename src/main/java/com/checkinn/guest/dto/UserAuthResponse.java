package com.checkinn.guest.dto;

import lombok.Getter;

@Getter
public class UserAuthResponse {
    private Long id;
    private String nome;
    private String email;
    private String funcao;
    private String role;
}
