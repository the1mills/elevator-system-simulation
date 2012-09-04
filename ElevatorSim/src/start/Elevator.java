package start;


import java.awt.Color;
import java.awt.Graphics;
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
	private volatile CentralDispatcher centralDispatcher;
	private boolean hasTask = false;
	private int currentFloor;
	private int nextFloor;
	private volatile Vector<Integer> floorsArray;
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
	private boolean paint = true;


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
			this.getCentralDispatcher().insertIntoDebuggingTable(this.getCentralDispatcher().getRunNumber(),this.getCentralDispatcher().getNumberOfFloors(),this.getCentralDispatcher().getNumberOfElevators(),problem,this.getCentralDispatcher().getCurrentTime()/1000,this.getClass().getName());
	
		}
		return this.getCapacity() - count;
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
	
	public synchronized CentralDispatcher getCentralDispatcher() {
		return centralDispatcher;
	}

	public synchronized  void setCentralDispatcher(CentralDispatcher centralDispatcher) {
		this.centralDispatcher = centralDispatcher;
	}

	public synchronized boolean isHasTask() {
		return hasTask;
	}

	public synchronized void setHasTask(boolean hasTask) {
		this.hasTask = hasTask;
	}
	
	public synchronized Vector<Integer> getFloorsArray() {
		return floorsArray;
	}

	public synchronized void setFloorsArray(Vector<Integer> floorsArray) {
		this.floorsArray = floorsArray;
	}

	public synchronized int getNextFloor() {
		return nextFloor;
	}

	public synchronized void setNextFloor(int nextFloor) {
		this.nextFloor = nextFloor;
	}
	
	public synchronized int getCurrentFloor() {
		return currentFloor;
	}

	public synchronized void setCurrentFloor(int currentFloor) {
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

	public Elevator(CentralDispatcher centralDispatcher, int initialFloor, int elevatorNumberId, int capacity,
			double maxSpeedBetweenFloors, double accRate, double decRatePerFloor, double timeFactor, int travelTimePerFloor, int loadUnloadTimePerPassenger, int capacityThresholdVariable) {

		this.initialFloor = initialFloor;
		this.centralDispatcher = centralDispatcher;
		this.elevatorNumberId = elevatorNumberId;
		this.capacity = capacity;
		this.currentNumberOfPeopleSpaces = capacity;
		this.setCurrentFloor(initialFloor);
		this.floorsArray = new Vector<Integer>(this.centralDispatcher.getNumberOfFloors());
		this.drawElevatorWidth = (int)((double)700/((double)this.getCentralDispatcher().getNumberOfElevators())*.75);
		this.drawElevatorHeight = (int)(((double)700/(double)this.getCentralDispatcher().getNumberOfFloors())*.75);
		this.drawWidth = (int)((double)700/(double)(this.getCentralDispatcher().getNumberOfElevators())*this.getElevatorNumberId()+ 50 + (int)((double)700/((double)this.getCentralDispatcher().getNumberOfElevators())*.25)/2);
		this.drawHeight = (700/(double)this.getCentralDispatcher().getNumberOfFloors());
		this.heightConstant = 100 + (int)((((double)700/(double)this.getCentralDispatcher().getNumberOfFloors()))) - (int)((((double)700/(double)this.getCentralDispatcher().getNumberOfFloors())*.25)/2);
		this.timeFactor = timeFactor;
		this.travelTimePerFloor = travelTimePerFloor;
		this.loadUnloadTimePerPassenger = loadUnloadTimePerPassenger;
		this.capacityThresholdVariable = capacityThresholdVariable;
		
		this.addObserver(this.centralDispatcher.getEca());
	}

	@Override
	public void run() {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		while(this.centralDispatcher.isKeepLooping()){
			try {
				loop();
			} catch (InterruptedException e) {
				String problem = "Interruption exception whiling looping in an elevator.";
				this.getCentralDispatcher().insertIntoDebuggingTable(this.getCentralDispatcher().getRunNumber(),this.getCentralDispatcher().getNumberOfFloors(),this.getCentralDispatcher().getNumberOfElevators(),problem,this.getCentralDispatcher().getCurrentTime()/1000,this.getClass().getName());
			}
		}	
	}
	
	private Vector<Integer> sortUp(Vector<Integer> temp, boolean first){
		
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
		intArray.add(x);
		temp.remove(k);
		}
		if(temp.isEmpty()){
			break;
		}
		}
		
		if(!intArrayDown.isEmpty()){
			intArrayDown = sortDown(intArrayDown, false);
		
		for(int i = 0; i < intArrayDown.size(); i++){
			intArray.add(intArrayDown.get(i));
		}
		}
		return intArray;
		
	}
	
	private Vector<Integer> sortDown(Vector<Integer> temp, boolean first){
		
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
		intArray.add(x);
		temp.remove(k);
		}
		if(temp.isEmpty()){
			break;
		}
		}
		
		if(!intArrayUp.isEmpty()){
			intArrayUp = sortUp(intArrayUp, false);
		
		for(int i = 0; i < intArrayUp.size(); i++){
			intArray.add(intArrayUp.get(i));
		}
		}
		
		return intArray;
	}
	
	private double findTravelTime(){
		
		int distance = Math.abs(this.getCurrentFloor()- this.getFloorsArray().get(0));
		
		return distance*3.1;
	}
	
	private double findTravelTimePerFloor(){
		
		// int distance = Math.abs(this.getCurrentFloor()- this.getFloorsArray().get(0));
		
		return 200;
		
	}
	
	private void removeDuplicates(){
		
		Vector<Integer> intTempArray = new Vector<Integer>();
		
		for(int i = 0; i < this.getFloorsArray().size(); i++){
			
			if(!intTempArray.contains(this.getFloorsArray().get(i))){
			intTempArray.add(this.getFloorsArray().get(i));
			}
		}
		this.setFloorsArray(intTempArray);
		
	}
	
	private synchronized void checkToSeeIfPeopleOnFloorForUntaskedElevator(){
		FloorOfBuilding fob = this.getCentralDispatcher().getFloorArray()[this.getCurrentFloor()];
		if(fob.findOutIfThereIsGroupWaiting()){
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
						}
						first = false;
					}
				}
				}
			}
		}
	}
	
	private void loop() throws InterruptedException{
		
		Graphics g = this.getCentralDispatcher().getEca().getGraphics();
		Integer[] temp1 = this.getCentralDispatcher().getDistributionOfUntaskedElevators();
		
		//add all desired floors of passengers to floorsarray
		for(int i = 0; i < this.getGroupsOfPassengers().size(); i++){
			if(this.getGroupsOfPassengers().get(i).getCumulativeTimeOfRidingElevator() == 0){
				this.getFloorsArray().add(this.getGroupsOfPassengers().get(i).getDesiredFloor());
				hasTask = true;
			}
		}
		
		if(!hasTask || this.getFloorsArray().isEmpty()){
			
			hasTask = false;
			goingUp = false;
			goingDown = false;
			if(paint){
			//this.getCentralDispatcher().getEca().paint(g, this.getDrawWidth(), (int)(this.getDrawHeight()*this.getCurrentFloor() + this.getHeightConstant()), this.getDrawElevatorWidth(), this.getDrawElevatorHeight(), Color.pink, ((Integer)(this.getCapacity()-this.getCurrentNumberOfPeopleSpaces())).toString());
			paint = false;
			}
			
			checkToSeeIfPeopleOnFloorForUntaskedElevator();
			
			temp1[this.getCurrentFloor()] += 1;
			if(temp1[this.getCurrentFloor()] >= 1){
				boolean move = false;
//				while(!hasTask && this.getCurrentFloor() > 0){
//					if(move){
//						break;
//					}
//					for(int i = 0; i < 10; i++){
//					Thread.sleep((long)((this.getTravelTimePerFloor()/this.getTimeFactor())/10));
//					if(hasTask){
//						move = true;
//						break;
//					}
//					}
//					this.getCentralDispatcher().getEa().paint(g, this.getDrawWidth(), (int)(this.getDrawHeight()*this.getCurrentFloor() + this.getHeightConstant()), this.getDrawElevatorWidth(), this.getDrawElevatorHeight(), Color.gray.lightGray);
//					this.setCurrentFloor(this.getCurrentFloor()-1);
//					this.getCentralDispatcher().getEa().paint(g, this.getDrawWidth(), (int)(this.getDrawHeight()*this.getCurrentFloor() + this.getHeightConstant()), this.getDrawElevatorWidth(), this.getDrawElevatorHeight(), Color.red);
//					
//				}
				
			}
			for(int i = 0; i < this.getGroupsOfPassengers().size(); i++){
				if(this.getGroupsOfPassengers().get(i).getCumulativeTimeOfRidingElevator() == 0 || !this.getGroupsOfPassengers().get(i).isGotServedByElevator()){
					String problem = "Untasked elevator has passengers in it...possibly because while in the untasked loop, passengers were added...?";
					this.getCentralDispatcher().insertIntoDebuggingTable(this.getCentralDispatcher().getRunNumber(),this.getCentralDispatcher().getNumberOfFloors(),this.getCentralDispatcher().getNumberOfElevators(),problem,this.getCentralDispatcher().getCurrentTime()/1000,this.getClass().getName());
					this.getFloorsArray().add(this.getGroupsOfPassengers().get(i).getDesiredFloor());
					hasTask = true;
				}
			}
		}
		
		if(hasTask || !this.getFloorsArray().isEmpty()){
			
			hasTask = true;
			paint = true;
			
			for(int i = 0; i < this.getGroupsOfPassengers().size(); i++){
				if(this.getGroupsOfPassengers().get(i).getCumulativeTimeOfRidingElevator() == 0 && !this.getFloorsArray().contains(this.getGroupsOfPassengers().get(i).getDesiredFloor())){
					this.getFloorsArray().add(this.getGroupsOfPassengers().get(i).getDesiredFloor());
				}
			}
			
			if(this.getFloorsArray().isEmpty()){
				hasTask = false;
				goingUp = false;
				goingDown = false;
				//this.getCentralDispatcher().getEa().paint(g, this.getDrawWidth(), (int)(this.getDrawHeight()*this.getCurrentFloor() + this.getHeightConstant()), this.getDrawElevatorWidth(), this.getDrawElevatorHeight(), Color.pink, ((Integer)(this.getCapacity()-this.getCurrentNumberOfPeopleSpaces())).toString());
				return;
			}
			
			if(this.getFloorsArray().size() > 1){
				removeDuplicates();
				int size = this.getFloorsArray().size();
				if(size == 0){
					String problem = "Something is wrong with the remove duplicates method.";
					this.getCentralDispatcher().insertIntoDebuggingTable(this.getCentralDispatcher().getRunNumber(),this.getCentralDispatcher().getNumberOfFloors(),this.getCentralDispatcher().getNumberOfElevators(),problem,this.getCentralDispatcher().getCurrentTime()/1000,this.getClass().getName());
				}
			}
	
			//if array size is one, do something different then if array size is greater than one...???
			
			this.setNextFloor(this.getFloorsArray().get(0));
			
			Vector<Integer> temp;
			if(this.getNextFloor() > this.getCurrentFloor()){
				this.goingUp = true;
				this.goingDown = false;
				this.available = false;
				removeDuplicates();
				temp = this.getFloorsArray();
				temp = sortUp(temp, true);
				this.setFloorsArray(temp);
				this.setNextFloor(this.getFloorsArray().get(0));
				this.available = true;
			} else if (this.getNextFloor() < this.getCurrentFloor()){
				this.goingUp = false;
				this.goingDown = true;
				this.available = false;
				removeDuplicates();
				temp = this.getFloorsArray();
				temp = sortDown(temp, true);
				this.setFloorsArray(temp);
				this.setNextFloor(this.getFloorsArray().get(0));
				this.available = true;
			}
			else{
				String problem = "Elevator seems to be on same floor as next floor.";
				this.getCentralDispatcher().insertIntoDebuggingTable(this.getCentralDispatcher().getRunNumber(),this.getCentralDispatcher().getNumberOfFloors(),this.getCentralDispatcher().getNumberOfElevators(),problem,this.getCentralDispatcher().getCurrentTime()/1000,this.getClass().getName());
			}
			
			temp = new Vector<Integer>();
			for(int i = 0; i < this.getFloorsArray().size(); i++){
				temp.add(this.getFloorsArray().get(i));
			}
			
			//double travelTime = findTravelTimePerFloor();
			isStoppedOnCurrentFloor = false;
			
			if(goingUp && !goingDown){
				while(this.getCurrentFloor() < this.getNextFloor()){
					Thread.sleep((long)(this.getTravelTimePerFloor()/this.getTimeFactor()));
				//	this.getCentralDispatcher().getEa().paint(g, this.getDrawWidth(), (int)(this.getDrawHeight()*this.getCurrentFloor() + this.getHeightConstant()), this.getDrawElevatorWidth(), this.getDrawElevatorHeight(), Color.white, "");
					this.setCurrentFloor(this.getCurrentFloor()+1);
				//	this.getCentralDispatcher().getEa().paint(g, this.getDrawWidth(), (int)(this.getDrawHeight()*this.getCurrentFloor() + this.getHeightConstant()), this.getDrawElevatorWidth(), this.getDrawElevatorHeight(), Color.blue.brighter(), ((Integer)(this.getCapacity()-this.getCurrentNumberOfPeopleSpaces())).toString());

					
					if(!temp.equals(this.getFloorsArray())){
						this.available = false;
						temp = this.getFloorsArray();
						temp = sortUp(temp, true);
						this.setFloorsArray(temp);
					//	this.getCentralDispatcher().getEa().paint(g, this.getDrawWidth(), (int)(this.getDrawHeight()*this.getCurrentFloor() + this.getHeightConstant()), this.getDrawElevatorWidth(), this.getDrawElevatorHeight(), Color.yellow.brighter(), ((Integer)(this.getCapacity()-this.getCurrentNumberOfPeopleSpaces())).toString());
						this.setNextFloor(this.getFloorsArray().get(0));
						this.available = true;
					}
					
					//here we look for floors that need help!
					if(this.getCurrentNumberOfPeopleSpaces() > 3){
						boolean extraStop = false;
					for(int i = this.getCurrentFloor() + 1; i < this.getNextFloor(); i++){
						if(this.getCentralDispatcher().getFloorArray()[i].getCurrentNumberWaitingToGoUp() > 2 ){
							for(int j = 0; j < this.getCentralDispatcher().getNumberOfElevators(); j++){
								if(this.getCentralDispatcher().getElevatorArray()[j].getFloorsArray().contains(i) && Math.abs(this.getCentralDispatcher().getElevatorArray()[j].getCurrentFloor() - i) < 3){
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
							ArrivalGroup ag = this.getCentralDispatcher().getFloorArray()[this.getNextFloor()].getNextInLine();
							if(ag != null){
							this.getCentralDispatcher().getRequestArray().add(0, ag);
							String problem = "A destination floor was removed from elevators floor array.";
							this.getCentralDispatcher().insertIntoDebuggingTable(this.getCentralDispatcher().getRunNumber(),this.getCentralDispatcher().getNumberOfFloors(),this.getCentralDispatcher().getNumberOfElevators(),problem,this.getCentralDispatcher().getCurrentTime()/1000,this.getClass().getName());
							this.getFloorsArray().remove(0);
							this.setNextFloor(this.getFloorsArray().get(0));
							}
						}
					}
				}
			}
			else if(goingDown && !goingUp){
				boolean doRemove = true;
				while(this.getCurrentFloor() > this.getNextFloor()){
					Thread.sleep((long)(this.getTravelTimePerFloor()/this.getTimeFactor()));
				//	this.getCentralDispatcher().getEa().paint(g, this.getDrawWidth(), (int)(this.getDrawHeight()*this.getCurrentFloor() + this.getHeightConstant()), this.getDrawElevatorWidth(), this.getDrawElevatorHeight(), Color.white, "");
					this.setCurrentFloor(this.getCurrentFloor()-1);
				//	this.getCentralDispatcher().getEa().paint(g, this.getDrawWidth(), (int)(this.getDrawHeight()*this.getCurrentFloor() + this.getHeightConstant()), this.getDrawElevatorWidth(), this.getDrawElevatorHeight(), Color.blue.brighter(), ((Integer)(this.getCapacity()-this.getCurrentNumberOfPeopleSpaces())).toString());

					if(!temp.equals(this.getFloorsArray())){
						this.available = false;
						temp = this.getFloorsArray();
						temp = sortDown(temp, true);
						this.setFloorsArray(temp);
				//		this.getCentralDispatcher().getEa().paint(g, this.getDrawWidth(), (int)(this.getDrawHeight()*this.getCurrentFloor() + this.getHeightConstant()), this.getDrawElevatorWidth(), this.getDrawElevatorHeight(), Color.yellow.brighter(), ((Integer)(this.getCapacity()-this.getCurrentNumberOfPeopleSpaces())).toString());
						this.setNextFloor(this.getFloorsArray().get(0));
						this.available = true;
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
							ArrivalGroup ag = this.getCentralDispatcher().getFloorArray()[this.getNextFloor()].getNextInLine();
							if(ag != null){
							this.getCentralDispatcher().getRequestArray().add(0, ag);
							String problem = "A destination floor was removed from elevators floor array.";
							this.getCentralDispatcher().insertIntoDebuggingTable(this.getCentralDispatcher().getRunNumber(),this.getCentralDispatcher().getNumberOfFloors(),this.getCentralDispatcher().getNumberOfElevators(),problem,this.getCentralDispatcher().getCurrentTime()/1000,this.getClass().getName());
							this.getFloorsArray().remove(0);
							this.setNextFloor(this.getFloorsArray().get(0));
							}
						}	
					}
				}
			}
			
			//Elevator is neither going up or down...let's hope that either the floorsarray is empty or even better, there is only one element in the array, and that is the current floor...
			else{
				if(goingUp && goingDown){
					String problem = "OHHHHH...shit!!...both going up and down....";
					this.getCentralDispatcher().insertIntoDebuggingTable(this.getCentralDispatcher().getRunNumber(),this.getCentralDispatcher().getNumberOfFloors(),this.getCentralDispatcher().getNumberOfElevators(),problem,this.getCentralDispatcher().getCurrentTime()/1000,this.getClass().getName());
					
				}
				if(this.getFloorsArray().size() == 0){
					String problem = "FloorArray should NOT be empty here...";
					this.getCentralDispatcher().insertIntoDebuggingTable(this.getCentralDispatcher().getRunNumber(),this.getCentralDispatcher().getNumberOfFloors(),this.getCentralDispatcher().getNumberOfElevators(),problem,this.getCentralDispatcher().getCurrentTime()/1000,this.getClass().getName());
					
					return;
				}
				else if(this.getFloorsArray().size()>1){
					String problem = "Perhaps elevator is neither going up or down... - Down: " + this.goingDown + ", Up: " + this.goingUp;
					this.getCentralDispatcher().insertIntoDebuggingTable(this.getCentralDispatcher().getRunNumber(),this.getCentralDispatcher().getNumberOfFloors(),this.getCentralDispatcher().getNumberOfElevators(),problem,this.getCentralDispatcher().getCurrentTime()/1000,this.getClass().getName());
					
				}
				else if(this.getFloorsArray().size() == 1){
					if(this.getFloorsArray().get(0) == this.getCurrentFloor()){
						String problem = "Interesting...elevator # " + this.getElevatorNumberId() + " - Perhaps elevator is neither going up or down... - Down: " + this.goingDown + ", Up: " + this.goingUp;
						this.getCentralDispatcher().insertIntoDebuggingTable(this.getCentralDispatcher().getRunNumber(),this.getCentralDispatcher().getNumberOfFloors(),this.getCentralDispatcher().getNumberOfElevators(),problem,this.getCentralDispatcher().getCurrentTime()/1000,this.getClass().getName());
						
					}
					else{
						String problem = "Not good...elevator # " + this.getElevatorNumberId();
						this.getCentralDispatcher().insertIntoDebuggingTable(this.getCentralDispatcher().getRunNumber(),this.getCentralDispatcher().getNumberOfFloors(),this.getCentralDispatcher().getNumberOfElevators(),problem,this.getCentralDispatcher().getCurrentTime()/1000,this.getClass().getName());
						
					}
				}
				else{
					String problem = "Double Interesting...elevator # " + this.getElevatorNumberId();
					this.getCentralDispatcher().insertIntoDebuggingTable(this.getCentralDispatcher().getRunNumber(),this.getCentralDispatcher().getNumberOfFloors(),this.getCentralDispatcher().getNumberOfElevators(),problem,this.getCentralDispatcher().getCurrentTime()/1000,this.getClass().getName());
					
				}
			}
			
			if(this.getCurrentFloor() != this.getFloorsArray().get(0)){
				String problem = "Ooo boy, current floor is not the same as what it should be..";
				this.getCentralDispatcher().insertIntoDebuggingTable(this.getCentralDispatcher().getRunNumber(),this.getCentralDispatcher().getNumberOfFloors(),this.getCentralDispatcher().getNumberOfElevators(),problem,this.getCentralDispatcher().getCurrentTime()/1000,this.getClass().getName());
			}
			isStoppedOnCurrentFloor = true;
		//	this.getCentralDispatcher().getEa().paint(g, this.getDrawWidth(), (int)(this.getDrawHeight()*this.getCurrentFloor() + this.getHeightConstant()), this.getDrawElevatorWidth(), this.getDrawElevatorHeight(), Color.green.brighter(), ((Integer)(this.getCapacity()-this.getCurrentNumberOfPeopleSpaces())).toString());
			this.getFloorsArray().remove(0);
			
			//we don't know what the next floor is yet...we have to load passengers and see what their requests are!
			
			if(this.getFloorsArray().size()>0){
				this.setNextFloor(this.getFloorsArray().get(0));
			} else {
			this.setNextFloor(-1);
			}
			
			if(this.getElevatorNumberId() == 1){
				System.out.println("");
				System.out.println("Elevator #: " + this.getElevatorNumberId());
				System.out.println("Elevator direction up: " + this.goingUp);
				System.out.println("Elevator direction down: " + this.goingDown);
				System.out.println("Current Floor: " + this.getCurrentFloor());
				System.out.println("Floor Array: " + this.getFloorsArray());
				System.out.println("Next Floor: " + this.getNextFloor());
				}
			
			
			//UNLOAD PASSENGERS
			for(int i = 0; i < this.getGroupsOfPassengers().size(); i++){
				if(this.getGroupsOfPassengers().get(i).getDesiredFloor() == this.getCurrentFloor() && this.getGroupsOfPassengers().get(i).isGotServedByElevator() == false){
					this.getGroupsOfPassengers().get(i).setCumulativeTimeOfRidingElevator(System.nanoTime()/1000000 - this.getGroupsOfPassengers().get(i).getTimeFirstBoarding());
					this.getGroupsOfPassengers().get(i).setGotServedByElevator(true);
					for(int j = 0; j < this.getGroupsOfPassengers().get(i).getNumberOfPeopleInGroup(); j++){
						
						Thread.sleep((long)(this.getLoadUnloadTimePerPassenger()/this.getTimeFactor()));
					}
			     }
			}
			
			FloorOfBuilding fob = this.getCentralDispatcher().getFloorArray()[this.getCurrentFloor()];
			
			if(this.getCurrentNumberOfPeopleSpaces() == this.getCapacity()){
				//we have an empty elevator! but we can't set untasked until we load ppl
				this.goingUp = false;
				this.goingDown = false;
			}
			
			
			//LOAD PASSENGERS
			int countPplWaiting = fob.getCurrentNumberWaiting();
			int countPplWaitingUp = fob.getCurrentNumberWaitingToGoUp();
			int countPplWaitingDown = fob.getCurrentNumberWaitingToGoDown();
			
			int letterSize = (int)(Math.min(Math.max(11, 350 / this.getCentralDispatcher().getNumberOfFloors()),40));
			
			if(countPplWaiting == 0){
				//may still have a task if there are people in the elevator still, but there is nobody to load so we return.
				return;
			}
			//do something here if elevator is at capacity
			//need to add code that checks to see if people have arrived to the floor during the time period the elevator was loading people!!!!
					
			int k = this.getCurrentNumberOfPeopleSpaces();
	
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
					
			//		this.getCentralDispatcher().getEa().paint(g, this.getDrawWidth(), (int)(this.getDrawHeight()*this.getCurrentFloor() + this.getHeightConstant()), this.getDrawElevatorWidth(), this.getDrawElevatorHeight(), Color.cyan, ((Integer)(this.getCapacity()-this.getCurrentNumberOfPeopleSpaces())).toString());
					
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
					
			//what to do with remaining people on the floor that didn't/couldn't get served by this current elevator:
			countPplWaiting = fob.getCurrentNumberWaiting();
			
			if(countPplWaiting > 0){
				
				boolean goingUpNewGroup = false;
				boolean goingDownNewGroup = false;
				
				for(int i = 0; i < fob.getArrivalGroupArray().size(); i++){
					if(fob.getArrivalGroupArray().get(i).isWaiting() == true){
						boolean add = true;
						if(!goingUpNewGroup && fob.getArrivalGroupArray().get(i).getDirection().equals("up")){
							for(int j = 0; j < this.getCentralDispatcher().getRequestArray().size(); j++){
								if(this.getCentralDispatcher().getRequestArray().size() > 1 && this.getCentralDispatcher().getRequestArray().get(j).getStartFloor() == fob.getArrivalGroupArray().get(i).getStartFloor()){
									
									if(fob.getArrivalGroupArray().get(i).getStartFloor() != this.getCurrentFloor()){
										String problem = "We have a serious problem with start floor of the customer not corresponding to the elevator's current floor that customer wants to load onto!";
										this.getCentralDispatcher().insertIntoDebuggingTable(this.getCentralDispatcher().getRunNumber(),this.getCentralDispatcher().getNumberOfFloors(),this.getCentralDispatcher().getNumberOfElevators(),problem,this.getCentralDispatcher().getCurrentTime()/1000,this.getClass().getName());
									}
									add = false;
									break;
								}
							}
							if(add == true){
							this.getCentralDispatcher().getRequestArray().add(fob.getArrivalGroupArray().get(i));
							goingUpNewGroup = true;
							}
						}
						if(!goingDownNewGroup && fob.getArrivalGroupArray().get(i).getDirection().equals("down")){
							for(int j = 0; j < this.getCentralDispatcher().getRequestArray().size(); j++){
								if(this.getCentralDispatcher().getRequestArray().size() > 1 && this.getCentralDispatcher().getRequestArray().get(j).getStartFloor() == fob.getArrivalGroupArray().get(i).getStartFloor()){
									add = false;
									break;
								}
							}
							if(add==true){
						   this.getCentralDispatcher().getRequestArray().add(fob.getArrivalGroupArray().get(i));
						   goingDownNewGroup = true;
							}
						}
						//if a group is found to be going down and a group is found to be going up, no need to keep looping through the rest of the people waiting, an up and down request has been sent to dispatcher
						if(goingUpNewGroup && goingDownNewGroup){
							break;
						}
					}
				}
			}
			
			Integer countPplWaiting2 = fob.getCurrentNumberWaiting();
			int countPplWaitingUp2 = fob.getCurrentNumberWaitingToGoUp();
			int countPplWaitingDown2 = fob.getCurrentNumberWaitingToGoDown();
			
		//	this.getCentralDispatcher().getEa().paintOver(g, this.getCurrentFloor());
		//	this.getCentralDispatcher().getEa().paint(g, this.getCurrentFloor(),countPplWaiting2,20,letterSize);
		//	this.getCentralDispatcher().getEa().paint(g,this.getCurrentFloor(),countPplWaitingUp2,30,(int)(letterSize*1.1));
		//	this.getCentralDispatcher().getEa().paint(g,this.getCurrentFloor(),countPplWaitingDown2,40,(int)(letterSize*1.2));
		}
	}
}