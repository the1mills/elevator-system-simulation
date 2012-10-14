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


	public ArrivalGroupArray(){
		
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
	
	public void forceUnlock(){
		  agaLock = false;
		 //   notify();
		
	}
	
}
