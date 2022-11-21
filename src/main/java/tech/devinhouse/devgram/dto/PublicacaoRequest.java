package tech.devinhouse.devgram.dto;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PublicacaoRequest {

    @NotEmpty(message = "{campo.obrigatorio}")
    @URL(message = "{campo.invalido}")
    private String urlDaImagem;

    @Size(min = 1, max = 200, message = "{campo.invalido}")
    private String texto;

}
