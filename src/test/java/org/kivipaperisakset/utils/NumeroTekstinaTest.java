package org.kivipaperisakset.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * NumeroTekstina-luokka sisältää NumeroTekstina-luokan testit.
 *
 *  @author Matleena Kankaanpää
 *  @version 1.0 (2023-04-29)
 */

class NumeroTekstinaTest {
    private NumeroTekstina sanakirja = NumeroTekstina.getInstance();

    /**
     * Tarkistaa, että getTekstina palauttaa odotetun merkkijonon, kun
     * numero löytyy sanakirjasta.
     */
    @Test
    void getTekstina() {
        sanakirja.lisaaNumero(4, "neljä");
        assertEquals(sanakirja.getTekstina(4), "neljä");
    }

    /**
     * Testaa, että getTekstina palauttaa sille annetun kokonaisluvun
     * muutettuna merkkijonoksi siinä tapauksessa, että numeroa ei löydy sanakirjasta.
     */
    @Test
    void oletusPalautusNumerona() {
        int i = 1;
        while (sanakirja.sisaltaa(i)) {
            i++;
        }
        assertEquals(String.valueOf(i), sanakirja.getTekstina(i));
    }
}