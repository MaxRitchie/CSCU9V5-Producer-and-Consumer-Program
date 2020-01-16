import java.util.*;

class Producer extends Thread {
    
    private  MessageQueue mbox;

    public Producer(MessageQueue m) {
	mbox = m;
    }              
    
    public void run() {

	Date message;
	
	while (true){
	    Factory.napping();
	    
	    // produce an item & enter it into the buffer
	    message = new Date();      
	    
	    try {
		System.out.println ("Producer calling (remote) send") ;          
		mbox.send(message);
	    }
	    catch (Exception e) {
		System.err.println(e);
	    }
	}
    }
    
}
