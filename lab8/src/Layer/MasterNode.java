package Layer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class MasterNode extends Node {


    public MasterNode(String layerName) {
        super(layerName, null);
        this.setNextNode(this);
        this.nodeName += "+G";
    }
}
