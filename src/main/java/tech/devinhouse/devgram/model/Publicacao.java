package tech.devinhouse.devgram.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PUBLICACOES")
@Data
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PUBLICACAO")
    private Long id;

    private String urlDaImagem;

    private String texto;

    private LocalDateTime dataPublicacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nome", referencedColumnName = "nome")
    private Perfil perfil;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, mappedBy = "publicacao")
    private List<Like> likes;

}
