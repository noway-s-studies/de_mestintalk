package tictactoe;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    final static int ELORELATAS = 10; // aka nehezseg
    final static int ELORELATAS2 = 2;

    public static void main(String[] args) {
        //minmax();
        //maxn();
        //automata();
        paranoid();
    }

    private static void minmax() {

        Allas allas = new Allas();
        Scanner sc = new Scanner(System.in);

        while (allas.vege() == false) {

            // a gep lepese
            if (allas.kovetkezo() == Allas.MASODIK) {
                allas = MinMax.ajanl(allas, (a) -> {
                    if (a.nyertes() != null) {
                        if (a.nyertes() == Allas.MASODIK) {
                            return 1;
                        }
                    }

                    // nem becsulunk
                    return 0;
                }, ELORELATAS).lep(allas);
                System.out.println("A gép lépése");
                System.out.println(allas);
                continue;
            }

            System.out.println("A játékos lépése");
            System.out.println(allas);

            ArrayList<Lepes> lepes = new ArrayList<>();
            for (Lepes l : Lepes.lepesek) {
                if (l.alkalmazhato(allas)) {
                    lepes.add(l);
                }
            }

            for (int i = 0; i < lepes.size(); i++) {
                Lepes l = lepes.get(i);
                if (l.alkalmazhato(allas)) {
                    System.out.println(i + ". " + l);
                }
            }

            int s;
            do {
                s = sc.nextInt();
            } while (s < 0 || s >= lepes.size());

            allas = lepes.get(s).lep(allas);
        }

        System.out.println("Vége");
        System.out.println(allas);
        Integer nyert = allas.nyertes();
        if (nyert == null) {
            System.out.println("Dontetlen");
        } else if (nyert == Allas.ELSO) {
            System.out.println("Nyert: X");
        } else if (nyert == Allas.MASODIK) {
            System.out.println("Nyert: O");
        }
    }

    private static void maxn() {
        Allas allas = new Allas();
        Scanner sc = new Scanner(System.in);

        while (allas.vege() == false) {

            // a gep lepese
            if (allas.kovetkezo() == Allas.MASODIK) {
                allas = MaxN.ajanl(allas, (a) -> {
                    // tetszolegesen sok jatekos eseten
                    // ha tobb mint ketto akkor boviteni a tombot
                    double[] v = new double[2];
                    for (int i = 1; i < v.length; i++) {
                        if (a.nyertes() != null) {
                            v[i - 1] = a.nyertes() == i ? +1 : -1;
                        } else {
                            v[i - 1] = 0;
                        }
                    }

                    return v;
                }, ELORELATAS).lep(allas);
                System.out.println("A gép lépése");
                System.out.println(allas);
                continue;
            }

            System.out.println("A játékos lépése");
            System.out.println(allas);

            ArrayList<Lepes> lepes = new ArrayList<>();
            for (Lepes l : Lepes.lepesek) {
                if (l.alkalmazhato(allas)) {
                    lepes.add(l);
                }
            }

            for (int i = 0; i < lepes.size(); i++) {
                Lepes l = lepes.get(i);
                if (l.alkalmazhato(allas)) {
                    System.out.println(i + ". " + l);
                }
            }

            int s;
            do {
                s = sc.nextInt();
            } while (s < 0 || s >= lepes.size());

            allas = lepes.get(s).lep(allas);
        }

        System.out.println("Vége");
        System.out.println(allas);
        Integer nyert = allas.nyertes();
        if (nyert == null) {
            System.out.println("Dontetlen");
        } else if (nyert == Allas.ELSO) {
            System.out.println("Nyert: X");
        } else if (nyert == Allas.MASODIK) {
            System.out.println("Nyert: O");
        }
    }

    private static void automata() {
        Allas allas = new Allas();

        while (allas.vege() == false) {

            // a gep lepese
            if (allas.kovetkezo() == Allas.MASODIK) {
                allas = MaxN.ajanl(allas, (a) -> {
                    // tetszolegesen sok jatekos eseten
                    // ha tobb mint ketto akkor boviteni a tombot
                    double[] v = new double[2];
                    for (int i = 1; i < v.length; i++) {
                        if (a.nyertes() != null) {
                            v[i - 1] = a.nyertes() == i ? +1 : -1;
                        } else {
                            v[i - 1] = 0;
                        }
                    }

                    return v;
                }, ELORELATAS).lep(allas);
                System.out.println("A O gép lépése");
                System.out.println(allas);
            } else {
                allas = MinMax.ajanl(allas, (a) -> {
                    if (a.nyertes() != null) {
                        if (a.nyertes() == Allas.ELSO) {
                            return 1;
                        }
                    }

                    // nem becsulunk
                    return 0;
                }, ELORELATAS2).lep(allas);
                System.out.println("A X gép lépése");
                System.out.println(allas);
            }
        }

        System.out.println("Vége");
        System.out.println(allas);
        Integer nyert = allas.nyertes();
        if (nyert == null) {
            System.out.println("Dontetlen");
        } else if (nyert == Allas.ELSO) {
            System.out.println("Nyert: X");
        } else if (nyert == Allas.MASODIK) {
            System.out.println("Nyert: O");
        }
    }

    private static void paranoid() {
        Allas allas = new Allas();

        while (allas.vege() == false) {

            // a gep lepese
            if (allas.kovetkezo() == Allas.ELSO) {
                allas = Paranoid.ajanl(allas, (a) -> {
                    if (a.nyertes() != null) {
                        if (a.nyertes() == Allas.ELSO) {
                            return 1;
                        } else {
                            return -1;
                        }

                    }

                    // nem becsulunk
                    return 0;
                }, ELORELATAS).lep(allas);
                System.out.println("A X gép lépése");
                System.out.println(allas);
            } else {
                allas = MinMax.ajanl(allas, (a) -> {
                    if (a.nyertes() != null) {
                        if (a.nyertes() == Allas.MASODIK) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }

                    // nem becsulunk
                    return 0;
                }, ELORELATAS2).lep(allas);
                System.out.println("A O gép lépése");
                System.out.println(allas);
            }
        }

        System.out.println("Vége");
        System.out.println(allas);
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
