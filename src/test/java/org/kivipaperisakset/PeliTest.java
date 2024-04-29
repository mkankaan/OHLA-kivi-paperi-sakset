package org.kivipaperisakset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.kivipaperisakset.peli.Peli;
import org.kivipaperisakset.peli.PeliBuilder;
import static org.junit.jupiter.api.Assertions.*;

class PeliTest {
    private PeliBuilder peliBuilder = new PeliBuilder();
    private Peli peli;

    @BeforeEach
    void setUp() {
        peliBuilder.reset();
    }

    @Test
    void oletusKonstruktori() {
        peli = peliBuilder.luo();
        assertEquals(peli.getPelaaja1().toString().toLowerCase(), "pelaaja 1");
        assertEquals(peli.getPelaaja2().toString().toLowerCase(), "pelaaja 2");
        assertEquals(peli.getMaxVoitot(), 1);
    }

    @Test
    void pelaaja1Puuttuu() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            peliBuilder.pelaaja1(null);
        });

        String expectedMessage = "Pelaaja puuttuu";
        assertTrue(exception.getMessage().toLowerCase().contains(expectedMessage.toLowerCase()));
    }

    @Test
    void pelaaja2Puuttuu() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            peliBuilder.pelaaja2(null);
        });

        String expectedMessage = "Pelaaja puuttuu";
        assertTrue(exception.getMessage().toLowerCase().contains(expectedMessage.toLowerCase()));
    }

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

    @Test
    void eiKahtaSamaaPelaajaa() {
        Pelaaja multitaskaaja = new Pelaaja();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            peliBuilder.pelaaja1(multitaskaaja).pelaaja2(multitaskaaja);
        });

        String expectedMessage = "pelaaja 1 ja pelaaja 2 eivät voi olla samat";
        assertTrue(exception.getMessage().toLowerCase().contains(expectedMessage));
    }

    @Test
    void tasapeli() {
        boolean tasapeli = false;

        while (!tasapeli) {
            Pelaaja pelaaja1 = new Pelaaja();
            Pelaaja pelaaja2 = new Pelaaja();
            peli = peliBuilder.pelaaja1(pelaaja1).pelaaja2(pelaaja2).maxVoitot(4).luo();
            peli.pelaa();

            if (peli.getTasapelit() > 0) {
                tasapeli = true;
                assert(pelaaja1.getVoitot() < peli.getPelatutPelit());
                assert(pelaaja2.getVoitot() < peli.getPelatutPelit());
                assertEquals((pelaaja1.getVoitot() + pelaaja2.getVoitot() + peli.getTasapelit()), peli.getPelatutPelit());
            }
        }
    }

    @Test
    void eiYlitaMaksimivoittoja() {
        Pelaaja pelaaja1 = new Pelaaja();

        for (int i = 0; i < 6; i++) {
            pelaaja1.lisaaVoitto();
        }

        peli = peliBuilder.pelaaja1(pelaaja1).maxVoitot(5).luo();
        peli.pelaa();
        assertEquals(peli.getPelatutPelit(), 0);
    }

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