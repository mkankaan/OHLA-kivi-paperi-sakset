
package org.kivipaperisakset;

import org.kivipaperisakset.valinta.Valinta;

/**
 * Pelaaja-luokka kuvaa pelin pelaajaa.
 *
 * @author Ira Dook, Matleena Kankaanpää
 * @version 1.0 (2023-04-29)
 */

public class Pelaaja {
    private static int juoksevaNumero = 1;
    private int pelaajaNumero, voitot;

    /**
     * Konstruktori.
     */
    public Pelaaja() {
        final int MAX_PELAAJIEN_LKM = 2;
        this.pelaajaNumero = juoksevaNumero++;

        if (juoksevaNumero > MAX_PELAAJIEN_LKM) {
            resetoiPelaajaNumero();
        }
    }

    /**
     * Palauttaa pelaajalle kertyneet voitot kokonaislukuna.
     * @return Pelaajan voitot kokonaislukuna.
     */
    public int getVoitot() {
        return voitot;
    }

    /**
     * Kasvattaa pelaajan voittojen määrää yhdellä.
     */
    public void lisaaVoitto() {
        voitot++;
    }

    /**
     * Palauttaa satunnaisesti yhden Valinta-enumin arvoista.
     * @return Satunnaisesti arvottu Valinta-enumin arvo.
     */
    public Valinta valitse() {
        Valinta[] vaihtoehdot = Valinta.values();
        return vaihtoehdot[arvoSatunnaisluku(vaihtoehdot.length)];
    }

    /**
     * Tulostaa ja palauttaa tiedon pelaajalle kertyneistä voitoista merkkijonona.
     * @return Merkkijono, joka ilmaisee pelaajan voittojen määrän.
     */
    public String tulostaVoitot() {
        String muoto = pelaajaNumero == 1 ? "ä" : "a";
        String monikko = voitot == 1 ? "" : "a";
        String viesti = "\t " + this + ":ll" + muoto + " koossa " + voitot + " voitto" + monikko + ".";
        System.out.println(viesti);
        return viesti;
    }

    /**
     * Resetoi pelaajanumerona toimivan juoksevan luokkamuuttujan
     * takaisin arvoon 1.
     */
    public static void resetoiPelaajaNumero() {
        juoksevaNumero = 1;
    }

    /**
     * Tulostaa pelaajanumeron muodossa "Pelaaja {pelaajanumero}"
     * @return Merkkijono, joka kertoo pelaajan numeron
     */
    public String toString() {
        return "Pelaaja " + pelaajaNumero;
    }

    private int arvoSatunnaisluku(int ylaraja) {
        return (int) (Math.random() * ylaraja);
    }
}