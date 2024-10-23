import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public abstract class AThreader<T> {
    private int splitFactor = 2;

    abstract void traverseToFill();
    abstract T processResults(List<Future<T>> list);

    public AThreader(int splitFactor) {
        if (splitFactor < 2) throw new IllegalArgumentException("Split factor should be not less than 2.");
        this.splitFactor = splitFactor;
    }

    protected T calculate(T[] array, T initialResult) throws Exception {
        int nThreads = Math.min(array.length, splitFactor);
        int counter = 0;

        ArrayList<Callable<T>> tasks = new ArrayList<>();
        ArrayList<T>[] numbers = new ArrayList[nThreads];
        for (int i = 0; i < numbers.length; i++) numbers[i] = new ArrayList<>();

        traverseToFill();
        for (int i = 0; i < nThreads; i++) tasks.add(new ArrayAggregator<T>(numbers[i]) {
            @Override
            public T call() throws Exception {
                return ArrayAggregator<T>.call();
            }
        });

        try(ExecutorService executor = Executors.newFixedThreadPool(nThreads)) {
            initialResult = processResults(executor.invokeAll(tasks));
            executor.shutdown();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return initialResult;
    }
}
