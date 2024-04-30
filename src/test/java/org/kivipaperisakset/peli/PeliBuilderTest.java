package org.kivipaperisakset.peli;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kivipaperisakset.Pelaaja;

import static org.junit.jupiter.api.Assertions.*;

class PeliBuilderTest {
    private PeliBuilder peliBuilder = new PeliBuilder();

    /**
     * Toiminnot, jotka suoritetaan ennen jokaista testiä.
     * Resetoidaan alkutilaan Peli-olio, jota ollaan luomassa.
     */
    @BeforeEach
    void setUp() {
        peliBuilder.reset();
    }

    /**
     * Testaa, asettavatko builderin pelaaja1- ja pelaaja2-setterit pelaajat
     * haluttuihin paikkoihin. (Testissä sijoitetaan pelaajat tarkoituksella "ristiin".)
     */
    @Test
    void lisaaPelaajatOikein() {
        Pelaaja pelaaja1 = new Pelaaja();
        Pelaaja pelaaja2 = new Pelaaja();
        Peli peli = peliBuilder.pelaaja1(pelaaja2).pelaaja2(pelaaja1).luo();
        assertEquals(peli.getPelaaja1().toString().toLowerCase(), "pelaaja 2");
        assertEquals(peli.getPelaaja2().toString().toLowerCase(), "pelaaja 1");
    }

    /**
     * Testaa, asettaako builder pelin maksimivoitot oikein.
     */
    @Test
    void maxVoitot() {
        Peli peli = peliBuilder.maxVoitot(300).luo();
        assertEquals(peli.getMaxVoitot(), 300);
    }

    /**
     * Restaa, resetoiko builder pelin oikein.
     */
    @Test
    void reset() {
        Peli peli = peliBuilder.maxVoitot(20).luo();
        peliBuilder.reset();
        peli = peliBuilder.luo();
        assertEquals(peli.getMaxVoitot(), 1);
    }
}