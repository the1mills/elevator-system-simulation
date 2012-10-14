package start;


import java.util.ArrayList;


public class IndividualSimulationRunData {


	private volatile Integer runNumber = 5;
	private int totalNumberServed = 0;
	private int totalNumberRemaining = 0;
	private volatile double totalTravelTime;
	private volatile double totalWaitingTime;
	private volatile double avgTravelTimePerCustomer;
	private volatile double[] travelTimesByFloor;
	private volatile double[] travelTimesByElevator;
	private volatile double[] waitingTimesByFloor;
	private volatile double simulatedTime;
	private volatile double runTime;
	private volatile double averageTotalTimePerGroup;
	private volatile double averageWaitingTimePerGroup;
	private volatile double averageTravelTimePerGroup;


	public double getAverageWaitingTimePerGroup() {
		return averageWaitingTimePerGroup;
	}

	public void setAverageWaitingTimePerGroup(double averageWaitingTimePerGroup) {
		this.averageWaitingTimePerGroup = averageWaitingTimePerGroup;
	}

	public double getAverageTravelTimePerGroup() {
		return averageTravelTimePerGroup;
	}

	public void setAverageTravelTimePerGroup(double averageTravelTimePerGroup) {
		this.averageTravelTimePerGroup = averageTravelTimePerGroup;
	}
	public Integer getRunNumber() {
		return runNumber;
	}

	public void setRunNumber(Integer runNumber) {
		this.runNumber = runNumber;
	}

	public int getTotalNumberServed() {
		return totalNumberServed;
	}

	public void setTotalNumberServed(int totalNumberServed) {
		this.totalNumberServed = totalNumberServed;
	}

	public double getTotalTravelTime() {
		return totalTravelTime;
	}

	public void setTotalTravelTime(double totalTravelTime) {
		this.totalTravelTime = totalTravelTime;
	}

	public double getTotalWaitingTime() {
		return totalWaitingTime;
	}

	public void setTotalWaitingTime(double totalWaitingTime) {
		this.totalWaitingTime = totalWaitingTime;
	}

	public double getAvgTravelTimePerCustomer() {
		return avgTravelTimePerCustomer;
	}

	public void setAvgTravelTimePerCustomer(double avgTravelTimePerCustomer) {
		this.avgTravelTimePerCustomer = avgTravelTimePerCustomer;
	}

	public double[] getTravelTimesByFloor() {
		return travelTimesByFloor;
	}

	public void setTravelTimesByFloor(double[] travelTimesByFloor) {
		this.travelTimesByFloor = travelTimesByFloor;
	}

	public double[] getTravelTimesByElevator() {
		return travelTimesByElevator;
	}

	public void setTravelTimesByElevator(double[] travelTimesByElevator) {
		this.travelTimesByElevator = travelTimesByElevator;
	}

	public double[] getWaitingTimesByFloor() {
		return waitingTimesByFloor;
	}

	public void setWaitingTimesByFloor(double[] waitingTimesByFloor) {
		this.waitingTimesByFloor = waitingTimesByFloor;
	}

	public double getSimulatedTime() {
		return simulatedTime;
	}

	public void setSimulatedTime(double simulatedTime) {
		this.simulatedTime = simulatedTime;
	}

	public double getRunTime() {
		return runTime;
	}

	public void setRunTime(double runTime) {
		this.runTime = runTime;
	}
	
	public IndividualSimulationRunData() throws InterruptedException {
		
		Thread.sleep(3000);
		forceUnlocks();
		initialize();
		calculateResults();
		insertIntoTable();
	}
	
	private void forceUnlocks() throws InterruptedException {
		
		CentralDispatcher.getRequestArray().forceUnlock();
		
		int ne = CentralDispatcher.getNumberOfElevators();
		
		for(int i = 0; i < ne; i++){
			
			CentralDispatcher.getElevatorArray()[i].getFloorsArray().forceUnlock();
		}
		
		int nf = CentralDispatcher.getNumberOfFloors();
		
		for(int i = 0; i < nf; i++){
			
			CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().forceUnlock();
		}
		
	}

	private void insertIntoTable(){
		
	String sql = "insert into p_elevator values('" + 
	CentralDispatcher.getRunNumber() + "',"
	+ "to_date('" + CentralDispatcher.getStartDate() + "','DYMONDDHH24:MI:SSYYYY')" + ","
	+ "to_date('" + CentralDispatcher.getEndDate() + "','DYMONDDHH24:MI:SSYYYY')" +",'"
	+ this.getAverageTravelTimePerGroup() + "','" 
	+ this.getAverageWaitingTimePerGroup() + "','" 
	+ this.totalTravelTime + "','"
	+ this.totalWaitingTime + "','"
	+ this.averageTotalTimePerGroup + "','"
	+ this.totalNumberServed + "','"
	+ this.totalNumberRemaining + "','"
	+ CentralDispatcher.getNumberOfFloors() + "','"
	+ CentralDispatcher.getNumberOfElevators() + "','"
	+ CentralDispatcher.getLengthOfSim() + "','"
	+ "Elevator Data String" + "','"
	+ CentralDispatcher.getTimeFactor() + "','" 
	+ CentralDispatcher.getNumberOfEmptySpacesToUseGoingToVariable()+ "','" 
	+ CentralDispatcher.getNumberOfEmptySpacesToUsePassingByVariable() + "','" 
	+ CentralDispatcher.getNumberOfEmptySpacesToUseSameFloorVariable() + "','" 
	+ CentralDispatcher.getCapacityThresholdVariable()  + "','" 
	+ CentralDispatcher.getNumberOfFloorsDifference()  + "','" 
	+ CentralDispatcher.getMaxDistanceForUntaskedVariable()  + "','" 
	+ CentralDispatcher.getCountUntaskedVariable()  + "','" 
	+ CentralDispatcher.getAppendDistanceVariable()  + "','" 
	+ CentralDispatcher.getDistanceAlreadyGoingVariable()  + "','" 
	+ CentralDispatcher.getCapacityOfElevator() + "')";	
	
	CentralDispatcher.getSds().execute(sql);
	}
	
	private void initialize(){
	
		waitingTimesByFloor = new double[CentralDispatcher.getNumberOfFloors()];
		travelTimesByFloor = new double[CentralDispatcher.getNumberOfFloors()];
		
	}
	
	private void calculateResults() throws InterruptedException{
		
		
		Thread.sleep(2000);
		
		double tempSumWaitingTime = 0;
		double tempSumTravelingTime =0;
		int numberServed = 0;
		int numberWaitingOnFloors = 0;
		int totalNumberOfGroups = 0;
		
		//calculate the total number of people in the system
		
		for(int i = 0; i < CentralDispatcher.getFloorArray().length; i++){
			totalNumberOfGroups += CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().size();
		}
		System.out.println(totalNumberOfGroups);
		
		System.out.println("");
		for(int i = 0; i < CentralDispatcher.getFloorArray().length; i++){
			int j =0;
			tempSumWaitingTime = 0;
			tempSumTravelingTime = 0;
			
				while(j < CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().size()  && CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().get(j).getCumulativeTimeOfWaiting() != 0 && CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().get(j).getCumulativeTimeOfRidingElevator() != 0){
					tempSumWaitingTime += CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().get(j).getCumulativeTimeOfWaiting();
					tempSumTravelingTime += CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().get(j).getCumulativeTimeOfRidingElevator();
					numberServed++;
					
					if(i == 3 && j > 10  && j < 20){
					System.out.println("Floor:" + i + "Arrival group #: " + j + "time spent waiting:" + CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().get(j).getCumulativeTimeOfWaiting() + "traveling time: " + CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().get(j).getCumulativeTimeOfRidingElevator() + "size: " + CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().get(j).getNumberOfPeopleInGroup());
					}
					
					j++;
				}
				waitingTimesByFloor[i] = tempSumWaitingTime;
				travelTimesByFloor[i] = tempSumTravelingTime;
				System.out.println("WAITING TIME FOR FLOOR: " + i + " -- " + travelTimesByFloor[i] + " -- Total Number of Arrival Groups Served: " + j + " -- Total Left: " + (CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().size() - j));
		}
		
		double totalSumWaiting = 0;
		double totalSumTraveling = 0;
		for(int i =0; i <waitingTimesByFloor.length; i++){
			totalSumWaiting += waitingTimesByFloor[i];
			totalSumTraveling += travelTimesByFloor[i];
		}
		this.totalWaitingTime = totalSumWaiting;
		this.totalTravelTime = totalSumTraveling;
		
		System.out.println("TOTAL WAITING TIME: " + this.totalWaitingTime);
		System.out.println("TOTAL TRAVELING TIME: " + this.totalTravelTime);
	
		this.averageWaitingTimePerGroup = (this.totalWaitingTime/numberServed)*CentralDispatcher.getTimeFactor()/1000;
		this.averageTravelTimePerGroup = (this.totalTravelTime/numberServed)*CentralDispatcher.getTimeFactor()/1000;
		this.averageTotalTimePerGroup = this.averageWaitingTimePerGroup + this.averageTravelTimePerGroup;
			
		System.out.println("Average WAITING TIME per Group: " + this.averageWaitingTimePerGroup);
		System.out.println("Average TRAVELING TIME per Group: " + this.averageTravelTimePerGroup);
		System.out.println("Average TOTAL TIME per Group: " + this.averageTotalTimePerGroup);
		this.totalNumberServed = numberServed;
		System.out.println("Total number served: " + numberServed);
		
		Thread.sleep(3000);
		
		//now we find out how many people are left waiting and haven't been served yet
		for(int i = 0; i < CentralDispatcher.getFloorArray().length; i++){
			int j =0;
			
				while(j < CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().size()){
					if(CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().get(j).isWaiting()){
					numberWaitingOnFloors++;
					}
					j++;
			}
		}
		
		System.out.println("Total number still waiting: " + numberWaitingOnFloors);
				
		int numberStillInElevators = 0;
		//now we find out how many people are in the elevators! :)
		for(int i = 0; i < CentralDispatcher.getFloorArray().length; i++){
			int j =0;
			
				while(j < CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().size()){
					if(CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().get(j).isWaiting()==false && CentralDispatcher.getFloorArray()[i].getArrivalGroupArray().get(j).isGotServedByElevator() == false){
					numberStillInElevators++;
					}
					j++;
			}
		}
		System.out.println("Total number still in elevators: " + numberStillInElevators);
		this.totalNumberRemaining = numberWaitingOnFloors + numberStillInElevators;
		
		Thread.sleep(3000);
	
		System.out.println("Type 1: " + CentralDispatcher.getCountOccurencesOfElevatorOnSameFloor());
		System.out.println("Type 2: " + CentralDispatcher.getCountOccurencesOfElevatorOfUntaskedLessRestrictive());
		System.out.println("Type 3: " + CentralDispatcher.getCountOccurencesOfElevatorAlreadyGoingToFloor());
		System.out.println("Type 4: " + CentralDispatcher.getCountOccurencesOfElevatorGoingPastFloor());
		System.out.println("Type 5: " + CentralDispatcher.getCountOccurencesOfElevatorAppending());
		System.out.println("Type 6: " + CentralDispatcher.getCountOccurencesFindClosestUntaskedElevator());
	
	
	}
}
