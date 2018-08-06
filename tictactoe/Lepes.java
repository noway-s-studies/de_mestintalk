package tictactoe;

import java.util.ArrayList;
import java.util.List;
import static tictactoe.Allas.MERET;
import static tictactoe.Allas.URES;

public class Lepes {

    int i, j;

    private Lepes(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public final static List<Lepes> lepesek = new ArrayList<>();

    static {
        for (int i = 0; i < MERET; i++) {
            for (int j = 0; j < MERET; j++) {
                lepesek.add(new Lepes(i, j));
            }
        }
    }

    public boolean alkalmazhato(Allas allas) {
        return allas.a[i][j] == URES;
    }

    public Allas lep(Allas allas) {
        Allas uj = new Allas();
        for (int k = 0; k < MERET; k++) {
            for (int l = 0; l < MERET; l++) {
                if (i == k && j == l) {
                    uj.a[k][l] = allas.kovetkezo();
                } else {
                    uj.a[k][l] = allas.a[k][l];
                }
            }
        }

        return uj;
    }

    @Override
    public String toString() {
        return "Lepes{" + "i=" + (i + 1) + ", j=" + (j + 1) + '}';
    }
}