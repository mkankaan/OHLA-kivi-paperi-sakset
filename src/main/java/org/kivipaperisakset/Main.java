package org.kivipaperisakset;

/**
 *
 * @author Ira Dook
 */

public class Main {
    public static void main(String[] args) {
        Peli peli = new Peli(new Pelaaja(), new Pelaaja(), new TulostettavaNumero(3, "kolme"));
        peli.pelaa();
    }
}