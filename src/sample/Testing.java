package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;
import java.lang.Math;

public class Testing {
    static TreeMap<String, Double> treeMap = Training.getMap(); //imports treeMap from testing
    static ObservableList<TestFile> emails = FXCollections.observableArrayList(); //for tableView

    public static void main() {
        File path = new File("data/test/spam");
        Test("spam", path); //runs the testing algorithm on the test spam files

        path = new File("data/test/ham");
        Test("ham", path); //runs the testing algorithm on the test ham files
    }

    public static void Test(String type, File path){
        double spamGivenFile = 0.0;
        File[] files = path.listFiles();
        for (File file : files) {
            try {
                Scanner scanner = new Scanner(file);
                scanner.useDelimiter("[ ;^0-9.\n   ]"); //the chars that split words
                double eta = 0.0;
                while (scanner.hasNext()) {
                    String word = scanner.next();
                    word = word.toLowerCase();
                    if (treeMap.containsKey(word)){
                            double spamGivenWord = treeMap.get(word);
                            if (spamGivenWord>0f && spamGivenWord <1f) {
                                System.out.println("ETA: "+ eta);
                                eta = eta + (Math.log(1 - spamGivenWord) - Math.log(spamGivenWord));
                            }
                    }
                }
                spamGivenFile = (double) 1 / (1 + Math.pow(Math.E, eta));
                emails.add(new TestFile(file.getName(), spamGivenFile, type));
            } catch (FileNotFoundException e) {
                System.out.println("File not Found");
                e.printStackTrace();
            }
        }
    }

    public static ObservableList<TestFile> getFiles(){
        return emails;
    }
}