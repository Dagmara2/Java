package bilboards;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        new BillboardApp();
        new ClientApp();
        new ManagerApp();
    }
}
