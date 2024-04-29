package org.kivipaperisakset.peli;

import org.kivipaperisakset.Pelaaja;

public class PeliBuilder {
    private Peli peli;

    public PeliBuilder() {
        reset();
    }

    public PeliBuilder pelaaja1(Pelaaja pelaaja) {
        peli.setPelaaja1(pelaaja);
        return this;
    }

    public PeliBuilder pelaaja2(Pelaaja pelaaja) {
        peli.setPelaaja2(pelaaja);
        return this;
    }

    public PeliBuilder maxVoitot(int maxVoitot) {
        peli.setMaxVoitot(maxVoitot);
        return this;
    }

    public Peli luo() {
        return peli;
    }

    public void reset() {
        this.peli = new Peli();
    }
}
