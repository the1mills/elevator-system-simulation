package start;

import javax.swing.JOptionPane;


public class ArrivalGroup {

	private volatile double cumulativeTimeOfWaiting;
	private volatile double cumulativeTimeOfRidingElevator;
	private volatile double timeFirstBoarding;
	private volatile double timeOfArrival;
	private int numberOfPeopleInGroup;
	private boolean waiting = true; // waiting before they first got onto elevator
	private boolean gotServedByElevator = false; // only true once they got off elevator
	private int desiredFloor;
	private int startFloor;
	private volatile String direction = "";
	
	public ArrivalGroup(double timeOfArrival, int numberOfPeopleInGroup, int desiredFloor, int startFloor){
		
		this.timeOfArrival = timeOfArrival;
		this.numberOfPeopleInGroup = numberOfPeopleInGroup;
		this.desiredFloor = desiredFloor;
		this.startFloor = startFloor;
		
		if(this.startFloor > this.desiredFloor){
			this.setDirection("down");
		}
		else if(this.startFloor < this.desiredFloor){
			this.setDirection("up");
		}
		else {
			JOptionPane
			.showMessageDialog(
					null,
					"Problem with start floor");
		}
	}
	
	public synchronized double getTimeFirstBoarding() {
		return timeFirstBoarding;
	}

	public synchronized void setTimeFirstBoarding(double timeFirstBoarding) {
		this.timeFirstBoarding = timeFirstBoarding;
	}
	
	public synchronized double getCumulativeTimeOfRidingElevator() {
		return cumulativeTimeOfRidingElevator;
	}

	public synchronized void setCumulativeTimeOfRidingElevator(
			double cumulativeTimeOfRidingElevator) {
		this.cumulativeTimeOfRidingElevator = cumulativeTimeOfRidingElevator;
	}

	public synchronized double getTotalTimeToBeServed() {
		
		return getCumulativeTimeOfRidingElevator() + getCumulativeTimeOfWaiting();
	}


	public int getStartFloor() {
		return startFloor;
	}

	public void setStartFloor(int startFloor) {
		this.startFloor = startFloor;
	}

	public synchronized String getDirection() {
		return direction;
	}

	public synchronized void setDirection(String direction) {
		this.direction = direction;
	}
	
	public boolean isWaiting() {
		return waiting;
	}

	public void setWaiting(boolean waiting) {
		this.waiting = waiting;
	}

	public int getDesiredFloor() {
		return desiredFloor;
	}

	public void setDesiredFloor(int desiredFloor) {
		this.desiredFloor = desiredFloor;
	}
	
	public synchronized double getCumulativeTimeOfWaiting() {
		return cumulativeTimeOfWaiting;
	}

	public synchronized void setCumulativeTimeOfWaiting(double cumulativeTimeOfWaiting) {
		this.cumulativeTimeOfWaiting = cumulativeTimeOfWaiting;
	}

	public synchronized double getTimeOfArrival() {
		return timeOfArrival;
	}

	public synchronized void setTimeOfArrival(double timeOfArrival) {
		this.timeOfArrival = timeOfArrival;
	}

	public synchronized int getNumberOfPeopleInGroup() {
		return numberOfPeopleInGroup;
	}

	public void setNumberOfPeopleInGroup(int numberOfPeopleInGroup) {
		this.numberOfPeopleInGroup = numberOfPeopleInGroup;
	}

	public boolean isGotServedByElevator() {
		return gotServedByElevator;
	}

	public void setGotServedByElevator(boolean gotServedByElevator) {
		this.gotServedByElevator = gotServedByElevator;
	}

}
