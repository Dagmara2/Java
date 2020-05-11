package sample;

public class recive {

    private int reciveID,toPay, clientID;
    private String payTo;

    public recive(int reciveID, String payTo, int toPay, int clientID)
    {
        this.reciveID=reciveID;
        this.payTo=payTo;
        this.toPay=toPay;
        this.clientID=clientID;
    }

    public recive()
    {
        this.reciveID=0;
        this.payTo=null;
        this.toPay=0;
        this.clientID=0;
    }

    public int getReciveID() {
        return reciveID;
    }

    public void setReciveID(int reciveID) {
        this.reciveID = reciveID;
    }

    public String getPayTo() {
        return payTo;
    }

    public void setPayTo(String payTo) {
        this.payTo = payTo;
    }

    public int getToPay() {
        return toPay;
    }

    public void setToPay(int toPay) {
        this.toPay = toPay;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
}
