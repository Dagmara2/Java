package bilboards;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manager extends JFrame implements ActionListener {

    private JList<IBillboard> billboardJList;
    private JButton refreshBtn=new JButton("Refresh");

    public Manager()
    {
        super("Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(550, 400);
        GridLayout layout = new GridLayout(4, 2, 20, 20);
        JPanel panel = new JPanel();
        panel.setSize(550, 150);
        panel.setLayout(layout);

        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        this.refreshBtn.addActionListener(this);
        panel.add(billboardJList);
        panel.add(refreshBtn);

        this.add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object o = e.getSource();

        if(o==refreshBtn)
        {

        }

    }
}
