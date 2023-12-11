package br.com.heitor.nuclea.model.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode()
@Entity
@Table(name = "membros")
@IdClass(MembrosId.class)
public class Membros {
    @Id
    @Column(name = "idprojeto")
    private Long idprojeto;

    @Id
    @Column(name = "idpessoa")
    private Long idpessoa;
}
