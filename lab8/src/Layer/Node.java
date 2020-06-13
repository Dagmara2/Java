package Layer;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


@WebService(serviceName = "Node")
public class Node{
    private static int portNumber = 4444;

    public Node(){

    }

    public int getMyPortNumber() {
        return myPortNumber;
    }

    private int myPortNumber;
    private String layerName;
    protected String nodeName;
    private Node nextNode;
    private ServerSocket serverSocket;
    private static int ID = 0;

    public Socket getSocket() {
        return socket;
    }

    private Socket socket;


    public Node(String layerName, Node nextNode) {
        registerNode();
        this.nextNode = nextNode;
        this.layerName = layerName;
        ID++;
        this.nodeName = layerName + ID;
    }

    private void registerNode(){
        Boolean ex;
        System.out.println("Registering node...");
        serverSocket = null;
        do {
            try {
                ex = false;
                //serverSocket = new ServerSocket(portNumber);

                //try {
                Socket echoSocket = new Socket("http://localhost", portNumber);
                PrintWriter out =
                        new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn =
                        new BufferedReader(
                                new InputStreamReader(System.in));
                socket = echoSocket;
            } catch (IOException e) {
                ex = true;
                portNumber++;
            }
        }while(ex);
        System.out.println("Node registered at: " + (portNumber));
        myPortNumber = portNumber;
        portNumber++;

    }

    public void activateNode(){
        try {
            System.out.println("Listening " + myPortNumber + "...");
            Socket clientSocket = serverSocket.accept();
            PrintWriter out =
                    new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public String getNodeName() {
        return nodeName;
    }
}
