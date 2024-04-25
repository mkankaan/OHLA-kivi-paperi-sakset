package org.kivipaperisakset;

import org.kivipaperisakset.peli.Peli;
import org.kivipaperisakset.peli.PeliBuilder;

/**
 *
 * @author Ira Dook
 */

public class Main {
    public static void main(String[] args) {
        Peli peli = new PeliBuilder().luo();
        //Peli peli = new Peli(new Pelaaja(), new Pelaaja(), new TulostettavaNumero(3, "kolme"));
        peli.pelaa();
    }
}