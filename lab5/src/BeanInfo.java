import java.awt.*;
import java.beans.*;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class BeanInfo {

    Class beanClass;

    public BeanInfo(Class c){
        beanClass = c;
    }


    public BeanDescriptor getBeanDescriptor() {
        BeanDescriptor bd = new BeanDescriptor(beanClass);
        bd.setDisplayName("My bean");
        return bd;
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        try {

            EventSetDescriptor changed = new EventSetDescriptor
                    (beanClass,"propertyChange", java.beans.PropertyChangeListener.class, "propertyChange");

            EventSetDescriptor push = new EventSetDescriptor
                    (beanClass, "vetoableChange", java.beans.VetoableChangeListener.class, "vetoableChange");

            EventSetDescriptor[] rv = { push, changed};
            return rv;
        } catch (IntrospectionException e) {
            throw new Error(e.toString());
        }
    }

    public int getDefaultEventIndex() {
        return 0;
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        ArrayList<PropertyDescriptor> temp = new ArrayList<PropertyDescriptor>();
        try {
            PropertyDescriptor textPD =
                    new PropertyDescriptor("text", beanClass);
            temp.add(textPD);
            PropertyDescriptor timePD =
                    new PropertyDescriptor("time", beanClass);
            temp.add(timePD);

            PropertyDescriptor[] rv = {textPD, timePD};
            return rv;
        } catch (IntrospectionException e) {
            PropertyDescriptor[] rv = new PropertyDescriptor[temp.size()];
            int i = 0;
            for(PropertyDescriptor pd: temp){
                rv[i] = pd;
                i++;
            }
            return rv;
        }
    }

    public int getDefaultPropertyIndex() {
        return 0;
    }

    public MethodDescriptor[] getMethodDescriptors() {
        Method setBeanMethod, getBean, getBeanView;
        Class args[] = {};
        //Class actionEventArgs[] = {java.awt.event.ActionEvent.class};
        //Class propertyChangeEventArgs[] = {PropertyChangeEvent.class};
        try {

            setBeanMethod = Bean.class.getMethod("setBean", LocalTime.class, String.class);
            getBean = Bean.class.getMethod("getBean");
            getBeanView = Bean.class.getMethod("getBeanView");


        } catch (Exception ex) {
            throw new Error("Missing method: " + ex);
        }
        MethodDescriptor result[] = {

                new MethodDescriptor(setBeanMethod),
                new MethodDescriptor(getBean),
                new MethodDescriptor(getBeanView)
        };
        return result;
    }

    public java.beans.BeanInfo[] getAdditionalBeanInfo() {
        return new java.beans.BeanInfo[0];
    }

    public Image getIcon(int iconKind) {
        return null;
    }


}
