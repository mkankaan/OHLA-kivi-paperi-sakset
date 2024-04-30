package org.kivipaperisakset.valinta;

/**
 * Valinta-enumin arvot edustavat mahdollisia siirtovaihtoehtoja
 * kivi-paperi-sakset -pelissä.
 *
 * @author Matleena Kankaanpää
 * @version 1.0 (2023-04-29)
 */

public enum Valinta {
    KIVI("kivi"),
    PAPERI("paperi"),
    SAKSET("sakset");

    private String kuvaus;

    Valinta(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    /**
     * Palauttaa siirron nimen.
     * @return      Siirron nimi merkkijonona.
     */
    @Override
    public String toString() {
        return kuvaus;
    }
}