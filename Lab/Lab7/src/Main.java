public class Main {
    public static void main(String[] args) {
        ArraySumThreader aThreader = new ArraySumThreader(5);
        MatrixMaxThreader mThreader = new MatrixMaxThreader(5);

        try {
            System.out.println(aThreader.calculate(new int[] {1, 2, 3, 4, 5, 6, 7, 8}));
            System.out.println(mThreader.calculate(new double[][] {new double[] {1, 2, 3, 4, 5, 6, 70, 8}, new double[] {1, 2, 3}}, MatrixMaxThreader.IndexingMethod.FLATTEN));
            System.out.println(mThreader.calculate(new double[][] {new double[] {1, 2, 3, 4, 5, 6, 70, 8}, new double[] {1, 2, 3}}, MatrixMaxThreader.IndexingMethod.TRAVERSE_LADDER));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }
}