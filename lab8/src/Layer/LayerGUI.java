package Layer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class LayerGUI {
    private JButton newNodeButton;
    private JButton drawButton;
    private JPanel drawingPanel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private JPanel mainPanel;
    private JButton clearButton;
    private JComboBox nodesCB;
    private JButton createMessageButton;
    private Graphics g;
    private String layerName = "A";
    private MasterNode mNode;
    private int noNodes = 1;
    private static int nodeScale = 20;
    ArrayList<Node> nodes = new ArrayList<Node>();

    public LayerGUI(){

        MasterNode mNode = new MasterNode(layerName);
        this.mNode = mNode;
        nodes.add(mNode);
        noNodes++;

        for(Node n: nodes) nodesCB.addItem(n);

        newNodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClear();
                Node newNode = new Node(layerName, nodes.get(0));
                nodes.get(nodes.size()-1).setNextNode(newNode);
                noNodes++;
                nodes.add(newNode);
                nodesCB.removeAllItems();
                for(Node n: nodes) nodesCB.addItem(n);
            }
        });
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClear();
            }
        });
        createMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] array = {layerName};
                MsgCreator.main(array, nodesCB.getSelectedItem());
            }
        });
    }

    private void onClear(){
        drawingPanel.removeAll();
        drawingPanel.updateUI();
    }

    public void draw(){
        int i = 0;
        for(Node n: nodes){
            drawNode(n, i);
            i++;
        }
    }

    private void drawNode(Node n, int index){
        int angle = 360/noNodes;
        angle/=(2*Math.PI);
        Double radius = 0.35*drawingPanel.getWidth()/2;
        Double sin = Math.sin(index*angle);
        Double cos = Math.cos(index*angle);

        drawingPanel.getGraphics().drawOval(drawingPanel.getWidth()/2 - (int)Math.round(cos*radius),
                drawingPanel.getHeight()/2 + (int)Math.round(sin*radius),
                drawingPanel.getWidth()/nodeScale, drawingPanel.getWidth()/nodeScale);
        drawingPanel.getGraphics().drawString(n.getNodeName(),drawingPanel.getWidth()/2 - (int)Math.round(cos*radius),
                drawingPanel.getHeight()/2 + (int)Math.round(sin*radius));
    }
}
