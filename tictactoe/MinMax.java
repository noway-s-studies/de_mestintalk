package tictactoe;

interface Heurisztika {

    double becsul(Allas a);
}

public class MinMax {

    static double min(Allas a, Heurisztika h, int korlat) {
        if (a.vege() || korlat == 0) {
            return h.becsul(a);
        }

        double min = Double.MAX_VALUE;
        for (Lepes l : Lepes.lepesek) {
            if (l.alkalmazhato(a)) {
                Allas uj = l.lep(a);
                double v = max(uj, h, korlat - 1);
                if (v < min) {
                    min = v;
                }
            }
        }

        return min;
    }

    static double max(Allas a, Heurisztika h, int korlat) {
        if (a.vege() || korlat == 0) {
            return h.becsul(a);
        }

        double max = -Double.MAX_VALUE;
        for (Lepes l : Lepes.lepesek) {
            if (l.alkalmazhato(a)) {
                Allas uj = l.lep(a);
                double v = min(uj, h, korlat - 1);
                if (v > max) {
                    max = v;
                }
            }
        }

        return max;
    }
    
    
    static Lepes ajanl(Allas a, Heurisztika h, int korlat) {
        double max = -Double.MAX_VALUE;
        Lepes legjobb = null;
        for (Lepes l : Lepes.lepesek) {
            if (l.alkalmazhato(a)) {
                Allas uj = l.lep(a);
                double v = min(uj, h, korlat - 1);
                if (v > max) {
                    max = v;
                    legjobb = l;
                }
            }
        }

        return legjobb;
    }
}
