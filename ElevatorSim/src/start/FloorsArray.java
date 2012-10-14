package start;

import java.util.Vector;

public class FloorsArray extends Vector<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public boolean faLock = false;
	private int lockedCount = 0;
	private Thread lockedBy = null;

	
	public FloorsArray(int size){
		super(size);
		
	}
	
	public FloorsArray(Vector<Integer> newVector) {
		
		super(newVector);
	}

	public synchronized void faLock()
			  throws InterruptedException{
		Thread callingThread = Thread.currentThread();
		  while(faLock && lockedBy != callingThread){
		      wait();
		    }
		  faLock = true;
		    lockedCount++;
		    lockedBy = callingThread;
			    
	 }
	
	public synchronized void faUnLock(){
		if(Thread.currentThread() == this.lockedBy){
			lockedCount--;
		  if(lockedCount == 0){
		    faLock = false;
		    notifyAll();
		  }
		}
		  }
	
	public void forceUnlock(){
		faLock = false;
	//    notify();
		
	}
}
