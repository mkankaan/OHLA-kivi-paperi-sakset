package org.kivipaperisakset.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumeroTekstinaTest {
    NumeroTekstina sanakirja;

    @BeforeEach
    void setUp() {
        sanakirja = NumeroTekstina.getInstance();
    }

    @Test
    void getTekstina() {
        sanakirja.lisaaNumero(4, "neljä");
        assertEquals(sanakirja.getTekstina(4), "neljä");
    }

    @Test
    void oletusPalautusNumerona() {
        int i = 1;
        while (sanakirja.sisaltaa(i)) {
            i++;
        }
        assertEquals(String.valueOf(i), sanakirja.getTekstina(i));
    }
}