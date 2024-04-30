package org.kivipaperisakset.valinta;

import java.util.Comparator;
import static org.kivipaperisakset.valinta.Valinta.*;

/**
 *  Vertailija-luokkaa voidaan käyttää Valinta-enumin arvojen
 *  vertailuun määrittäessä kivi-paperi-sakset -pelin voittajaa.
 *  
 *  @author Matleena Kankaanpää
 *  @version 1.0 (2023-04-29)
 */

public class Vertailija implements Comparator<Valinta> {

    /**
     * Vertaa kahta Valinta-enumin arvoa keskenään ja
     * palauttaa tulosta kuvaavan kokonaisluvun.
     * @param valinta1    Ensimmäinen vertailtava arvo.
     * @param valinta2    Ensimmäinen vertailtava arvo.
     * @return            1 jos valinta1 voittaa valinta2:n,
     *                    -1 jos valinta1 häviää valinta2:lle,
     *                    0 jos arvot ovat samat.
     */
    @Override
    public int compare(Valinta valinta1, Valinta valinta2) {
        if (valinta1 == KIVI && valinta2 == SAKSET) {
            return 1;
        }

        if (valinta1 == SAKSET && valinta2 == KIVI) {
            return -1;
        }

        return valinta1.compareTo(valinta2);
    }
}