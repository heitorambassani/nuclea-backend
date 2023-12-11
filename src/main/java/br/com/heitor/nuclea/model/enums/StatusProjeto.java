package br.com.heitor.nuclea.model.enums;

import lombok.Getter;

@Getter
public enum StatusProjeto {

    EM_ANALISE(1, "em análise"),
    ANALISE_REALIZADA(2, "análise realizada"),
    ANALISE_APROVADA(3, "análise aprovada"),
    INICIADO(4, "iniciado"),
    PLANEJADO(5, "planejado"),
    EM_ANDAMENTO(6, "em andamento"),
    ENCERRADO(7, "encerrado"),
    CANCELADO(8, "cancelado");

    private Integer id;
    private String tag;

    public static StatusProjeto fromId(Integer id) {
        for (StatusProjeto statusProjeto : StatusProjeto.values()) {
            if (statusProjeto.getId().equals(id)) {
                return statusProjeto;
            }
        }
        throw new IllegalArgumentException();
    }

    public static StatusProjeto fromTag(String tag) {
        for (StatusProjeto statusProjeto : StatusProjeto.values()) {
            if (statusProjeto.getTag().equalsIgnoreCase(tag)) {
                return statusProjeto;
            }
        }
        throw new IllegalArgumentException();
    }

    private StatusProjeto(Integer id, String tag) {
        this.id = id;
        this.tag = tag;
    }
}
