package bilboards;

import java.io.Serializable;
import java.time.Duration;

public class Ad implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public String advertText;
    public Duration displayPeriod;
    public int orderID;


    Ad(String advertText, Duration displayPeriod, int orderID)
    {
        this.advertText=advertText;
        this.displayPeriod=displayPeriod;
        this.orderID=orderID;
    }
}
