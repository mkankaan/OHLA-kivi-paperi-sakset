package org.kivipaperisakset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.kivipaperisakset.valinta.Valinta;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.kivipaperisakset.Pelaaja.resetoiPelaajaNumero;
import static org.kivipaperisakset.valinta.Valinta.*;

class PelaajaTest {
    private Pelaaja pelaaja;

    @BeforeEach
    void setUp() {
        resetoiPelaajaNumero();
        pelaaja = new Pelaaja();
    }

    @Test
    void oikeaMaaraVoittojaLisataan() {
        for (int i = 0; i < 3; i++) {
            pelaaja.lisaaVoitto();
        }
        assertEquals(pelaaja.getVoitot(), 3, "Voittojen määrä ei täsmää");
    }

    @Test
    void valintaOikeistaVaihoehdoista() {
        Valinta valinta = pelaaja.valitse();
        assert(valinta == KIVI || valinta == PAPERI || valinta == SAKSET);
    }

    @Test
    void valintojenSuhdeOikea() {
        int kivetLkm = 0;
        int paperitLkm = 0;
        int saksetLkm = 0;

        for (int i = 0; i < 1000; i++) {
            switch (pelaaja.valitse()) {
                case KIVI:
                    kivetLkm++;
                    break;
                case PAPERI:
                    paperitLkm++;
                    break;
                case SAKSET:
                    saksetLkm++;
                    break;
                default:
                    assert(false);
            }
        }

        assert (kivetLkm > 250 && kivetLkm < 500);
        assert (saksetLkm > 250 && saksetLkm < 500);
        assert (paperitLkm > 250 && paperitLkm < 500);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0 voittoa",
            "1, 1 voitto",
            "5, 5 voittoa",
    })
    void tulostaaVoitotOikein(int voitot, String tulos) {
        for (int i = 0; i < voitot; i++) {
            pelaaja.lisaaVoitto();
        }
        assert (pelaaja.tulostaVoitot().contains(tulos));

        if (voitot == 1) {
            assert (!pelaaja.tulostaVoitot().contains("voittoa"));
        }
    }

    @Test
    void oikeaTulostusEriPelaajanumeroilla() {
        assert (pelaaja.tulostaVoitot().toLowerCase().contains("pelaaja 1:llä"));
        assert (new Pelaaja().tulostaVoitot().toLowerCase().contains("pelaaja 2:lla"));
    }

    @Test
    void pelaajaNumeroResetoituu() {
        Pelaaja pelaaja2 = new Pelaaja();
        Pelaaja pelaaja3 = new Pelaaja();
        assertEquals(pelaaja2.toString().toLowerCase(), "pelaaja 2");
        assertEquals(pelaaja3.toString().toLowerCase(), "pelaaja 1");
    }
}