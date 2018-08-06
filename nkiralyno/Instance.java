package hu.unideb.inf.ildv444;

import java.util.Arrays;
import java.util.Random;

public class Instance {
	/**
	 * 
	 */
	private int column[];
	
	
	/**
	 * 
	 */
	private Integer fit;
	
	
	/**
	 * 
	 * @param n
	 */
	private Instance(int n) {
		
		column = new int[n];
		
	}
	
	
	/**
	 * 
	 */
	public static final Random RANDOM = new Random(System.currentTimeMillis());
	
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public static Instance newRandomInstance(int n) {
		Instance instance = new Instance(n);
		
		for( int i = 0; i < n; i++ )
		{
			instance.column[i] = RANDOM.nextInt(n); //0,...,n-1
		}
		
		return instance;
	}
	
	
	/**
	 * Fitness fv a fittség kiszámításához.
	 * @return
	 */
	public int fittness() {
		int n = column.length;
		if (fit == null) 
		{
			fit = 1 + n * (n - 1) / 2 - hit();
		}
		
		return fit.intValue();
	}
	
	
	/**
	 * 
	 * @return
	 */
	public int hit() {
		int n = column.length;
		int h = 0;
		
		for (int i = 0; i < n; i++) 
		{ 
			for (int j = i + 1; j < n; j++) 
			{
				if ( column[i] == column[j] || Math.abs(i - j) == Math.abs(column[i] - column[j])) 
				{
					h++;
				}
			}
		}
		
		return h;
	}
	
	
	/**
	 * 
	 * @param prob
	 * @return
	 */
	public Instance mutation(double prob) {
		
		if ( RANDOM.nextDouble() < prob )
		{
			int n = column.length;
			Instance inst = new Instance(n);
			for( int i = 0; i < n; i++)
			{
				inst.column[i] = this.column[i];
			}
			
			inst.column[RANDOM.nextInt(n)] = RANDOM.nextInt(n);
			return inst;
		}
		else 
		{
			return this;
		}
		
	}
	
	
	/**
	 * 
	 * @param inst1
	 * @param inst2
	 * @return
	 */
	public static Instance crossover(Instance inst1, Instance inst2)
	{
		int n = inst1.column.length;
		Instance result = new Instance(n);
		int k = RANDOM.nextInt(n-1);
		
		for(int i = 0; i < n; i++)
		{
			result.column[i] = i<=k ? inst1.column[i] : inst2.column[i];
		}
		
		return result;
	}
	
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return 0;
	}


	
	/**
	 * 
	 * @param instance
	 * @return
	 */
	public boolean equals(Instance instance) {
		int n = column.length;
		
		for (int i = 0; i < n; i++)
		{
			if(this.column[i] == instance.column[i])
				return false;
		}
		
		return true;
	}

	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Instance && this.equals((Instance)obj);
	}

	
	/**
	 * 
	 */
	@Override
	public String toString() {
		
		StringBuffer table = new StringBuffer(hit() + "\n");
		int n = column.length;
		
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				table.append( column[i] == j ? " x " : " . " );
			}
			
			table.append("\n");
		}
		
		return table.toString();
		
	}
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		Instance inst = Instance.newRandomInstance(8);
		System.out.println( inst );
		System.out.println( inst.mutation(1) );
	}
	
}
