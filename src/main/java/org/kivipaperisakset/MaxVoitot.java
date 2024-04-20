package org.kivipaperisakset;

public class MaxVoitot {
    private int maara;
    private String teksti;

    public MaxVoitot(int maara, String teksti) {
        this.maara = maara;
        this.teksti = teksti;
    }

    public int getMaara() {
        return maara;
    }

    @Override
    public String toString() {
        return teksti;
    }
}