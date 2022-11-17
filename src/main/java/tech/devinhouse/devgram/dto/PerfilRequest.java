package tech.devinhouse.devgram.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PerfilRequest {

    private String nome;

    private String biografia;

    private LocalDate dataNascimento;

    private String profissao;

}
