package org.kivipaperisakset;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PeliTest {
    @Test
    void pelaaja1Puuttuu() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Peli(null, new Pelaaja(), null);
        });

        String expectedMessage = "Pelaaja puuttuu";
        assertTrue(exception.getMessage().toLowerCase().contains(expectedMessage.toLowerCase()));
    }

    @Test
    void pelaaja2Puuttuu() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Peli(new Pelaaja(), null, null);
        });

        String expectedMessage = "Pelaaja puuttuu";
        assertTrue(exception.getMessage().toLowerCase().contains(expectedMessage.toLowerCase()));
    }

    @Test
    void oletusMaxVoitotJosNull() {
        Peli peli = new Peli(new Pelaaja(), new Pelaaja(), null);
        assertEquals(peli.getMaxVoitot(), 1);
    }

    @Test
    void negatiivinenMaxVoitot() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Peli(new Pelaaja(), new Pelaaja(), new TulostettavaNumero(-1, "mahdoton"));
        });

        String expectedMessage = "voittojen maksimimäärän oltava vähintään 1";
        assertTrue(exception.getMessage().toLowerCase().contains(expectedMessage.toLowerCase()));
    }

    @Test
    void eiKahtaSamaaPelaajaa() {
        Pelaaja multitaskaaja = new Pelaaja();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Peli(multitaskaaja, multitaskaaja, null);
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
            Peli peli = new Peli(pelaaja1, pelaaja2, new TulostettavaNumero(3, "kolme"));
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

        Peli peli = new Peli(pelaaja1, new Pelaaja(), new TulostettavaNumero(5, "viisi"));
        peli.pelaa();
        assertEquals(peli.getPelatutPelit(), 0);
    }
}