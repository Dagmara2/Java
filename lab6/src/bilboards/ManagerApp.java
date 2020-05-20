package bilboards;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import static javafx.application.Application.launch;

public class ManagerApp extends JFrame implements IManager, Serializable, ActionListener {

    private String host = "Manager";
    private int port = 2900;
    private static BillboardApp billboardApp;
    private static ClientApp clientApp;
    private List<IBillboard> billboardList = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();
    private int billboardID;
    private int orderID = 0;
    Registry registry;

    private JList<IBillboard> billboardJList ;
    private JButton refreshBtn=new JButton("Refresh");
    private DefaultListModel billboardModel = new DefaultListModel();

    public List<IBillboard> getBillboardList() {
        return billboardList;
    }

    public void initializeManager() throws RemoteException, NotBoundException {

        ManagerApp nManager = new ManagerApp();
       // IManager interfaceManager = (IManager) UnicastRemoteObject.exportObject(this, Registry.REGISTRY_PORT);
        this.registry = LocateRegistry.createRegistry(port);
        this.registry.rebind(host, nManager);
    }

    protected ManagerApp() throws RemoteException, NotBoundException {
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
        billboardJList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        billboardJList =new JList<IBillboard>(billboardModel);

        this.add(panel);
        setVisible(true);

        initializeManager();
    }



    @Override
    public int bindBillboard(IBillboard billboard) throws RemoteException {

        billboardList.add(billboard);
        billboardID++;
        return billboardID;
    }

    @Override
    public boolean unbindBillboard(int billboardId) throws RemoteException {
        for (IBillboard billboard : billboardList) {
            if (billboard.getId() == billboardId) {
                billboardList.remove(billboard);
                System.out.println("To Manager: Billboard " + billboardId + " disconnected");
            }
        }
        return false;
    }

    @Override
    public boolean placeOrder(Order order) throws RemoteException {
        orderList.add(order);
        order.client.setOrderId(orderID);
        orderID++;
        return true;
    }

    @Override
    public boolean withdrawOrder(int orderId) throws RemoteException {
        for (IBillboard billboard : billboardList) {
            billboard.removeAdvertisement(orderId);
        }
        return false;
    }

    public static void main(String[] args) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if(o==refreshBtn)
        {

        }
    }
}
