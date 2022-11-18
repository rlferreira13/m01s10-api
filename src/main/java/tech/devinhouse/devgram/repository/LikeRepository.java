package tech.devinhouse.devgram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.devinhouse.devgram.model.Like;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query("select l from Like l where l.publicacao.id = :idPublicacao and l.nomePerfil = :nomePerfil")
    Optional<Like> findByIdPublicacaoNomePerfil(Long idPublicacao, String nomePerfil);

}
