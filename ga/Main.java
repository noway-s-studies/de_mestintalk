import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Table.Builder;

public class Main {
	public static void main(String[] args) {
		final int POP_SIZE = 20;
		// 1
		ArrayList<Table> population = new ArrayList<>();
		Builder tb = new Builder(4);
		while (population.size() < POP_SIZE) {
			population.add(tb.build());
		}

		// 2
		while (true) {
			Table goal = null;
			double sumfit = 0;
			for (Table t : population) {
				sumfit += t.fitness();

				if (t.accept()) {
					goal = t;
					break;
				}

				if (goal != null) {
					System.out.println(goal);
					return;
				}

			}

			ArrayList<Table> nextGen = new ArrayList<>();
			Table parent1;
			Table parent2;
			while (nextGen.size() < population.size()) {
				double r = Table.RANDOM.doubles(0, sumfit).findFirst().getAsDouble();
				int ix = 0;
				double f = population.get(ix).fitness();
				while (f < r) {
					ix++;
					r -= f;
				}

				parent1 = population.get(ix);

				r = Table.RANDOM.doubles(0, sumfit).findFirst().getAsDouble();
				ix = 0;
				f = population.get(ix).fitness();
				while (f < r) {
					ix++;
					r -= f;
				}

				parent2 = population.get(ix);

				nextGen.add(parent1.cross(parent2)[0]); // csak gyorsasag miatt
			}
			
		}
	}
}
