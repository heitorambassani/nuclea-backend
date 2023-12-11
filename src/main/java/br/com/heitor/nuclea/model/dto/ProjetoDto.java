package br.com.heitor.nuclea.model.dto;

import br.com.heitor.nuclea.model.entities.Pessoa;
import br.com.heitor.nuclea.model.enums.Risco;
import br.com.heitor.nuclea.model.enums.StatusProjeto;
import br.com.heitor.nuclea.utils.serialize.RiscoDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjetoDto implements Serializable {
    private Long id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataPrevisaoFim;
    private String descricao;
    @JsonDeserialize(using = RiscoDeserializer.class)
    private StatusProjeto status;
    private Risco risco;
    private Pessoa gerente;
}
