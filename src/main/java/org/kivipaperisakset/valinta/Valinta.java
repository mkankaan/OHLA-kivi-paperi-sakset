package org.kivipaperisakset.valinta;

public enum Valinta {
    KIVI("kivi"),
    PAPERI("paperi"),
    SAKSET("sakset");

    private String kuvaus;

    Valinta(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    @Override
    public String toString() {
        return kuvaus;
    }
}