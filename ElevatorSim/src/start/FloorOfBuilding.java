package start;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Vector;


public class FloorOfBuilding extends Observable implements Runnable{
	
	private volatile double interArrivalTime;
	private int avgNumberPerArrivalGroup;
	private int floorNumber;
	private volatile CentralDispatcher centralDispatcher;
	private int currentNumberWaiting =0;
	private volatile ArrivalGroupArray arrivalGroupArray = null;
	private volatile double timeFactor;
	private volatile ArrivalGroupArray aga = null;
	
	public synchronized ArrivalGroup getNextInLine(){
		for(int i = 0; i < this.getArrivalGroupArray().size(); i++){
			if(this.getArrivalGroupArray().get(i).isWaiting()){
				return this.getArrivalGroupArray().get(i);
			}
		}
		return null;
	}
	
//	public synchronized CentralDispatcher getCentralDispatcher() {
//		return centralDispatcher;
//	}
//
//	public synchronized void setCentralDispatcher(CentralDispatcher centralDispatcher) {
//		this.centralDispatcher = centralDispatcher;
//	}

	public synchronized int getCurrentNumberWaiting() {
		
		int count = 0;
		for(int i = 0; i < this.getArrivalGroupArray().size(); i++){
			if(this.getArrivalGroupArray().get(i).isWaiting()){
			for(int j = 0; j < this.getArrivalGroupArray().get(i).getNumberOfPeopleInGroup(); j++){
				count++;
			}
			}
		}
		return count;
	}
	
	public synchronized int getCurrentNumberWaitingToGoUp(){
		int count = 0;
		for(int i = 0; i < this.getArrivalGroupArray().size(); i++){
			if(this.getArrivalGroupArray().get(i).isWaiting() && this.getArrivalGroupArray().get(i).getDirection().equals("up")){
			for(int j = 0; j < this.getArrivalGroupArray().get(i).getNumberOfPeopleInGroup();  j++){
				count++;
			}
			}
		}
		return count;
	}
	
	public synchronized int getCurrentNumberWaitingToGoDown(){
		int count = 0;
		for(int i = 0; i < this.getArrivalGroupArray().size(); i++){
			if(this.getArrivalGroupArray().get(i).isWaiting() && this.getArrivalGroupArray().get(i).getDirection().equals("down")){
			for(int j = 0; j < this.getArrivalGroupArray().get(i).getNumberOfPeopleInGroup();  j++){
				count++;
			}
			}
		}
		return count;
	}

	public synchronized void setCurrentNumberWaiting(int currentNumberWaiting) {
		this.currentNumberWaiting = currentNumberWaiting;
	}

	public synchronized ArrivalGroupArray getArrivalGroupArray() {
		return arrivalGroupArray;
	}

	public synchronized void setArrivalGroupArray(ArrivalGroupArray arrivalGroupArray) {
		this.arrivalGroupArray = arrivalGroupArray;
	}
	public synchronized double getInterArrivalTime() {
		return interArrivalTime;
	}

	public synchronized void setInterArrivalTime(double interArrivalTime) {
		this.interArrivalTime = interArrivalTime;
	}

	public synchronized int getAvgNumberPerArrivalGroup() {
		return avgNumberPerArrivalGroup;
	}

	public synchronized void setAvgNumberPerArrivalGroup(int avgNumberPerArrivalGroup) {
		this.avgNumberPerArrivalGroup = avgNumberPerArrivalGroup;
	}

	public synchronized int getFloorNumber() {
		return floorNumber;
	}

	public synchronized void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}
	
	public FloorOfBuilding(double interArrivalTime,
			int avgNumberPerArrivalGroup, int floorNumber, double timeFactor) {
		
		this.interArrivalTime = interArrivalTime;
		this.avgNumberPerArrivalGroup = avgNumberPerArrivalGroup;
		this.floorNumber = floorNumber;
		this.timeFactor = timeFactor;
		//aga = new ArrivalGroupArray();
		arrivalGroupArray  = new ArrivalGroupArray();;
		this.addObserver(CentralDispatcher.getEca());
	}
	
	@Override
	public void run() {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("This is floor number: " + this.getFloorNumber());
		
		while(this.centralDispatcher.isKeepLooping()){
			try {
				loop();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public synchronized boolean findOutIfThereIsGroupWaiting(){
		
		for(int i = 0; i < this.getArrivalGroupArray().size(); i++){
			if(this.getArrivalGroupArray().get(i).isWaiting()){
				return true;
			}
		}
		return false;
	}
	

	private void loop() throws InterruptedException{
		
		 Integer countPplWaiting = this.getCurrentNumberWaiting();
		 Integer countPplWaitingUp = this.getCurrentNumberWaitingToGoUp();
		 Integer countPplWaitingDown = this.getCurrentNumberWaitingToGoDown();
		
		 String waiting = countPplWaiting.toString() + " " + countPplWaitingUp.toString() + " " + countPplWaitingDown.toString();
		
		 setChanged();
		notifyObservers(waiting);
			
	
		int randomFloor = (int)(Math.random()*CentralDispatcher.getNumberOfFloors());
		if(randomFloor == this.getFloorNumber()){
			return;
		}
		int randomNumberOfPeople = RandomVariableGenerator.getNextGroupSize(3, 1, 0);
	
		double interArrivalTimeRand = RandomVariableGenerator.generateExponentialRandomVariable((long)(this.interArrivalTime/this.timeFactor));
		
		Thread.sleep((long)interArrivalTimeRand);
		ArrivalGroup ag = new ArrivalGroup(System.nanoTime()/1000000, randomNumberOfPeople, randomFloor, this.getFloorNumber());
		this.getArrivalGroupArray().add(ag);
		
		//here we see if there is already an untasked elevator on the floor 
		Elevator[] temp = CentralDispatcher.getElevatorArray();
		for(int i = 0; i < temp.length; i++){
			if(temp[i].getCurrentFloor() == this.getFloorNumber() && temp[i].isHasTask()== false && temp[i].isStoppedOnCurrentFloor()){
				
				//make the elevator wait here, lock the elevator, make unavailable...
				if(temp[i].getCurrentNumberOfPeopleSpaces() != temp[i].getCapacity()){
					String problem = "Untasked elevator has people on it while stopped on a floor!! lame...";
					CentralDispatcher.insertIntoDebuggingTable(CentralDispatcher.getRunNumber(), CentralDispatcher.getNumberOfFloors(),CentralDispatcher.getNumberOfElevators(),problem,CentralDispatcher.getCurrentTime()/1000, this.getClass().getName());
				}
			   int groupSize = ag.getNumberOfPeopleInGroup();
			   if(temp[i].getCurrentNumberOfPeopleSpaces() >= groupSize){
		//	   temp[i].setAvailable(false);
			   temp[i].setHasTask(true);
		//	   CentralDispatcher.getElevatorThreadArray()[i].sleep((long)(groupSize*2000/CentralDispatcher.getTimeFactor()));	
			   ag.setWaiting(false);
			   ag.setCumulativeTimeOfWaiting(System.nanoTime()/1000000-ag.getTimeOfArrival());
			   ag.setTimeFirstBoarding(System.nanoTime()/1000000);
			   temp[i].getGroupsOfPassengers().add(ag);
		//	   temp[i].setAvailable(true);
			   return;
			   }
			}
			
		}
		//perhaps we don't need the following section
		//we find a tasked elevator stopped on the same floor, that has space and is going the right direction
//		temp = CentralDispatcher.getElevatorArray();
//		for(int i = 0; i < temp.length; i++){
//			//basic/preliminary conditions to add people
//			String direction = "";
//			if( temp[i].isGoingUp()){
//				direction = "up";
//			}
//			else if(temp[i].isGoingDown()){
//				direction = "down";
//			}
//			else{
//				//problem...
//			}
//			if(temp[i].getCurrentFloor() == this.getFloorNumber() && temp[i].isAvailable() && temp[i].isStoppedOnCurrentFloor() && temp[i].getCurrentNumberOfPeopleSpaces() >= 1+ ag.getNumberOfPeopleInGroup() 
//					&& ((temp[i].isGoingDown() && ag.getDirection().equals("down")) || (temp[i].isGoingUp() && ag.getDirection().equals("up")))){
//				//here we check to see if there are people waiting for this elevator already in front the newest group
//				for(int j = 0; j < this.getArrivalGroupArray().size(); j++){
//					if(this.getArrivalGroupArray().get(j).isWaiting() && this.getArrivalGroupArray().get(j).getDirection().equals(direction));
//				}
//			   temp[i].setHasTask(true);
//			   int groupSize = ag.getNumberOfPeopleInGroup();
//			   CentralDispatcher.getElevatorThreadArray()[i].sleep((long)(groupSize*2000/CentralDispatcher.getTimeFactor()));
//			   ag.setWaiting(false);
//			   ag.setCumulativeTimeOfWaiting(System.nanoTime()/1000000-ag.getTimeOfArrival());
//			   ag.setTimeFirstBoarding(System.nanoTime()/1000000);
//			   temp[i].getGroupsOfPassengers().add(ag);
//			   temp[i].setHasTask(true);
//				return;
//			}
//		}
		
		//if we can't find an elevator on the same floor, then we send the Central Dispatcher a new request to find us an elevator to serve these new people waiting!
		// here we only add new elements to the request array if there isn't a redundancy.
		
	//	CentralDispatcher.ra.ralock();
	
		CentralDispatcher.addToRequestArray(ag);
		getCurrentNumberWaiting();  // this will notify observers
	
		 countPplWaiting = this.getCurrentNumberWaiting();
		 countPplWaitingUp = this.getCurrentNumberWaitingToGoUp();
		 countPplWaitingDown = this.getCurrentNumberWaitingToGoDown();
		
		 waiting = countPplWaiting.toString() + " " + countPplWaitingUp.toString() + " " + countPplWaitingDown.toString();
		
		setChanged();
		notifyObservers(waiting);
		
	}
	
}




