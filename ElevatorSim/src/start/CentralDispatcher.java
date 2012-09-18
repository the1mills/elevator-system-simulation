package start;

import java.util.Date;
import java.util.List;
import java.util.Vector;


public class CentralDispatcher{


	private static int runNumber;
	private static volatile MemoryMgt memoryMgt = new MemoryMgt(0);
	private static int numberOfFloors;
	private static boolean[] isSomeoneWaitingOnFloor;
	private static volatile double[] currentTimeOfWaiting;
	public static int numberOfElevators;
	private static volatile Elevator[] elevatorArray;
	private static volatile FloorOfBuilding[] floorArray;
	private static volatile Thread[] elevatorThreadArray;
	private static volatile Thread[] floorThreadArray;
	private static boolean firstLoop = true;
	private static boolean keepLooping = true;
	private static volatile double currentTime;
	private static volatile Vector<ArrivalGroup> requestArray;
	private static int integerOne = 3;
	private static int countNull = 0;
	private static int countOccurencesOfElevatorOnSameFloor = 0;
	private static int countOccurencesOfElevatorOfRestrictiveUntasked = 0;
	private static int countOccurencesOfElevatorAlreadyGoingToFloor = 0;
	private static int countOccurencesOfElevatorGoingPastFloor = 0;
	private static int countOccurencesFindClosestUntaskedElevator = 0;
	private static int countOccurencesOfElevatorAppending = 0;
	private static volatile ElevatorAnimation ea = null;
	private static volatile Integer[] distributionOfUntaskedElevators;
	private static volatile double timeFactor;
	private static volatile IndividualSimulationRunData isrd = null;
	private static int numberOfEmptySpacesToUseSameFloorVariable; //1
	private static int numberOfEmptySpacesToUseGoingToVariable;  //2
	private static int numberOfEmptySpacesToUsePassingByVariable;  //3
	private static int capacityThresholdVariable;  //4
	private static int numberOfFloorsDifference;  //5
	private static int maxDistanceForUntaskedVariable;  //6
	private static int countUntaskedVariable; //7
	private static int appendDistanceVariable; //8
	private static int distanceAlreadyGoingVariable; //9
	static volatile SQLDataServer sds = null;
	private static volatile int capacityOfElevator; //10
	private static volatile long loadUnloadTimePerPassenger = 2000;
	private static Date startDate = null;
	private static Date endDate = null;
	private static volatile double startOfSim;
	private static double lengthOfSim = 1500000;
	private static volatile ElevatorComponentAnimation eca = null;
	private static boolean simulationPaused = false;
	public static volatile RequestArray ra = null;
	public static volatile Integer[][] floorTruth = null;
	

	public static synchronized boolean isSimulationPaused() {
		return simulationPaused;
	}
	

	public static synchronized RequestArray getRa() {
		return ra;
	}

	public static synchronized void setRa(RequestArray ra) {
		CentralDispatcher.ra = ra;
	}

	public static synchronized Integer[][] getFloorTruth() {
		return floorTruth;
	}

	public static synchronized void setFloorTruth(Integer[][] floorTruth) {
		CentralDispatcher.floorTruth = floorTruth;
	}

	public static synchronized void setSimulationPaused(boolean simulationPaused) throws InterruptedException {
		CentralDispatcher.simulationPaused = simulationPaused;
		if(CentralDispatcher.simulationPaused){
			for(int i = 0; i < CentralDispatcher.getFloorThreadArray().length; i++){
				CentralDispatcher.getFloorThreadArray()[i].sleep(50);	
			}
			for(int i = 0; i < CentralDispatcher.getElevatorThreadArray().length; i++){
				CentralDispatcher.getFloorThreadArray()[i].sleep(50);	
			}
		
		}
		else{
			for(int i = 0; i < CentralDispatcher.getFloorThreadArray().length; i++){
				CentralDispatcher.getFloorThreadArray()[i].interrupt();	
			}
			for(int i = 0; i < CentralDispatcher.getElevatorThreadArray().length; i++){
				CentralDispatcher.getFloorThreadArray()[i].interrupt();	
			}
			
		}
	}

	
	public static long getLoadUnloadTimePerPassenger() {
		return loadUnloadTimePerPassenger;
	}

	public static void setLoadUnloadTimePerPassenger(long loadUnloadTimePerPassenger) {
		CentralDispatcher.loadUnloadTimePerPassenger = loadUnloadTimePerPassenger;
	}

	public static String getStartDate() {
		return CentralDispatcher.startDate.toString().replace("EDT", "").replace(" ", "");
	}
	
	public synchronized static ElevatorComponentAnimation getEca() {
		return eca;
	}

	public synchronized  static void setEca(ElevatorComponentAnimation eca) {
		CentralDispatcher.eca = eca;
	}

	public static  double getLengthOfSim() {
		return lengthOfSim;
	}

	public static void setLengthOfSim(double lengthOfSim) {
		CentralDispatcher.lengthOfSim = lengthOfSim;
	}

	public static double getStartOfSim() {
		return startOfSim;
	}

	public static void setStartOfSim(double startOfSim) {
		CentralDispatcher.startOfSim = startOfSim;
	}

	public static  String getEndDate() {
		return CentralDispatcher.endDate.toString().replace("EDT", "").replace(" ", "");
	}

	public static void setEndDate(Date endDate) {
		CentralDispatcher.endDate = endDate;
	}

	public static void setStartDate(Date startDate) {
		CentralDispatcher.startDate = startDate;
	}

	public static synchronized SQLDataServer getSds() {
		return sds;
	}

	public static synchronized void setSds(SQLDataServer sds) {
		CentralDispatcher.sds = sds;
	}

	public static int getCapacityOfElevator() {
		return capacityOfElevator;
	}

	public static void setCapacityOfElevator(int capacityOfElevator) {
		CentralDispatcher.capacityOfElevator = capacityOfElevator;
	}

	public static synchronized Integer getRunNumber() {
		return (Integer)runNumber;
	}

	public static synchronized void setRunNumber(int runNumber) {
		CentralDispatcher.runNumber = runNumber;
	}

	public static synchronized int getCountOccurencesOfElevatorOfRestrictiveUntasked() {
		return countOccurencesOfElevatorOfRestrictiveUntasked;
	}

	public static synchronized void setCountOccurencesOfElevatorOfRestrictiveUntasked(
			int countOccurencesOfElevatorOfRestrictiveUntasked) {
		CentralDispatcher.countOccurencesOfElevatorOfRestrictiveUntasked = countOccurencesOfElevatorOfRestrictiveUntasked;
	}

	public static synchronized int getAppendDistanceVariable() {
		return appendDistanceVariable;
	}

	public static synchronized void setAppendDistanceVariable(int appendDistanceVariable) {
		CentralDispatcher.appendDistanceVariable = appendDistanceVariable;
	}

	public static synchronized int getDistanceAlreadyGoingVariable() {
		return distanceAlreadyGoingVariable;
	}

	public static synchronized  void setDistanceAlreadyGoingVariable(int distanceAlreadyGoingVariable) {
		CentralDispatcher.distanceAlreadyGoingVariable = distanceAlreadyGoingVariable;
	}
	public static synchronized int getCountOccurencesOfElevatorAppending() {
		return countOccurencesOfElevatorAppending;
	}

	public static synchronized void setCountOccurencesOfElevatorAppending(
			int countOccurencesOfElevatorAppending) {
		CentralDispatcher.countOccurencesOfElevatorAppending = countOccurencesOfElevatorAppending;
	}

	public static synchronized int getNumberOfEmptySpacesToUseSameFloorVariable() {
		return numberOfEmptySpacesToUseSameFloorVariable;
	}

	public static synchronized void setNumberOfEmptySpacesToUseSameFloorVariable(
			int numberOfEmptySpacesToUseSameFloorVariable) {
		CentralDispatcher.numberOfEmptySpacesToUseSameFloorVariable = numberOfEmptySpacesToUseSameFloorVariable;
	}

	public static synchronized int getNumberOfEmptySpacesToUseGoingToVariable() {
		return numberOfEmptySpacesToUseGoingToVariable;
	}

	public static synchronized void setNumberOfEmptySpacesToUseGoingToVariable(
			int numberOfEmptySpacesToUseGoingToVariable) {
		CentralDispatcher.numberOfEmptySpacesToUseGoingToVariable = numberOfEmptySpacesToUseGoingToVariable;
	}

	public static synchronized int getNumberOfEmptySpacesToUsePassingByVariable() {
		return numberOfEmptySpacesToUsePassingByVariable;
	}

	public static synchronized void setNumberOfEmptySpacesToUsePassingByVariable(
			int numberOfEmptySpacesToUsePassingByVariable) {
		CentralDispatcher.numberOfEmptySpacesToUsePassingByVariable = numberOfEmptySpacesToUsePassingByVariable;
	}

	public static synchronized int getCapacityThresholdVariable() {
		return capacityThresholdVariable;
	}

	public static synchronized void setCapacityThresholdVariable(int capacityThresholdVariable) {
		CentralDispatcher.capacityThresholdVariable = capacityThresholdVariable;
	}

	public static synchronized int getNumberOfFloorsDifference() {
		return numberOfFloorsDifference;
	}

	public static synchronized void setNumberOfFloorsDifference(int numberOfFloorsDifference) {
		CentralDispatcher.numberOfFloorsDifference = numberOfFloorsDifference;
	}

	public static synchronized int getMaxDistanceForUntaskedVariable() {
		return maxDistanceForUntaskedVariable;
	}

	public static synchronized void setMaxDistanceForUntaskedVariable(int maxDistanceForUntaskedVariable) {
		CentralDispatcher.maxDistanceForUntaskedVariable = maxDistanceForUntaskedVariable;
	}

	public static synchronized int getCountUntaskedVariable() {
		return countUntaskedVariable;
	}

	public static synchronized void setCountUntaskedVariable(int countUntaskedVariable) {
		CentralDispatcher.countUntaskedVariable = countUntaskedVariable;
	}
	public static synchronized double getTimeFactor() {
		return timeFactor;
	}

	public static synchronized void setTimeFactor(double timeFactor) {
		CentralDispatcher.timeFactor = timeFactor;
	}

	public static synchronized IndividualSimulationRunData getIsrd() {
		return isrd;
	}

	public static synchronized void setIsrd(IndividualSimulationRunData isrd) {
		CentralDispatcher.isrd = isrd;
	}

	public static synchronized Integer[] getDistributionOfUntaskedElevators() {
		return distributionOfUntaskedElevators;
	}

	public static synchronized void setDistributionOfUntaskedElevators(
			Integer[] distributionOfUntaskedElevators) {
		CentralDispatcher.distributionOfUntaskedElevators = distributionOfUntaskedElevators;
	}

	public static synchronized ElevatorAnimation getEa() {
		return ea;
	}

	public static synchronized void setEa(ElevatorAnimation ea) {
		CentralDispatcher.ea = ea;
	}

	public static synchronized MemoryMgt getMemoryMgt() {
		return memoryMgt;
	}

	public static synchronized void setMemoryMgt(MemoryMgt memoryMgt) {
		CentralDispatcher.memoryMgt = memoryMgt;
	}

	public static synchronized int getIntegerOne() {
		return integerOne;
	}

	public static synchronized void setIntegerOne(int integerOne) {
		CentralDispatcher.integerOne = integerOne;
	}

	public static synchronized int getCountNull() {
		return countNull;
	}

	public static synchronized void setCountNull(int countNull) {
		CentralDispatcher.countNull = countNull;
	}

	public static synchronized int getCountOccurencesFindClosestUntaskedElevator() {
		return countOccurencesFindClosestUntaskedElevator;
	}

	public static synchronized void setCountOccurencesFindClosestUntaskedElevator(
			int countOccurencesFindClosestUntaskedElevator) {
		CentralDispatcher.countOccurencesFindClosestUntaskedElevator = countOccurencesFindClosestUntaskedElevator;
	}

	public static synchronized int getCountOccurencesOfElevatorOnSameFloor() {
		return countOccurencesOfElevatorOnSameFloor;
	}

	public static synchronized void setCountOccurencesOfElevatorOnSameFloor(
			int countOccurencesOfElevatorOnSameFloor) {
		CentralDispatcher.countOccurencesOfElevatorOnSameFloor = countOccurencesOfElevatorOnSameFloor;
	}

	public static synchronized int getCountOccurencesOfElevatorOf50PercentUntasked() {
		return countOccurencesOfElevatorOfRestrictiveUntasked;
	}

	public static synchronized void setCountOccurencesOfElevatorOf50PercentUntasked(
			int countOccurencesOfElevatorOf50PercentUntasked) {
		CentralDispatcher.countOccurencesOfElevatorOfRestrictiveUntasked = countOccurencesOfElevatorOf50PercentUntasked;
	}

	public static synchronized int getCountOccurencesOfElevatorAlreadyGoingToFloor() {
		return countOccurencesOfElevatorAlreadyGoingToFloor;
	}

	public static synchronized void setCountOccurencesOfElevatorAlreadyGoingToFloor(
			int countOccurencesOfElevatorAlreadyGoingToFloor) {
		CentralDispatcher.countOccurencesOfElevatorAlreadyGoingToFloor = countOccurencesOfElevatorAlreadyGoingToFloor;
	}

	public static synchronized int getCountOccurencesOfElevatorGoingPastFloor() {
		return countOccurencesOfElevatorGoingPastFloor;
	}

	public static synchronized void setCountOccurencesOfElevatorGoingPastFloor(
			int countOccurencesOfElevatorGoingPastFloor) {
		CentralDispatcher.countOccurencesOfElevatorGoingPastFloor = countOccurencesOfElevatorGoingPastFloor;
	}

	public static synchronized int getNumberOfFloors() {
		return numberOfFloors;
	}

	public static synchronized void setNumberOfFloors(int numberOfFloors) {
		CentralDispatcher.numberOfFloors = numberOfFloors;
	}

	public static synchronized boolean[] getIsSomeoneWaitingOnFloor() {
		return isSomeoneWaitingOnFloor;
	}

	public static synchronized void setIsSomeoneWaitingOnFloor(
			boolean[] isSomeoneWaitingOnFloor) {
		CentralDispatcher.isSomeoneWaitingOnFloor = isSomeoneWaitingOnFloor;
	}

	public static synchronized double[] getCurrentTimeOfWaiting() {
		return currentTimeOfWaiting;
	}

	public static synchronized void setCurrentTimeOfWaiting(
			double[] currentTimeOfWaiting) {
		CentralDispatcher.currentTimeOfWaiting = currentTimeOfWaiting;
	}

	public static synchronized int getNumberOfElevators() {
		return numberOfElevators;
	}

	public static synchronized void setNumberOfElevators(int numberOfElevators) {
		CentralDispatcher.numberOfElevators = numberOfElevators;
	}

	public static synchronized Elevator[] getElevatorArray() {
		return elevatorArray;
	}

	public static synchronized void setElevatorArray(Elevator[] elevatorArray) {
		CentralDispatcher.elevatorArray = elevatorArray;
	}

	public static synchronized FloorOfBuilding[] getFloorArray() {
		return floorArray;
	}

	public static synchronized void setFloorArray(FloorOfBuilding[] floorArray) {
		CentralDispatcher.floorArray = floorArray;
	}

	public static synchronized Thread[] getElevatorThreadArray() {
		return elevatorThreadArray;
	}

	public static synchronized void setElevatorThreadArray(Thread[] elevatorThreadArray) {
		CentralDispatcher.elevatorThreadArray = elevatorThreadArray;
	}

	public static synchronized Thread[] getFloorThreadArray() {
		return floorThreadArray;
	}

	public static synchronized void setFloorThreadArray(Thread[] floorThreadArray) {
		CentralDispatcher.floorThreadArray = floorThreadArray;
	}

	public static synchronized boolean isFirstLoop() {
		return firstLoop;
	}

	public static synchronized void setFirstLoop(boolean firstLoop) {
		CentralDispatcher.firstLoop = firstLoop;
	}

	public static synchronized boolean isKeepLooping() {
		return keepLooping;
	}

	public static synchronized void setKeepLooping(boolean keepLooping) {
		CentralDispatcher.keepLooping = keepLooping;
	}

	public static synchronized double getCurrentTime() {
		return currentTime;
	}

	public static synchronized void setCurrentTime(double currentTime) {
		CentralDispatcher.currentTime = currentTime;
	}

	public static synchronized Vector<ArrivalGroup> getRequestArray() {
		return requestArray;
	}
	
	public static synchronized ArrivalGroup getNextRequestSync(){
		
		if(CentralDispatcher.getRequestArray().size() > 0){
			return CentralDispatcher.getRequestArray().get(0);
		}
		return null;
	}

	public static synchronized void setRequestArray(
			Vector<ArrivalGroup> requestArray) {
		CentralDispatcher.requestArray = requestArray;
	}

	public CentralDispatcher(int runNumber, int numberOfFloors, int numberOfElevators, int capacityVariable, int distanceAlreadyGoingVariable, int appendDistanceVariable,
			int countUntaskedVariable, int maxDistanceForUntaskedVariable,
			int numberOfFloorsDifference,
			int numberOfEmptySpacesToUseGoingToVariable,
			int numberOfEmptySpacesToUsePassingByVariable,
			int numberOfEmptySpacesToUseSameFloorVariable,
			int capacityThresholdVariable, double timeFactor)
			throws InterruptedException {
		
		CentralDispatcher.setStartDate(new Date());
		CentralDispatcher.numberOfFloors = numberOfFloors;
		CentralDispatcher.numberOfElevators = numberOfElevators;
		CentralDispatcher.capacityOfElevator = capacityVariable;
		CentralDispatcher.timeFactor = timeFactor;
		CentralDispatcher.numberOfEmptySpacesToUseGoingToVariable = numberOfEmptySpacesToUseGoingToVariable;
		CentralDispatcher.capacityThresholdVariable = capacityThresholdVariable;
		CentralDispatcher.numberOfEmptySpacesToUseSameFloorVariable = numberOfEmptySpacesToUseSameFloorVariable;
		CentralDispatcher.numberOfEmptySpacesToUsePassingByVariable = numberOfEmptySpacesToUsePassingByVariable;
		CentralDispatcher.numberOfFloorsDifference = numberOfFloorsDifference;
		CentralDispatcher.maxDistanceForUntaskedVariable = maxDistanceForUntaskedVariable;
		CentralDispatcher.countUntaskedVariable = countUntaskedVariable;
		CentralDispatcher.appendDistanceVariable = appendDistanceVariable;
		CentralDispatcher.distanceAlreadyGoingVariable = distanceAlreadyGoingVariable;
		CentralDispatcher.runNumber = runNumber;

		
		floorTruth = new Integer[getNumberOfFloors()][2];
		for(int i = 0; i < getNumberOfFloors(); i++){
			for(int j = 0; j < 2; j++){
				if(i == 0 && j == 1){
					floorTruth[i][j] = null;
					floorTruth[i][j] = new Integer(0);
				}
				else if(i == getNumberOfFloors() - 1 && j == 0){
					//floorTruth[i][j] = null;
					floorTruth[i][j] = new Integer(0);
				}
				else{
				floorTruth[i][j] = new Integer(0);
				}
				}
		}
		CentralDispatcher.sds = new SQLDataServer();
		
		initializeTables();
		
		distributionOfUntaskedElevators = new Integer[numberOfFloors];
		for (int i = 0; i < CentralDispatcher.distributionOfUntaskedElevators.length; i++) {
			CentralDispatcher.distributionOfUntaskedElevators[i] = 0;
		}

		eca = new ElevatorComponentAnimation();
		eca.setVisible(true);
		
		Thread.sleep(2500);
		
		initialize();
		
		Thread.sleep(2500);

	
		while (keepLooping) {
			loop();
		}

		setEndDate(new Date());
		isrd = new IndividualSimulationRunData();
		
	}
	
   public static synchronized void addToRequestArray(ArrivalGroup ag){
		

		for(int i = 0; i < CentralDispatcher.getRequestArray().size(); i++){
			if(CentralDispatcher.getRequestArray().get(i).getStartFloor() == ag.getStartFloor() && CentralDispatcher.getRequestArray().get(i).getDirection().equals(ag.getDirection())){
				return;
			}
		}
		CentralDispatcher.getRequestArray().add(ag);
	}
   
	
	private static void initializeTables(){
		String sql = "truncate table debugging_table";
		sds.execute(sql);
	}

	public static  void insertIntoDebuggingTable(int runNumber, int numberOfFloors, int numOfElevators,String problem, double currentTime, String classname) {
		String sql = "insert into debugging_table values('" + runNumber + "','" + numberOfFloors + "','" + numOfElevators + "','" + problem + "','" + currentTime + "','" + classname + "')";
		sds.execute(sql);
	}

	private static void initialize() {
		
		ra = new RequestArray();
		
		CentralDispatcher.elevatorArray = new Elevator[numberOfElevators];
		CentralDispatcher.elevatorThreadArray = new Thread[numberOfElevators];
		CentralDispatcher.floorArray = new FloorOfBuilding[numberOfFloors];
		CentralDispatcher.floorThreadArray = new Thread[numberOfFloors];
		CentralDispatcher.requestArray = RequestArray.requestArray;
		
		for (int i = 0; i < numberOfElevators; i++) {

			elevatorArray[i] = new Elevator(0, i, CentralDispatcher.getCapacityOfElevator(), 6, 2, 3,
					CentralDispatcher.timeFactor, 2000, 2000, CentralDispatcher.capacityThresholdVariable);
			elevatorThreadArray[i] = new Thread(elevatorArray[i]);
		}

		for (int j = 0; j < numberOfFloors; j++) {

			floorArray[j] = new FloorOfBuilding(60000, 3, j,
					CentralDispatcher.timeFactor);
			floorThreadArray[j] = new Thread(floorArray[j]);
		}

		CentralDispatcher.setStartOfSim(System.nanoTime() / 1000000);

		for (int i = 0; i < numberOfElevators; i++) {
			elevatorThreadArray[i].start();
		}
		for (int i = 0; i < numberOfFloors; i++) {
			floorThreadArray[i].start();
		}
	}

	private static ArrivalGroup getNextRequest() throws InterruptedException {
		ArrivalGroup ag = null;
		if (!CentralDispatcher.getRequestArray().isEmpty()
				&& CentralDispatcher.getRequestArray().get(0) != null) {
			ag = CentralDispatcher.getRequestArray().get(0);
		} else {
			Thread.sleep((long) (100 / CentralDispatcher.timeFactor + 5));
			getNextRequest();
		}
		return ag;
	}

	private static double getPercentageOfUntaskedElevators() {

		int count = 0;
		for (int i = 0; i < CentralDispatcher.getNumberOfElevators(); i++) {
			if (!CentralDispatcher.getElevatorArray()[i].isHasTask()) {
				count++;
			}
		}
		double percentage = (double) count
				/ (double) CentralDispatcher.getNumberOfElevators();
		return percentage;
	}

	private static int getNumberOfUntaskedElevators() {

		int count = 0;
		for (int i = 0; i < CentralDispatcher.getNumberOfElevators(); i++) {
			if (!CentralDispatcher.getElevatorArray()[i].isHasTask()) {
				count++;
			}
		}
		return count;
	}

	public static Elevator findClosestUntaskedElevator(int nextFloor) {

		int maxDistance = CentralDispatcher.getNumberOfFloors();
		Elevator elevatorToUse = null;

		for (int i = 0; i < CentralDispatcher.getElevatorArray().length; i++) {

			if (!CentralDispatcher.getElevatorArray()[i].isHasTask()) {
				if (Math.abs(CentralDispatcher.getElevatorArray()[i].getCurrentFloor()
						- nextFloor) < maxDistance) {
					maxDistance = Math.abs(CentralDispatcher.getElevatorArray()[i]
							.getCurrentFloor()
							- nextFloor);
					elevatorToUse = CentralDispatcher.getElevatorArray()[i];
					//break;
				}
			}

		}
		if (elevatorToUse != null
				&& maxDistance < CentralDispatcher.maxDistanceForUntaskedVariable) {
			elevatorToUse.setHasTask(true);
			return elevatorToUse;
		}

		return elevatorToUse;
	}

	public static Elevator findClosestUntaskedElevatorLessRestrictive(int nextFloor) {

		int maxDistance = CentralDispatcher.getNumberOfFloors();
		Elevator elevatorToUse = null;

		for (int i = 0; i < CentralDispatcher.getElevatorArray().length; i++) {

			if (!CentralDispatcher.getElevatorArray()[i].isHasTask()) {
				if (Math.abs(CentralDispatcher.getElevatorArray()[i].getCurrentFloor()
						- nextFloor) < maxDistance) {
					maxDistance = Math.abs(CentralDispatcher.getElevatorArray()[i]
							.getCurrentFloor()
							- nextFloor);
					elevatorToUse = CentralDispatcher.getElevatorArray()[i];
					//break;
				}
			}

		}
		if (elevatorToUse != null /*
								 * && maxDistance <
								 * CentralDispatcher.maxDistanceForUntaskedVariable
								 */) {
			elevatorToUse.setHasTask(true);
			return elevatorToUse;
		}

		return elevatorToUse;
	}

	private static Elevator seeIfElevatorIsAlreadyGoingtoFloor(int nextFloor) {
	
		Elevator elevatorToUse = null;
		List<Integer> temp;
		for (int i = 0; i < CentralDispatcher.getElevatorArray().length; i++) {
			temp = CentralDispatcher.getElevatorArray()[i].getFloorsArray();
			if ((CentralDispatcher.getElevatorArray()[i].isHasTask() || CentralDispatcher
					.getElevatorArray()[i].getFloorsArray().size() > 1)
					&& CentralDispatcher.getElevatorArray()[i]
							.getCurrentNumberOfPeopleSpaces() > CentralDispatcher.numberOfEmptySpacesToUseGoingToVariable) {
				if (temp.contains(nextFloor) && Math.abs(CentralDispatcher.getElevatorArray()[i].getCurrentFloor()- nextFloor) < distanceAlreadyGoingVariable) {
					return elevatorToUse = CentralDispatcher.getElevatorArray()[i];
				}
			}
		}
		return elevatorToUse;
	}

	private static Elevator seeIfElevatorIsPassingByFloor(int nextFloor) {

		Elevator elevatorToUse = null;

		for (int i = 0; i < CentralDispatcher.getElevatorArray().length; i++) {
			if (CentralDispatcher.getElevatorArray()[i].isHasTask()
					&& CentralDispatcher.getElevatorArray()[i].isAvailable()
					&& (CentralDispatcher.getElevatorArray()[i].getCurrentFloor() + CentralDispatcher.numberOfFloorsDifference) < nextFloor
					&& CentralDispatcher.getElevatorArray()[i].isGoingUp()
					&& CentralDispatcher.getElevatorArray()[i]
							.getCurrentNumberOfPeopleSpaces() > CentralDispatcher.numberOfEmptySpacesToUsePassingByVariable) {
				return elevatorToUse = CentralDispatcher.getElevatorArray()[i];
			} else if (CentralDispatcher.getElevatorArray()[i].isHasTask()
					&& CentralDispatcher.getElevatorArray()[i].isAvailable()
					&& (CentralDispatcher.getElevatorArray()[i].getCurrentFloor() - CentralDispatcher.numberOfFloorsDifference) > nextFloor
					&& CentralDispatcher.getElevatorArray()[i].isGoingDown()
					&& CentralDispatcher.getElevatorArray()[i]
							.getCurrentNumberOfPeopleSpaces() > CentralDispatcher.numberOfEmptySpacesToUsePassingByVariable) {
				
				return elevatorToUse = CentralDispatcher.getElevatorArray()[i];
			}
		}
		return elevatorToUse;
	}

	private static Elevator findElevatorToAppendFloorTo(int nextFloor) throws InterruptedException {

		Elevator elevatorToUse = null;

		for (int i = 0; i < CentralDispatcher.getElevatorArray().length; i++) {
			CentralDispatcher.getElevatorArray()[i].getFloorsArray().faLock();
			if (CentralDispatcher.getElevatorArray()[i].isHasTask()
					&& CentralDispatcher.getElevatorArray()[i].isAvailable()
					&& (CentralDispatcher.getElevatorArray()[i].getCurrentFloor() + CentralDispatcher.numberOfFloorsDifference) < nextFloor
					&& CentralDispatcher.getElevatorArray()[i].isGoingUp()
					&& CentralDispatcher.getElevatorArray()[i]
							.getCurrentNumberOfPeopleSpaces() > CentralDispatcher.numberOfEmptySpacesToUsePassingByVariable) {
				
				
				int size = CentralDispatcher.getElevatorArray()[i].getFloorsArray().size();
				if(size > 0){
				if(CentralDispatcher.getElevatorArray()[i].getFloorsArray().get(size-1) < nextFloor){
					CentralDispatcher.getElevatorArray()[i].getFloorsArray().faUnLock();
				return elevatorToUse = CentralDispatcher.getElevatorArray()[i];
				}
				}
				
				//what's this below??...
			} else if (CentralDispatcher.getElevatorArray()[i].isHasTask()
					&& CentralDispatcher.getElevatorArray()[i].isAvailable()
					&& (CentralDispatcher.getElevatorArray()[i].getCurrentFloor() - CentralDispatcher.numberOfFloorsDifference) > nextFloor
					&& CentralDispatcher.getElevatorArray()[i].isGoingDown()
					&& CentralDispatcher.getElevatorArray()[i]
							.getCurrentNumberOfPeopleSpaces() > CentralDispatcher.numberOfEmptySpacesToUsePassingByVariable) {
				int size = CentralDispatcher.getElevatorArray()[i].getFloorsArray().size();
				if(size > 0){
				if(CentralDispatcher.getElevatorArray()[i].getFloorsArray().get(size-1) > nextFloor){
					CentralDispatcher.getElevatorArray()[i].getFloorsArray().faUnLock();
					return elevatorToUse = CentralDispatcher.getElevatorArray()[i];
					}
				}
			}
			CentralDispatcher.getElevatorArray()[i].getFloorsArray().faUnLock();
		}
		
		return elevatorToUse;
	}

	private static Elevator checkToSeeIfElevatorAlreadyOnSameFloor(int nextFloor) {
		Elevator elevatorToUse = null;

		// first we try to find an empty/untasked elevator to use on the current floor
		for (int i = 0; i < CentralDispatcher.getElevatorArray().length; i++) {
			if (CentralDispatcher.getElevatorArray()[i].getCurrentFloor() == nextFloor
					&& CentralDispatcher.getElevatorArray()[i].isHasTask() == false
					&& CentralDispatcher.getElevatorArray()[i].isStoppedOnCurrentFloor()
					&& CentralDispatcher.getElevatorArray()[i]
							.getCurrentNumberOfPeopleSpaces() > CentralDispatcher.numberOfEmptySpacesToUseSameFloorVariable) {

				if (CentralDispatcher.getElevatorArray()[i].getCapacity() != CentralDispatcher
						.getElevatorArray()[i].getCurrentNumberOfPeopleSpaces()) {
					
					String problem = "Elevator capacity problem!!! Elevator has no task, but has people in it!!!";
					insertIntoDebuggingTable(CentralDispatcher.getRunNumber(),CentralDispatcher.getNumberOfFloors(),CentralDispatcher.getNumberOfElevators(),problem,CentralDispatcher.getCurrentTime()/1000,"CentralDispatcher");
				}

				elevatorToUse = CentralDispatcher.getElevatorArray()[i];
				elevatorToUse.setHasTask(true);
				return elevatorToUse;
			}
		}

		// if there are no empty/untasked elevators on the current floor, passengers will look for a tasked elevator
		for (int i = 0; i < CentralDispatcher.getElevatorArray().length; i++) {
			if (CentralDispatcher.getElevatorArray()[i].getCurrentFloor() == nextFloor
					&& CentralDispatcher.getElevatorArray()[i].isStoppedOnCurrentFloor()
					&& CentralDispatcher.getElevatorArray()[i]
							.getCurrentNumberOfPeopleSpaces() > CentralDispatcher.numberOfEmptySpacesToUseSameFloorVariable) {
				elevatorToUse = CentralDispatcher.getElevatorArray()[i];
				elevatorToUse.setHasTask(true);
				return elevatorToUse;
			}
		}
		return elevatorToUse;
	}
	

	public static void loop() throws InterruptedException {

		if (memoryMgt.getCurrentPercentage() > 75) {
			memoryMgt.cleanMemory();
		}

		CentralDispatcher.setCurrentTime(System.nanoTime() / 1000000);
		if (CentralDispatcher.getCurrentTime() - CentralDispatcher.getStartOfSim() > (int) (CentralDispatcher.getLengthOfSim() / CentralDispatcher.timeFactor)) {
			CentralDispatcher.setKeepLooping(false);
			return;
		}
		
		//make sure all floors with people waiting are assigned an elevator
		
//		for(int i = 0; i < CentralDispatcher.getFloorArray().length; i++){
//			if(CentralDispatcher.getFloorArray()[i].findOutIfThereIsGroupWaiting()){
//				Vector<Integer> temp = null;
//				for(int j = 0; j < CentralDispatcher.getElevatorArray().length; j++){
//					temp = CentralDispatcher.getElevatorArray()[j].getFloorsArray();
//					if(temp.contains(CentralDispatcher.getFloorArray()[i].getFloorNumber())){
//						break;
//					}
//					CentralDispatcher.getRequestArray().add(CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().get(0));
//					String direction = CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().get(0).getDirection();
//					if(CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().size()>1){
//						for(int k = 1; k < CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().size(); k++){
//							if(CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().get(k).isWaiting() && !CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().get(k).getDirection().equals(direction)){
//								CentralDispatcher.getRequestArray().add(CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().get(k));
//								break;
//							}
//						}
//					}
//				}
//			}
//		}
		
		//if an elevator is near the top or bottom floor and is going up or down respectively, it can pass a floor then come back and swing to get it on the way back to the middle floors

	//	ra.ralock();
		ArrivalGroup ag = getNextRequestSync();
	//	ra.raUnlock();
		
		
		
		Elevator elevatorToUse = null;

		if (ag == null) {
			//countNull++;
			return;
		}
		
        if(ag.getDirection().equals("up")){
		 	getFloorTruth()[ag.getStartFloor()][0] ++;
		} else if(ag.getDirection().equals("down")){
			getFloorTruth()[ag.getStartFloor()][1] ++;
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
		//	ra.ralock();
			CentralDispatcher.getRequestArray().remove(0);
		//	ra.raUnlock();
			return;
		}

		// TYPE_TWO
		int countUntasked = getNumberOfUntaskedElevators();
		if (countUntasked > CentralDispatcher.countUntaskedVariable) {
			elevatorToUse = findClosestUntaskedElevator(ag.getStartFloor());
			if (elevatorToUse != null) {
				countOccurencesOfElevatorOfRestrictiveUntasked++;
				if(elevatorToUse.getCurrentFloor() != ag.getStartFloor()){
				elevatorToUse.getFloorsArray().add(ag.getStartFloor());	
				}
		//		ra.ralock();
				CentralDispatcher.getRequestArray().remove(0);
		//		ra.raUnlock();
				elevatorToUse.setHasTask(true);
				return;
			}
		}

		// TYPE_THREE
		elevatorToUse = seeIfElevatorIsAlreadyGoingtoFloor(ag.getStartFloor());
		if (elevatorToUse != null) {
			countOccurencesOfElevatorAlreadyGoingToFloor++;
		//	ra.ralock();
			CentralDispatcher.getRequestArray().remove(0);
		//	ra.raUnlock();
			return;
		}


		// TYPE_FOUR
		elevatorToUse = seeIfElevatorIsPassingByFloor(ag.getStartFloor());
		if (elevatorToUse != null) {
			countOccurencesOfElevatorGoingPastFloor++;
			// we must sort the FloorsArray then add at the right index!
	//		if (elevatorToUse.isAvailable()) {
				elevatorToUse.getFloorsArray().add(ag.getStartFloor());
				elevatorToUse.setHasTask(true);
		//		ra.ralock();
				CentralDispatcher.getRequestArray().remove(0);
		//		ra.raUnlock();
				return;
		//	}
		}

		// TYPE_FIVE
		elevatorToUse = findElevatorToAppendFloorTo(ag.getStartFloor());
		if (elevatorToUse != null) {
			countOccurencesOfElevatorAppending++;
			// we must sort the FloorsArray then add at the right index!
		//	if (elevatorToUse.isAvailable()) {
				elevatorToUse.getFloorsArray().add(ag.getStartFloor());
				elevatorToUse.setHasTask(true);
		//		ra.ralock();
				CentralDispatcher.getRequestArray().remove(0);
		//		ra.raUnlock();
				return;
		//	}
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
			insertIntoDebuggingTable(CentralDispatcher.getRunNumber(),CentralDispatcher.getNumberOfFloors(),CentralDispatcher.getNumberOfElevators(),problem,CentralDispatcher.getCurrentTime()/1000,"CentralDispatcher");
	
			
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
			String problem = "We should never get to CentralDispatcher point...";
			insertIntoDebuggingTable(CentralDispatcher.getRunNumber(),CentralDispatcher.getNumberOfFloors(),CentralDispatcher.getNumberOfElevators(),problem,CentralDispatcher.getCurrentTime()/1000,"CentralDispatcher");
	
		}
		countOccurencesFindClosestUntaskedElevator++;
		elevatorToUse.setHasTask(true);
	//	ra.ralock();
		CentralDispatcher.getRequestArray().remove(0);
	//	ra.raUnlock();
	}

	
	
	
}
