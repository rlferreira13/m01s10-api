package tech.devinhouse.devgram.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdatePerfilRequest {

    private String biografia;

    private LocalDate dataNascimento;

    private String profissao;

}
