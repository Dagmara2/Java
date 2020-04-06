import javax.swing.*;
import java.awt.*;
import java.beans.EventSetDescriptor;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalTime;

public class App {
    public static void main(String[] args) {

        // BeanInfo beanInfo = Introspector.getBeanInfo(Bean.class);//
        BeanInfo beanInfo = new BeanInfo(Bean.class);//

        PropertyDescriptor[] pd = beanInfo.getPropertyDescriptors();
        MethodDescriptor[] md = beanInfo.getMethodDescriptors();
        EventSetDescriptor[] evd = beanInfo.getEventSetDescriptors();

        printDescriptors(pd, md, evd);

        try {
            Bean bean= new Bean();
          // Bean bean = (Bean) md[0].getMethod().invoke(null);
            JFrame frame = new JFrame("lab05");
            frame.setContentPane(bean.getBeanView().mainPanel);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            md[2].getMethod().invoke(bean,  LocalTime.of(00,00), "test message");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    private static void printDescriptors(PropertyDescriptor[] pd, MethodDescriptor[] md, EventSetDescriptor[] evd){
        System.out.println("Właściwości:");
        for (int i = 0; i < pd.length; i++) {
            System.out.println(pd[i].getShortDescription());
            // getReadMethod i getWriteMethod zwracają obiekty typu Method
            System.out.println(" getter: "+ pd[i].getReadMethod());
            System.out.println(" setter: "+ pd[i].getWriteMethod());
        }

        System.out.println("\nMetody:");
        for (int i=0; i<md.length; i++) {
            System.out.println(" " + md[i].getMethod());
        }

        System.out.println("\nZdarzenia:");
        for (int i = 0; i < evd.length; i++) {
            System.out.println("         " + evd[i].getShortDescription());
            Method[] met = evd[i].getListenerMethods();
            System.out.println("Metody obsługi:");
            for (int j=0; j < met.length; j++)   System.out.println(" " + met[j]);
        }
    }

}
