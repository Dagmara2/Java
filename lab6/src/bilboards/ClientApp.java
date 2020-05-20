package bilboards;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ClientApp extends JFrame implements IClient, Serializable, ActionListener {

    private String host= "Client";
    private int clientPort = 2950;
    private Registry registry;
    private IClient interfaceClient;
    private IManager interfaceManager;
    public List<Integer> orders = new ArrayList<>();
    ClientApp clientApp;

    private JTextArea messageArea = new JTextArea(10,15);
    private JTextField timeField = new JTextField(15);
    private JButton confirmBtn = new JButton("Confirm");

    protected ClientApp() throws RemoteException, NotBoundException {
        super("Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(550, 400);
        GridLayout layout = new GridLayout(4, 2, 20, 20);
        JPanel panel = new JPanel();
        panel.setSize(550, 150);
        panel.setLayout(layout);

        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.add(messageArea);
        panel.add(timeField);
        panel.add(confirmBtn);

        this.confirmBtn.addActionListener(this);
        this.add(panel);
        setVisible(true);

        initializeClient();
    }
    public void initializeClient() throws RemoteException, NotBoundException
    {
        ClientApp nClient = new ClientApp();
        // IManager interfaceManager = (IManager) UnicastRemoteObject.exportObject(this, Registry.REGISTRY_PORT);
        this.registry = LocateRegistry.getRegistry(clientPort);
        this.registry.rebind(host, nClient);
        this.setInterfaceManager((IManager) registry.lookup("Manager"));

    }

    @Override
    public void setOrderId(int orderId) throws RemoteException {
        orders.add(orderId);
    }
    @Override
    public List<Integer> getOrders() throws RemoteException {
        return orders;
    }
    public static void main(String[] args) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if(o==confirmBtn)
        {

            try {
                clientApp.getInterfaceManager().placeOrder(new Order(messageArea.getText(), Duration.parse(timeField.getText()),clientApp));
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }

        }

    }
    public void setInterfaceManager(IManager interfaceManager) {
        this.interfaceManager = interfaceManager;
    }

    public IManager getInterfaceManager() {
        return interfaceManager;
    }
}
