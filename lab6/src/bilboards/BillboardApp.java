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

public class BillboardApp extends JFrame implements IBillboard, Serializable, ActionListener {

    private String messageBillboard;
    private String host= "Billboard";
    private int billboardPort = 3000;

    private IManager interfaceManager;
    private IBillboard interfaceBillboard;
    private Duration displayInterval;
    public List<Ad> adArrayList = new ArrayList<>();
    private int[] capacity = {3,3};

    private Registry registry;


    private JButton startBtn = new JButton("Start");
    private JButton stopBtn = new JButton("Stop");
    private JTextArea messageArea = new JTextArea(10,15);

    private static final long serialVersionUID = 1L;

    protected BillboardApp() throws RemoteException , NotBoundException {
        super("Billboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(550, 400);
        GridLayout layout = new GridLayout(4, 2, 20, 20);
        JPanel panel = new JPanel();
        panel.setSize(550, 150);
        panel.setLayout(layout);

        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.add(messageArea);
        panel.add(startBtn);
        panel.add(stopBtn);

        this.startBtn.addActionListener(this);
        this.stopBtn.addActionListener(this);

        this.add(panel);
        setVisible(true);


        initializeBillboard();
    }



    public void initializeBillboard() throws RemoteException, NotBoundException {
        BillboardApp nBillboard = new BillboardApp();
         IManager interfaceManager = (IManager) UnicastRemoteObject.exportObject(this, Registry.REGISTRY_PORT);
        this.registry = LocateRegistry.getRegistry(billboardPort);
        this.registry.rebind(host, nBillboard);
        this.setInterfaceManager((IManager) registry.lookup("Manager"));
        this.getInterfaceManager().bindBillboard(interfaceBillboard);

        try {
            messageArea.setText(String.valueOf(getAdvertisements()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean addAdvertisement(String advertText, Duration displayPeriod, int orderId) throws RemoteException {
        if(getCapacity()[1]>0){
            adArrayList.add(new Ad(advertText,displayPeriod,orderId));
            capacity[1]-=1;
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean removeAdvertisement(int orderId) throws RemoteException {

        adArrayList.remove(orderId);
        capacity[1]+=1;
        return false;
    }

    @Override
    public int[] getCapacity() throws RemoteException {

        return capacity;
    }

    @Override
    public void setDisplayInterval(Duration displayInterval) throws RemoteException {
        this.displayInterval = displayInterval;
    }

    @Override
    public boolean start() throws RemoteException {

        System.out.println("Billboard "+billboardPort+" ON");

        return false;
    }

    @Override
    public boolean stop() throws RemoteException {

        System.out.println("Billboard "+billboardPort+" OFF");

        return false;
    }

    @Override
    public int getId() throws RemoteException {
        return billboardPort;
    }
    @Override
    public List<Ad> getAdvertisements() throws RemoteException {
        return adArrayList;
    }
    public static void main(String[] args) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if(o==startBtn)
        {

        }
        if(o==stopBtn)
        {

        }

    }
    public void setInterfaceManager(IManager interfaceManager) {
        this.interfaceManager = interfaceManager;
    }

    public IManager getInterfaceManager() {
        return interfaceManager;
    }

}
