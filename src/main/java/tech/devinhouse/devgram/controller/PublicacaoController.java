package tech.devinhouse.devgram.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.devinhouse.devgram.dto.*;
import tech.devinhouse.devgram.model.Like;
import tech.devinhouse.devgram.model.Publicacao;
import tech.devinhouse.devgram.service.PublicacaoService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/perfis/{perfil}/publicacoes")
@AllArgsConstructor
public class PublicacaoController {

    private PublicacaoService service;
    private ModelMapper mapper;


    @PostMapping
    public ResponseEntity<PublicacaoResponse> criar(@PathVariable("perfil") String perfil, @RequestBody @Valid PublicacaoRequest request) {
        Publicacao publicacao = mapper.map(request, Publicacao.class);
        publicacao = service.criar(perfil, publicacao);
        PublicacaoResponse resp = mapper.map(publicacao, PublicacaoResponse.class);
        return ResponseEntity.created(URI.create(resp.getId().toString())).body(resp);
    }

    @GetMapping
    public ResponseEntity<List<PublicacaoResponse>> listar(@PathVariable("perfil") String perfil) {
        List<Publicacao> publicacoes = service.consultar(perfil);
        List<PublicacaoResponse> resp = publicacoes.stream().map(p -> mapper.map(p, PublicacaoResponse.class)).collect(Collectors.toList());
        return ResponseEntity.ok(resp);
    }

    @GetMapping("{id}")
    public ResponseEntity<PublicacaoResponse> consultar(@PathVariable("perfil") String perfil, @PathVariable("id") Long id) {
        Publicacao publicacao = service.consultar(perfil, id);
        PublicacaoResponse resp = mapper.map(publicacao, PublicacaoResponse.class);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PublicacaoResponse> excluir(@PathVariable("perfil") String perfil, @PathVariable("id") Long id) {
        service.excluir(perfil, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{idPublicacao}/like")
    public ResponseEntity<PublicacaoResponse> darLike(
            @PathVariable("perfil") String nomePerfil,
            @PathVariable("idPublicacao") Long idPublicacao,
            @RequestBody @Valid LikeRequest request) {
        Like like = mapper.map(request, Like.class);
        try {
            Publicacao publicacao = service.adicionarLike(nomePerfil, idPublicacao, like);
            PublicacaoResponse resp = mapper.map(publicacao, PublicacaoResponse.class);
            return ResponseEntity.ok(resp);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
