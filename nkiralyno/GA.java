package hu.unideb.inf.ildv444;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class GA {
	/**
	 * 
	 */
	int initialSize;
	
	
	/**
	 * 
	 */
	double prob;
	
	
	boolean elitism;
	
	/**
	 * 
	 * @param initialSize
	 * @param prob
	 */
	public GA(int initialSize, double prob, boolean elitism) {
		this.initialSize = initialSize;
		this.prob = prob;
		this.elitism = elitism;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public LinkedHashSet<Instance> initialPopulation() {
		
		LinkedHashSet<Instance> result = new LinkedHashSet<>();
		
		while( result.size() < this.initialSize )
		{
			result.add(Instance.newRandomInstance(50));
		}	
		
		return result;
	}
	
	
	/**
	 * 
	 * @param population
	 * @return
	 */
	public LinkedHashSet<Instance> nextPopulation(LinkedHashSet<Instance> population)
	{
		int sfit = 0;
		for ( Instance inst : population)
		{
			sfit += inst.fittness();
		}
		
		LinkedHashSet<Instance> newPopulation = new LinkedHashSet<>();
		
		
		if( elitism ) 
		{
			Instance best = population.iterator().next();
			for ( Instance inst : population)
			{
				if(best.fittness() < inst.fittness())
					best = inst;
			}
			
			newPopulation.add(best);
		}
		
		while (newPopulation.size() < population.size())
		{
			ArrayList<Instance> selection = new ArrayList<>();
			while (selection.size() < 2) 
			{
				
				int rnd = Instance.RANDOM.nextInt(sfit);
				Instance selected = null;
				
				for ( Instance inst : population)
				{
					if ( rnd < inst.fittness() )
					{
						selected = inst;
						break;
					}
					else 
					{
						rnd -= inst.fittness();
					}
				}
				
				selection.add(selected);
			}
			
			Instance inst = Instance.crossover( selection.get(0), selection.get(1) ).mutation(this.prob);
			newPopulation.add(inst);
		}
		
		return newPopulation;
	}
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GA ga = new GA(10, 0.1, true);
		LinkedHashSet<Instance> population = ga.initialPopulation();
		while(true) {
			Instance best = population.iterator().next();
			for ( Instance i :population)
			{
				if(best.fittness() < i.fittness()) {
					best = i;
				}
			}
			System.out.println(best);
			
			if(best.hit() == 0)
				return;
			
			population = ga.nextPopulation(population);
		}
	}
	
}
