public class Vector {

    private static final int N = 2;

    double v[] = new double[N];

    public Vector(){
        for (int i=0;i<N; i++){
            v[i] = Entity.RANDOM.nextDouble();
        }
    }

    double distance(Vector arg0){
        double d = 0;
        for (int i=0; i<N; i++)
            d += (v[i]-arg0.v[i]) * (v[i]-arg0.v[i]);
        return Math.sqrt(d);
    }

    Vector add(Vector arg0){
        Vector ret = new Vector();
        for (int i=0;i<N;i++){
            ret.v[i] = v[i] + arg0.v[i];
        }
        return ret;
    }

    Vector div(int a) {
        Vector ret = new Vector();
        for (int i=0;i<N;i++){
            ret.v[i] = v[i] / a;
        }
        return ret;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vector){
            Vector arg = (Vector)o;
            for (int i=0;i<N;i++)
                if (v[i] != arg.v[i])
                    return false;
            return true;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
