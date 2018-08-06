package tictactoe;

public class Paranoid {
    static Lepes ajanl(Allas a, Heurisztika h, int korlat) {
        Double max = null;
        Lepes legjobb = null;
        for (Lepes l : Lepes.lepesek) {
            if (l.alkalmazhato(a)) {
                Allas uj = l.lep(a);
                double v = josag(uj, a.kovetkezo(), h, korlat - 1);
                if (max == null || max < v) {
                    max = v;
                    legjobb = l;
                }
            }
        }
        System.out.println(max);

        return legjobb;
    }

    private static double josag(Allas a, int t, Heurisztika h, int korlat) {
        if (a.vege() || korlat == 0) {
            return h.becsul(a);
        }

        Double min = null, max = null;
        for (Lepes l : Lepes.lepesek) {
            if (l.alkalmazhato(a)) {
                Allas uj = l.lep(a);
                double v = josag(uj, t, h, korlat - 1);
                if (min == null || min > v) {
                    min = v;
                }
                if (max == null || max < v) {
                    max = v;
                }

            }
        }

        return a.kovetkezo() == t ? max : min;
    }
}
