package org.kivipaperisakset.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * NumeroTekstina-luokka sisältää singleton-tyyppisen sanakirjan,
 * josta voidaan hakea avain-arvo -pareina kokonaislukuja
 * numeerisessa ja kirjoitetussa muodossa.
 *
 * @author Matleena Kankaanpää
 * @version 1.0 (2023-04-29)
 */

public class NumeroTekstina {
    private static NumeroTekstina INSTANCE;
    private Map<Integer, String> sanakirja;

    private NumeroTekstina() {
        sanakirja = new HashMap<>();
        sanakirja.put(3, "kolme");
    }

    /**
     * Palauttaa NumeroTekstina-singletonin instanssin. Jos
     * instanssia ei ole vielä luotu, se luodaan.
     * @return      NumeroTeksina-instanssi.
     */
    public static NumeroTekstina getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NumeroTekstina();
        }
        return INSTANCE;
    }

    /**
     * Lisää sanakirjaan avain-arvo -parin. Jos sanakirjasta
     * löytyy jo arvo samalla avaimella, entinen arvo ylikirjoitetaan.
     * @param numero    Sanakirjaan lisättävä avain.
     * @param teksti    Sanakirjaan lisättävän avaimen arvo.
     */
    public void lisaaNumero(int numero, String teksti) {
        sanakirja.put(numero, teksti);
    }

    /**
     * Palauttaa sanakirjasta kokonaislukuavaimella löytyvän
     * arvon merkkijonona, tai avaimen merkkijonona, jos
     * avainta ei löydy sanakirjasta.
     * @param avain     Haettava avain kokonaislukuna.
     * @return          Jos avain löytyi sanakirjasta, palautetaan
     *                  sen arvona oleva teksti merkkijonona. Jos
     *                  avainta ei löytynyt, palautetaan avain merkkijonona.
     */
    public String getTekstina(int avain) {
        if (!sanakirja.containsKey(avain)) {
            return String.valueOf(avain);
        }
        return sanakirja.get(avain);
    }

    /**
     * Palauttaa tiedon siitä, löytyykö tietty avain sanakirjasta.
     * @param arvo      Haettava avain kokonaislukuna.
     * @return          True jos avain löytyy, false jos ei löydy.
     */
    public boolean sisaltaa(int arvo) {
        return sanakirja.containsKey(arvo);
    }
}
