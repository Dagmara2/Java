package Message;
import javax.xml.soap.*;

public class Message {

    public SOAPMessage getMsg() {
        return msg;
    }

    private SOAPMessage msg;

    public Message(Node sender, Node gate, Integer mode,
                   Node reveiver, String msg){
        try {
            MessageFactory factory = MessageFactory.newInstance();
            SOAPMessage message = factory.createMessage();

            SOAPHeader header = message.getSOAPHeader();
            SOAPBody body = message.getSOAPBody();
            header.detachNode();


        } catch (SOAPException e) {
            e.printStackTrace();
        }

    }
}
