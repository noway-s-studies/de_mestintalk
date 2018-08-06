package tictactoe;

public class Allas {

    static final int URES = 0;
    static final int ELSO = 1;
    static final int MASODIK = 2;
    static final int MERET = 3;

    int a[][] = new int[MERET][MERET];

    public Allas() {
        for (int i = 0; i < MERET; i++) {
            for (int j = 0; j < MERET; j++) {
                a[i][j] = 0;
            }
        }
    }

    public Integer nyertes() {
        // elso foatlo
        if (a[0][0] == a[1][1] && a[1][1] == a[2][2] && a[1][1] != 0) {
            return a[1][1]; // mert az ertek egyben a jatekost is
        }

        // masodik foatlo
        if (a[2][0] == a[1][1] && a[1][1] == a[0][2] && a[1][1] != 0) {
            return a[1][1];
        }

        // sorok szerint
        for (int i = 0; i < MERET; i++) {
            if (a[i][0] == a[i][1] && a[i][1] == a[i][2] && a[i][1] != 0) {
                return a[i][0];
            }
        }

        // oszlopok szerint
        for (int j = 0; j < MERET; j++) {
            if (a[0][j] == a[1][j] && a[1][j] == a[2][j] && a[1][j] != 0) {
                return a[0][j];
            }
        }

        // dontetlen
        return null;
    }

    public boolean vege() {
        if (nyertes() != null) {
            return true;
        }

        // van olyan hely ahova nem leptek?
        for (int i = 0; i < MERET; i++) {
            for (int j = 0; j < MERET; j++) {
                if (a[i][j] == URES) {
                    return false;
                }
            }
        }

        return true;
    }

    public int kovetkezo() {
        int m = 1;
        for (int i = 0; i < MERET; i++) {
            for (int j = 0; j < MERET; j++) {
                int x = a[i][j];
                switch (x) {
                    case ELSO:
                        m += 1;
                        break;
                    case MASODIK:
                        m += -1;
                        break;
                }
            }
        }

        return m;
    }

    @Override
    public String toString() {
        String ret = "\n";
        for (int i = 0; i < MERET; i++) {
            for (int j = 0; j < MERET; j++) {
                String ertek = "";
                switch (a[i][j]) {
                    case URES:
                        ertek = " .";
                        break;
                    case ELSO:
                        ertek = " X";
                        break;
                    case MASODIK:
                        ertek = " O";
                        break;
                }
                ret += ertek;
            }

            ret += "\n";
        }

        return ret;
    }
}