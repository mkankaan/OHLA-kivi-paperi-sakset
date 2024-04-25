
package org.kivipaperisakset;

import org.kivipaperisakset.valinta.Valinta;

/**
 *
 * @author Ira Dook
 */

public class Pelaaja {
    private static int juoksevaNumero = 1;
    private int pelaajaNumero, voitot;

    public Pelaaja() {
        this.pelaajaNumero = juoksevaNumero++;
    }

    public Pelaaja(int numero) {
        this.pelaajaNumero = numero;
    }

    public int getVoitot() {
        return voitot;
    }

    public void lisaaVoitto() {
        voitot++;
    }

    public Valinta valitse() {
        Valinta[] vaihtoehdot = Valinta.values();
        return vaihtoehdot[arvoSatunnaisluku(vaihtoehdot.length)];
    }

    // yläraja mukana vai ei?
    private int arvoSatunnaisluku(int ylaraja) {
        return (int) (Math.random() * ylaraja);
    }

    public String tulostaVoitot() {
        String muoto = pelaajaNumero == 1 ? "ä" : "a";
        String monikko = voitot == 1 ? "" : "a";
        String viesti = "\t " + this + ":ll" + muoto + " koossa " + voitot + " voitto" + monikko + ".";
        System.out.println(viesti);
        return viesti;
    }

    public String toString() {
        return "Pelaaja " + pelaajaNumero;
    }
}