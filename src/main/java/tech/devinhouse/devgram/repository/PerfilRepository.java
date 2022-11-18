package tech.devinhouse.devgram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.devgram.model.Perfil;
import tech.devinhouse.devgram.model.Status;

import java.util.List;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, String> {

    List<Perfil> findByStatus(Status status);

}
