package tech.devinhouse.devgram.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LIKES")
@Data
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LIKE")
    private Long id;

    private String nomePerfil;

    private LocalDateTime dataInclusao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PUBLICACAO", referencedColumnName = "ID_PUBLICACAO")
    private Publicacao publicacao;

}