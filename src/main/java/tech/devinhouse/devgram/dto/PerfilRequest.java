package tech.devinhouse.devgram.dto;

import lombok.Data;
import tech.devinhouse.devgram.model.Status;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class PerfilRequest {

    @NotEmpty(message = "{campo.obrigatorio}")
    @Size(min = 4, max = 40, message = "{campo.invalido}")
    private String nome;

    @NotEmpty(message = "{campo.obrigatorio}")
    private String biografia;

    @NotNull(message = "{campo.obrigatorio}")
    @Past(message = "{campo.invalido}")
    private LocalDate dataNascimento;

    @NotEmpty(message = "{campo.obrigatorio}")
    @Size(min = 4, max = 20, message = "{campo.invalido}")
    private String profissao;

    @NotNull(message = "{campo.obrigatorio}")
    private Status status;

}
