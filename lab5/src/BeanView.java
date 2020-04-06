import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class BeanView implements VetoableChangeListener, PropertyChangeListener {

    public JPanel mainPanel;
    private JTextField chooseTimeField;
    private JTextField chooseMessageField;
    private JTextArea messageArea;
    private JTextField timeField;
    private JButton confirmBtn;
    private JButton confirmMessageBtn;
    private JTextField currentTimeField;
    private JTextField currentTime;
    private JButton refreshBtn;
    private JTextField chooseMessageTextField;

    Bean bean;


public BeanView(Bean bean)
{
    this.bean=bean;
    messageArea.setText(bean.getText());
    bean.addPropertyChangeListener(this);
    bean.addVetoableChangeListener(this);


    confirmBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            onConfirm();
        }
    });

    confirmMessageBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            onConfirmMessage();
        }
    });
    refreshBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshTime();
        }
    });



}

    private void refreshTime() {
       String now = String.valueOf(LocalTime.now().truncatedTo(ChronoUnit.MINUTES));

        currentTime.setText(now);
    }

    private void onConfirmMessage() { bean.setText(messageArea.getText()); }


    private void onConfirm() {
    try {
        bean.setTime(LocalTime.parse(timeField.getText()));
    } catch (PropertyVetoException e) {
        e.printStackTrace();
    }catch (DateTimeParseException e){
        JOptionPane.showMessageDialog(new JFrame(),
                "Check time format, hh:mm.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
        if(bean.getTime().equals(LocalTime.now().truncatedTo(ChronoUnit.MINUTES))){
            JOptionPane.showMessageDialog(new JFrame(),
                    messageArea.getText() ,
                    "Alarm",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }


    @Override
    public void vetoableChange(PropertyChangeEvent e) throws PropertyVetoException {
        System.out.println(LocalTime.parse((CharSequence)e.getNewValue()));
        if (e.getSource() == bean) {
            if(LocalDate.parse((CharSequence)e.getNewValue()).isBefore(ChronoLocalDate.from(LocalTime.now()))){
                String massage = "The time cant be before now!";
                System.out.println(massage);
                throw new PropertyVetoException(massage, e);
            }
            else{
                timeField.setText(bean.getText());
            }

        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
