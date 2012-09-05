package start;


import java.awt.Graphics;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.SwingWorker;

public class CentralDispatcher {

	private int runNumber;
	private volatile MemoryMgt memoryMgt = new MemoryMgt(0);
	private int numberOfFloors;
	private boolean[] isSomeoneWaitingOnFloor;
	private volatile double[] currentTimeOfWaiting;
	public static int numberOfElevators;
	private volatile Elevator[] elevatorArray;
	private volatile FloorOfBuilding[] floorArray;
	private volatile Thread[] elevatorThreadArray;
	private volatile Thread[] floorThreadArray;
	private boolean firstLoop = true;
	private boolean keepLooping = true;
	private volatile double currentTime;
	private volatile Vector<ArrivalGroup> requestArray;
	private int integerOne = 3;
	private int countNull = 0;
	private int countOccurencesOfElevatorOnSameFloor = 0;
	private int countOccurencesOfElevatorOfRestrictiveUntasked = 0;
	private int countOccurencesOfElevatorAlreadyGoingToFloor = 0;
	private int countOccurencesOfElevatorGoingPastFloor = 0;
	private int countOccurencesFindClosestUntaskedElevator = 0;
	private int countOccurencesOfElevatorAppending = 0;
	private volatile ElevatorAnimation ea = null;
	private volatile Integer[] distributionOfUntaskedElevators;
	private volatile double timeFactor;
	private volatile IndividualSimulationRunData isrd = null;
	private int numberOfEmptySpacesToUseSameFloorVariable; //1
	private int numberOfEmptySpacesToUseGoingToVariable;  //2
	private int numberOfEmptySpacesToUsePassingByVariable;  //3
	private int capacityThresholdVariable;  //4
	private int numberOfFloorsDifference;  //5
	private int maxDistanceForUntaskedVariable;  //6
	private int countUntaskedVariable; //7
	private int appendDistanceVariable; //8
	private int distanceAlreadyGoingVariable; //9
	private volatile SQLDataServer sds = null;
	private volatile int capacityOfElevator;
	private volatile long loadUnloadTimePerPassenger = 2000;
	private Date startDate = null;
	private Date endDate = null;
	private volatile double startOfSim;
	private double lengthOfSim = 1500000;
	private ElevatorComponentAnimation eca = null;

	public long getLoadUnloadTimePerPassenger() {
		return loadUnloadTimePerPassenger;
	}

	public void setLoadUnloadTimePerPassenger(long loadUnloadTimePerPassenger) {
		this.loadUnloadTimePerPassenger = loadUnloadTimePerPassenger;
	}

	public ElevatorComponentAnimation getEca() {
		return eca;
	}

	public void setEca(ElevatorComponentAnimation eca) {
		this.eca = eca;
	}

	public double getLengthOfSim() {
		return lengthOfSim;
	}

	public void setLengthOfSim(double lengthOfSim) {
		this.lengthOfSim = lengthOfSim;
	}

	public double getStartOfSim() {
		return startOfSim;
	}

	public void setStartOfSim(double startOfSim) {
		this.startOfSim = startOfSim;
	}

	public String getEndDate() {
		return this.endDate.toString().replace("EDT", "").replace(" ", "");
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public synchronized SQLDataServer getSds() {
		return sds;
	}

	public synchronized void setSds(SQLDataServer sds) {
		this.sds = sds;
	}

	public int getCapacityOfElevator() {
		return capacityOfElevator;
	}

	public void setCapacityOfElevator(int capacityOfElevator) {
		this.capacityOfElevator = capacityOfElevator;
	}

	public synchronized int getRunNumber() {
		return runNumber;
	}

	public synchronized void setRunNumber(int runNumber) {
		this.runNumber = runNumber;
	}

	public synchronized int getCountOccurencesOfElevatorOfRestrictiveUntasked() {
		return countOccurencesOfElevatorOfRestrictiveUntasked;
	}

	public synchronized void setCountOccurencesOfElevatorOfRestrictiveUntasked(
			int countOccurencesOfElevatorOfRestrictiveUntasked) {
		this.countOccurencesOfElevatorOfRestrictiveUntasked = countOccurencesOfElevatorOfRestrictiveUntasked;
	}

	public synchronized int getAppendDistanceVariable() {
		return appendDistanceVariable;
	}

	public synchronized void setAppendDistanceVariable(int appendDistanceVariable) {
		this.appendDistanceVariable = appendDistanceVariable;
	}

	public synchronized int getDistanceAlreadyGoingVariable() {
		return distanceAlreadyGoingVariable;
	}

	public synchronized  void setDistanceAlreadyGoingVariable(int distanceAlreadyGoingVariable) {
		this.distanceAlreadyGoingVariable = distanceAlreadyGoingVariable;
	}
	public synchronized int getCountOccurencesOfElevatorAppending() {
		return countOccurencesOfElevatorAppending;
	}

	public synchronized void setCountOccurencesOfElevatorAppending(
			int countOccurencesOfElevatorAppending) {
		this.countOccurencesOfElevatorAppending = countOccurencesOfElevatorAppending;
	}

	public synchronized int getNumberOfEmptySpacesToUseSameFloorVariable() {
		return numberOfEmptySpacesToUseSameFloorVariable;
	}

	public synchronized void setNumberOfEmptySpacesToUseSameFloorVariable(
			int numberOfEmptySpacesToUseSameFloorVariable) {
		this.numberOfEmptySpacesToUseSameFloorVariable = numberOfEmptySpacesToUseSameFloorVariable;
	}

	public synchronized int getNumberOfEmptySpacesToUseGoingToVariable() {
		return numberOfEmptySpacesToUseGoingToVariable;
	}

	public synchronized void setNumberOfEmptySpacesToUseGoingToVariable(
			int numberOfEmptySpacesToUseGoingToVariable) {
		this.numberOfEmptySpacesToUseGoingToVariable = numberOfEmptySpacesToUseGoingToVariable;
	}

	public synchronized int getNumberOfEmptySpacesToUsePassingByVariable() {
		return numberOfEmptySpacesToUsePassingByVariable;
	}

	public synchronized void setNumberOfEmptySpacesToUsePassingByVariable(
			int numberOfEmptySpacesToUsePassingByVariable) {
		this.numberOfEmptySpacesToUsePassingByVariable = numberOfEmptySpacesToUsePassingByVariable;
	}

	public synchronized int getCapacityThresholdVariable() {
		return capacityThresholdVariable;
	}

	public synchronized void setCapacityThresholdVariable(int capacityThresholdVariable) {
		this.capacityThresholdVariable = capacityThresholdVariable;
	}

	public synchronized int getNumberOfFloorsDifference() {
		return numberOfFloorsDifference;
	}

	public synchronized void setNumberOfFloorsDifference(int numberOfFloorsDifference) {
		this.numberOfFloorsDifference = numberOfFloorsDifference;
	}

	public synchronized int getMaxDistanceForUntaskedVariable() {
		return maxDistanceForUntaskedVariable;
	}

	public synchronized void setMaxDistanceForUntaskedVariable(int maxDistanceForUntaskedVariable) {
		this.maxDistanceForUntaskedVariable = maxDistanceForUntaskedVariable;
	}

	public synchronized int getCountUntaskedVariable() {
		return countUntaskedVariable;
	}

	public synchronized void setCountUntaskedVariable(int countUntaskedVariable) {
		this.countUntaskedVariable = countUntaskedVariable;
	}
	public synchronized double getTimeFactor() {
		return timeFactor;
	}

	public synchronized void setTimeFactor(double timeFactor) {
		this.timeFactor = timeFactor;
	}

	public synchronized IndividualSimulationRunData getIsrd() {
		return isrd;
	}

	public synchronized void setIsrd(IndividualSimulationRunData isrd) {
		this.isrd = isrd;
	}

	public synchronized Integer[] getDistributionOfUntaskedElevators() {
		return distributionOfUntaskedElevators;
	}

	public synchronized void setDistributionOfUntaskedElevators(
			Integer[] distributionOfUntaskedElevators) {
		this.distributionOfUntaskedElevators = distributionOfUntaskedElevators;
	}

	public synchronized ElevatorAnimation getEa() {
		return ea;
	}

	public synchronized void setEa(ElevatorAnimation ea) {
		this.ea = ea;
	}

	public synchronized MemoryMgt getMemoryMgt() {
		return memoryMgt;
	}

	public synchronized void setMemoryMgt(MemoryMgt memoryMgt) {
		this.memoryMgt = memoryMgt;
	}

	public synchronized int getIntegerOne() {
		return integerOne;
	}

	public synchronized void setIntegerOne(int integerOne) {
		this.integerOne = integerOne;
	}

	public synchronized int getCountNull() {
		return countNull;
	}

	public synchronized void setCountNull(int countNull) {
		this.countNull = countNull;
	}

	public synchronized int getCountOccurencesFindClosestUntaskedElevator() {
		return countOccurencesFindClosestUntaskedElevator;
	}

	public synchronized void setCountOccurencesFindClosestUntaskedElevator(
			int countOccurencesFindClosestUntaskedElevator) {
		this.countOccurencesFindClosestUntaskedElevator = countOccurencesFindClosestUntaskedElevator;
	}

	public synchronized int getCountOccurencesOfElevatorOnSameFloor() {
		return countOccurencesOfElevatorOnSameFloor;
	}

	public synchronized void setCountOccurencesOfElevatorOnSameFloor(
			int countOccurencesOfElevatorOnSameFloor) {
		this.countOccurencesOfElevatorOnSameFloor = countOccurencesOfElevatorOnSameFloor;
	}

	public synchronized int getCountOccurencesOfElevatorOf50PercentUntasked() {
		return countOccurencesOfElevatorOfRestrictiveUntasked;
	}

	public synchronized void setCountOccurencesOfElevatorOf50PercentUntasked(
			int countOccurencesOfElevatorOf50PercentUntasked) {
		this.countOccurencesOfElevatorOfRestrictiveUntasked = countOccurencesOfElevatorOf50PercentUntasked;
	}

	public synchronized int getCountOccurencesOfElevatorAlreadyGoingToFloor() {
		return countOccurencesOfElevatorAlreadyGoingToFloor;
	}

	public synchronized void setCountOccurencesOfElevatorAlreadyGoingToFloor(
			int countOccurencesOfElevatorAlreadyGoingToFloor) {
		this.countOccurencesOfElevatorAlreadyGoingToFloor = countOccurencesOfElevatorAlreadyGoingToFloor;
	}

	public synchronized int getCountOccurencesOfElevatorGoingPastFloor() {
		return countOccurencesOfElevatorGoingPastFloor;
	}

	public synchronized void setCountOccurencesOfElevatorGoingPastFloor(
			int countOccurencesOfElevatorGoingPastFloor) {
		this.countOccurencesOfElevatorGoingPastFloor = countOccurencesOfElevatorGoingPastFloor;
	}

	public synchronized int getNumberOfFloors() {
		return numberOfFloors;
	}

	public synchronized void setNumberOfFloors(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}

	public synchronized boolean[] getIsSomeoneWaitingOnFloor() {
		return isSomeoneWaitingOnFloor;
	}

	public synchronized void setIsSomeoneWaitingOnFloor(
			boolean[] isSomeoneWaitingOnFloor) {
		this.isSomeoneWaitingOnFloor = isSomeoneWaitingOnFloor;
	}

	public synchronized double[] getCurrentTimeOfWaiting() {
		return currentTimeOfWaiting;
	}

	public synchronized void setCurrentTimeOfWaiting(
			double[] currentTimeOfWaiting) {
		this.currentTimeOfWaiting = currentTimeOfWaiting;
	}

	public synchronized int getNumberOfElevators() {
		return numberOfElevators;
	}

	public synchronized void setNumberOfElevators(int numberOfElevators) {
		this.numberOfElevators = numberOfElevators;
	}

	public synchronized Elevator[] getElevatorArray() {
		return elevatorArray;
	}

	public synchronized void setElevatorArray(Elevator[] elevatorArray) {
		this.elevatorArray = elevatorArray;
	}

	public synchronized FloorOfBuilding[] getFloorArray() {
		return floorArray;
	}

	public synchronized void setFloorArray(FloorOfBuilding[] floorArray) {
		this.floorArray = floorArray;
	}

	public synchronized Thread[] getElevatorThreadArray() {
		return elevatorThreadArray;
	}

	public synchronized void setElevatorThreadArray(Thread[] elevatorThreadArray) {
		this.elevatorThreadArray = elevatorThreadArray;
	}

	public synchronized Thread[] getFloorThreadArray() {
		return floorThreadArray;
	}

	public synchronized void setFloorThreadArray(Thread[] floorThreadArray) {
		this.floorThreadArray = floorThreadArray;
	}

	public synchronized boolean isFirstLoop() {
		return firstLoop;
	}

	public synchronized void setFirstLoop(boolean firstLoop) {
		this.firstLoop = firstLoop;
	}

	public synchronized boolean isKeepLooping() {
		return keepLooping;
	}

	public synchronized void setKeepLooping(boolean keepLooping) {
		this.keepLooping = keepLooping;
	}

	public synchronized double getCurrentTime() {
		return currentTime;
	}

	public synchronized void setCurrentTime(double currentTime) {
		this.currentTime = currentTime;
	}

	public synchronized Vector<ArrivalGroup> getRequestArray() {
		return requestArray;
	}
	
	public synchronized ArrivalGroup getNextRequestSync(){
		if(this.getRequestArray().size() > 0){
			return this.getRequestArray().get(0);
		}
		return null;
	}

	public synchronized void setRequestArray(
			Vector<ArrivalGroup> requestArray) {
		this.requestArray = requestArray;
	}

	public CentralDispatcher(int runNumber, int numberOfFloors, int numberOfElevators, int capacityVariable, int distanceAlreadyGoingVariable, int appendDistanceVariable,
			int countUntaskedVariable, int maxDistanceForUntaskedVariable,
			int numberOfFloorsDifference,
			int numberOfEmptySpacesToUseGoingToVariable,
			int numberOfEmptySpacesToUsePassingByVariable,
			int numberOfEmptySpacesToUseSameFloorVariable,
			int capacityThresholdVariable, double timeFactor)
			throws InterruptedException {
		
		this.setStartDate(new Date());
		this.numberOfFloors = numberOfFloors;
		this.numberOfElevators = numberOfElevators;
		this.capacityOfElevator = capacityVariable;
		this.timeFactor = timeFactor;
		this.numberOfEmptySpacesToUseGoingToVariable = numberOfEmptySpacesToUseGoingToVariable;
		this.capacityThresholdVariable = capacityThresholdVariable;
		this.numberOfEmptySpacesToUseSameFloorVariable = numberOfEmptySpacesToUseSameFloorVariable;
		this.numberOfEmptySpacesToUsePassingByVariable = numberOfEmptySpacesToUsePassingByVariable;
		this.numberOfFloorsDifference = numberOfFloorsDifference;
		this.maxDistanceForUntaskedVariable = maxDistanceForUntaskedVariable;
		this.countUntaskedVariable = countUntaskedVariable;
		this.appendDistanceVariable = appendDistanceVariable;
		this.distanceAlreadyGoingVariable = distanceAlreadyGoingVariable;
		this.runNumber = runNumber;

		this.sds = new SQLDataServer();
		
		initializeTables();
		
		distributionOfUntaskedElevators = new Integer[numberOfFloors];
		for (int i = 0; i < this.distributionOfUntaskedElevators.length; i++) {
			this.distributionOfUntaskedElevators[i] = 0;
		}

		eca = new ElevatorComponentAnimation(this);
		eca.setVisible(true);
		
		Thread.sleep(2500);
		
		initialize();
		
		Thread.sleep(2500);

	
		while (keepLooping) {
			loop();
		}

//		SwingWorker sw = new SwingWork(this);
//		sw.execute();
		setEndDate(new Date());
		isrd = new IndividualSimulationRunData(this);
		
	}
	
	private void initializeTables(){
		String sql = "truncate table debugging_table";
		sds.execute(sql);
	}

	public void insertIntoDebuggingTable(int runNumber, int numberOfFloors, int numOfElevators,String problem, double currentTime, String classname) {
		String sql = "insert into debugging_table values('" + runNumber + "','" + numberOfFloors + "','" + numOfElevators + "','" + problem + "','" + currentTime + "','" + classname + "')";
		sds.execute(sql);
	}

	private void initialize() {
		this.elevatorArray = new Elevator[numberOfElevators];
		this.elevatorThreadArray = new Thread[numberOfElevators];
		this.floorArray = new FloorOfBuilding[numberOfFloors];
		this.floorThreadArray = new Thread[numberOfFloors];
		this.requestArray = new Vector<ArrivalGroup>();

		for (int i = 0; i < numberOfElevators; i++) {

			elevatorArray[i] = new Elevator(this, 0, i, this.getCapacityOfElevator(), 6, 2, 3,
					this.timeFactor, 2000, 2000, this.capacityThresholdVariable);
			elevatorThreadArray[i] = new Thread(elevatorArray[i]);
		}

		for (int j = 0; j < numberOfFloors; j++) {

			floorArray[j] = new FloorOfBuilding(this, 60000, 3, j,
					this.timeFactor);
			floorThreadArray[j] = new Thread(floorArray[j]);
		}

		this.setStartOfSim(System.nanoTime() / 1000000);

		for (int i = 0; i < numberOfElevators; i++) {
			elevatorThreadArray[i].start();
		}
		for (int i = 0; i < numberOfFloors; i++) {
			floorThreadArray[i].start();
		}
	}

	private ArrivalGroup getNextRequest() throws InterruptedException {
		ArrivalGroup ag = null;
		if (!this.getRequestArray().isEmpty()
				&& this.getRequestArray().get(0) != null) {
			ag = this.getRequestArray().get(0);
		} else {
			Thread.sleep((long) (100 / this.timeFactor + 5));
			getNextRequest();
		}
		return ag;
	}

	private double getPercentageOfUntaskedElevators() {

		int count = 0;
		for (int i = 0; i < this.getNumberOfElevators(); i++) {
			if (!this.getElevatorArray()[i].isHasTask()) {
				count++;
			}
		}
		double percentage = (double) count
				/ (double) this.getNumberOfElevators();
		return percentage;
	}

	private int getNumberOfUntaskedElevators() {

		int count = 0;
		for (int i = 0; i < this.getNumberOfElevators(); i++) {
			if (!this.getElevatorArray()[i].isHasTask()) {
				count++;
			}
		}
		return count;
	}

	public Elevator findClosestUntaskedElevator(int nextFloor) {

		int maxDistance = this.getNumberOfFloors();
		Elevator elevatorToUse = null;

		for (int i = 0; i < this.getElevatorArray().length; i++) {

			if (!this.getElevatorArray()[i].isHasTask()) {
				if (Math.abs(this.getElevatorArray()[i].getCurrentFloor()
						- nextFloor) < maxDistance) {
					maxDistance = Math.abs(this.getElevatorArray()[i]
							.getCurrentFloor()
							- nextFloor);
					elevatorToUse = this.getElevatorArray()[i];
				}
			}

		}
		if (elevatorToUse != null
				&& maxDistance < this.maxDistanceForUntaskedVariable) {
			elevatorToUse.setHasTask(true);
			return elevatorToUse;
		}

		return elevatorToUse;
	}

	public Elevator findClosestUntaskedElevatorLessRestrictive(int nextFloor) {

		int maxDistance = this.getNumberOfFloors();
		Elevator elevatorToUse = null;

		for (int i = 0; i < this.getElevatorArray().length; i++) {

			if (!this.getElevatorArray()[i].isHasTask()) {
				if (Math.abs(this.getElevatorArray()[i].getCurrentFloor()
						- nextFloor) < maxDistance) {
					maxDistance = Math.abs(this.getElevatorArray()[i]
							.getCurrentFloor()
							- nextFloor);
					elevatorToUse = this.getElevatorArray()[i];
				}
			}

		}
		if (elevatorToUse != null /*
								 * && maxDistance <
								 * this.maxDistanceForUntaskedVariable
								 */) {
			elevatorToUse.setHasTask(true);
			return elevatorToUse;
		}

		return elevatorToUse;
	}

	private Elevator seeIfElevatorIsAlreadyGoingtoFloor(int nextFloor) {
	
		Elevator elevatorToUse = null;
		List<Integer> temp;
		for (int i = 0; i < this.getElevatorArray().length; i++) {
			temp = this.getElevatorArray()[i].getFloorsArray();
			if ((this.getElevatorArray()[i].isHasTask() || this
					.getElevatorArray()[i].getFloorsArray().size() > 1)
					&& this.getElevatorArray()[i]
							.getCurrentNumberOfPeopleSpaces() > this.numberOfEmptySpacesToUseGoingToVariable) {
				if (temp.contains(nextFloor) && Math.abs(this.getElevatorArray()[i].getCurrentFloor()- nextFloor) < distanceAlreadyGoingVariable) {
					return elevatorToUse = this.getElevatorArray()[i];
				}
			}
		}
		return elevatorToUse;
	}

	private Elevator seeIfElevatorIsPassingByFloor(int nextFloor) {

		Elevator elevatorToUse = null;

		for (int i = 0; i < this.getElevatorArray().length; i++) {
			if (this.getElevatorArray()[i].isHasTask()
					&& this.getElevatorArray()[i].isAvailable()
					&& (this.getElevatorArray()[i].getCurrentFloor() + this.numberOfFloorsDifference) < nextFloor
					&& this.getElevatorArray()[i].isGoingUp()
					&& this.getElevatorArray()[i]
							.getCurrentNumberOfPeopleSpaces() > this.numberOfEmptySpacesToUsePassingByVariable) {
				return elevatorToUse = this.getElevatorArray()[i];
			} else if (this.getElevatorArray()[i].isHasTask()
					&& this.getElevatorArray()[i].isAvailable()
					&& (this.getElevatorArray()[i].getCurrentFloor() - this.numberOfFloorsDifference) > nextFloor
					&& this.getElevatorArray()[i].isGoingDown()
					&& this.getElevatorArray()[i]
							.getCurrentNumberOfPeopleSpaces() > this.numberOfEmptySpacesToUsePassingByVariable) {
				
				return elevatorToUse = this.getElevatorArray()[i];
			}
		}
		return elevatorToUse;
	}

	private Elevator findElevatorToAppendFloorTo(int nextFloor) {

		Elevator elevatorToUse = null;

		for (int i = 0; i < this.getElevatorArray().length; i++) {
			if (this.getElevatorArray()[i].isHasTask()
					&& this.getElevatorArray()[i].isAvailable()
					&& (this.getElevatorArray()[i].getCurrentFloor() + this.numberOfFloorsDifference) < nextFloor
					&& this.getElevatorArray()[i].isGoingUp()
					&& this.getElevatorArray()[i]
							.getCurrentNumberOfPeopleSpaces() > this.numberOfEmptySpacesToUsePassingByVariable) {
				int size = this.getElevatorArray()[i].getFloorsArray().size();
				if(size > 0){
				if(this.getElevatorArray()[i].getFloorsArray().get(size-1) < nextFloor){
				return elevatorToUse = this.getElevatorArray()[i];
				}
				}
				
			} else if (this.getElevatorArray()[i].isHasTask()
					&& this.getElevatorArray()[i].isAvailable()
					&& (this.getElevatorArray()[i].getCurrentFloor() - this.numberOfFloorsDifference) > nextFloor
					&& this.getElevatorArray()[i].isGoingDown()
					&& this.getElevatorArray()[i]
							.getCurrentNumberOfPeopleSpaces() > this.numberOfEmptySpacesToUsePassingByVariable) {
				int size = this.getElevatorArray()[i].getFloorsArray().size();
				if(size > 0){
				if(this.getElevatorArray()[i].getFloorsArray().get(size-1) > nextFloor){
					return elevatorToUse = this.getElevatorArray()[i];
					}
				}
			}
		}
		return elevatorToUse;
	}

	private Elevator checkToSeeIfElevatorAlreadyOnSameFloor(int nextFloor) {
		Elevator elevatorToUse = null;

		// first we try to find an empty/untasked elevator to use on the current floor
		for (int i = 0; i < this.getElevatorArray().length; i++) {
			if (this.getElevatorArray()[i].getCurrentFloor() == nextFloor
					&& this.getElevatorArray()[i].isHasTask() == false
					&& this.getElevatorArray()[i].isStoppedOnCurrentFloor()
					&& this.getElevatorArray()[i]
							.getCurrentNumberOfPeopleSpaces() > this.numberOfEmptySpacesToUseSameFloorVariable) {

				if (this.getElevatorArray()[i].getCapacity() != this
						.getElevatorArray()[i].getCurrentNumberOfPeopleSpaces()) {
					
					String problem = "Elevator capacity problem!!! Elevator has no task, but has people in it!!!";
					insertIntoDebuggingTable(this.getRunNumber(),this.getNumberOfFloors(),this.getNumberOfElevators(),problem,this.getCurrentTime()/1000,this.getClass().getName());
				}

				elevatorToUse = this.getElevatorArray()[i];
				elevatorToUse.setHasTask(true);
				return elevatorToUse;
			}
		}

		// if there are no empty/untasked elevators on the current floor, passengers will look for a tasked elevator
		for (int i = 0; i < this.getElevatorArray().length; i++) {
			if (this.getElevatorArray()[i].getCurrentFloor() == nextFloor
					&& this.getElevatorArray()[i].isStoppedOnCurrentFloor()
					&& this.getElevatorArray()[i]
							.getCurrentNumberOfPeopleSpaces() > this.numberOfEmptySpacesToUseSameFloorVariable) {
				elevatorToUse = this.getElevatorArray()[i];
				elevatorToUse.setHasTask(true);
				return elevatorToUse;
			}
		}
		return elevatorToUse;
	}
	

	public void loop() throws InterruptedException {

		if (memoryMgt.getCurrentPercentage() > 75) {
			memoryMgt.cleanMemory();
		}

		this.setCurrentTime(System.nanoTime() / 1000000);
		if (this.getCurrentTime() - this.getStartOfSim() > (int) (this.getLengthOfSim() / this.timeFactor)) {
			this.setKeepLooping(false);
			return;
		}
		
		//make sure all floors with people waiting are assigned an elevator
		
//		for(int i = 0; i < this.getFloorArray().length; i++){
//			if(this.getFloorArray()[i].findOutIfThereIsGroupWaiting()){
//				Vector<Integer> temp = null;
//				for(int j = 0; j < this.getElevatorArray().length; j++){
//					temp = this.getElevatorArray()[j].getFloorsArray();
//					if(temp.contains(this.getFloorArray()[i].getFloorNumber())){
//						break;
//					}
//					this.getRequestArray().add(this.getFloorArray()[i].getArrivalGroupArray().get(0));
//					String direction = this.getFloorArray()[i].getArrivalGroupArray().get(0).getDirection();
//					if(this.getFloorArray()[i].getArrivalGroupArray().size()>1){
//						for(int k = 1; k < this.getFloorArray()[i].getArrivalGroupArray().size(); k++){
//							if(this.getFloorArray()[i].getArrivalGroupArray().get(k).isWaiting() && !this.getFloorArray()[i].getArrivalGroupArray().get(k).getDirection().equals(direction)){
//								this.getRequestArray().add(this.getFloorArray()[i].getArrivalGroupArray().get(k));
//								break;
//							}
//						}
//					}
//				}
//			}
//		}
		
		//if an elevator is near the top or bottom floor and is going up or down respectively, it can pass a floor then come back and swing to get it on the way back to the middle floors

		ArrivalGroup ag = getNextRequest();
		Elevator elevatorToUse = null;

		if (ag == null) {
			//countNull++;
			return;
		}

		// add ag on floor x + 1 to elevator that is going up to floor x, as
		// long as...conditions ABC are met...
		// add ag on floor x - 1 to elevator that is going down to floor x, as
		// long as...conditions ABC are met...

		// TYPE_ONE
		elevatorToUse = checkToSeeIfElevatorAlreadyOnSameFloor(ag
				.getStartFloor());
		if (elevatorToUse != null) {
			countOccurencesOfElevatorOnSameFloor++;
			elevatorToUse.setHasTask(true);
			this.getRequestArray().remove(0);
			return;
		}

		// TYPE_TWO
		int countUntasked = getNumberOfUntaskedElevators();
		if (countUntasked > this.countUntaskedVariable) {
			elevatorToUse = findClosestUntaskedElevator(ag.getStartFloor());
			if (elevatorToUse != null) {
				countOccurencesOfElevatorOfRestrictiveUntasked++;
				if(elevatorToUse.getCurrentFloor() != ag.getStartFloor()){
				elevatorToUse.getFloorsArray().add(ag.getStartFloor());	
				}
				this.getRequestArray().remove(0);
				elevatorToUse.setHasTask(true);
				return;
			}
		}

		// TYPE_THREE
		elevatorToUse = seeIfElevatorIsAlreadyGoingtoFloor(ag.getStartFloor());
		if (elevatorToUse != null) {
			countOccurencesOfElevatorAlreadyGoingToFloor++;
			this.getRequestArray().remove(0);
			return;
		}


		// TYPE_FOUR
		elevatorToUse = seeIfElevatorIsPassingByFloor(ag.getStartFloor());
		if (elevatorToUse != null) {
			countOccurencesOfElevatorGoingPastFloor++;
			// we must sort the FloorsArray then add at the right index!
			if (elevatorToUse.isAvailable()) {
				elevatorToUse.getFloorsArray().add(ag.getStartFloor());
				elevatorToUse.setHasTask(true);
				this.getRequestArray().remove(0);
				return;
			}
		}

		// TYPE_FIVE
		elevatorToUse = findElevatorToAppendFloorTo(ag.getStartFloor());
		if (elevatorToUse != null) {
			countOccurencesOfElevatorAppending++;
			// we must sort the FloorsArray then add at the right index!
			if (elevatorToUse.isAvailable()) {
				elevatorToUse.getFloorsArray().add(ag.getStartFloor());
				elevatorToUse.setHasTask(true);
				this.getRequestArray().remove(0);
				return;
			}
		}

		// TYPE_SIX
		elevatorToUse = findClosestUntaskedElevatorLessRestrictive(ag
				.getStartFloor());
		int count = 0;
		while (elevatorToUse == null) {
			count++;
			if (count > 6) {
				return;
			}
			elevatorToUse = findClosestUntaskedElevatorLessRestrictive(ag.getStartFloor());
		}
		if (elevatorToUse.getCurrentFloor() < ag.getStartFloor()) {
			elevatorToUse.setGoingUp(true);
			elevatorToUse.setGoingDown(false);
			elevatorToUse.getFloorsArray().add(ag.getStartFloor());
		} else if (elevatorToUse.getCurrentFloor() > ag.getStartFloor()) {
			elevatorToUse.setGoingDown(true);
			elevatorToUse.setGoingUp(false);
			elevatorToUse.getFloorsArray().add(ag.getStartFloor());
		} else if (elevatorToUse.getCurrentFloor() == ag.getStartFloor()) {
			
			String problem = "Closest UNTASKED elevator on same floor, but TYPE_SIX";
			insertIntoDebuggingTable(this.getRunNumber(),this.getNumberOfFloors(),this.getNumberOfElevators(),problem,this.getCurrentTime()/1000,this.getClass().getName());
	
			
			if (elevatorToUse.getCurrentFloor() > ag.getDesiredFloor()) {
				elevatorToUse.getFloorsArray().add(ag.getDesiredFloor());
				elevatorToUse.setGoingDown(true);
				elevatorToUse.setGoingUp(false);
			} else if (elevatorToUse.getCurrentFloor() < ag.getDesiredFloor()) {
				elevatorToUse.getFloorsArray().add(ag.getDesiredFloor());
				elevatorToUse.setGoingUp(true);
				elevatorToUse.setGoingDown(false);
			}

		} else {
			String problem = "We should never get to this point...";
			insertIntoDebuggingTable(this.getRunNumber(),this.getNumberOfFloors(),this.getNumberOfElevators(),problem,this.getCurrentTime()/1000,this.getClass().getName());
	
		}
		countOccurencesFindClosestUntaskedElevator++;
		elevatorToUse.setHasTask(true);
		this.getRequestArray().remove(0);
	}

	public String getStartDate() {
		// TODO Auto-generated method stub
		return this.startDate.toString().replace("EDT", "").replace(" ", "");
	}
	
	
	private class SwingWork extends SwingWorker<Void,Void>{
		
		private CentralDispatcher cd = null;
		
		public SwingWork(CentralDispatcher cd){
			this.cd = cd;
		}

		@Override
		protected Void doInBackground() throws Exception {
			while (keepLooping) {
				loop();
			}
			return null;
		}
		
		protected void done(){
			setEndDate(new Date());
			try {
				isrd = new IndividualSimulationRunData(cd);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
