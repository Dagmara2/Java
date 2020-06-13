package Layer;

import javax.swing.*;

public class Layer {
    public static void main(String[] args){

        LayerGUI lg = new LayerGUI();

        JFrame frame = new JFrame();
        frame.setContentPane(lg.getMainPanel());
        frame.pack();
        frame.setVisible(true);

        lg.draw();

    }
}