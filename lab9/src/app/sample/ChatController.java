package app.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import library.*;

import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ResourceBundle;




public class ChatController implements Initializable {

    @FXML
    private Label  sendLabel, msgLabel;
    @FXML
    private Button sendBtn, selectBtn;
    @FXML
    private TextArea msgArea,recivedArea;
    @FXML
    private TextField nameTF,portTF;


    private FileChooser fileChooser;
    Library library;
    PrivateKey privateKey;
    PublicKey publicKey;
    String msgEncrypted, msgDecrypted;
    private KeyPair pair;
    ClientRead cr;
    String hostname = "localhost";
    private Socket socket;
    private PrintWriter printWriter;
    private int port=2020;
    ClientWrite cw;

    public void sendMsg(ActionEvent actionEvent) throws Exception {

        String name = nameTF.getText();
        privateKey = library.setPrivate("KeyPair/privateKey" + name);
        msgEncrypted = library.encryptMsg(msgArea.getText(),privateKey);
        socket=new Socket(hostname, Integer.parseInt(portTF.getText()));
        OutputStream outputStream = socket.getOutputStream();
        printWriter=new PrintWriter(outputStream);
        printWriter.print(name + ": "+ msgEncrypted);
        printWriter.flush();
        printWriter.close();
        socket.close();
        outputStream.close();
    }

    public void selectKey(ActionEvent actionEvent) throws FileNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException {
        fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        library = new Library();

        if(selectedFile!=null)
        {
            try {
                publicKey=library.setPublic("KeyPair/"+ selectedFile.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Public key: "+publicKey);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            cr=new ClientRead(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void decodeMsg(ActionEvent actionEvent) throws Exception {

        //publicKey = library.setPublic("KeyPair/privateKey" + name);
        msgDecrypted=library.decryptMsg(recivedArea.getText(),publicKey);
        recivedArea.clear();
        recivedArea.setText(msgDecrypted);

    }
}
