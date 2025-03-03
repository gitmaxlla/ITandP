import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public abstract class AThreader<T, A> {
    private int splitFactor = 2;

    protected abstract void traverseToFill(A array, ArrayList<T>[] numbers, int nThreads);
    protected abstract T processResults(List<Future<T>> list) throws ExecutionException, InterruptedException;
    protected abstract Callable<T> instantiate(ArrayList<T> numbers);

    public AThreader(int splitFactor) {
        if (splitFactor < 2) throw new IllegalArgumentException("Split factor should be not less than 2.");
        this.splitFactor = splitFactor;
    }

    protected T calculate(A array, int arrayLength, T initialResult) throws Exception {
        int nThreads = Math.min(arrayLength, splitFactor);
        int counter = 0;

        ArrayList<Callable<T>> tasks = new ArrayList<>();
        ArrayList<T>[] numbers = new ArrayList[nThreads];
        for (int i = 0; i < numbers.length; i++) numbers[i] = new ArrayList<>();

        traverseToFill(array, numbers, nThreads);
        for (int i = 0; i < nThreads; i++) tasks.add(instantiate(numbers[i]));

        try(ExecutorService executor = Executors.newFixedThreadPool(nThreads)) {
            initialResult = processResults(executor.invokeAll(tasks));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return initialResult;
    }
}
