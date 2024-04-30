package org.kivipaperisakset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.kivipaperisakset.peli.Peli;
import org.kivipaperisakset.peli.PeliBuilder;
import static org.junit.jupiter.api.Assertions.*;

/**
 * PeliTest-luokka sisältää Peli-luokan testit.
 *
 *  @author Matleena Kankaanpää
 *  @version 1.0 (2023-04-29)
 */

class PeliTest {
    private PeliBuilder peliBuilder = new PeliBuilder();
    private Peli peli;

    /**
     * Toiminnot, jotka suoritetaan ennen jokaista testiä.
     * Peli resetoidaan alkutilaan.
     */
    @BeforeEach
    void setUp() {
        peliBuilder.reset();
    }

    /**
     * Testaa pelin oletuskonstruktoria.
     */
    @Test
    void oletusKonstruktori() {
        peli = peliBuilder.luo();
        assertEquals(peli.getPelaaja1().toString().toLowerCase(), "pelaaja 1");
        assertEquals(peli.getPelaaja2().toString().toLowerCase(), "pelaaja 2");
        assertEquals(peli.getMaxVoitot(), 1);
    }

    /**
     * Testaa, heitetäänkö odotettu poikkeus, jos pelaaja1:ksi yritetään asettaa null.
     */
    @Test
    void pelaaja1Puuttuu() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            peliBuilder.pelaaja1(null);
        });

        String expectedMessage = "Pelaaja puuttuu";
        assertTrue(exception.getMessage().toLowerCase().contains(expectedMessage.toLowerCase()));
    }

    /**
     * Testaa, heitetäänkö odotettu poikkeus, jos pelaaja2:ksi yritetään asettaa null.
     */
    @Test
    void pelaaja2Puuttuu() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            peliBuilder.pelaaja2(null);
        });

        String expectedMessage = "Pelaaja puuttuu";
        assertTrue(exception.getMessage().toLowerCase().contains(expectedMessage.toLowerCase()));
    }

    /**
     * Testaa, heitetäänkö odotettu poikkeus, jos voittojen maksimiarvoksi
     * yritetään asettaa nolla tai negatiivinen luku.
     */
    @ParameterizedTest
    @CsvSource({
            "-16", "-1", "0"
    })
    void maxVoitotNollaTaiAlle(int maxVoitot) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            peliBuilder.maxVoitot(maxVoitot);
        });

        String expectedMessage = "voittojen maksimimäärän oltava vähintään 1";
        assertTrue(exception.getMessage().toLowerCase().contains(expectedMessage.toLowerCase()));
    }

    /**
     * Testaa, heitetäänkö odotettu poikkeus, jos pelaaja1:ksi ja pelaaja2:ksi
     * yritetään asettaa sama Pelaaja-olio.
     */
    @Test
    void eiKahtaSamaaPelaajaa() {
        Pelaaja multitaskaaja = new Pelaaja();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            peliBuilder.pelaaja1(multitaskaaja).pelaaja2(multitaskaaja);
        });

        String expectedMessage = "pelaaja 1 ja pelaaja 2 eivät voi olla samat";
        assertTrue(exception.getMessage().toLowerCase().contains(expectedMessage));
    }

    /**
     * Testaa, lasketaanko tasapelien määrä oikein kummallekin pelaajalle.
     */
    @Test
    void tasapelitOikein() {
        boolean tasapeli = false;

        while (!tasapeli) {
            Pelaaja pelaaja1 = new Pelaaja();
            Pelaaja pelaaja2 = new Pelaaja();
            peli = peliBuilder.pelaaja1(pelaaja1).pelaaja2(pelaaja2).maxVoitot(4).luo();
            peli.pelaa();

            if (peli.getTasapelit() > 0) {
                tasapeli = true;
                assert(pelaaja1.getVoitot() < peli.getPelatutKierrokset());
                assert(pelaaja2.getVoitot() < peli.getPelatutKierrokset());
                assertEquals((pelaaja1.getVoitot() + pelaaja2.getVoitot() + peli.getTasapelit()), peli.getPelatutKierrokset());
            }
        }
    }

    /**
     * Testaa, jatkuuko peli, jos maksimivoittojen määrä on ylitetty.
     */
    @Test
    void eiYlitaMaksimivoittoja() {
        Pelaaja pelaaja1 = new Pelaaja();

        for (int i = 0; i < 6; i++) {
            pelaaja1.lisaaVoitto();
        }

        peli = peliBuilder.pelaaja1(pelaaja1).maxVoitot(5).luo();
        peli.pelaa();
        assertEquals(peli.getPelatutKierrokset(), 0);
    }

    /**
     * Testaa, tulostetaanko pelin tulokset oikeassa muodossa voittojen
     * eri määrillä.
     * @param maxVoitot     Voittojen määrä kullakin kierroksella kokonaislukuna.
     * @param monikko       Tulostuksessa odotettava merkkijono "voitto"/"voittoa"
     *                      riippuen siitä, loppuuko peli yhteen vai useampaan voittoon.
     */
    @ParameterizedTest
    @CsvSource({
            "1, voitto", "2, voittoa", "11, voittoa"
    })
    void voittojenTulostusOikeassaMuodossa(int maxVoitot, String monikko) {
        peli = peliBuilder.maxVoitot(maxVoitot).luo();
        String tulos = peli.pelaa();
        String testattavaSana = tulos.split("-")[0].split(" ")[1].trim();
        assertEquals(testattavaSana.toLowerCase(), monikko.toLowerCase(), "Voittojen määrän tulostus väärässä muodossa");
    }
}