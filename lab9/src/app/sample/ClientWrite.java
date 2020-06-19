package app.sample;

import library.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class ClientWrite {

    Socket socket;
    public String name;
    private int port;
    Library library;
    PrivateKey privateKey;
    PublicKey publicKey;
    String msgEncrypted;

    //sendMsg(){}
    ClientWrite(String name) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
        this.name=name;
        generate();
        init(new Stage());
    }


    public void generate() throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {

        library = new Library();
        library.genKey();
        library.createKeys(name);
    }
    public void init(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("chat.fxml"));
        primaryStage.setTitle("Chat");
        primaryStage.setScene(new Scene(root, 600, 350));
        primaryStage.show();
    }
}
