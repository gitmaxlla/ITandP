public class Arithmetic {
    public static double add(Object a, Object b) {
        a = checkVariable(a);
        b = checkVariable(b);
        return (double)a + (double)b;
    }

    public static double sub(Object a, Object b) {
        a = checkVariable(a);
        b = checkVariable(b);
        return (double)a - (double)b;
    }

    public static double mul(Object a, Object b) {
        a = checkVariable(a);
        b = checkVariable(b);
        return (double)a * (double)b;
    }

    public static double div(Object a, Object b) {
        a = checkVariable(a);
        b = checkVariable(b);
        if ((double)b == 0.0) throw new CustomUnsupportedOperationException("Division by zero");
        return (double)a / (double)b;
    }

    public static Object checkVariable(Object a) {
        if (!(a instanceof Number)) throw new CustomUnsupportedOperationException("Provided object can't be used as a number");
        if (a instanceof Float) return ((Float)a).doubleValue();
        return a;
    }

}
