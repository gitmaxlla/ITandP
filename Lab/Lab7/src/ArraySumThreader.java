import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ArraySumThreader {
    private int splitFactor = 2;

    public ArraySumThreader(int splitFactor) {
        if (splitFactor < 2) throw new IllegalArgumentException("Split factor should be not less than 2.");
        this.splitFactor = splitFactor;
    }

    public int calculate(int[] array) throws Exception {
        int sum = 0;
        int nThreads = Math.min(array.length, splitFactor);
        int counter = 0;

        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
        ArrayList<Callable<Integer>> tasks = new ArrayList<>();

        ArrayList<Integer>[] numbers = new ArrayList[nThreads];
        for (int i = 0; i < numbers.length; i++) numbers[i] = new ArrayList<>();

        while (counter < array.length) {
            numbers[counter % nThreads].add(array[counter]);
            ++counter;
        }

        for (int i = 0; i < nThreads; i++) tasks.add(new ArraySumCallable(numbers[i]));
        for(Future<Integer> i : executor.invokeAll(tasks)) sum += i.get();
        executor.shutdown();

        return sum;
    }

    private static class ArraySumCallable extends ArrayAggregator<Integer> implements Callable<Integer> {
        public ArraySumCallable(ArrayList<Integer> array) {
            super(array);
        }

        public Integer call() throws Exception {
            AtomicInteger sum = new AtomicInteger();
            this.stream().forEach(sum::addAndGet);
            return sum.get();
        }
    }
}
