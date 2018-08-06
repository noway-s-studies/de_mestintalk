package kkozep;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

class VSet extends HashSet<Vector> {
}

public class Entity {

    public static final int K = 3; // 3 darab klaszter ket dimenzios ertekekbol
    public static final Random RAND = new Random();

    Vector[] center = new Vector[K]; // a kozepek
    final VSet S;

    // cache + mellekhatasa van a fitnessnek
    // es csak egyszer akarjuk lefuttatni
    private Double f = null;

    public Entity(VSet S) {
        this.S = S;
        VSet centers = new VSet();
        ArrayList<Vector> arr = new ArrayList<>(S);
        while (centers.size() < K) {
            centers.add(arr.get(RAND.nextInt(S.size())));
        }

        Iterator<Vector> it = centers.iterator();
        for (int i = 0; i < K; i++) {
            center[i] = it.next();
        }
    }

    double fitness() {
        if (f != null) {
            return f;
        }

        VSet cluster[] = new VSet[K];
        for (int i = 0; i < cluster.length; i++) {
            cluster[i] = new VSet();
        }

        // klaszterkeszites
        for (Vector v : S) {
            int best = 0;
            for (int i = 1; i < K; i++) {
                if (center[i].distance(v) < center[best].distance(v)) {
                    best = i;
                }
            }

            cluster[best].add(v);
        }

        // uj kozepek
        for (int i = 1; i < K; i++) {
            Vector sum = new Vector();
            for (Vector v : cluster[i]) {
                sum = sum.add(v);
            }

            sum = sum.div(cluster[i].size());
            center[i] = sum;
        }

        // ujra besoroljuk a clastereket
        for (int i = 0; i < cluster.length; i++) {
            cluster[i].clear();
        }
        for (Vector v : S) {
            int best = 0;
            for (int i = 1; i < K; i++) {
                if (center[i].distance(v) < center[best].distance(v)) {
                    best = i;
                }
            }

            cluster[best].add(v);
        }

        // celfuggveny
        double m = 0;
        for (int i = 1; i < K; i++) {
            for (Vector v : cluster[i]) {
                m += v.distance(center[i]);
            }
        }

        return f = 1 / m;
    }

    Entity mutation() {
        if (RAND.nextInt(100) > 2) {
            return this;
        }

        Entity e = new Entity(S);

        // melyik klasztert modositjuk random
        int g = RAND.nextInt(K);

        Vector rv = new Vector();
        for (int i = 0; i < Vector.N; i++) {
            // mennyivel (nagyon kicsivel)
            double r = RAND.nextDouble() / 50.0;
            if (RAND.nextBoolean()) {
                r = -r;
            }

            rv.v[i] = r;
        }

        for (int i = 0; i < K; i++) {
            e.center[i] = i == g ? center[i].add(rv) : center[i];
        }

        return e;
    }

    public static Entity crossover(Entity e1, Entity e2) {
        Entity e = new Entity(e1.S);
        int l = RAND.nextInt(K - 1) + 1;
        for (int i = 0; i < K; i++) {
            e.center[i] = i < l ? e1.center[i] : e2.center[i];
        }

        return e;
    }
}
