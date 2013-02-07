package start;

import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;



public class RequestArray extends Vector<ArrivalGroup> implements Lock{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5427172902158392227L;
	public static boolean raLock = false;
	private int lockedCount = 0;
	private Thread lockedBy = null;

	
     public RequestArray(Vector<ArrivalGroup> newVector) {
		super(newVector);
	}
	
     public RequestArray() {
 		super();
 	}

//	
//	
//	public synchronized void ralock()
//			  throws InterruptedException{
//		Thread callingThread = Thread.currentThread();
//			    while(raLock && lockedBy != callingThread){
//			      wait();
//			    }
//			    raLock = true;
//			    lockedCount++;
//			    lockedBy = callingThread;
//			    
//	 }
//	
//	public synchronized void raUnlock(){
//		if(Thread.currentThread() == this.lockedBy){
//			lockedCount--;
//		  if(lockedCount == 0){
//		    raLock = false;
//		    notifyAll();
//		  }
//		}
//	}
//			
//	public void forceUnlock(){
//		  raLock = false;
//		//    notify();
//	}
	
	
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