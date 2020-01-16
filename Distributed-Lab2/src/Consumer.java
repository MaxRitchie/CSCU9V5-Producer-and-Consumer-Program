import java.util.*;

class Consumer extends Thread {
    
    private  MessageQueue mbox;
    
    public Consumer(MessageQueue m) {
	mbox = m;
    }
    
    public void run() {
	
	Date message;
	
	while (true) {

	    Factory.napping();
         
	    // consume an item from the buffer
	    try { 
		System.out.println("Consumer calling (remote) receive") ;      
		message = (Date)mbox.receive();
		
		if (message != null)
		    // Process the item
		    ;
	    }
	    catch (Exception e) {
		System.err.println(e);
	    }
	}
    }
    
}
