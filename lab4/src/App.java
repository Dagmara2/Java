import javafx.stage.FileChooser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class App extends JFrame implements ActionListener {


    private JPanel panel = new JPanel();
    //private JComboBox<String> comboBox = new JComboBox<>();
    private JButton loadBtn = new JButton("Load");
    private JTextArea inputArea = new JTextArea(5,20);
    private JTextArea outputArea = new JTextArea(5,20);
    private JTextArea infoArea = new JTextArea(5,20);
    private JButton chooseBtn = new JButton("Choose");

    String className = "TXT";

    Font font = new Font("MonoSpaced", Font.BOLD, 12);

    private List<String> foundClassNames = new ArrayList<>();

    CClassLoader loaderClass = new CClassLoader();
    String message;

    Class cl;
    Object obj;


    public static void main(String [] args)
    {
        new App();
    }

    public App() {



        inputArea.setEditable(true);
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        infoArea.setEditable(false);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);


        panel.add(chooseBtn);
        panel.add(loadBtn);

        panel.add(infoArea);
        panel.add(inputArea);
        panel.add(outputArea);
        setContentPane(panel);
        setTitle("Lab04");
        setSize(new Dimension(600,600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        outputArea.setFont(font);
        inputArea.setFont(font);
        infoArea.setFont(font);

        loadBtn.addActionListener(this);
        chooseBtn.addActionListener(this);
       // comboBox.setEditable(false);
      //  comboBox.addActionListener(this);
        //comboBox.setMaximumRowCount(3);

    }

    public String getMessage()
    {
       message = inputArea.getText();
       return message;
    }



    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if(e.getSource()==loadBtn)
        {
            String tempText = inputArea.getText();
            CClassLoader loader = new CClassLoader();
            Class<?> c = null;
            try {
                c = loader.findClass(className);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            Object o = null;
            try {
                o = c.getDeclaredConstructor().newInstance();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            }
            Method main = null;
            try {
                main = c.getMethod("submitTask", String.class);
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            }
            Object r = null;
            try {
                r = main.invoke(o,tempText);
            } catch (IllegalAccessException ex) {


            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }

            Method getInfo = null;
            try {
                getInfo = c.getMethod("getInfo");
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            }
            Object objInfo = null;
            try {
                objInfo = getInfo.invoke(o);
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }

            Method getResult = null;
            try {
                getResult = c.getMethod("getResult");
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            }
            Object objResult = null;
            try {
                objResult = getResult.invoke(o);
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }

            infoArea.setText(objInfo.toString());

            outputArea.setText(objResult.toString());
            System.out.print(r);
        }

        if(e.getSource()==chooseBtn)
        {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "CLASS", "class");
            fileChooser.setFileFilter(filter);
            int f = fileChooser.showOpenDialog(null);

            if(f==JFileChooser.APPROVE_OPTION) {
                File file=fileChooser.getSelectedFile();
                String result2 = file.getName().substring(0, file.getName().indexOf("."));
                System.out.println(result2);
                className = result2;
            }

        }

    }




}
