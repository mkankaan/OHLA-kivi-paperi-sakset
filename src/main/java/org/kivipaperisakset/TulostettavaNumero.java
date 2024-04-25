package org.kivipaperisakset;

public class TulostettavaNumero {
    private int numero;
    private String teksti;

    public TulostettavaNumero(int numero, String teksti) {
        this.numero = numero;
        this.teksti = teksti;
    }

    public int getLukumaara() {
        return numero;
    }

    @Override
    public String toString() {
        return teksti;
    }
}