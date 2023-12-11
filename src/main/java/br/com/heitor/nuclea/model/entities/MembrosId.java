package br.com.heitor.nuclea.model.entities;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode()
public class MembrosId implements Serializable {
    private Long idprojeto;
    private Long idpessoa;
}
