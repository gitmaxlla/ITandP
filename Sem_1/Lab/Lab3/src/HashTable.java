import java.util.LinkedList;

public class HashTable <K, V> {
    private int size;
    private final LinkedList<Pair<K, V>>[] array;

    public static void test() {
        HashTable<String, String> myTable = new HashTable<>();
        myTable.put("Jacob", "Employee");
        myTable.put("Jacob2", "Employee");
        myTable.put("Jacob2", "Employee2");

        System.out.println(myTable.toString());
        
        myTable.remove("Jacob");
        System.out.println(myTable.toString());

        myTable.put("Jacob", "Employee");
        System.out.println(myTable.toString());

        myTable.remove("Jacob2");
        myTable.remove("Jacob");
        System.out.println(myTable.toString());
    }

    public HashTable() {
        array = new LinkedList[100];
        size = 0;
    }

    public void put(K key, V value) {
        int index = toIndex(hash(key));

        if (array[index] == null) {
            array[index] = new LinkedList<>();
        }

        for (int i = 0; i < array[index].size(); i++)
        {
            if (key == array[index].get(i).getFirst()) {
                array[index].get(i).setSecond(value);
                return;
            }
        }

        array[index].add(new Pair<K, V>(key, value));
        ++size;
    }

    public V get(K key) {
        int index = toIndex(hash(key));

        for (int i = 0; i < array[index].size(); i++) {
            if (key == array[index].get(i).getFirst()) {
                return array[index].get(i).getSecond();
            }
        }

        return null;
    }

    public String remove(K key) {
        int index = toIndex(hash(key));
        String info = "";

        for (int i = 0; i < array[index].size(); i++) {
            if (key == array[index].get(i).getFirst()) {
                info = array[index].remove(i).toString();
                --size;
                break;
            }
        }

        return info;
    }
    public int toIndex(int hash) {
        return hash % 100;
    }

    public int hash(K key) {
        String base = key.toString();

        int  charSum = 0;

        for (int i = 0; i < base.length(); i++) {
            charSum += base.charAt(i);
            charSum %= 100;
        }

        return charSum;
    }

    @Override
    public String toString() {
        String result = super.toString() + '\n';
        result += "Size: " + size() + '\n';

        if (!isEmpty()) {
            result += "Index table:";
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null && !array[i].isEmpty()) {
                    result += "\n\t" + i + ":\n";
                    for (int j = 0; j < array[i].size(); j++) {
                        result += "\t\t" + array[i].get(j).toString() + '\n';
                    }
                }
            }
        }

        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
