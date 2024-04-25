package org.kivipaperisakset.valinta;

import java.util.Comparator;
import static org.kivipaperisakset.valinta.Valinta.*;

public class Vertailija implements Comparator<Valinta> {
    @Override
    public int compare(Valinta v1, Valinta v2) {
        if (v1 == KIVI && v2 == SAKSET) {
            return 1;
        }

        if (v1 == SAKSET && v2 == KIVI) {
            return -1;
        }

        return v1.compareTo(v2);
    }
}