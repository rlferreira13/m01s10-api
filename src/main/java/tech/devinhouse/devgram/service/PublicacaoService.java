package tech.devinhouse.devgram.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.devinhouse.devgram.exception.RegistroExistenteException;
import tech.devinhouse.devgram.exception.RegistroNaoEncontradoException;
import tech.devinhouse.devgram.model.Like;
import tech.devinhouse.devgram.model.Perfil;
import tech.devinhouse.devgram.model.Publicacao;
import tech.devinhouse.devgram.repository.LikeRepository;
import tech.devinhouse.devgram.repository.PerfilRepository;
import tech.devinhouse.devgram.repository.PublicacaoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PublicacaoService {

    private PublicacaoRepository publicacaoRepo;
    private PerfilRepository perfilRepo;
    private LikeRepository likeRepo;


    public Publicacao criar(String nomePerfil, Publicacao publicacao) {
        Perfil perfil = perfilRepo.findById(nomePerfil)
                .orElseThrow(() -> new RegistroNaoEncontradoException(Perfil.class.getSimpleName(), nomePerfil));
        final String url = publicacao.getUrlDaImagem();
        boolean existeComMesmaUrl = perfil.getPublicacoes().stream().anyMatch(p -> p.getUrlDaImagem().equals(url));
        if (existeComMesmaUrl)
            throw new RegistroExistenteException(Publicacao.class.getSimpleName(), url);
        publicacao.setPerfil(perfil);
        publicacao.setDataPublicacao(LocalDateTime.now());
        publicacao = publicacaoRepo.save(publicacao);
        return publicacao;
    }

    public List<Publicacao> consultar(String nomePerfil) {
        Perfil perfil = perfilRepo.findById(nomePerfil)
                .orElseThrow(() -> new RegistroNaoEncontradoException(Perfil.class.getSimpleName(), nomePerfil));
        return perfil.getPublicacoes();
//        return publicacaoRepo.findByNomePerfil(nomePerfil);
    }

    public Publicacao consultar(String nome, Long idPublicacao) {
        Optional<Publicacao> publicacaoOpt = publicacaoRepo.findByPerfilEidPublicacao(nome, idPublicacao);
        return publicacaoOpt.orElseThrow(() -> new RegistroNaoEncontradoException(Publicacao.class.getSimpleName(), idPublicacao));
//        Perfil perfil = perfilRepo.findById(nome)
//                .orElseThrow(() -> new RegistroNaoEncontradoException(Perfil.class.getSimpleName(), nome));
//        Publicacao publicacao = perfil.getPublicacoes().stream()
//                .filter(p -> p.getId().longValue() == idPublicacao).findFirst()
//                .orElseThrow(() -> new RegistroNaoEncontradoException(Publicacao.class.getSimpleName(), idPublicacao));
//        return publicacao;
    }

    public void excluir(String nome, Long idPublicacao) {
        Optional<Publicacao> publicacaoOpt = publicacaoRepo.findByPerfilEidPublicacao(nome, idPublicacao);
        if (publicacaoOpt.isEmpty())
            throw new RegistroNaoEncontradoException(Publicacao.class.getSimpleName(), idPublicacao);
        publicacaoRepo.deleteById(idPublicacao);
    }

    public Publicacao adicionarLike(String perfil, Long idPublicacao, Like like) {
        Publicacao publicacao = this.consultar(perfil, idPublicacao);
        boolean jahTemLikeDestePerfil = likeRepo.findByIdPublicacaoNomePerfil(idPublicacao, like.getNomePerfil()).isPresent();
//        for (Like l : publicacao.getLikes()) {
//            if (l.getNomePerfil().equals(like.getNomePerfil())) {
//                jahTemLikeDestePerfil = true;
//                break;
//            }
//        }
//        boolean jahTemLikeDestePerfil = publicacao.getLikes().stream()
//                .anyMatch(l -> l.getNomePerfil().equals(like.getNomePerfil()));
        if (jahTemLikeDestePerfil)
            throw new IllegalArgumentException("Perfil já deu like nesta publicacao");
        like.setPublicacao(publicacao);
        like.setDataInclusao(LocalDateTime.now());
        like = likeRepo.save(like);
        publicacao.getLikes();
        return publicacao;
    }
}