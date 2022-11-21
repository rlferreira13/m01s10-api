package tech.devinhouse.devgram.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PublicacaoResponse {

    private Long id;

    private String urlDaImagem;

    private String texto;

    private LocalDateTime dataPublicacao;

    private List<LikeResponse> likes;

}
