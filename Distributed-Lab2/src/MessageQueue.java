import java.util.*;
import java.rmi.*;
 
public interface MessageQueue extends java.rmi.Remote 
{
   /*
    * This implements a non-blocking send
    */
   public void send(Object item) throws java.rmi.RemoteException;
   
   /*
    * This implements a non-blocking receive
    */
    
   public Object receive() throws java.rmi.RemoteException;
}
