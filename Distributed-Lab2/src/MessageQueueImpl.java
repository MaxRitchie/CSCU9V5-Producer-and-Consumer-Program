import java.util.*;
import java.rmi.*;
import java.net.*;

public class MessageQueueImpl extends java.rmi.server.UnicastRemoteObject implements MessageQueue
{

    private Vector queue;

    public MessageQueueImpl() throws RemoteException
    {

        queue = new Vector();

    }

    // Non-blocking send
    public synchronized void send(Object item) throws RemoteException
    {

        System.out.println("Entered send");
        queue.addElement(item);
        System.out.println("Producer entered " + item + " size = " + queue.size());
        try {
            Thread.sleep(200);
        }
        catch (Exception e) {
        }
        System.out.println("Left send");
    }

    // Non-blocking receive
    public synchronized Object receive() throws RemoteException
    {

        Object item;

        if (queue.size() == 0) {
            System.out.println("Consumer removed an empty item");
            return null;
        }
        else {
            item = queue.firstElement();
            queue.removeElementAt(0);
            System.out.println("Consumer removed " + item + " size = " + queue.size());
            return item;
        }
    }

    public static void main(String args[])
    {

        System.setSecurityManager(new SecurityManager());

        try {
            InetAddress host_addr = InetAddress.getLocalHost();
            String host_name = host_addr.getHostName();
            System.out.println("Server host address is " + host_addr);
            System.out.println("Server host is " + host_name);

            MessageQueue server = new MessageQueueImpl();
            Naming.rebind("//" + host_name + "/MessageServer3", server);
            //Naming.rebind("//127.0.0.1/MessageServer3", server);
            System.out.println("Server Bound");
        }
        catch (Exception e) {
            System.err.println(e);
            System.err.println("error in catch: main: MessageQueueImpl");
        }
    }

}
