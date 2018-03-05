package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Training {

    public static void main() {
        //Map<String, Integer> spamMap = new HashMap<String, Integer>();

        File path = new File("data/train/spam");
        Map<String, Integer> spamMap = makeMap(path);

        path = new File("data/train/ham");
        Map<String, Integer> hamMap1 = makeMap(path);

        path = new File("data/train/ham2");
        Map<String, Integer> hamMap2 = makeMap(path);

        Map<String, Integer> hamMap= new HashMap<>();
        hamMap.putAll(hamMap1);
        hamMap.putAll(hamMap2);


    }

    public static Map<String, Integer> makeMap(File path){
        File[] files = path.listFiles();
        Map<String, Integer> map= new HashMap<>();

        for (File file : files) {
            try {
                System.out.println(file);
                Scanner scanner = new Scanner(file);
                scanner.useDelimiter("[^0-9]");
                while (scanner.hasNext()) {
                    String word = scanner.next();
                    word = word.toLowerCase();
                    if (map.containsKey(word)) {
                        int count = map.get(word) + 1;
                        map.put(word, count);
                    } else {
                        map.put(word, 1);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}