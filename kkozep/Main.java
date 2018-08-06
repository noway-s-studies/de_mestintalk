package kkozep;

import java.util.ArrayList;
import alapgenerikus.Table;

public class Main {

    public static void main(String[] args) {
        final int POP_SIZE = 5;
        // 1
        ArrayList<Entity> population = new ArrayList<>();
        // az univerzum
        VSet univ = new VSet();
        for (int i = 0; i < 100; i++) {
            univ.add(new Vector());
        }

        while (population.size() < POP_SIZE) {
            population.add(new Entity(univ));
        }

        // 2 uj generacio, elitistan
        while (true) {
            // megkeressuk a legjobb elemet, 
            Entity best = population.get(0);
            for (Entity e : population) {
                if (best.fitness() < e.fitness()) {
                    best = e;
                }
            }

            System.out.println(best.fitness());
            ArrayList<Entity> nextGen = new ArrayList<>();
            // megorizzuk a legjobbat
            nextGen.add(best);

            while (nextGen.size() < population.size()) {
                Entity s1 = selectRand(population);
                Entity s2 = selectRand(population);

                Entity n = Entity.crossover(s1, s2);
                nextGen.add(n.mutation());
            }
            population = nextGen;
        }
    }

    public static Entity selectRand(ArrayList<Entity> population) {
        double sum = 0;
        for (Entity e : population) {
            sum += e.fitness();
        }

        // random egyed fitnesz erteke
        double r = Entity.RAND.nextDouble() * sum;
        double tmp = 0;
        Entity selected = null;
        // megkeressuk ezt a random elemet
        for (int i = 0; i < population.size(); i++) {
            double f = population.get(i).fitness();
            if (tmp + f > r) {
                selected = population.get(i);
                break;
            }

            tmp += f;
        }

        return selected;
    }
}
