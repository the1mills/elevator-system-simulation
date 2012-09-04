package start;


import java.util.ArrayList;


public class IndividualSimulationRunData {

	private volatile SQLDataServer sds;
	private volatile Integer runNumber = 5;
	private int totalNumberServed = 0;
	private int totalNumberRemaining = 0;
	private volatile double totalTravelTime;
	private volatile double totalWaitingTime;
	private volatile double avgTravelTimePerCustomer;
	private volatile double[] travelTimesByFloor;
	private volatile double[] travelTimesByElevator;
	private volatile double[] waitingTimesByFloor;
	private volatile CentralDispatcher cd;
	private volatile double simulatedTime;
	private volatile double runTime;
	private volatile double averageTotalTimePerGroup;
	private volatile double averageWaitingTimePerGroup;
	private volatile double averageTravelTimePerGroup;

	public SQLDataServer getSds() {
		return sds;
	}

	public void setSds(SQLDataServer sds) {
		this.sds = sds;
	}

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

	public CentralDispatcher getCd() {
		return cd;
	}

	public void setCd(CentralDispatcher cd) {
		this.cd = cd;
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
	
	public IndividualSimulationRunData(CentralDispatcher cd) throws InterruptedException {
		this.cd = cd;
		this.sds = cd.getSds();
		
		initialize();
		calculateResults();
		insertIntoTable();
	}
	
	private void insertIntoTable(){
		
	String sql = "insert into p_elevator values('" + 
	this.getCd().getRunNumber() + "',"
	+ "to_date('" + this.getCd().getStartDate() + "','DYMONDDHH24:MI:SSYYYY')" + ","
	+ "to_date('" + this.getCd().getEndDate() + "','DYMONDDHH24:MI:SSYYYY')" +",'"
	+ this.getAverageTravelTimePerGroup() + "','" 
	+ this.getAverageWaitingTimePerGroup() + "','" 
	+ this.totalTravelTime + "','"
	+ this.totalWaitingTime + "','"
	+ this.averageTotalTimePerGroup + "','"
	+ this.totalNumberServed + "','"
	+ this.totalNumberRemaining + "','"
	+ this.getCd().getNumberOfFloors() + "','"
	+ this.getCd().getNumberOfElevators() + "','"
	+ this.getCd().getLengthOfSim() + "','"
	+ "Elevator Data String" + "','"
	+ this.getCd().getTimeFactor() + "','" 
	+ this.getCd().getNumberOfEmptySpacesToUseGoingToVariable()+ "','" 
	+ this.getCd().getNumberOfEmptySpacesToUsePassingByVariable() + "','" 
	+ this.getCd().getNumberOfEmptySpacesToUseSameFloorVariable() + "','" 
	+ this.getCd().getCapacityThresholdVariable()  + "','" 
	+ this.getCd().getNumberOfFloorsDifference()  + "','" 
	+ this.getCd().getMaxDistanceForUntaskedVariable()  + "','" 
	+ this.getCd().getCountUntaskedVariable()  + "','" 
	+ this.getCd().getAppendDistanceVariable()  + "','" 
	+ this.getCd().getDistanceAlreadyGoingVariable()  + "','" 
	+ this.getCd().getCapacityOfElevator() + "')";	
	
	this.sds.execute(sql);
	}
	
	private void initialize(){
	
		waitingTimesByFloor = new double[cd.getNumberOfFloors()];
		travelTimesByFloor = new double[cd.getNumberOfFloors()];
		
	}
	
	private void calculateResults() throws InterruptedException{
		
		
		Thread.sleep(2000);
		
		double tempSumWaitingTime = 0;
		double tempSumTravelingTime =0;
		int numberServed = 0;
		int numberWaitingOnFloors = 0;
		int totalNumberOfGroups = 0;
		
		//calculate the total number of people in the system
		
		for(int i = 0; i < cd.getFloorArray().length; i++){
			totalNumberOfGroups += cd.getFloorArray()[i].getArrivalGroupArray().size();
		}
		System.out.println(totalNumberOfGroups);
		
		System.out.println("");
		for(int i = 0; i < cd.getFloorArray().length; i++){
			int j =0;
			tempSumWaitingTime = 0;
			tempSumTravelingTime = 0;
			
				while(j < cd.getFloorArray()[i].getArrivalGroupArray().size()  && cd.getFloorArray()[i].getArrivalGroupArray().get(j).getCumulativeTimeOfWaiting() != 0 && cd.getFloorArray()[i].getArrivalGroupArray().get(j).getCumulativeTimeOfRidingElevator() != 0){
					tempSumWaitingTime += cd.getFloorArray()[i].getArrivalGroupArray().get(j).getCumulativeTimeOfWaiting();
					tempSumTravelingTime += cd.getFloorArray()[i].getArrivalGroupArray().get(j).getCumulativeTimeOfRidingElevator();
					numberServed++;
					
					if(i == 3 && j > 10  && j < 20){
					System.out.println("Floor:" + i + "Arrival group #: " + j + "time spent waiting:" + cd.getFloorArray()[i].getArrivalGroupArray().get(j).getCumulativeTimeOfWaiting() + "traveling time: " + cd.getFloorArray()[i].getArrivalGroupArray().get(j).getCumulativeTimeOfRidingElevator() + "size: " + cd.getFloorArray()[i].getArrivalGroupArray().get(j).getNumberOfPeopleInGroup());
					}
					
					j++;
				}
				waitingTimesByFloor[i] = tempSumWaitingTime;
				travelTimesByFloor[i] = tempSumTravelingTime;
				System.out.println("WAITING TIME FOR FLOOR: " + i + " -- " + travelTimesByFloor[i] + " -- Total Number of Arrival Groups Served: " + j + " -- Total Left: " + (cd.getFloorArray()[i].getArrivalGroupArray().size() - j));
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
	
		this.averageWaitingTimePerGroup = (this.totalWaitingTime/numberServed)*this.getCd().getTimeFactor()/1000;
		this.averageTravelTimePerGroup = (this.totalTravelTime/numberServed)*this.getCd().getTimeFactor()/1000;
		this.averageTotalTimePerGroup = this.averageWaitingTimePerGroup + this.averageTravelTimePerGroup;
			
		System.out.println("Average WAITING TIME per Group: " + this.averageWaitingTimePerGroup);
		System.out.println("Average TRAVELING TIME per Group: " + this.averageTravelTimePerGroup);
		System.out.println("Average TOTAL TIME per Group: " + this.averageTotalTimePerGroup);
		this.totalNumberServed = numberServed;
		System.out.println("Total number served: " + numberServed);
		
		Thread.sleep(3000);
		
		//now we find out how many people are left waiting and haven't been served yet
		for(int i = 0; i < cd.getFloorArray().length; i++){
			int j =0;
			
				while(j < cd.getFloorArray()[i].getArrivalGroupArray().size()){
					if(cd.getFloorArray()[i].getArrivalGroupArray().get(j).isWaiting()){
					numberWaitingOnFloors++;
					}
					j++;
			}
		}
		
		System.out.println("Total number still waiting: " + numberWaitingOnFloors);
				
		int numberStillInElevators = 0;
		//now we find out how many people are in the elevators! :)
		for(int i = 0; i < cd.getFloorArray().length; i++){
			int j =0;
			
				while(j < cd.getFloorArray()[i].getArrivalGroupArray().size()){
					if(cd.getFloorArray()[i].getArrivalGroupArray().get(j).isWaiting()==false && cd.getFloorArray()[i].getArrivalGroupArray().get(j).isGotServedByElevator() == false){
					numberStillInElevators++;
					}
					j++;
			}
		}
		System.out.println("Total number still in elevators: " + numberStillInElevators);
		this.totalNumberRemaining = numberWaitingOnFloors + numberStillInElevators;
		
		Thread.sleep(3000);
	
		System.out.println(cd.getCountOccurencesOfElevatorOnSameFloor());
		System.out.println(cd.getCountOccurencesOfElevatorOf50PercentUntasked());
		System.out.println(cd.getCountOccurencesOfElevatorAlreadyGoingToFloor());
		System.out.println(cd.getCountOccurencesOfElevatorGoingPastFloor());
		System.out.println(cd.getCountOccurencesOfElevatorAppending());
		System.out.println(cd.getCountOccurencesFindClosestUntaskedElevator());
	
	
	}
}
