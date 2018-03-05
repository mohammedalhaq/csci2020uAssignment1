package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Training {
    private static int[] fileCount = {0,0}; //0 is # of spam files, 1 is # ham
    private static TreeMap<String, Double> treeMap = new TreeMap<>();


    public static void main() {

        //puts the spam words and counts into a map
        File path = new File("data/train/spam");
        Map<String, Integer> trainSpamFreq = makeMap(path, 0);

        //puts the ham words and counts into a map
        path = new File("data/train/ham");
        Map<String, Integer> hamMap1 = makeMap(path, 1);

        //puts the ham words from the other directory into another map
        path = new File("data/train/ham2");
        Map<String, Integer> hamMap2 = makeMap(path, 1);

        //combines both ham maps
        Map<String, Integer> trainHamFreq = new HashMap<>();
        trainHamFreq.putAll(hamMap1);
        trainHamFreq.putAll(hamMap2);

        //iterates through the spam map to get the spam words
        //only through the spam map because if its not in the spam map then its ham guaranteed
         for (Map.Entry<String, Integer> entry : trainSpamFreq.entrySet()){
            double wordGivenHam = 0; //0 if word isnt in Ham emails then its 0
            int spamValue = entry.getValue();
            String word = entry.getKey();
            if (trainHamFreq.containsKey(word)){
                int hamValue = trainHamFreq.get(word);
                wordGivenHam = (double) hamValue/fileCount[1];
            }
            double wordGivenSpam = (double) spamValue/fileCount[0];
            double spamGivenWord = (wordGivenSpam)/(wordGivenHam+wordGivenSpam);
            //puts the calculated spamGivenWord into the treeMap with the word
            treeMap.put(word, spamGivenWord);
        }

    }

    private static Map<String, Integer> makeMap(File path, int i){
        File[] files = path.listFiles();
        Map<String, Integer> map= new HashMap<>();

        for (File file : files) { //iterates through the files in the directory
            fileCount[i]++;
            try {
                Scanner scanner = new Scanner(file);
                scanner.useDelimiter("[ ;^0-9.\n   ]"); //the chars that split words
                while (scanner.hasNext()){
                    String word = scanner.next();
                    word = word.toLowerCase(); //case dose not matter
                    if (map.containsKey(word)) { //adds 1 if the words is already in the map
                        int count = map.get(word) + 1;
                        map.put(word, count);
                    } else { //puts the first appearance into the map
                        map.put(word, 1);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not Found");
                e.printStackTrace();
            }
        }
        return map;
    }

    public static TreeMap<String, Double> getMap(){
        return treeMap;
    }
}
