import javafx.fxml.FXML;

import java.util.ArrayList;

public class GA {
    public static void main(String[] args) {
        // 1
        ArrayList<Entity> population = new ArrayList<>();

        VSet univ = new VSet();
        for (int i=0; i<100;i++)
            univ.add(new Vector());
        while (population.size()<5){
            population.add(new Entity(univ));
        }

        while (true){
            Entity best = population.get(0);
            for (Entity e : population)
                if(best.fitness()<e.fitness())
                    best = e;
            System.out.println(best.fitness());

            ArrayList<Entity> nextGen = new ArrayList<>();
            nextGen.add(best); // elitista
            while (nextGen.size()<population.size()){
                Entity s1 = select(population);
                Entity s2 = select(population);

                Entity n = Entity.crossover(s1,s2);
                nextGen.add(n.mutation());


            }
            population = nextGen;
        }
    }

    private static Entity select(ArrayList<Entity> population) {

            double sum = 0;
            for (Entity e : population)
                sum += e.fitness();
            double ran = Entity.RANDOM.nextDouble()*sum;
            double tmp = 0;
            Entity selected = null;
            for (int i=0; i<population.size(); i++){
                if (tmp + population.get(i).fitness() > ran ){
                    selected = population.get(i);
                    break;
                }
                tmp += population.get(i).fitness();
            }
            return selected;
    }
}
