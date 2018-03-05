package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Spam Master 3000");

        TableView tableView = new TableView();
        tableView.setEditable(true);

        TableColumn fileName = new TableColumn("File");
        fileName.setMinWidth(250);
        TableColumn actualClass = new TableColumn("Actual Class");
        actualClass.setMinWidth(100);
        TableColumn spamProb = new TableColumn("Spam Probability");
        spamProb.setMinWidth(250);
        tableView.getColumns().addAll(fileName, actualClass, spamProb);
        tableView.setPadding(new Insets(1,1,20,1));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(tableView);

        primaryStage.setScene(new Scene(stackPane, 600, 475));
        primaryStage.show();

        //Training.main();

    }



    public static void main(String[] args) {
        launch(args);
    }
}