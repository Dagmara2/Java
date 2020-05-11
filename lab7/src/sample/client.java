package sample;

public class client {

    private int clientNumber;
    private String firstName, lastName;

    public client(int ClientNumber, String FirstName, String LastName)
    {
        this.clientNumber=ClientNumber;
        this.firstName=FirstName;
        this.lastName=LastName;
    }

    public client()
    {
        this.clientNumber=0;
        this.firstName=null;
        this.lastName=null;
    }

    public int getClientNumber(){return clientNumber;}
    public void setClientNumber(int clientNumber) {this.clientNumber=clientNumber;}

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {this.lastName = lastName; }

}
