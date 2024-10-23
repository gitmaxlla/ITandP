import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Top10Words {
    public static void main(String[] args) {
        File file = new File("pg1228.txt");
        Scanner scanner = null;
        String text = "";

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Map<String, Integer> words = new HashMap<>();

        scanner.findAll("[A-Za-z]{2,}|[Aa]").forEach(match -> {
            String word = match.group();
            if (!words.containsKey(word)) words.put(word, 1);
            else words.put(word, words.get(word) + 1);
        });

        scanner.close();

        ArrayList<Map.Entry<String, Integer>> wordList = new ArrayList<>();

        for (String key: words.keySet()) {
            wordList.add(Map.entry(key, words.get(key)));
        }

        Collections.sort(wordList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() == o2.getValue()) return 0;
                return (o1.getValue() < o2.getValue()) ? 1 : -1;
            }
        });

        for (int i = 0; i < 10; i++) {
            System.out.println(wordList.get(i));
        }
    }
}