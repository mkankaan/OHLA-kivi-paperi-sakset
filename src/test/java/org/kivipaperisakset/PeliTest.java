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
    void samaPelaaja() {
        Pelaaja multitaskaaja = new Pelaaja();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Peli(multitaskaaja, multitaskaaja, null);
        });

        String expectedMessage = "pelaaja 1 ja pelaaja 2 eivät voi olla samat";
        assertTrue(exception.getMessage().toLowerCase().contains(expectedMessage));
    }

    @Test
    void tasapeli() {

    }
}