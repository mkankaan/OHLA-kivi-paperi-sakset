package org.kivipaperisakset.peli;

import org.kivipaperisakset.Pelaaja;
import org.kivipaperisakset.utils.NumeroTekstina;
import org.kivipaperisakset.valinta.Vertailija;
import org.kivipaperisakset.valinta.Valinta;
import static org.kivipaperisakset.Pelaaja.resetoiPelaajaNumero;

public class Peli {
    private Pelaaja pelaaja1, pelaaja2;
    private int pelatutPelit, tasapelit, maxVoitot;
    private final int OLETUS_MAX_VOITOT = 1;

    Peli() {
        resetoiPelaajaNumero();
        this.pelaaja1 = new Pelaaja();
        this.pelaaja2 = new Pelaaja();
        vertaaPelaajia();
        this.maxVoitot = OLETUS_MAX_VOITOT;
        this.pelatutPelit = 0;
        this.tasapelit = 0;
    }

    private Pelaaja validoiPelaaja(Pelaaja pelaaja) {
        if (pelaaja == null) {
            throw new IllegalArgumentException("Pelaaja puuttuu");
        }
        return pelaaja;
    }

    private void vertaaPelaajia() {
        if (this.pelaaja1 == this.pelaaja2) {
            throw new IllegalArgumentException("Pelaaja 1 ja pelaaja 2 eivät voi olla samat");
        }
    }

    public int getTasapelit() {
        return tasapelit;
    }

    public int getPelatutPelit() {
        return pelatutPelit;
    }

    public String pelaa() {
        while (!peliLoppui()) {
            tulostaEra();
            tulostaTasapelit();

            Valinta pelaaja1Valinta = pelaaVuoro(pelaaja1);
            Valinta pelaaja2Valinta = pelaaVuoro(pelaaja2);

            Vertailija vertailija = new Vertailija();
            int tulos = vertailija.compare(pelaaja1Valinta, pelaaja2Valinta);

            if (tulos > 0) {
                kasitteleVoitto(pelaaja1);
            } else if (tulos < 0) {
                kasitteleVoitto(pelaaja2);
            } else {
                kasitteleTasapeli();
            }

            pelatutPelit++;
            System.out.println();
        };
        return tulostaVoitot();
    }

    private boolean peliLoppui() {
        return (pelaaja1.getVoitot() >= maxVoitot || (pelaaja2.getVoitot() >= maxVoitot));
    }

    private Valinta pelaaVuoro(Pelaaja pelaaja) {
        Valinta valinta = pelaaja.valitse();
        System.out.println(pelaaja + ": " + valinta);
        pelaaja.tulostaVoitot();
        return valinta;
    }

    private void kasitteleVoitto(Pelaaja pelaaja) {
        System.out.println(pelaaja + " voittaa");
        pelaaja.lisaaVoitto();
    }

    private void kasitteleTasapeli() {
        System.out.println("\n\t\t\t Tasapeli \n");
        tasapelit++;
    }

    private void tulostaEra() {
        System.out.println("Erä: " + pelatutPelit + " =====================\n");
    }

    private void tulostaTasapelit() {
        System.out.println("Tasapelien lukumäärä: " + tasapelit + "\n");
    }

    private String tulostaVoitot() {
        NumeroTekstina sanakirja = NumeroTekstina.getInstance();
        String monikko = maxVoitot == 1 ? "" : "A";
        String viesti = sanakirja.getTekstina(maxVoitot).toUpperCase() + " VOITTO" + monikko + " - PELI PÄÄTTYY";
        System.out.println(viesti);
        return viesti;
    }

    public void setPelaaja1(Pelaaja pelaaja) {
        this.pelaaja1 = validoiPelaaja(pelaaja);
        vertaaPelaajia();
    }

    public void setPelaaja2(Pelaaja pelaaja) {
        this.pelaaja2 = validoiPelaaja(pelaaja);
        vertaaPelaajia();
    }

    public void setMaxVoitot(int maxVoitot) {
        if (maxVoitot < 1) {
            throw new IllegalArgumentException("Voittojen maksimimäärän oltava vähintään 1");
        }
        this.maxVoitot = maxVoitot;
    }

    public Pelaaja getPelaaja1() {
        return pelaaja1;
    }

    public Pelaaja getPelaaja2() {
        return pelaaja2;
    }

    public int getMaxVoitot() {
        return maxVoitot;
    }
}