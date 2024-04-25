package org.kivipaperisakset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kivipaperisakset.peli.Peli;
import org.kivipaperisakset.peli.PeliBuilder;
import static org.junit.jupiter.api.Assertions.*;

class PeliTest {
    private PeliBuilder peliBuilder = new PeliBuilder();

    @BeforeEach
    void setUp() {
        peliBuilder.reset();
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

    @Test
    void oletusMaxVoitotJosNull() {
        Peli peli = peliBuilder.maxVoitot(null).luo();
        assertEquals(peli.getMaxVoitot(), 1);
    }

    @Test
    void negatiivinenMaxVoitot() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            peliBuilder.maxVoitot(new TulostettavaNumero(-1, "virhe")).luo();
        });

        String expectedMessage = "voittojen maksimimäärän oltava vähintään 1";
        assertTrue(exception.getMessage().toLowerCase().contains(expectedMessage.toLowerCase()));
    }

    @Test
    void samaPelaaja() {
        Pelaaja multitaskaaja = new Pelaaja();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            peliBuilder.pelaaja1(multitaskaaja).pelaaja2(multitaskaaja);
        });

        String expectedMessage = "pelaaja 1 ja pelaaja 2 eivät voi olla samat";
        assertTrue(exception.getMessage().toLowerCase().contains(expectedMessage));
    }

    @Test
    void oletusKonstruktori() {
        Peli peli = peliBuilder.luo();
        assertEquals(peli.getPelaaja1().toString().toLowerCase(), "pelaaja 1");
        assertEquals(peli.getPelaaja2().toString().toLowerCase(), "pelaaja 2");
        assertEquals(peli.getMaxVoitot(), 1);
    }
}