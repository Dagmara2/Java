package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        ResourceBundle resources = ResourceBundle.getBundle("language");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"),resources);
        primaryStage.setTitle("Asteroid Counter");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);

    }


    public static void main(String[] args) {
        launch(args);
    }
}