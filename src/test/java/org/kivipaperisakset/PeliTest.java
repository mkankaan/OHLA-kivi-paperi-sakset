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
    void samaPelaaja() {
        Pelaaja multitaskaaja = new Pelaaja();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            peliBuilder.pelaaja1(multitaskaaja).pelaaja2(multitaskaaja);
        });

        String expectedMessage = "Pelaaja 1 ja pelaaja 2 eivät voi olla samat";
        assertTrue(exception.getMessage().toLowerCase().contains(expectedMessage.toLowerCase()));
    }
}