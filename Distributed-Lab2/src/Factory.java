import java.rmi.*;
import java.net.*;

 
public class Factory{

    // napping time is in 1/10 sec (random)
    private static final int NAP_TIME = 2;

    public Factory(String server_host){
	
	// remote object
	MessageQueue mailBox;
	
	System.setSecurityManager(new SecurityManager());
	
	try {    
	    InetAddress host_addr = InetAddress.getLocalHost();
	    String host_name = host_addr.getHostName();
	    System.out.println ("Client address is "+host_addr);
	    System.out.println ("Client host is "+host_name+"\n\n");

	    System.out.println ("Server host is "+server_host);
	    
            mailBox = (MessageQueue)Naming.lookup("rmi://"+server_host+"/MessageServer3");
      
	    System.out.println("Mailbox bounded");

	    // now create the producer and consumer threads
	    Producer producerThread = new Producer(mailBox);
	    Consumer consumerThread = new Consumer(mailBox);
	    
	    producerThread.start();
	    consumerThread.start();
	    
                      
	}
	catch (Exception e) {
	    System.err.println(e);
	    System.err.println("Error in catch: main : Factory.java") ;
	}
   }
    

    // producer and consumer will call this to nap
    public static void napping() {
	
	int sleepTime = (int) (NAP_TIME * Math.random() );
	try { Thread.sleep(sleepTime*1000); }
	catch(InterruptedException e) { }
    }
    
    
    public static void main(String argv[]) {
	
	if ((argv.length < 1) || (argv.length > 1))
	    { System.out.println("Usage: [remote host name] ") ;
		System.exit (1) ;
	    }
	
	String server_host = argv[0] ;
	
	
	// now start factory
	Factory client = new Factory(server_host);
    }
    
}

