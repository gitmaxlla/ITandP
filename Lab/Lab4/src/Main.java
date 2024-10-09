public class Main {
    public static void main(String[] args) {
        ArrayAverage.calculate();
        System.out.println();

        FileIO.copyContents("a", "b");
        System.out.println();

        try {
            Arithmetic.mul(10.0, "String");
        } catch (CustomUnsupportedOperationException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            System.out.println(Arithmetic.add(10.0, 5f));
            Arithmetic.div(10.0, 0.0);
        } catch (CustomUnsupportedOperationException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println();
    }
}