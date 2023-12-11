package br.com.heitor.nuclea.model.enums;

import lombok.Getter;

@Getter
public enum Risco {

    BAIXO(1, "Baixo"),
    MEDIO(2, "Médio"),
    ALTO(3, "Alto");

    private Integer id;
    private String tag;

    public static Risco fromId(Integer id) {
        for (Risco risco : Risco.values()) {
            if (risco.getId().equals(id)) {
                return risco;
            }
        }
        throw new IllegalArgumentException();
    }

    public static Risco fromTag(String tag) {
        for (Risco risco : Risco.values()) {
            if (risco.getTag().equalsIgnoreCase(tag)) {
                return risco;
            }
        }
        throw new IllegalArgumentException();
    }

    private Risco(Integer id, String tag) {
        this.id = id;
        this.tag = tag;
    }
}
