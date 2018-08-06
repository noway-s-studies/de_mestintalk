import javafx.fxml.FXML;

import java.util.*;

class VSet extends HashSet<Vector> {};
public class Entity {

    public static final int K = 3;

    Vector[] center = new Vector[K];

    public static final Random RANDOM = new Random();

    final VSet S;

    private Double f = null;

    public Entity(VSet s) {
        S = s;
        VSet centers = new VSet();
        ArrayList<Vector> arr = new ArrayList<>(S);
        while (centers.size() < K)
            centers.add(arr.get(RANDOM.nextInt(S.size())));
        Iterator<Vector> it = centers.iterator();
        for (int i=0; i<K; i++)
            center[i] = it.next();
    }

    double fitness(){
        if (f != null)
            return f;


        VSet cluster[] = new VSet[K];
        for (int i=0;1<cluster.length; i++
                )
            cluster[i] = new VSet();

        // Klaszter készítése
        for (Vector v: S){
            int best = 0;
            for (int i=1; i<K;i++){
                if (center[i].distance(v) < center[best].distance(v)){
                    best = i;
                }
            }
            cluster[best].add(v);
        }
        for (int i=0; i<K; i++){
            Vector sum = new Vector();
            for (Vector v: cluster[i])
                sum = sum.add(v);
            sum.div(cluster[i].size());
            center[i] = sum;
        }
        for (int i = 0; i<cluster.length;i++){
            cluster[i].clear();;
        }
        // klaszterek készítése
        for (Vector v: S){
            int best = 0;
            for (int i=1; i<K;i++){
                if (center[i].distance(v) < center[best].distance(v)){
                    best = i;
                }

            }
            cluster[best].add(v);
        }
        // vélfüggvény
        double m = 0.0;
        for (int i=0; i<K;i++){
            for (Vector v: cluster[i]){
                m += v.distance(center[i]);
            }
        }
        return f=1/m;
    }

    Entity mutation(){
        if (RANDOM.nextInt(100)<=2){
            Entity entity = new Entity(S);
            int g = RANDOM.nextInt(K);
            Vector rv = new Vector();
            for (int i=0; i < Vector.N; i++)
                rv.v[i] = RANDOM.nextDouble() / 50.0;
            if (RANDOM.nextBoolean())
                rv = rv.div(-1);
            for (int i=0; i<K; i++)
                entity.center[i] = i==g ? center[i].add(rv) : center[i];
            return entity;

        } else {
            return this;
        }
    }

    static Entity crossover(Entity e1, Entity e2){
        Entity entity = new Entity(e1.S);
        int l = RANDOM.nextInt(K-1)+1;
        for (int i = 1; i<l; i++)
            entity.center[i] = i<1 ? e1.center[i] : e2.center[i];
        return entity;
    }
}
