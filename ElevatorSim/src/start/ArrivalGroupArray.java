package start;

import java.util.Vector;

public class ArrivalGroupArray extends Vector<ArrivalGroup>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean agaLock = false;
	private int lockedCount = 0;
	private Thread lockedBy = null;
//	public static volatile Vector<ArrivalGroup> arrivalGroupArray = null;

	
//	public synchronized Vector<ArrivalGroup> getArrivalGroupArray() {
//		return this;
//	}


	public ArrivalGroupArray(){
		
		
//		arrivalGroupArray = new Vector<ArrivalGroup>();
	}
	
	

	public synchronized void agalock()
			  throws InterruptedException{
		Thread callingThread = Thread.currentThread();
			    while(agaLock && lockedBy != callingThread){
			      wait();
			    }
			    agaLock = true;
			    lockedCount++;
			    lockedBy = callingThread;
			    
	 }
	
	public synchronized void agaUnLock(){
		if(Thread.currentThread() == this.lockedBy){
			lockedCount--;
		  if(lockedCount == 0){
		    agaLock = false;
		    notifyAll();
		  }
		}
	}
	
}
