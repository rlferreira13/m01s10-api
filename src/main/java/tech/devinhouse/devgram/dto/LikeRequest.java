package tech.devinhouse.devgram.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LikeRequest {

    @NotEmpty(message = "{campo.obrigatorio}")
    private String nomePerfil;  // nome do perfuil que curtiu a publicacao

}
