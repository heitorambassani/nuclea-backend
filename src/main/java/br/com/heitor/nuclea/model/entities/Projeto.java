package br.com.heitor.nuclea.model.entities;

import br.com.heitor.nuclea.model.enums.Risco;
import br.com.heitor.nuclea.model.enums.StatusProjeto;
import br.com.heitor.nuclea.utils.serialize.RiscoDeserializer;
import br.com.heitor.nuclea.utils.serialize.StatusProjetoDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode()
@Entity
@Table(name = "projeto")
@JsonIgnoreProperties(value= {"membros"})
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_previsao_fim")
    private LocalDate dataPrevisaoFim;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "descricao", length = 5000)
    private String descricao;

    @Column(name = "status", length = 45)
    @JsonDeserialize(using = StatusProjetoDeserializer.class)
    private StatusProjeto status;

    @Column(name = "orcamento")
    private Double orcamento;

    @Column(name = "risco")
    @JsonDeserialize(using = RiscoDeserializer.class)
    private Risco risco;

    @ManyToOne
    @JoinColumn(name = "idgerente")
    private Pessoa gerente;

    @ManyToMany
    @JoinTable(name = "membros",
            joinColumns = @JoinColumn(name = "idprojeto"),
            inverseJoinColumns = @JoinColumn(name = "idpessoa"))
    private Set<Pessoa> membros;

}
