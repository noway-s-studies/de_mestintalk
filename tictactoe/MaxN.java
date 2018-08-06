package tictactoe;

interface Becsles {

    double[] becsul(Allas a);
}

public class MaxN {

    private static double[] max(Allas a, Becsles b, int korlat) {
        if (korlat == 0 || a.vege()) {
            return b.becsul(a);
        }

        double max[] = null;
        // a kovetkezo user az eredeti allapot szerint, es -1 hogy a tombbe jo index legyen
        int ix = a.kovetkezo() - 1;
        for (Lepes l : Lepes.lepesek) {
            if (l.alkalmazhato(a)) {
                Allas uj = l.lep(a);
                double v[] = max(uj, b, korlat - 1);
                if (max == null || max[ix] < v[ix]) {
                    max = v;
                }
            }
        }

        return max;
    }

    static Lepes ajanl(Allas a, Becsles b, int korlat) {
        Lepes legjobb = null;

        double max[] = null;
        // a kovetkezo user az eredeti allapot szerint, es -1 hogy a tombbe jo index legyen
        int ix = a.kovetkezo() - 1;
        for (Lepes l : Lepes.lepesek) {
            if (l.alkalmazhato(a)) {
                Allas uj = l.lep(a);
                double v[] = max(uj, b, korlat - 1);
                if (max == null || max[ix] < v[ix]) {
                    max = v;
                    legjobb = l;
                }
            }
        }

        return legjobb;
    }
}
