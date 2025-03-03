import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public final class ArraySumThreader extends AThreader<Integer, Integer[]> {
    @Override
    protected void traverseToFill(Integer[] array, ArrayList<Integer>[] numbers, int nThreads) {
        int counter = 0;

        while (counter < array.length) {
            numbers[counter % nThreads].add(array[counter]);
            ++counter;
        }
    }

    @Override
    protected Callable<Integer> instantiate(ArrayList<Integer> numbers) {
        return new ArraySumCallable(numbers);
    }

    public Integer calculate(Integer[] array) throws Exception {
        return super.calculate(array, array.length, 0);
    }

    @Override
    protected Integer processResults(List<Future<Integer>> list) throws ExecutionException, InterruptedException {
        int sum = 0;
        for(Future<Integer> i : list) sum += i.get();
        return sum;
    }

    public ArraySumThreader(int splitFactor) {
        super(splitFactor);
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
