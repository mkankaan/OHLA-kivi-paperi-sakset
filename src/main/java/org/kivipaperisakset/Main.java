package org.kivipaperisakset;

/**
 *
 * @author Ira Dook
 */

public class Main {
    public static void main(String[] args) {
        Peli peli = new Peli(new Pelaaja(), new Pelaaja(), 3);
        peli.pelaa();
    }
}