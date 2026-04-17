package br.com.alura.adopet.api.model;

public enum TipoPet {
    GATO ("GATO"),
    CACHORRO ("CACHORRO");

    private String tipo;

    TipoPet(String text) {
        this.tipo = text;
    }

    public static TipoPet fromString(String text) {
        for (TipoPet tipoPet : TipoPet.values()) {
            if (tipoPet.tipo.equalsIgnoreCase(text)) {
                return tipoPet;
            }
        }
        throw new IllegalArgumentException("Nenhuma tipo encontrado para a string fornecida: " + text);
    }
}
