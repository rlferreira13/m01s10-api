package tech.devinhouse.devgram.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.devinhouse.devgram.dto.PerfilRequest;
import tech.devinhouse.devgram.dto.PerfilResponse;
import tech.devinhouse.devgram.dto.UpdatePerfilRequest;
import tech.devinhouse.devgram.model.Perfil;
import tech.devinhouse.devgram.service.PerfilService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/perfis")
@AllArgsConstructor
public class PerfilController {

    private PerfilService service;
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<PerfilResponse>> listar() {
        List<Perfil> perfis = service.consultar();
        List<PerfilResponse> resp = new ArrayList<>();
        for (Perfil perfil: perfis) {
            PerfilResponse r = mapper.map(perfil, PerfilResponse.class);
            resp.add(r);
        }
//        List<PerfilResponse> resp = perfis.stream()
//                .map(p -> mapper.map(p, PerfilResponse.class)).collect(Collectors.toList());
        return ResponseEntity.ok(resp);
    }

    @GetMapping("{nome}")
    public ResponseEntity<PerfilResponse> listar(@PathVariable("nome") String nome) {
        Perfil perfil = service.consultar(nome);
        PerfilResponse resp = mapper.map(perfil, PerfilResponse.class);
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<PerfilResponse> criar(@RequestBody PerfilRequest request) {
        Perfil perfil = mapper.map(request, Perfil.class);
        perfil = service.criar(perfil);
        PerfilResponse resp = mapper.map(perfil, PerfilResponse.class);
        return ResponseEntity.created(URI.create(resp.getNome())).body(resp);
    }

    @PutMapping("{nome}")
    public ResponseEntity<PerfilResponse> atualizar(@PathVariable("nome") String nome, @RequestBody UpdatePerfilRequest request) {
        Perfil perfil = mapper.map(request, Perfil.class);
        perfil.setNome(nome);
        perfil = service.atualizar(perfil);
        PerfilResponse resp = mapper.map(perfil, PerfilResponse.class);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("{nome}")
    public ResponseEntity excluir(@PathVariable("nome") String nome) {
       service.excluir(nome);
       return ResponseEntity.noContent().build();
    }

}
