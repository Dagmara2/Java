package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import mbean.ClientOp;
import mbean.IClientOp;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;

public class JMXController {

    @FXML
    private Button ABtn, BBtn, CBtn, aNxtBtn, bNxtBtn, cNxtBtn;
    @FXML
    private TextArea aInfoArea, bInfoArea, cInfoArea, ticketNumberField;
    @FXML
    private ChoiceBox<String> priorityCB;

    ClientOp clientOp;
    int jmxPort = 8080;
    JMXServiceURL target = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:" + jmxPort + "/jmxrmi");
    JMXConnector connector = JMXConnectorFactory.connect(target);
    MBeanServerConnection mbs = connector.getMBeanServerConnection();
    ObjectName oname = new ObjectName(ManagementFactory.RUNTIME_MXBEAN_NAME);
    String vendor = (String) mbs.getAttribute(oname, "VmVendor");
    IClientOp proxy = JMX.newMXBeanProxy(mbs, new ObjectName("mbean.dszykulska:name=" + "ClientOp"),
            IClientOp.class);

    public JMXController() throws IOException, MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException {
    }


    public void initialize() {
        priorityCB.getItems().add("1");
        priorityCB.getItems().add("2");
        priorityCB.getItems().add("3");
    }

    public void APress(ActionEvent actionEvent) {


        int ticketNumber = 0;
        for (Object item : ClientOp.arrayListAQueue) {
            ticketNumber = (int) item;
        }
        ticketNumber++;
        ClientOp.arrayListAQueue.add(ticketNumber);


        ticketNumberField.setText("A" + String.valueOf(clientOp.getALastTicket()));
        aInfoArea.setText("Queue: " + String.valueOf(ClientOp.arrayListAQueue) + "\n\nNow: " + String.valueOf(clientOp.getAFirstTicket()));


        //ClientAOp.deleteTicket();
        // aInfoArea.setText("Queue: " +String.valueOf(ClientAOp.arrayListAQueue)+"\n\nNow: "+String.valueOf(ClientAOp.getFirstTicket()));
        //if(ClientAOp.arrayListAQueue.size() == 0){aInfoArea.setText("Nobody in queue");}

    }

    public void BPress(ActionEvent actionEvent) {


        int ticketNumber = 0;
        for (Object item : ClientOp.arrayListBQueue) {
            ticketNumber = (int) item;
        }
        ticketNumber++;
        ClientOp.arrayListBQueue.add(ticketNumber);

        ticketNumberField.setText("B" + String.valueOf(clientOp.getBLastTicket()));
        bInfoArea.setText("Queue: " + String.valueOf(ClientOp.arrayListBQueue) + "\n\nNow: " + String.valueOf(clientOp.getBFirstTicket()));


    }

    public void CPress(ActionEvent actionEvent) {

        ticketNumberField.setText("");

        int ticketNumber = 0;
        for (Object item : ClientOp.arrayListCQueue) {
            ticketNumber = (int) item;
        }
        ticketNumber++;
        ClientOp.arrayListCQueue.add(ticketNumber);

        ticketNumberField.setText("C" + String.valueOf(clientOp.getCLastTicket()));
        cInfoArea.setText("Queue: " + String.valueOf(ClientOp.arrayListCQueue) + "\n\nNow: " + String.valueOf(clientOp.getCFirstTicket()));

    }

    public void StartA(ActionEvent actionEvent) {
        String value = (String) priorityCB.getValue();
        if (value == "1") {

                try {

                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clientOp.deleteATicket();
                aInfoArea.setText("Queue: " + String.valueOf(ClientOp.arrayListAQueue) + "\n\nNow: " + String.valueOf(clientOp.getAFirstTicket()));
            }


        if (value == "2") {

                try {

                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            clientOp.deleteATicket();
            aInfoArea.setText("Queue: " + String.valueOf(ClientOp.arrayListAQueue) + "\n\nNow: " + String.valueOf(clientOp.getAFirstTicket()));
        }
        if (value == "3") {
            try {

                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            clientOp.deleteATicket();
            aInfoArea.setText("Queue: " + String.valueOf(ClientOp.arrayListAQueue) + "\n\nNow: " + String.valueOf(clientOp.getAFirstTicket()));
            }
    }

    public void StartB(ActionEvent actionEvent) {
        String value = (String) priorityCB.getValue();
       if (value == "1") {
              try {

                Thread.sleep(3000);
           } catch (InterruptedException e) {
             e.printStackTrace();
           }

                clientOp.deleteBTicket();
                bInfoArea.setText("Queue: " + String.valueOf(ClientOp.arrayListBQueue) + "\n\nNow: " + String.valueOf(clientOp.getBFirstTicket()));

        }

       if (value == "2") {
                try {

                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
           clientOp.deleteBTicket();
           bInfoArea.setText("Queue: " + String.valueOf(ClientOp.arrayListBQueue) + "\n\nNow: " + String.valueOf(clientOp.getBFirstTicket()));
        }
        if (value == "3") {
                try {

                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            clientOp.deleteBTicket();
            bInfoArea.setText("Queue: " + String.valueOf(ClientOp.arrayListBQueue) + "\n\nNow: " + String.valueOf(clientOp.getBFirstTicket()));
        }
    }

    public void StartC(ActionEvent actionEvent) {
        String value = (String) priorityCB.getValue();
        if (value == "1") {

                try {

                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clientOp.deleteCTicket();
                cInfoArea.setText("Queue: " + String.valueOf(ClientOp.arrayListCQueue) + "\n\nNow: " + String.valueOf(clientOp.getCFirstTicket()));
        }

        if (value == "2") {
                try {

                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            clientOp.deleteCTicket();
            cInfoArea.setText("Queue: " + String.valueOf(ClientOp.arrayListCQueue) + "\n\nNow: " + String.valueOf(clientOp.getCFirstTicket()));
        }
        if (value == "3") {
                try {

                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            clientOp.deleteCTicket();
            cInfoArea.setText("Queue: " + String.valueOf(ClientOp.arrayListCQueue) + "\n\nNow: " + String.valueOf(clientOp.getCFirstTicket()));
        }
    }
}
