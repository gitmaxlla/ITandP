import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.*;

public class MatrixMaxThreader {
    private int splitFactor = 2;

    public enum IndexingMethod {
        FLATTEN,
        TRAVERSE_LADDER
    };

    public MatrixMaxThreader(int splitFactor) {
        if (splitFactor < 2) throw new IllegalArgumentException("Split factor should be not less than 2.");
        this.splitFactor = splitFactor;
    }

    public double calculate(double[][] matrix, IndexingMethod indexingMethod) throws Exception {
        int matrix_size = 0;
        for (double[] i : matrix) matrix_size += i.length;

        int nThreads = Math.min(matrix_size, splitFactor);
        ArrayList<Double>[] numbers = new ArrayList[nThreads];
        for (int i = 0; i < numbers.length; i++) numbers[i] = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        ArrayList<Callable<Double>> tasks = new ArrayList<>();

        int counter = 0;

        if (indexingMethod == IndexingMethod.FLATTEN)
        {
            double[] mFlattened = Arrays.stream(matrix).flatMapToDouble(o -> Arrays.stream(o)).toArray();
            while (counter < mFlattened.length) {
                numbers[counter % nThreads].add(mFlattened[counter]);
                ++counter;
            }
        }
        else if (indexingMethod == IndexingMethod.TRAVERSE_LADDER)
        {
            int valuesTraversed = 0;
            int currentIndex = 0;
            int indexSwitcher = 0;

            while (currentIndex < matrix.length) {
                numbers[indexSwitcher++].add(matrix[currentIndex][valuesTraversed]);
                if (indexSwitcher == numbers.length) indexSwitcher = 0;

                ++valuesTraversed;

                if (valuesTraversed == matrix[currentIndex].length) {
                    ++currentIndex;
                    valuesTraversed = 0;
                }
            }
        }

        double max = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < nThreads; i++) tasks.add(new MatrixMaxCallable(numbers[i]));
        for(Future<Double> i : executor.invokeAll(tasks)) {
            double value = i.get();
            if (value > max) max = value;
        }

        executor.shutdown();

        return max;
    }

    private static class MatrixMaxCallable extends ArrayAggregator<Double> implements Callable<Double> {
        public MatrixMaxCallable(ArrayList<Double> array) {
            super(array);
        }

        public Double call() throws Exception {
            return this.stream().max(new Comparator<Double>() {
                @Override
                public int compare(Double o1, Double o2) {
                    return o1.compareTo(o2);
                }
            }).get();
        }
    }
}
