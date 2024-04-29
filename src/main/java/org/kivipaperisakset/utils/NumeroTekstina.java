package org.kivipaperisakset.utils;

import java.util.HashMap;
import java.util.Map;

public class NumeroTekstina {
    private static NumeroTekstina INSTANCE;
    private Map<Integer, String> sanakirja;

    private NumeroTekstina() {
        sanakirja = new HashMap<>();
        sanakirja.put(3, "kolme");
    }

    public static NumeroTekstina getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NumeroTekstina();
        }
        return INSTANCE;
    }

    public void lisaaNumero(int numero, String teksti) {
        sanakirja.put(numero, teksti);
    }

    public String getTekstina(int i) {
        if (!sanakirja.containsKey(i)) {
            return String.valueOf(i);
        }
        return sanakirja.get(i);
    }

    public boolean sisaltaa(int i) {
        return sanakirja.containsKey(i);
    }
}
