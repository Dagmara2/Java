package sample;

import java.util.Date;

public class payment {

    private int paymentID, amount, clientID;
    private String paymentDate;


    public payment(int paymentID, String paymentDate, int amount, int clientID)
    {
        this.paymentID=paymentID;
        this.paymentDate=paymentDate;
        this.amount=amount;
        this.clientID=clientID;
    }

    public payment()
    {
        this.paymentID=0;
        this.paymentDate=null;
        this.amount=0;
        this.clientID=0;
    }

    public int getPaymentID() {
        return paymentID;
    }
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public String getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getClientID() {
        return clientID;
    }
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
}
