import java.beans.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Bean {

    private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);
    private VetoableChangeSupport veto = new VetoableChangeSupport(this);
    BeanView beanView;
    static Bean instance = null;

    public String getText() {return text;}
    String text;
    public void setText(String text) { this.text=text;}

    public LocalTime getTime() {return localTime;}
    LocalTime localTime;
    synchronized void setTime(LocalTime time)  throws PropertyVetoException
    {
        LocalTime oldTime = time;
        veto.fireVetoableChange("time", oldTime, time);

        this.localTime=time;
        propertyChange.firePropertyChange("time", oldTime, time);
    }

    public void setBean(LocalTime newTime, String s){
        try {
            setTime(newTime);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        setText(s);
    }
    public Bean getBean(){
        return this;
    }

    public BeanView getBeanView(){
        return beanView;
    }

    //----------------konstruktory------------------------

    public Bean() {
        this("Some text", LocalTime.now());
    }

    public Bean(String s, LocalTime lt) {
        try {
            setTime(lt);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        setText(s);

        beanView = new BeanView(this);
    }

    //-------------------sluchacze----------------------------

    public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChange.addPropertyChangeListener(l);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChange.removePropertyChangeListener(l);
    }

    public synchronized void addVetoableChangeListener(VetoableChangeListener l) {
        veto.addVetoableChangeListener(l);
    }

    public synchronized void removeVetoableChangeListener(VetoableChangeListener l) {
        veto.removeVetoableChangeListener(l);
    }


}
