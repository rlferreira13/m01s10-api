package tech.devinhouse.devgram.service;

import lombok.AllArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.devinhouse.devgram.exception.RegistroExistenteException;
import tech.devinhouse.devgram.exception.RegistroNaoEncontradoException;
import tech.devinhouse.devgram.model.Perfil;
import tech.devinhouse.devgram.repository.PerfilRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PerfilService {

    private PerfilRepository repo;

    public List<Perfil> consultar() {
        return repo.findAll();
    }

    public Perfil consultar(String nome) {
        Optional<Perfil> perfilOpt = repo.findById(nome);
        if (perfilOpt.isPresent())
            return perfilOpt.get();
        throw new RegistroNaoEncontradoException("Perfil", nome);
//        return perfilOpt.orElseThrow(() -> new RegistroNaoEncontradoException("Nao existe perfil com este nome"));
    }

    public Perfil criar(Perfil perfil) {
        boolean existe = repo.existsById(perfil.getNome());
        if (existe)
            throw new RegistroExistenteException("Perfil", perfil.getNome());
        perfil.setDataCriacao(LocalDateTime.now());
        perfil.setDataAtualizacao(LocalDateTime.now());
        repo.save(perfil);
        return perfil;
    }

    public Perfil atualizar(Perfil perfil) {
//        Perfil perfilBD = repo.findById(perfil.getNome())
//                .orElseThrow(() -> new RegistroNaoEncontradoException("Perfil Nao encontrado"));
        Optional<Perfil> perfilOpt = repo.findById(perfil.getNome());
        if (perfilOpt.isEmpty())
            throw new RegistroNaoEncontradoException("Perfil", perfil.getNome());
        Perfil perfilBD = perfilOpt.get();
        perfilBD.setBiografia(perfil.getBiografia());
        perfilBD.setProfissao(perfil.getProfissao());
        perfilBD.setDataNascimento(perfil.getDataNascimento());
        perfilBD.setDataAtualizacao(LocalDateTime.now());
        repo.save(perfilBD);
        return perfilBD;
    }

    public void excluir(String nome) {
        boolean existe = repo.existsById(nome);
        if (!existe) {
            throw new RegistroNaoEncontradoException("Perfil", nome);
        }
        repo.deleteById(nome);
    }

}
