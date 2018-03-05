package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;
import java.lang.Math;

public class Testing {
    private static TreeMap<String, Double> treeMap = Training.getMap(); //imports treeMap from testing
    private static ObservableList<TestFile> emails = FXCollections.observableArrayList(); //holds emails
    private static int numTruePositives, numTrueNegatives, numFalsePositives; //variables required for precision/accuracy
    private static int numFiles = 0; //num of files


    public static void main(File directory) {
        File path = new File(directory + "/test/spam");
        Test("Spam", path); //runs the testing algorithm on the test spam files

        path = new File(directory + "/test/ham");
        Test("Ham", path); //runs the testing algorithm on the test ham files
    }

    public static void Test(String type, File path){
        File[] files = path.listFiles();  //puts all the files into an array to iterate through
        for (File file : files) {
            numFiles++; //counts files
            try {
                Scanner scanner = new Scanner(file);
                scanner.useDelimiter("[ ;^0-9.\n:\t!?/]"); //the chars that split words
                double eta = 0.0; //summation for probability of spam given file
                while (scanner.hasNext()) {
                    String word = scanner.next();
                    word = word.toLowerCase();
                    if (treeMap.containsKey(word)){ //checks if the word is in treemap
                            double spamGivenWord = treeMap.get(word);
                            if (spamGivenWord>0.0f && spamGivenWord <1.0f) {
                                //System.out.println("ETA: "+ eta);
                                eta = eta + Math.log(1 - spamGivenWord) - Math.log(spamGivenWord);                            }
                            }
                    }
                double spamGivenFile = (double) 1 / (1 + Math.pow(Math.E, eta));
                emails.add(new TestFile(file.getName(), spamGivenFile, type));
                if (type.equals("Spam") && spamGivenFile>0.5){
                    numTruePositives++;
                } else if(type.equals("Ham") && spamGivenFile<0.5){
                    numTrueNegatives++;
                } else if (type.equals("Ham") && spamGivenFile>0.5) {
                    numFalsePositives++;
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not Found");
                e.printStackTrace();
            }
        }
    }

    //to return data for the TableView
    public static ObservableList<TestFile> getFiles(){
        return emails;
    }

    //calculates precision
    public static String getPrecision(){
        return Double.toString(numTruePositives/(numFalsePositives+numTrueNegatives));
    }

    //calculates accuracy
    public static String getAccuracy(){
        return Double.toString((numFalsePositives+numTrueNegatives)/numFiles);
    }
}