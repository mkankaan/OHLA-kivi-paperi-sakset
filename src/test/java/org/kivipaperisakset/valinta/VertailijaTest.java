package org.kivipaperisakset.valinta;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.kivipaperisakset.valinta.Valinta.*;

class VertailijaTest {
    private Vertailija vertailija = new Vertailija();

    @Test
    void vertaaKiviKivi() {
        assertEquals(vertailija.compare(KIVI, KIVI), 0);
    }

    @Test
    void vertaaPaperiPaperi() {
        assertEquals(vertailija.compare(PAPERI, PAPERI), 0);
    }

    @Test
    void vertaaSaksetSakset() {
        assertEquals(vertailija.compare(SAKSET, SAKSET), 0);
    }

    @Test
    void vertaaKiviPaperi() {
        assertEquals(vertailija.compare(KIVI, PAPERI), -1);
    }

    @Test
    void vertaaPaperiKivi() {
        assertEquals(vertailija.compare(PAPERI, KIVI), 1);
    }

    @Test
    void vertaaKiviSakset() {
        assertEquals(vertailija.compare(KIVI, SAKSET), 1);
    }

    @Test
    void vertaaSaksetKivi() {
        assertEquals(vertailija.compare(SAKSET, KIVI), -1);
    }

    @Test
    void vertaaSaksetPaperi() {
        assertEquals(vertailija.compare(SAKSET, PAPERI), 1);
    }

    @Test
    void vertaaPaperiSakset() {
        assertEquals(vertailija.compare(PAPERI, SAKSET), -1);
    }
}