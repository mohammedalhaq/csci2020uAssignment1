package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.border.Border;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Spam Master 3000");

        //creates a TableView to view results
        TableView tableView = new TableView();
        tableView.setEditable(true);

        //initializes TableView columns
        TableColumn fileName = new TableColumn("File");
        fileName.setMinWidth(280);
        TableColumn actualClass = new TableColumn("Actual Class");
        actualClass.setMinWidth(140);
        TableColumn spamProb = new TableColumn("Spam Probability");
        spamProb.setMinWidth(280);
        //Sets the colums to the TableView
        tableView.getColumns().addAll(fileName, actualClass, spamProb);
        tableView.setPadding(new Insets(1,1,1,1));
        tableView.setMaxHeight(600);
        tableView.setTranslateY(-50); //setMaxHeight centers the table, this corrects that

        //initializes Labels and TextFields for the bottom part of the program
        Label accuracyLabel = new Label("Accuracy");
        Label precisionLabel = new Label("Precision");
        TextField accuracyField = new TextField();
        TextField precisionField= new TextField();
        accuracyField.setEditable(false);                           //the accuracy should not be editable
        precisionField.setEditable(false);                          //the precision should not be editable

        //creates a GridPane to lay the Labels and TextFields out
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.add(accuracyLabel, 0, 0);
        gridPane.add(accuracyField, 1, 0);
        gridPane.add(precisionLabel, 0, 1);
        gridPane.add(precisionField, 1, 1);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setTranslateY(600);

        //Creates a stackpane to combine the GridPane and TableView
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(tableView, gridPane);
        stackPane.setTranslateY(0);
        primaryStage.setScene(new Scene(stackPane, 700, 700));
        primaryStage.show();

        //Training.main();

    }



    public static void main(String[] args) {
        launch(args);
    }
}

