package start;


import java.awt.Color;
import java.util.Observable;
import java.util.Vector;

public class Elevator extends Observable implements Runnable {

	private int totalNumberServed = 0;
	private volatile double cumulativeIdleTime = 0;
	private volatile double avgTravelTimePerCustomer;
	private volatile double[] allTravelTimes = null;
	private int initialFloor = 0;
	private int capacity;
	private int currentNumberOfPeopleSpaces;
	private volatile double maxSpeedBetweenFloors;
	private volatile double accRatePerFloor;
	private volatile double decRatePerFloor;
	private boolean goingUp = true;
	private boolean goingDown = false;
	private int elevatorNumberId;
	private boolean hasTask = false;
	private int currentFloor;
	private int nextFloor;
	private volatile FloorsArray floorsArray = null;
	private int lastGroupServed = 0;
	private boolean available = true;
	private volatile Vector<ArrivalGroup> groupsOfPassengers = new Vector<ArrivalGroup>();
	private volatile double drawHeight = 0;
	private int drawWidth = 0;
	private int drawElevatorHeight = 0;
	private int drawElevatorWidth = 0;
	private int heightConstant = 0;
	private boolean isStoppedOnCurrentFloor = false;
	private volatile double timeFactor;
	private int travelTimePerFloor;
	private int loadUnloadTimePerPassenger;
	private int capacityThresholdVariable;


	public int getCapacityThresholdVariable() {
		return capacityThresholdVariable;
	}

	public void setCapacityThresholdVariable(int capacityThresholdVariable) {
		this.capacityThresholdVariable = capacityThresholdVariable;
	}

	public double getTimeFactor() {
		return timeFactor;
	}

	public void setTimeFactor(double timeFactor) {
		this.timeFactor = timeFactor;
	}

	public int getTravelTimePerFloor() {
		return travelTimePerFloor;
	}

	public void setTravelTimePerFloor(int travelTimePerFloor) {
		this.travelTimePerFloor = travelTimePerFloor;
	}

	public int getLoadUnloadTimePerPassenger() {
		return loadUnloadTimePerPassenger;
	}

	public void setLoadUnloadTimePerPassenger(int loadUnloadTimePerPassenger) {
		this.loadUnloadTimePerPassenger = loadUnloadTimePerPassenger;
	}
	
	public boolean isStoppedOnCurrentFloor() {
		return isStoppedOnCurrentFloor;
	}

	public void setStoppedOnCurrentFloor(boolean isStoppedOnCurrentFloor) {
		this.isStoppedOnCurrentFloor = isStoppedOnCurrentFloor;
	}

	public int getHeightConstant() {
		return heightConstant;
	}

	public void setHeightConstant(int heightConstant) {
		this.heightConstant = heightConstant;
	}
	public double getDrawHeight() {
		return drawHeight;
	}

	public void setDrawHeight(double drawHeight) {
		this.drawHeight = drawHeight;
	}

	public int getDrawWidth() {
		return drawWidth;
	}

	public void setDrawWidth(int drawWidth) {
		this.drawWidth = drawWidth;
	}

	public int getDrawElevatorHeight() {
		return drawElevatorHeight;
	}

	public void setDrawElevatorHeight(int drawElevatorHeight) {
		this.drawElevatorHeight = drawElevatorHeight;
	}

	public int getDrawElevatorWidth() {
		return drawElevatorWidth;
	}

	public void setDrawElevatorWidth(int drawElevatorWidth) {
		this.drawElevatorWidth = drawElevatorWidth;
	}
	public synchronized Vector<ArrivalGroup> getGroupsOfPassengers() {
		return groupsOfPassengers;
	}

	public synchronized void setGroupsOfPassengers(
			Vector<ArrivalGroup> GroupsOfPassengers) {
		this.groupsOfPassengers = GroupsOfPassengers;
	}
	public synchronized boolean isAvailable() {
		return available;
	}

	public synchronized void setAvailable(boolean available) {
		this.available = available;
	}
	public synchronized int getCurrentNumberOfPeopleSpaces() {
		
		int count = 0;
		for(int i = 0; i < this.getGroupsOfPassengers().size(); i++){
			if(this.getGroupsOfPassengers().get(i).isGotServedByElevator() == false){
				for(int j = 0; j <this.getGroupsOfPassengers().get(i).getNumberOfPeopleInGroup(); j++){
					count++;
				}	
			}	
		}
		if(this.capacity - count < 0){
			String problem = "There are more people in the elevator than the capacity of the elevator!!";
			CentralDispatcher.insertIntoDebuggingTable(CentralDispatcher.getRunNumber(),CentralDispatcher.getNumberOfFloors(),CentralDispatcher.getNumberOfElevators(),problem,CentralDispatcher.getCurrentTime()/1000,this.getClass().getName());
	
		}
		return this.getCapacity() - count;
	}
	
	public synchronized Integer getNumberOfPassengers(){
		Integer count = 0;
		for(int i = 0; i < this.getGroupsOfPassengers().size(); i++){
			if(this.getGroupsOfPassengers().get(i).isGotServedByElevator() == false){
				for(int j = 0; j <this.getGroupsOfPassengers().get(i).getNumberOfPeopleInGroup(); j++){
					count++;
				}	
			}	
		}
		return count;
	}
	

	public synchronized void setCurrentNumberOfPeopleSpaces(int currentNumberOfPeopleSpaces) {
		this.currentNumberOfPeopleSpaces = currentNumberOfPeopleSpaces;
	}

	public synchronized int getLastGroupServed() {
		return lastGroupServed;
	}

	public synchronized void setLastGroupServed(int lastGroupServed) {
		this.lastGroupServed = lastGroupServed;
	}
	
	public synchronized boolean isHasTask() {
		return hasTask;
	}

	public synchronized void setHasTask(boolean hasTask) {
		this.hasTask = hasTask;
	}
	
	public synchronized FloorsArray getFloorsArray() {
		return floorsArray;
	}

	public synchronized void setFloorsArray(FloorsArray floorsArray) {
		this.floorsArray = floorsArray;
	}

	public synchronized int getNextFloor() {
		
		return nextFloor;
	}

	public synchronized void setNextFloor(int nextFloor) {
		this.nextFloor = nextFloor;
	}
	
	public synchronized int getCurrentFloor() {
		return this.currentFloor;
	}

	public synchronized void setCurrentFloor(Integer currentFloor) {
		this.currentFloor = currentFloor;
		setChanged();
		notifyObservers(currentFloor);
	}

	public synchronized int getTotalNumberServed() {
		return totalNumberServed;
	}

	public synchronized void setTotalNumberServed(int totalNumberServed) {
		this.totalNumberServed = totalNumberServed;
	}

	public synchronized double getAvgTravelTimePerCustomer() {
		return avgTravelTimePerCustomer;
	}

	public synchronized void setAvgTravelTimePerCustomer(double avgTravelTimePerCustomer) {
		this.avgTravelTimePerCustomer = avgTravelTimePerCustomer;
	}

	public synchronized double[] getAllTravelTimes() {
		return allTravelTimes;
	}

	public synchronized void setAllTravelTimes(double[] allTravelTimes) {
		this.allTravelTimes = allTravelTimes;
	}

	public synchronized int getInitialFloor() {
		return initialFloor;
	}

	public synchronized void setInitialFloor(int initialFloor) {
		this.initialFloor = initialFloor;
	}

	public synchronized int getCapacity() {
		return capacity;
	}

	public synchronized void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public synchronized double getMaxSpeedBetweenFloors() {
		return maxSpeedBetweenFloors;
	}

	public synchronized void setMaxSpeedBetweenFloors(double maxSpeedBetweenFloors) {
		this.maxSpeedBetweenFloors = maxSpeedBetweenFloors;
	}

	public synchronized double getAccRatePerFloor() {
		return accRatePerFloor;
	}

	public synchronized void setAccRatePerFloor(double accRatePerFloor) {
		this.accRatePerFloor = accRatePerFloor;
	}

	public synchronized double getDecRatePerFloor() {
		return decRatePerFloor;
	}

	public synchronized void setDecRatePerFloor(double decRatePerFloor) {
		this.decRatePerFloor = decRatePerFloor;
	}

	public synchronized boolean isGoingUp() {
		return goingUp;
	}

	public synchronized void setGoingUp(boolean goingUp) {
		this.goingUp = goingUp;
	}

	public synchronized boolean isGoingDown() {
		return goingDown;
	}

	public synchronized void setGoingDown(boolean goingDown) {
		this.goingDown = goingDown;
	}

	public synchronized int getElevatorNumberId() {
		return elevatorNumberId;
	}

	public synchronized void setElevatorNumberId(int elevatorNumberId) {
		this.elevatorNumberId = elevatorNumberId;
	}

	public Elevator(int initialFloor, int elevatorNumberId, int capacity,
			double maxSpeedBetweenFloors, double accRate, double decRatePerFloor, double timeFactor, int travelTimePerFloor, int loadUnloadTimePerPassenger, int capacityThresholdVariable) {

		this.initialFloor = initialFloor;
		this.elevatorNumberId = elevatorNumberId;
		this.capacity = capacity;
		this.currentNumberOfPeopleSpaces = capacity;
		this.setCurrentFloor(initialFloor);
		this.floorsArray =  new FloorsArray(CentralDispatcher.getNumberOfFloors());
		this.drawElevatorWidth = (int)((double)700/((double)CentralDispatcher.getNumberOfElevators())*.75);
		this.drawElevatorHeight = (int)(((double)700/(double)CentralDispatcher.getNumberOfFloors())*.75);
		this.drawWidth = (int)((double)700/(double)(CentralDispatcher.getNumberOfElevators())*this.getElevatorNumberId()+ 50 + (int)((double)700/((double)CentralDispatcher.getNumberOfElevators())*.25)/2);
		this.drawHeight = (700/(double)CentralDispatcher.getNumberOfFloors());
		this.heightConstant = 100 + (int)((((double)700/(double)CentralDispatcher.getNumberOfFloors()))) - (int)((((double)700/(double)CentralDispatcher.getNumberOfFloors())*.25)/2);
		this.timeFactor = timeFactor;
		this.travelTimePerFloor = travelTimePerFloor;
		this.loadUnloadTimePerPassenger = loadUnloadTimePerPassenger;
		this.capacityThresholdVariable = capacityThresholdVariable;
		
		this.addObserver(CentralDispatcher.getEca());
	}

	@Override
	public void run() {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		while(CentralDispatcher.isKeepLooping()){
			try {
				loop();
			} catch (InterruptedException e) {
				String problem = "Interruption exception whiling looping in an elevator.";
				CentralDispatcher.insertIntoDebuggingTable(CentralDispatcher.getRunNumber(),CentralDispatcher.getNumberOfFloors(),CentralDispatcher.getNumberOfElevators(),problem,CentralDispatcher.getCurrentTime()/1000,this.getClass().getName());
			}
		}	
	}
	
	private synchronized  FloorsArray sortUp(Vector<Integer> temp, boolean first){
		
		int currentFloor = this.getCurrentFloor();
		Vector<Integer> intArray = new Vector<Integer>();
		Vector<Integer> intArrayDown = new Vector<Integer>();
		boolean yes = false;
		
		int k = -1;
		int x = Integer.MAX_VALUE;
		while(!temp.isEmpty()){
			yes = false;
			k = -1;
			x = Integer.MAX_VALUE;
		for(int i =temp.size()-1; i >= 0; i--){
			if(temp.get(i) == currentFloor){
				System.out.println("problem!!!!" + "  First = " + first);
			}
			if(temp.get(i) < currentFloor && first == true){
				intArrayDown.add(temp.get(i));
				temp.remove(i);
				yes = true;
				break;
			}
			else if(temp.get(i) < x ){
				x = temp.get(i);
				k = i;
			}
		}
		if(!yes){
	//	if(x >= 0 && k >= 0){
		intArray.add(x);
		temp.remove(k);
		}
		}
		
		if(!intArrayDown.isEmpty()){
			intArrayDown = sortDown(intArrayDown, false);
		
		for(int i = 0; i < intArrayDown.size(); i++){
			intArray.add(intArrayDown.get(i));
		}
		}
		return new FloorsArray(intArray);
		
	}
	
	private synchronized FloorsArray sortDown(Vector<Integer> temp, boolean first){
		
		int currentFloor = this.getCurrentFloor();
		Vector<Integer> intArray = new Vector<Integer>();
		Vector<Integer> intArrayUp = new Vector<Integer>();
		
		int k = -1;
		int x = Integer.MIN_VALUE;
		boolean yes = false;
		while(!temp.isEmpty()){
			k = -1;
			x = Integer.MIN_VALUE;
			yes = false;
		for(int i =temp.size()-1; i >= 0; i--){
			if(temp.get(i) == currentFloor){
				System.out.println("problem!!!!" + "  First = " + first);
			}
			if(temp.get(i) > currentFloor && first == true){
				intArrayUp.add(temp.get(i));
				temp.remove(i);
				yes = true;
			}
			else if(temp.get(i) > x ){
				x = temp.get(i);
				k = i;
			}
		}
		if(!yes){
	//	if(k>=0){
		intArray.add(x);
		temp.remove(k);
		}
		}
		
		if(!intArrayUp.isEmpty()){
			intArrayUp = sortUp(intArrayUp, false);
		
		for(int i = 0; i < intArrayUp.size(); i++){
			intArray.add(intArrayUp.get(i));
		}
		}
		
		return new FloorsArray(intArray);
	}
	
	private double findTravelTime(){
		
		int distance = Math.abs(this.getCurrentFloor()- this.getFloorsArray().get(0));
		
		return distance*3.1;
	}
	
	private double findTravelTimePerFloor(){
		
		// int distance = Math.abs(this.getCurrentFloor()- this.getFloorsArray().get(0));
		
		return 200;
		
	}
	
	private synchronized void removeDuplicates(){
		
		Vector<Integer> intTempArray = new FloorsArray(CentralDispatcher.getNumberOfFloors());
		
		for(int i = 0; i < this.getFloorsArray().size(); i++){
			
			if(!intTempArray.contains(this.getFloorsArray().get(i))){
			intTempArray.add(this.getFloorsArray().get(i));
			}
		}
		this.setFloorsArray((FloorsArray) intTempArray);
		
	}
	
	private synchronized void checkToSeeIfPeopleOnFloorForUntaskedElevator() throws InterruptedException{
		FloorOfBuilding fob = CentralDispatcher.getFloorArray()[this.getCurrentFloor()];
		if(fob.findOutIfThereIsGroupWaiting()){
			fob.getArrivalGroupArray().agalock();
			boolean first = true;
			for(int i = 0; i < fob.getArrivalGroupArray().size(); i++){
				if(fob.getArrivalGroupArray().get(i).isWaiting()){
					if((this.goingDown == false && this.goingUp == false) || (fob.getArrivalGroupArray().get(i).getDirection().equals("up") && this.goingUp == true) || (this.goingDown == true && fob.getArrivalGroupArray().get(i).getDirection().equals("down"))){
					this.getGroupsOfPassengers().add(fob.getArrivalGroupArray().get(i));
					if(first){
						if(fob.getArrivalGroupArray().get(i).getDirection().equals("up")){
							this.goingUp = true;
						}
						else if(fob.getArrivalGroupArray().get(i).getDirection().equals("down")){
							this.goingDown = true;
						}
						else{
							System.out.println("SHIT");
							///
						}
						first = false;
					}
				}
				}
			}
			fob.getArrivalGroupArray().agaUnLock();
		}
	}
	
	private synchronized FloorsArray copyArray(Vector<Integer> vectorToCopy){
		
		Vector<Integer> newVector = new Vector<Integer>();
		
		for(int i = 0; i < vectorToCopy.size(); i++){
			
			newVector.add(vectorToCopy.get(i));
		}
		
		return new FloorsArray(newVector);
	}
	
	
	private synchronized boolean vectorsAreSame(Vector<Integer> vector1, Vector<Integer> vector2){
		
		if(vector1.size() != vector2.size()){
			return false;
		}
		
		for(int i = 0; i < Math.min(vector1.size(),vector2.size()); i++){
			if(vector1.get(i).intValue() != vector2.get(i).intValue()){
				return false;
			}
		}
		
		return true;
	}
	
	public synchronized void setNextFloor() throws InterruptedException{
		this.getFloorsArray().faLock();
		if(this.getFloorsArray().size()>0){
			this.setNextFloor(this.getFloorsArray().get(0));
		} else {
		this.setNextFloor(-1);
		}
		this.getFloorsArray().faUnLock();
	}
	
	private void loop() throws InterruptedException{
		
		Integer[] temp1 = CentralDispatcher.getDistributionOfUntaskedElevators();
		
		//add all desired floors of passengers to floorsarray
		this.getFloorsArray().faLock();
		for(int i = 0; i < this.getGroupsOfPassengers().size(); i++){
			if(this.getGroupsOfPassengers().get(i).getCumulativeTimeOfRidingElevator() == 0 || this.getGroupsOfPassengers().get(i).isGotServedByElevator() == false){ 
					hasTask = true;
			if(!this.getFloorsArray().contains(this.getGroupsOfPassengers().get(i).getDesiredFloor())){
				this.getFloorsArray().add(this.getGroupsOfPassengers().get(i).getDesiredFloor());
				
			}
		}
		}
		this.getFloorsArray().faUnLock();
		
		
		if(!hasTask || this.getFloorsArray().isEmpty()){
			
			hasTask = false;
			goingUp = false;
			goingDown = false;
			
			// set untasked
			setChanged();
			notifyObservers(Color.pink);
			
			Thread.sleep(10);
			
			setChanged();
			notifyObservers(new String("E"+this.getNumberOfPassengers().toString()));
			
			checkToSeeIfPeopleOnFloorForUntaskedElevator();
			
			temp1[this.getCurrentFloor()] += 1;
			if(temp1[this.getCurrentFloor()] >= 1){
				boolean move = false;

				// move elevators to floors with high demand even if nobody is there
				
			}
			
			for(int i = 0; i < this.getGroupsOfPassengers().size(); i++){
				if(this.getGroupsOfPassengers().get(i).getCumulativeTimeOfRidingElevator() == 0 || !this.getGroupsOfPassengers().get(i).isGotServedByElevator()){
					String problem = "Untasked elevator has passengers in it...possibly because while in the untasked loop, passengers were added...?";
					CentralDispatcher.insertIntoDebuggingTable(CentralDispatcher.getRunNumber(),CentralDispatcher.getNumberOfFloors(),CentralDispatcher.getNumberOfElevators(),problem,CentralDispatcher.getCurrentTime()/1000,this.getClass().getName());
					if(!this.getFloorsArray().contains(this.getGroupsOfPassengers().get(i).getDesiredFloor())){
					this.getFloorsArray().add(this.getGroupsOfPassengers().get(i).getDesiredFloor());
					}
					hasTask = true;
				}
			}
			if(hasTask){
			return;
			}
			
			
			if( this.getCapacity() - this.getCurrentNumberOfPeopleSpaces()  != this.getNumberOfPassengers()){
				String problem = "capacity problem - doesnt equal spaces and no of passengers";
				CentralDispatcher.insertIntoDebuggingTable(CentralDispatcher.getRunNumber(),CentralDispatcher.getNumberOfFloors(),CentralDispatcher.getNumberOfElevators(),problem,CentralDispatcher.getCurrentTime()/1000,this.getClass().getName());
		
			}
			
//			if(this.getCurrentNumberOfPeopleSpaces() < this.getCapacity()){
//				//if we found a rider that somehow was hiding, return to take him to the right floor
//				hasTask = true;
//				return;
//			}
			
			
			//below we find a floor with people waiting where no elevator is going to
			int x = 0;
			int y = 0;
			int z = CentralDispatcher.getNumberOfFloors();
			int m = 0;
			int p = this.getCurrentFloor();
			
			while(m < z){
				m++;
				y = Math.abs(y);
				y += 1;
				y = y*-1;
				x = p - y;
				if(x < 0 || x > z -1){
					
					continue;
				}
				if( CentralDispatcher.getFloorArray()[x].getCurrentNumberWaiting() > 0 && CentralDispatcher.getFloorTruth()[x][0] < 1){
					this.getFloorsArray().add(x);
					 CentralDispatcher.floorTruth[x][0]++;
					this.hasTask = true;
				    break;
				}
				if(CentralDispatcher.getFloorArray()[x].getCurrentNumberWaiting() > 0 && CentralDispatcher.getFloorTruth()[x][1] < 1){
					this.getFloorsArray().add(x);
					 CentralDispatcher.floorTruth[x][1]++;
				    this.hasTask = true;
				    break;
		      	}
			}
			
			setChanged();
			notifyObservers(new String("E"+this.getNumberOfPassengers().toString()));
		
		}
		
		
		if(hasTask || !this.getFloorsArray().isEmpty()){
			
			
			hasTask = true;
			
			setChanged();
			notifyObservers("E"+this.getNumberOfPassengers().toString());
			
			for(int i = 0; i < this.getGroupsOfPassengers().size(); i++){
				if(this.getGroupsOfPassengers().get(i).getCumulativeTimeOfRidingElevator() == 0 && !this.getFloorsArray().contains(this.getGroupsOfPassengers().get(i).getDesiredFloor())){
					this.getFloorsArray().add(this.getGroupsOfPassengers().get(i).getDesiredFloor());
				}
			}
			
			if(this.getFloorsArray().isEmpty()){
				hasTask = false;
				goingUp = false;
				goingDown = false;
				
				setChanged();
				notifyObservers(Color.pink);
				return;
			}
			
			
		//	this.available = false;
			
			if(this.getFloorsArray().size() > 1){
				removeDuplicates();
			}
	
			//if array size is one, do something different then if array size is greater than one...???
			
			this.setNextFloor();
			
			FloorsArray temp;
			if(this.getNextFloor() > this.getCurrentFloor()){
				this.goingUp = true;
				this.goingDown = false;
				temp = copyArray(this.getFloorsArray());
				temp.faLock();
				temp =  sortUp(temp, true);
				this.setFloorsArray(temp);
				this.setNextFloor();
				temp.faUnLock();
			//	this.available = true;
			} else if (this.getNextFloor() < this.getCurrentFloor()){
				this.goingUp = false;
				this.goingDown = true;
				temp =  copyArray(this.getFloorsArray());
				temp.faLock();
				temp = sortDown(temp, true);
				this.setFloorsArray(temp);
				this.setNextFloor();
				temp.faUnLock();
			//	this.available = true;
			}
			else{
				String problem = "Elevator seems to be on same floor as next floor.";
				CentralDispatcher.insertIntoDebuggingTable(CentralDispatcher.getRunNumber(),CentralDispatcher.getNumberOfFloors(),CentralDispatcher.getNumberOfElevators(),problem,CentralDispatcher.getCurrentTime()/1000,this.getClass().getName());
			}
			
			
			temp = new FloorsArray(CentralDispatcher.getNumberOfFloors());
			this.getFloorsArray().faLock();
			temp = copyArray(this.getFloorsArray());
			temp.faLock();
			
			//double travelTime = findTravelTimePerFloor();
			isStoppedOnCurrentFloor = false;
			
			if(goingUp && !goingDown){
				while(this.getCurrentFloor() < this.getNextFloor()){
					setChanged();
					notifyObservers(Color.blue);
					Thread.sleep((long)(this.getTravelTimePerFloor()/this.getTimeFactor()));
					this.setCurrentFloor(this.getCurrentFloor()+1);
					
					if(!vectorsAreSame(temp,this.getFloorsArray())){
					//	this.available = false;
						temp =  copyArray(this.getFloorsArray());
						temp = sortUp(temp, true);
						this.setFloorsArray((FloorsArray)temp);
						setChanged();
						notifyObservers(Color.yellow);
						this.setNextFloor();
					//	this.available = true;
					}
					
					
					
					//here we look for floors that need help!
					if(this.getCurrentNumberOfPeopleSpaces() > 3){
						boolean extraStop = false;
					for(int i = this.getCurrentFloor() + 1; i < this.getNextFloor(); i++){
						if(CentralDispatcher.getFloorArray()[i].getCurrentNumberWaitingToGoUp() > 2 ){
							for(int j = 0; j < CentralDispatcher.getNumberOfElevators(); j++){
								if(CentralDispatcher.getElevatorArray()[j].getFloorsArray().contains(i) && Math.abs(CentralDispatcher.getElevatorArray()[j].getCurrentFloor() - i) < 3){
									extraStop = false;
									break;
								}
							}
							if(extraStop){
							this.setNextFloor(i);
							break;
							}
						}
					}
					}
					
					//here we remove nextfloor if we are near capacity and nobody is getting off at that floor, cool!! :)
					boolean doRemove = true;
					for(int i = 0; i < this.getGroupsOfPassengers().size(); i++){
						if(this.getGroupsOfPassengers().get(i).getDesiredFloor() == this.getNextFloor()){
							doRemove = false;
							break;
						}
					}
					// need to fix code to see if there is more than one groups on this floor, one going down, one going up
					if(doRemove){
						if(this.getCurrentNumberOfPeopleSpaces() < this.capacityThresholdVariable){
							ArrivalGroup ag = CentralDispatcher.getFloorArray()[this.getNextFloor()].getNextInLine();
							if(ag != null){
							CentralDispatcher.getRequestArray().add(0, ag);
							String problem = "A destination floor was removed from elevators floor array.";
							CentralDispatcher.insertIntoDebuggingTable(CentralDispatcher.getRunNumber(),CentralDispatcher.getNumberOfFloors(),CentralDispatcher.getNumberOfElevators(),problem,CentralDispatcher.getCurrentTime()/1000,this.getClass().getName());
							this.getFloorsArray().remove(0);
							this.setNextFloor();
							}
						}
					}
				}
			}
			else if(goingDown && !goingUp){
				boolean doRemove = true;
				while(this.getCurrentFloor() > this.getNextFloor()){
					Thread.sleep((long)(this.getTravelTimePerFloor()/this.getTimeFactor()));
					setChanged();
					notifyObservers(Color.blue);
					this.setCurrentFloor(this.getCurrentFloor()-1);
					setChanged();
					notifyObservers(Color.blue);
					
					if(!vectorsAreSame(temp,this.getFloorsArray())){
					//	this.available = false;
						temp = copyArray(this.getFloorsArray());
						temp = sortDown(temp, true);
						this.setFloorsArray(temp);
						setChanged();
						notifyObservers(Color.yellow);
						this.setNextFloor();
					//	this.available = true;
					}
					doRemove = true;
					for(int i = 0; i < this.getGroupsOfPassengers().size(); i++){
						if(this.getGroupsOfPassengers().get(i).getDesiredFloor() == this.getNextFloor()){
							doRemove = false;
							break;
						}
					}
					if(doRemove){
						if(this.getCurrentNumberOfPeopleSpaces() < this.capacityThresholdVariable){
							ArrivalGroup ag = CentralDispatcher.getFloorArray()[this.getNextFloor()].getNextInLine();
							if(ag != null){
							CentralDispatcher.getRequestArray().add(0, ag);
							String problem = "A destination floor was removed from elevators floor array.";
							CentralDispatcher.insertIntoDebuggingTable(CentralDispatcher.getRunNumber(),CentralDispatcher.getNumberOfFloors(),CentralDispatcher.getNumberOfElevators(),problem,CentralDispatcher.getCurrentTime()/1000,this.getClass().getName());
							this.getFloorsArray().remove(0);
							this.setNextFloor();
							}
						}	
					}
				}
			}
			
			//Elevator is neither going up or down...let's hope that either the floorsarray is empty or even better, there is only one element in the array, and that is the current floor...
			else{
					String problem = "FloorArray should NOT be empty here...";
					CentralDispatcher.insertIntoDebuggingTable(CentralDispatcher.getRunNumber(),CentralDispatcher.getNumberOfFloors(),CentralDispatcher.getNumberOfElevators(),problem,CentralDispatcher.getCurrentTime()/1000,this.getClass().getName());
					
			}
			temp.faUnLock(); //////////////////// what???
			
			if(this.getCurrentFloor() != this.getFloorsArray().get(0)){
				String problem = "Ooo boy, current floor is not the same as what it should be..";
				CentralDispatcher.insertIntoDebuggingTable(CentralDispatcher.getRunNumber(),CentralDispatcher.getNumberOfFloors(),CentralDispatcher.getNumberOfElevators(),problem,CentralDispatcher.getCurrentTime()/1000,this.getClass().getName());
			}
			isStoppedOnCurrentFloor = true;
			
		
			setChanged();
			notifyObservers(Color.green);
			this.getFloorsArray().remove(0);
			
			//we don't know what the next floor is yet...we have to load passengers and see what their requests are!
			
			setNextFloor();
			
			
			if(this.getElevatorNumberId() == 1){
				System.out.println("");
				System.out.println("Elevator #: " + this.getElevatorNumberId());
				System.out.println("Elevator direction up: " + this.goingUp);
				System.out.println("Elevator direction down: " + this.goingDown);
				System.out.println("Current Floor: " + this.getCurrentFloor());
				System.out.println("Floor Array: " + this.getFloorsArray());
				System.out.println("Next Floor: " + this.getNextFloor());
				}
			
			
			setChanged();
			notifyObservers("E"+this.getNumberOfPassengers().toString());
			
			//NOW, WE UNLOAD PASSENGERS
			for(int i = 0; i < this.getGroupsOfPassengers().size(); i++){
				if(this.getGroupsOfPassengers().get(i).getDesiredFloor() == this.getCurrentFloor() && this.getGroupsOfPassengers().get(i).isGotServedByElevator() == false){
					this.getGroupsOfPassengers().get(i).setCumulativeTimeOfRidingElevator(System.nanoTime()/1000000 - this.getGroupsOfPassengers().get(i).getTimeFirstBoarding());
					this.getGroupsOfPassengers().get(i).setGotServedByElevator(true);
					for(int j = 0; j < this.getGroupsOfPassengers().get(i).getNumberOfPeopleInGroup(); j++){
						
						Thread.sleep((long)(this.getLoadUnloadTimePerPassenger()/this.getTimeFactor()));
					}
			    }
			}
			
			
			if(this.getCurrentNumberOfPeopleSpaces() == this.getCapacity()){
				//we have an empty elevator! but we can't set untasked until we load ppl
				this.goingUp = false;
				this.goingDown = false;
			}
			
			FloorOfBuilding fob = CentralDispatcher.getFloorArray()[this.getCurrentFloor()];
			
			
			
			//NOW WE LOAD NEW PASSENGERS
			Integer countPplWaiting = fob.getCurrentNumberWaiting();
			Integer countPplWaitingUp = fob.getCurrentNumberWaitingToGoUp();
			Integer countPplWaitingDown = fob.getCurrentNumberWaitingToGoDown();
			
			String waiting = countPplWaiting.toString() + " " + countPplWaitingUp.toString() + " " + countPplWaitingDown.toString();
			
			setChanged();
			notifyObservers(waiting);
			
			
			if(countPplWaiting == 0){

				CentralDispatcher.floorTruth[fob.getFloorNumber()][0]--;
				CentralDispatcher.floorTruth[fob.getFloorNumber()][1]--;
				//may still have a task if there are people in the elevator still, but there is nobody to load so we return.
				return;
			}
			
			//do something here if elevator is at capacity
			//need to add code that checks to see if people have arrived to the floor during the time period the elevator was loading people!!!!
					
			int k = this.getCurrentNumberOfPeopleSpaces();
	
			fob.getArrivalGroupArray().agalock();
			for(int i = 0; i < fob.getArrivalGroupArray().size(); i++){
				if(fob.getArrivalGroupArray().get(i).isWaiting() == true){
				
					if(fob.getArrivalGroupArray().get(i).getNumberOfPeopleInGroup() <= k &&  ((this.goingDown == false && this.goingUp == false)|| (this.goingUp == true && this.goingDown == false && fob.getArrivalGroupArray().get(i).getDirection().equals("up")) || (this.goingUp == false && this.goingDown == true && fob.getArrivalGroupArray().get(i).getDirection().equals("down")))){
					
					if(this.goingDown == false && this.goingUp == false){
						if(fob.getArrivalGroupArray().get(i).getDirection().equals("up")){
							this.goingUp = true;
						}
						else if(fob.getArrivalGroupArray().get(i).getDirection().equals("down")){
							this.goingDown = true;
						}
					}
							
					setChanged();
					notifyObservers(Color.cyan);
					
					fob.getArrivalGroupArray().get(i).setCumulativeTimeOfWaiting(System.nanoTime()/1000000-fob.getArrivalGroupArray().get(i).getTimeOfArrival());
					fob.getArrivalGroupArray().get(i).setWaiting(false);
					fob.getArrivalGroupArray().get(i).setTimeFirstBoarding(System.nanoTime()/1000000);
					this.getGroupsOfPassengers().add(fob.getArrivalGroupArray().get(i));

					for(int j = 0; j < fob.getArrivalGroupArray().get(i).getNumberOfPeopleInGroup(); j++){
						Thread.sleep((long)(this.getLoadUnloadTimePerPassenger()/this.getTimeFactor()));
					}
					
					//for all new passengers, add their desired floor to this elevator's destination array
					if(!this.getFloorsArray().contains(fob.getArrivalGroupArray().get(i).getDesiredFloor())){
					    this.getFloorsArray().add(fob.getArrivalGroupArray().get(i).getDesiredFloor());
					}
					
					k = this.getCurrentNumberOfPeopleSpaces();
					
					if(k <= 1){
						break;
					}
					}
				}
			}
			fob.getArrivalGroupArray().agaUnLock();
					
			//what to do with remaining people on the floor that didn't/couldn't get served by this current elevator:
			countPplWaiting = fob.getCurrentNumberWaiting();
			
			if(this.goingDown = true){
				CentralDispatcher.floorTruth[this.getCurrentFloor()][1]--;
			}
			else if(this.goingUp== true){
				CentralDispatcher.floorTruth[this.getCurrentFloor()][0]--;
			}
			else{
				
			}
			
			
			
			if(countPplWaiting > 0){
				
				boolean goingUpNewGroup = false;
				boolean goingDownNewGroup = false;
				
				fob.getArrivalGroupArray().agalock();
				for(int i = 0; i < fob.getArrivalGroupArray().size(); i++){
					if(fob.getArrivalGroupArray().get(i).isWaiting() == true){
						boolean add = true;
						if(!goingUpNewGroup && fob.getArrivalGroupArray().get(i).getDirection().equals("up")){
							
							CentralDispatcher.ra.ralock();
							for(int j = 0; j < CentralDispatcher.getRequestArray().size(); j++){
								if(CentralDispatcher.getRequestArray().size() > 1 && CentralDispatcher.getRequestArray().get(j).getStartFloor() == fob.getArrivalGroupArray().get(i).getStartFloor()){
									
									if(fob.getArrivalGroupArray().get(i).getStartFloor() != this.getCurrentFloor()){
										String problem = "We have a serious problem with start floor of the customer not corresponding to the elevator's current floor that customer wants to load onto!";
										CentralDispatcher.insertIntoDebuggingTable(CentralDispatcher.getRunNumber(),CentralDispatcher.getNumberOfFloors(),CentralDispatcher.getNumberOfElevators(),problem,CentralDispatcher.getCurrentTime()/1000,this.getClass().getName());
									}
									add = false;
									break;
								}
							}
							if(add == true){
							CentralDispatcher.getRequestArray().add(fob.getArrivalGroupArray().get(i));
							goingUpNewGroup = true;
							}
							CentralDispatcher.ra.raUnlock();
						}
						if(!goingDownNewGroup && fob.getArrivalGroupArray().get(i).getDirection().equals("down")){
							
							CentralDispatcher.ra.ralock();
							for(int j = 0; j < CentralDispatcher.getRequestArray().size(); j++){
								if(CentralDispatcher.getRequestArray().size() > 1 && CentralDispatcher.getRequestArray().get(j).getStartFloor() == fob.getArrivalGroupArray().get(i).getStartFloor()){
									add = false;
									break;
								}
							}
							if(add==true){
						   CentralDispatcher.getRequestArray().add(fob.getArrivalGroupArray().get(i));
						   goingDownNewGroup = true;
							}
							CentralDispatcher.ra.raUnlock();
						}
						//if a group is found to be going down and a group is found to be going up, no need to keep looping through the rest of the people waiting, an up and down request has been sent to dispatcher
						if(goingUpNewGroup && goingDownNewGroup){
							break;
						}
					}
				}
				fob.getArrivalGroupArray().agaUnLock();
			}
			
			setChanged();
			notifyObservers(new String("E"+this.getNumberOfPassengers().toString()));
			
			countPplWaiting = fob.getCurrentNumberWaiting();
			countPplWaitingUp = fob.getCurrentNumberWaitingToGoUp();
			countPplWaitingDown = fob.getCurrentNumberWaitingToGoDown();
			
			waiting = countPplWaiting.toString() + " " + countPplWaitingUp.toString() + " " + countPplWaitingDown.toString();
			
			setChanged();
			notifyObservers(waiting);
			
		}
	}
}