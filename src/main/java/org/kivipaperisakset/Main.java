package org.kivipaperisakset;

import org.kivipaperisakset.peli.*;

/**
 *
 * @author Ira Dook
 */

public class Main {
    public static void main(String[] args) {
        Peli peli = new PeliBuilder().maxVoitot(3).luo();
        peli.pelaa();
    }
}