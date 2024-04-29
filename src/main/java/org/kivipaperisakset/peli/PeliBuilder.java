package org.kivipaperisakset.peli;

import org.kivipaperisakset.Pelaaja;

/**
 * Builder-mallin mukaisesti toimiva luokka, jonka avulla voidaan
 * luoda Peli-olio tietyillä halutuilla ominaisuuksilla.
 *
 * @author Matleena Kankaanpää
 * @version 1.0 (2023-04-29)
 */

public class PeliBuilder {
    private Peli peli;

    /**
     * Konstruktori.
     */
    public PeliBuilder() {
        reset();
    }

    /**
     * Asettaa pelin 1. pelaajan ja palauttaa itsensä.
     * @param pelaaja   Pelaaja, joka asetetaan pelin 1. pelaajaksi.
     * @return          PeliBuilder-olio itse.
     */
    public PeliBuilder pelaaja1(Pelaaja pelaaja) {
        peli.setPelaaja1(pelaaja);
        return this;
    }

    /**
     * Asettaa pelin 2. pelaajan ja palauttaa itsensä.
     * @param pelaaja   Pelaaja, joka asetetaan pelin 2. pelaajaksi.
     * @return          PeliBuilder-olio itse.
     */
    public PeliBuilder pelaaja2(Pelaaja pelaaja) {
        peli.setPelaaja2(pelaaja);
        return this;
    }

    /**
     * Asettaa pelin maksimivoittojen määrän ja palauttaa itsensä.
     * @param maxVoitot Pelille asetettava maksimivoittojen määrä.
     * @return          PeliBuilder-olio itse.
     */
    public PeliBuilder maxVoitot(int maxVoitot) {
        peli.setMaxVoitot(maxVoitot);
        return this;
    }

    /**
     * Palauttaa tämän luokan avulla luodun Peli-olion.
     * @return          Luotu Peli-olio.
     */
    public Peli luo() {
        return peli;
    }

    /**
     * Luo uuden Peli-olion alkutilassa.
     */
    public void reset() {
        this.peli = new Peli();
    }
}
