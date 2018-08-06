import java.util.Random;
import static java.lang.Math.abs;

// fitnesz erteket is ez kezeli
// a fitnesz erteknel el akarjuk kezelni a nullat
// n = 4, 6 max ertek, elkeruljuk nullat ergo 1 ... 7
// ergo a max = 1 + (n - 1) + (n - 2) ... + 1 + 0
public class Table {
	public static final Random RANDOM = new Random();
	private int max;
	private int a[];
	private Double f;

	static class Builder {
		private final int n;

		public Builder(int n) {
			this.n = n;
		}

		public Table build() {
			Table t = new Table();
			t.a = new int[n];
			t.max = 1;

			for (int i = 0; i < t.a.length; i++) {
				t.a[i] = RANDOM.nextInt(n);
				t.max += 1;

			}

			return t;
		}
	}

	public double fitness() {
		if (f != null)
			return f;

		int u = 0;
		// u az utesek szama
		// balrol jobbra
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				// vagy ugyanabban a sorban vannak
				// vagy ugyanannyi az oszlop es sor tavolsaguk akkor
				// atloban vannak
				if (a[i] == a[j] || abs(a[i] - a[j]) == abs(i - j))
					u += 1;

			}
		}

		f = Double.valueOf(max - u);
		return f;
	}

	public boolean accept() {
		return fitness() == max;
	}

	public Table[] cross(Table t) {
		final int b[] = t.a; // csak olvashatosag miatt
		Table r1 = new Table();
		Table r2 = new Table();
		r1.max = r2.max = max; // mivel nem a buildert hasznaljuk
		final int e1[] = r1.a = new int[a.length];
		final int e2[] = r2.a = new int[a.length];

		final int pivot = RANDOM.nextInt(a.length - 1) + 1;
		for (int i = 0; i < a.length; i++) {
			e1[i] = i < pivot ? a[i] : b[i];
			e2[i] = i < pivot ? b[i] : a[i];
		}

		return new Table[] { r1, r2 };
	}

	public Table mutate() {
		Table r = new Table();
		final int b[] = r.a = new int[a.length];
		r.max = max;

		System.arraycopy(a, 0, b, 0, a.length);
		b[RANDOM.nextInt(a.length)] = RANDOM.nextInt(a.length);
		return r;
	}

	@Override
	public String toString() {
		String ret = "fitness: " + fitness() + "\n";
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				if (a[j] == i)
					ret += "X|";
				else
					ret += ".|";
			}
			ret += "\n";
		}

		return ret;
	}
}
