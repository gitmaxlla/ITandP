public class Main {
    public static void main(String[] args) {
        ArraySumThreader aThreader = new ArraySumThreader(5);
        MatrixMaxThreader mThreader = new MatrixMaxThreader(5);

        try {
            System.out.println(aThreader.calculate(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8}));
            System.out.println();

            System.out.println(mThreader.calculate(new Double[][] {new Double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 70.0, 8.0}, new Double[] {1.0, 2.0, 3.0}}, MatrixMaxThreader.IndexingMethod.TRAVERSE_LADDER));
            System.out.println();

            MovingStocks newStock = new MovingStocks(new Double[] {1.0, 20.0, 23.0, 94.0, 120.0, 5.0, 12.0, 98.0, 11.0, 1.0, 0.5, 0.89, 16.0, 15.0, 1.0, 65.6, 40.1, 19.0, 0.005, 19.0, 49.2, 18.0, 10.0, 100.0, 59.2});
            newStock.transfer();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}