package app.sample;

import library.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.PrivateKey;
import java.security.PublicKey;


public class ClientRead {


    private BufferedReader reader;
    private Socket socket;
    private String name;
    private int port;
    Library library;
    PrivateKey privateKey;
    PublicKey publicKey;
    String msgEncrypted;
    Thread thread=null;

    ClientRead(int port) throws IOException {

        this.port=port;

    }
   public void start() throws IOException {
       InputStream input = socket.getInputStream();
       reader = new BufferedReader(new InputStreamReader(input));
       String response = reader.readLine();
   }
   public void stop(){}

   public void init() throws IOException {

   }
}
