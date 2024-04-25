package org.kivipaperisakset;

public class TulostettavaNumero {
    private int maara;
    private String teksti;

    public TulostettavaNumero(int numero, String teksti) {
        this.maara = numero;
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