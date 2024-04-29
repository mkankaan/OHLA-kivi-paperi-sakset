package org.kivipaperisakset;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class PeliTest {
    @Test
    void pelaaja1Puuttuu() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Peli(null, new Pelaaja(), 1);
        });

        String expectedMessage = "Pelaaja puuttuu";
        assertTrue(exception.getMessage().toLowerCase().contains(expectedMessage.toLowerCase()));
    }

    @Test
    void pelaaja2Puuttuu() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Peli(new Pelaaja(), null, 9);
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
            new Peli(new Pelaaja(), new Pelaaja(), maxVoitot);
        });

        String expectedMessage = "voittojen maksimimäärän oltava vähintään 1";
        assertTrue(exception.getMessage().toLowerCase().contains(expectedMessage.toLowerCase()));
    }

    @Test
    void eiKahtaSamaaPelaajaa() {
        Pelaaja multitaskaaja = new Pelaaja();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Peli(multitaskaaja, multitaskaaja, 3);
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
            Peli peli = new Peli(pelaaja1, pelaaja2, 4);
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

        Peli peli = new Peli(pelaaja1, new Pelaaja(), 5);
        peli.pelaa();
        assertEquals(peli.getPelatutPelit(), 0);
    }

    @ParameterizedTest
    @CsvSource({
            "1, voitto", "2, voittoa", "11, voittoa"
    })
    void voittojenTulostusOikeassaMuodossa(int maxVoitot, String monikko) {
        Peli peli = new Peli(new Pelaaja(), new Pelaaja(), maxVoitot);
        String tulos = peli.pelaa();
        String testattavaSana = tulos.split("-")[0].split(" ")[1].trim();
        assertEquals(testattavaSana.toLowerCase(), monikko.toLowerCase(), "Voittojen määrän tulostus väärässä muodossa");
    }
}