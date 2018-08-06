package kkozep;

import java.util.Arrays;

public class Vector {

    public static final int N = 2;
    double v[] = new double[N];
    
    public Vector() {
        for (int i = 0; i < N; i++) {
            v[i] = Entity.RAND.nextDouble();
        }
    }

    double distance(Vector arg0) {
        double d = 0;
        for (int i = 0; i < N; i++) {
            d += (v[i] - arg0.v[i]) * (v[i] - arg0.v[i]);
        }

        return Math.sqrt(d);
    }

    Vector add(Vector arg0) {
        Vector ret = new Vector();
        for (int i = 0; i < N; i++) {
            ret.v[i] = v[i] + arg0.v[i];
        }

        return ret;
    }

    Vector div(int a) {
        Vector ret = new Vector();
        for (int i = 0; i < N; i++) {
            ret.v[i] = v[i] / a;
        }

        return ret;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Arrays.hashCode(this.v);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vector other = (Vector) obj;
        if (!Arrays.equals(this.v, other.v)) {
            return false;
        }

        return true;
    }
}
