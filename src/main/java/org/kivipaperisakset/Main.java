package org.kivipaperisakset;

import org.kivipaperisakset.peli.*;

/**
 * Pääohjelma, jossa peli luodaan ja käynnistetään.
 *
 * @author Ira Dook, Matleena Kankaanpää
 * @version 1.0 (2023-04-29)
 */

public class Main {
    public static void main(String[] args) {
        Peli peli = new PeliBuilder().maxVoitot(3).luo();
        peli.pelaa();
    }
}