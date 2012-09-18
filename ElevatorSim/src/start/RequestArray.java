package start;

import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;



public class RequestArray implements Lock{
	
	public static boolean raLock = false;
	public volatile static Vector<ArrivalGroup> requestArray = null;
	private int lockedCount = 0;
	private Thread lockedBy = null;


	public RequestArray(){
		
		requestArray = new Vector<ArrivalGroup>();
		
	}
	
	
	public static synchronized Vector<ArrivalGroup> getRequestArray() {
		return requestArray;
	}



	public static synchronized void setRequestArray(
			Vector<ArrivalGroup> requestArray) {
		RequestArray.requestArray = requestArray;
	}
	
	
	
	public synchronized void ralock()
			  throws InterruptedException{
		Thread callingThread = Thread.currentThread();
			    while(raLock && lockedBy != callingThread){
			      wait();
			    }
			    raLock = true;
			  //  lockedCount++;
			    lockedBy = callingThread;
			    
	 }
	
	public synchronized void raUnlock(){
		if(Thread.currentThread() == this.lockedBy){
			//lockedCount--;
		//  if(lockedCount == 0){
		    raLock = false;
		    notifyAll();
	//	  }
		}
	}
			  
	
	
	@Override
	public void lock() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit)
			throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		
	}
	
}