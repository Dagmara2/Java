package app.sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    /*@Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("chat.fxml"));
        primaryStage.setTitle("Chat");
        primaryStage.setScene(new Scene(root, 600, 350));
        primaryStage.show();
    }*/


    @Override
    public void start(Stage primaryStage) throws Exception {
        ClientWrite cw1 = new ClientWrite("Ala");
       // ClientWrite cw2 = new ClientWrite("Ola");
       // cw1.init(new Stage());
    }


    public static void main(String[] args) {

        launch(args);
    }
}
