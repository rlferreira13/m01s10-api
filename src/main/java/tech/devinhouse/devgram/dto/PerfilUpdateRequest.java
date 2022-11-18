package tech.devinhouse.devgram.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class PerfilUpdateRequest {

    @NotEmpty(message = "{campo.obrigatorio}")
    @Size(min = 2, max = 100, message = "{campo.invalido}")
    private String biografia;

    @NotNull(message = "{campo.obrigatorio}")
    @Past(message = "{campo.invalido}")
    private LocalDate dataNascimento;

    @NotEmpty(message = "{campo.obrigatorio}")
    @Size(min = 4, max = 20, message = "{campo.invalido}")
    private String profissao;

}
