package mbean;

import javax.management.*;
import javax.management.openmbean.OpenDataException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;


/*class Client
{

    public int ticketNumber;
    int priority;
*/
    /**
     Initializes the ticket number, and the priority to 0.
     */
  /*  public Client(int ticketNumber)
    {
        this.ticketNumber = ticketNumber;
        this.priority = 0;
    }*/

    /**
     Initializes the ticket number and priority to given values.
     */
  /*  public Client(int ticketNumber, int priority)
    {
        this.ticketNumber = ticketNumber;
        this.priority = priority;
    }*/

    /**
     returns the priority.
     */
   /* int getPriority()
    {
        return priority;
    }

    void setPriority(int priority)
    {
        this.priority = priority;
    }*/

    /**
     returns the ticket number.
     */
   /* int getTicketNumber()
    {
        return ticketNumber;
    }
}*/

/**
 Methods
 */
public class ClientOp implements IClientOp
{
    public static ArrayList arrayListAQueue = new ArrayList();
    public static ArrayList arrayListBQueue = new ArrayList();
    public static ArrayList arrayListCQueue = new ArrayList();

    /**
     Returns and deletes the first ticket in the queue.
     */
    public int deleteATicket()
    {
        return (int) arrayListAQueue.remove(0);
    }

    /**
     Returns(doesn't delete) the first ticket in the queue.
     */
    public int getAFirstTicket()
    {
        return (int) arrayListAQueue.get(0) ;
    }
    /**
     Returns(doesn't delete) the last ticket in the queue.
     */
    public int getALastTicket()
    {
        return (int) arrayListAQueue.get(arrayListAQueue.size()-1);
    }

    /**
     Returns and deletes the first ticket in the queue.
     */
    public int deleteBTicket()
    {
        return (int) arrayListBQueue.remove(0);
    }

    /**
     Returns(doesn't delete) the first ticket in the queue.
     */
    public int getBFirstTicket()
    {
        return (int) arrayListBQueue.get(0) ;
    }
    /**
     Returns(doesn't delete) the last ticket in the queue.
     */
    public int getBLastTicket()
    {
        return (int) arrayListBQueue.get(arrayListBQueue.size()-1);
    }

    /**
     Returns and deletes the first ticket in the queue.
     */
    public int deleteCTicket()
    {
        return (int) arrayListCQueue.remove(0);
    }

    /**
     Returns(doesn't delete) the first ticket in the queue.
     */
    public int getCFirstTicket()
    {
        return (int) arrayListCQueue.get(0) ;
    }
    /**
     Returns(doesn't delete) the last ticket in the queue.
     */
    public int getCLastTicket()
    {
        return (int) arrayListCQueue.get(arrayListCQueue.size()-1);
    }
    /*
     * Należy uruchomić z opcjami: -Dcom.sun.management.jmxremote
     * -Dcom.sun.management.jmxremote.port=8080
     * -Dcom.sun.management.jmxremote.authenticate=false
     * -Dcom.sun.management.jmxremote.ssl=false
     */
    public static void main(String[] args) throws OpenDataException {
        ClientOp impl = new ClientOp();
        while (true) {

            try {
                ObjectName objectName = new ObjectName("mbean.dszykulska:name=" + "ClientOp");
                MBeanServer server = ManagementFactory.getPlatformMBeanServer();
                server.registerMBean(new ClientOp(), objectName);
            } catch (MalformedObjectNameException | InstanceAlreadyExistsException |
                    MBeanRegistrationException | NotCompliantMBeanException e) {
                // handle exceptions
            }
        }
    }

}