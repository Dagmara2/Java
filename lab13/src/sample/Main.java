package sample;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
       /* FXMLLoader loader = new FXMLLoader();
        String fxmlDocPath = "sample.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        GridPane root = (GridPane) loader.load(fxmlStream);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("A FXML Example without any Controller");
        primaryStage.show();*/

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 700,400);
        scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("FXML");
        primaryStage.show();

    }
}