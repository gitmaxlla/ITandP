import java.util.*;
import java.util.concurrent.*;

public final class MatrixMaxThreader extends AThreader<Double, Double[][]> {
    public enum IndexingMethod {
        FLATTEN,
        TRAVERSE_LADDER
    };

    private IndexingMethod indexingMethod;

    @Override
    protected Double processResults(List<Future<Double>> list) throws ExecutionException, InterruptedException {
        double max = Double.NEGATIVE_INFINITY;

        for(Future<Double> i : list) {
            double value = i.get();
            if (value > max) max = value;
        }

        return max;
    }

    @Override
    protected void traverseToFill(Double[][] array, ArrayList<Double>[] numbers, int nThreads) {
        if (indexingMethod == IndexingMethod.FLATTEN)
        {
            int counter = 0;

            // Not good code
            Object[] objFlattened = Arrays.stream(array).flatMap(Arrays::stream).toArray();
            Double[] mFlattened = new Double[objFlattened.length];
            for (int i = 0; i < objFlattened.length; i++) {
                mFlattened[i] = Double.valueOf(objFlattened[i].toString());
            }
            // 


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

            while (currentIndex < array.length) {
                numbers[indexSwitcher++].add(array[currentIndex][valuesTraversed]);
                if (indexSwitcher == numbers.length) indexSwitcher = 0;

                ++valuesTraversed;

                if (valuesTraversed == array[currentIndex].length) {
                    ++currentIndex;
                    valuesTraversed = 0;
                }
            }
        }
    }

    @Override
    protected Callable<Double> instantiate(ArrayList<Double> numbers) {
        return new MatrixMaxCallable(numbers);
    }

    public MatrixMaxThreader(int splitFactor) {
        super(splitFactor);
    }

    public double calculate(Double[][] matrix, IndexingMethod indexingMethod) throws Exception {
        this.indexingMethod = indexingMethod;
        int matrix_size = 0;
        for (Double[] i : matrix) matrix_size += i.length;
        return super.calculate(matrix, matrix_size, 0.0);
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
