import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Sales {
    private ConcurrentHashMap<String, Integer> items = new ConcurrentHashMap<>();

    public void addItem(String name, int totalSales) {
        if(!items.containsKey(name)) items.put(name, totalSales);
        else items.put(name, items.get(name) + totalSales);
    }

    public void printInfo() {
        if (items.isEmpty()) return;
        Iterator<String> iterator = items.keys().asIterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println(key + ": " + items.get(key));
        }
    }

    public int totalSales() {
        int sum = 0;
        if (items == null) return 0;

        Iterator<String> iterator = items.keys().asIterator();
        while (iterator.hasNext()) {
            sum += items.get(iterator.next());
        }
        return sum;
    }

    public String bestseller() {
        if (items.isEmpty()) return null;
        LinkedList<Map.Entry<String, Integer>> list = new LinkedList<>(items.entrySet());
        list.sort((i1, i2) -> {
            if (i1.getValue() > i2.getValue()) return -1;
            else if (i1.getValue() < i2.getValue()) return 1;
            return 0;
        });
        return list.get(0).getKey();
    }
}
