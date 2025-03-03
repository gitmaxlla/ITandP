import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

public abstract class ArrayAggregator<T> implements Callable<T> {
    private final ArrayList<T> array;

    public ArrayAggregator(ArrayList<T> array) {
        if (array.isEmpty()) throw new IllegalArgumentException("Provided array should not be empty");
        this.array = array;
        System.out.println("-> " + array.toString());
    }

    public Stream<T> stream() {
        return array.stream();
    }
}
