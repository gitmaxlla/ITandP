import java.util.*;
import java.util.concurrent.*;

public class MovingStocks {
    private final LinkedList<Double> itemsWeight;
    private final double MAX_CAPACITY = 150.0;

    public MovingStocks(Double[] itemsWeight) {
        if (itemsWeight.length < 1) throw new IllegalArgumentException("Array should have at least one element");
        this.itemsWeight = new LinkedList<Double>(Arrays.stream(itemsWeight).toList());
        if (Arrays.stream(itemsWeight).max((Double::compareTo)).get() > MAX_CAPACITY) throw new IllegalArgumentException("The maximum weight value provided exceeds how much a worker can carry (" + MAX_CAPACITY + ").");
    }

    private synchronized void takeItems(LinkedList<Double> array) throws InterruptedException {
        int totalWeight = 0;
        ArrayList<Double> collectedItems = new ArrayList<>();

        if (array.isEmpty()) return;

        while (totalWeight + array.getFirst() <= MAX_CAPACITY) {
            collectedItems.add(array.getFirst());
            totalWeight += array.removeFirst();
        }

        System.out.println("[" + Thread.currentThread().getName() + "] Transferred (" + totalWeight + "): " + collectedItems.toString());
    }

    private synchronized Double peekItem(LinkedList<Double> array) {
        return array.getFirst();
    }

    public void transfer() throws InterruptedException {
        LinkedList<Double> transferList = itemsWeight;
        CountDownLatch workersBusy = new CountDownLatch(3);

        try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {
            List<Runnable> workerJobs = new ArrayList<>();
            Runnable transferItems = () -> {
                try {
                    while(!transferList.isEmpty()) {
                        takeItems(transferList);
                        Thread.sleep(Math.round((Math.random() * 1000)) + 1000);
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }finally {
                    workersBusy.countDown();
                }
            };

            for (int i = 0; i < 3; i++) executorService.submit(transferItems);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        workersBusy.await();
    }
}
