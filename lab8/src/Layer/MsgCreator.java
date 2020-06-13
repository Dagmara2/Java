package Layer;

import javax.swing.*;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class MsgCreator extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField receiver;
    private JComboBox modeCB;
    private JLabel layerLbl;
    private Node sender;
    String layer;
    private JTextArea messageBody;
    private JLabel senderLbl;

    public MsgCreator(String layer, Node sender) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        layerLbl.setText(layer);
        senderLbl.setText(sender.getNodeName());

        modeCB.addItem("Unicast");
        modeCB.addItem("Broadcast");
        modeCB.addItem("Local Broadcast");

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        modeCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mode = (String) modeCB.getSelectedItem();
                if(mode.equals("Unicast") ){
                    receiver.setEnabled(true);
                    receiver.setEditable(true);
                }
                else{
                    receiver.setEnabled(false);
                    receiver.setEditable(false);
                }
            }
        });
    }

    private void onOK() {
        MessageFactory factory = null;
        try {
            factory = MessageFactory.newInstance();
            SOAPMessage message = factory.createMessage();

            SOAPPart soapPart = message.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();

            SOAPHeader header = envelope.getHeader();
            SOAPBody body = envelope.getBody();

            header.addChildElement(new QName("Layer", layer));
            header.addChildElement(new QName("Receiver", receiver.getText()));
            header.addChildElement(new QName("Mode",(String) modeCB.getSelectedItem()));
            header.addChildElement(new QName("Sender",sender.getNodeName()));

            SOAPElement text = body.addBodyElement(new QName("Message from" + sender.getNodeName()));
            text.addTextNode(messageBody.getText());

            message.saveChanges();

            SOAPConnectionFactory myFct = SOAPConnectionFactory.newInstance();
            SOAPConnection myCon = myFct.createConnection();

            Socket sock = sender.getSocket();

            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream(), "UTF-8"));
            // You can use "UTF8" for compatibility with the Microsoft virtual machine.
            wr.write("POSTpath" + " HTTP/1.0\r\n");
            wr.write("Hostalhost\r\n");
            wr.write("Content-Length xmldata.length()" + "\r\n");
            wr.write("Content-Typet/xml; charset=\"utf-8\"\r\n");
            wr.write("\r\n");
            wr.flush();



            //URL endpoint = new URL("http://localhost:" + sender.getNextNode().getMyPortNumber());
            //myCon.

        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args, Object sender) {
        MsgCreator dialog = new MsgCreator(args[0], (Node)sender);
        dialog.layer = args[0];
        dialog.sender = (Node) sender;
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
