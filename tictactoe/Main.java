package tictactoe;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Allas allas = new Allas();
        Scanner sc = new Scanner(System.in);
        while (allas.vege() == false) {
            System.out.println(allas);
            ArrayList<Lepes> lepes = new ArrayList<>();
            for (Lepes l : Lepes.lepesek) {
                if (l.alkalmazhato(allas)) {
                    a-> {
                        double[] v = new double[2];
                        for (int i = 0; i < v.length; i++) {
                            if (a.nyertes != null) {
                                v[i-1] = a.nyertes()==i ? +1 : -1;
                            } else {
                                v[i-1] = 0;
                            }
                        }
                        return v;
                    };
                } else  {
                    allas = MinMax.ajanl(allas,a->{
                        if (a.nyertes() != null)
                            return a.nyertes()==1 ? +1 : -1;
                        return 0;
                    }, 9).lep(alias);
                }
            }
            for (int i = 0; i < lepes.size(); i++) {
                System.out.println(i + ". " + lepes.get(i));
            }
            int s;
            do {
                s = sc.nextInt();
            } while (s < 0 || s >= lepes.size());
            allas = lepes.get(s).lep(allas);
        }
        System.out.println("Vége");
        Integer nyert = allas.nyertes();
        if (nyert == null) {
            System.out.println("Dontetlen");
        } else if (nyert == Allas.ELSO) {
            System.out.println("Nyert: X");
        } else if (nyert == Allas.MASODIK) {
            System.out.println("Nyert: O");
        }
    }
}