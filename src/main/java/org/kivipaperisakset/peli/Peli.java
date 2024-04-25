package org.kivipaperisakset.peli;

import org.kivipaperisakset.Pelaaja;
import org.kivipaperisakset.TulostettavaNumero;
import org.kivipaperisakset.valinta.Vertailija;
import org.kivipaperisakset.valinta.Valinta;

public class Peli {
    private Pelaaja pelaaja1, pelaaja2;
    private TulostettavaNumero maxVoitot;
    private int pelatutPelit, tasapelit;
    private TulostettavaNumero oletusMaxVoitot;

    Peli() {
        this.pelaaja1 = new Pelaaja();
        this.pelaaja2 = new Pelaaja();
        this.oletusMaxVoitot = new TulostettavaNumero(1, "yksi");
        this.maxVoitot = oletusMaxVoitot;
        this.pelatutPelit = 0;
        this.tasapelit = 0;
    }

    public void setPelaaja1(Pelaaja pelaaja) {
        this.pelaaja1 = validoiPelaaja(pelaaja);
        vertaaPelaajia();
    }

    public void setPelaaja2(Pelaaja pelaaja) {
        this.pelaaja2 = validoiPelaaja(pelaaja);
        vertaaPelaajia();
    }

    public void setMaxVoitot(TulostettavaNumero maxVoitot) {
        if (maxVoitot == null) {
            this.maxVoitot = oletusMaxVoitot;
            return;
        }

        if (maxVoitot.getLukumaara() < 1) {
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
        return this.maxVoitot.getLukumaara();
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

    public boolean pelaa() {
        do {
            tulostaEra();
            tulostaTasapelit();

            Valinta pelaaja1Valinta = pelaaVuoro(pelaaja1);
            Valinta pelaaja2Valinta = pelaaVuoro(pelaaja2);

            Vertailija vertailija = new Vertailija();
            int tulos = vertailija.compare(pelaaja1Valinta, pelaaja2Valinta);
            boolean pelaaja1Voitti = tulos > 0;
            boolean pelaaja2Voitti = tulos < 0;

            if (pelaaja1Voitti) {
                kasitteleVoitto(pelaaja1);
            } else if (pelaaja2Voitti) {
                kasitteleVoitto(pelaaja2);
            } else {
                kasitteleTasapeli();
            }

            pelatutPelit++;
            System.out.println();
        } while (!peliLoppui());

        tulostaVoitot();
        return true;
    }

    private boolean peliLoppui() {
        return (pelaaja1.getVoitot() >= maxVoitot.getLukumaara()) || (pelaaja2.getVoitot() >= maxVoitot.getLukumaara());
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

    private String kasitteleTasapeli() {
        String viesti = "\n\t\t\t Tasapeli \n";
        System.out.println(viesti);
        tasapelit++;
        return viesti;
    }

    private String tulostaEra() {
        String viesti = "Erä: " + pelatutPelit + " =====================\n";
        System.out.println(viesti);
        return viesti;
    }

    private String tulostaTasapelit() {
        String viesti = "Tasapelien lukumäärä: " + tasapelit + "\n";
        System.out.println(viesti);
        return viesti;
    }

    private String tulostaVoitot() {
        String monikko = maxVoitot.getLukumaara() == 1 ? "" : "A";
        String viesti = maxVoitot.toString().toUpperCase() + " VOITTO" + monikko + " - PELI PÄÄTTYY";
        System.out.println(viesti);
        return viesti;
    }
}