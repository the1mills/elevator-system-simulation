package start;


import java.util.Observable;

public class FloorOfBuilding extends Observable implements Runnable {

	private volatile double interArrivalTime;
	private int avgNumberPerArrivalGroup;
	private int floorNumber;
	private volatile ArrivalGroupArray arrivalGroupArray = null;

	public synchronized ArrivalGroup getNextInLine() {
		for (int i = 0; i < this.getArrivalGroupArray().size(); i++) {
			if (this.getArrivalGroupArray().get(i).isWaiting()) {
				return this.getArrivalGroupArray().get(i);
			}
		}
		return null;
	}


	public synchronized int getCurrentNumberWaiting() {

		int count = 0;
		for (int i = 0; i < this.getArrivalGroupArray().size(); i++) {
			if (this.getArrivalGroupArray().get(i).isWaiting()) {
				for (int j = 0; j < this.getArrivalGroupArray().get(i)
						.getNumberOfPeopleInGroup(); j++) {
					count++;
				}
			}
		}
		return count;
	}

	public synchronized int getCurrentNumberWaitingToGoUp() {
		int count = 0;
		for (int i = 0; i < this.getArrivalGroupArray().size(); i++) {
			if (this.getArrivalGroupArray().get(i).isWaiting()
					&& this.getArrivalGroupArray().get(i).getDirection()
							.equals("up")) {
				for (int j = 0; j < this.getArrivalGroupArray().get(i)
						.getNumberOfPeopleInGroup(); j++) {
					count++;
				}
			}
		}
		return count;
	}

	public synchronized int getCurrentNumberWaitingToGoDown() {
		int count = 0;
		for (int i = 0; i < this.getArrivalGroupArray().size(); i++) {
			if (this.getArrivalGroupArray().get(i).isWaiting()
					&& this.getArrivalGroupArray().get(i).getDirection()
							.equals("down")) {
				for (int j = 0; j < this.getArrivalGroupArray().get(i)
						.getNumberOfPeopleInGroup(); j++) {
					count++;
				}
			}
		}
		return count;
	}

	public synchronized ArrivalGroupArray getArrivalGroupArray() {
		return arrivalGroupArray;
	}

	public synchronized void setArrivalGroupArray(
			ArrivalGroupArray arrivalGroupArray) {
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

	public synchronized void setAvgNumberPerArrivalGroup(
			int avgNumberPerArrivalGroup) {
		this.avgNumberPerArrivalGroup = avgNumberPerArrivalGroup;
	}

	public synchronized int getFloorNumber() {
		return floorNumber;
	}

	public synchronized void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public FloorOfBuilding(double interArrivalTime,
			int avgNumberPerArrivalGroup, int floorNumber) {

		this.interArrivalTime = interArrivalTime;
		this.avgNumberPerArrivalGroup = avgNumberPerArrivalGroup;
		this.floorNumber = floorNumber;
		arrivalGroupArray = new ArrivalGroupArray();
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

		while (CentralDispatcher.isKeepLooping()) {
			try {
				loop();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized boolean findOutIfThereIsGroupWaiting() {

		for (int i = 0; i < this.getArrivalGroupArray().size(); i++) {
			if (this.getArrivalGroupArray().get(i).isWaiting()) {
				return true;
			}
		}
		return false;
	}
	
	private Integer getHourOfTheDay(double nowTime) {

		double numSecondsPerDay = 24 * 60 * 60;
		
		nowTime = ((nowTime/1000*CentralDispatcher.getTimeFactor()) % numSecondsPerDay);
		
		double x = numSecondsPerDay*1/24;

		if (nowTime < x) {
			return 0;
		} else if (nowTime < numSecondsPerDay*2/24) {
			return 1;
		} else if (nowTime < numSecondsPerDay*3/24) {
			return 2;
		} else if (nowTime < numSecondsPerDay*4/24) {
			return 3;
		} else if (nowTime < numSecondsPerDay*5/24) {
			return 4;
		} else if (nowTime < numSecondsPerDay*6/24) {
			return 5;
		} else if (nowTime < numSecondsPerDay*7/24) {
			return 6;
		} else if (nowTime < numSecondsPerDay*8/24) {
			return 7;
		} else if (nowTime < numSecondsPerDay*9/24) {
			return 8;
		} else if (nowTime < numSecondsPerDay*10/24) {
			return 9;
		} else if (nowTime < numSecondsPerDay*11/24) {
			return 10;
		} else if (nowTime < numSecondsPerDay*12/24) {
			return 11;
		} else if (nowTime < numSecondsPerDay*13/24) {
			return 12;
		} else if (nowTime < numSecondsPerDay*14/24) {
			return 13;
		} else if (nowTime < numSecondsPerDay*15/24) {
			return 14;
		} else if (nowTime < numSecondsPerDay*16/24) {
			return 15;
		} else if (nowTime < numSecondsPerDay*17/24) {
			return 16;
		} else if (nowTime < numSecondsPerDay*18/24) {
			return 17;
		} else if (nowTime < numSecondsPerDay*19/24) {
			return 18;
		} else if (nowTime < numSecondsPerDay*20/24) {
			return 19;
		} else if (nowTime < numSecondsPerDay*21/24) {
			return 20;
		} else if (nowTime < numSecondsPerDay*22/24) {
			return 21;
		} else if (nowTime < numSecondsPerDay*23/24) {
			return 22;
		} else if (nowTime < numSecondsPerDay*24/24) {
			return 23;
		} 
		else{
			return null;
		}

		
	}
	
	
	
	

	private void loop() throws InterruptedException {

		Integer countPplWaiting = this.getCurrentNumberWaiting();
		Integer countPplWaitingUp = this.getCurrentNumberWaitingToGoUp();
		Integer countPplWaitingDown = this.getCurrentNumberWaitingToGoDown();

		String waiting = countPplWaiting.toString() + " "
				+ countPplWaitingUp.toString() + " "
				+ countPplWaitingDown.toString();

		setChanged();
		notifyObservers(waiting);

		int randomFloor = (int) (Math.random() * CentralDispatcher
				.getNumberOfFloors());
		if (randomFloor == this.getFloorNumber()) {
			return;
		}
		int randomNumberOfPeople = RandomVariableGenerator.getNextGroupSize(3,
				1, 0);
		
		double nowTime = System.nanoTime()/1000000 - CentralDispatcher.getStartOfSim();
		
		double sleepTime = getHourOfTheDay(nowTime);

		double interArrivalTimeRand = RandomVariableGenerator
				.generateExponentialRandomVariable((long) (this.interArrivalTime / CentralDispatcher.getTimeFactor()));

		Thread.sleep((long) interArrivalTimeRand);
		ArrivalGroup ag = new ArrivalGroup(System.nanoTime() / 1000000,
				randomNumberOfPeople, randomFloor, this.getFloorNumber());
		this.getArrivalGroupArray().add(ag);

		// here we see if there is already an untasked elevator on the floor
		Elevator[] temp = CentralDispatcher.getElevatorArray();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].getCurrentFloor() == this.getFloorNumber()
					&& temp[i].isHasTask() == false
					&& temp[i].isStoppedOnCurrentFloor()) {

				// make the elevator wait here, lock the elevator, make
				// unavailable...
				if (temp[i].getCurrentNumberOfPeopleSpaces() != temp[i]
						.getCapacity()) {
					String problem = "Untasked elevator has people on it while stopped on a floor!! lame...";
					CentralDispatcher.insertIntoDebuggingTable(
							CentralDispatcher.getRunNumber(), CentralDispatcher
									.getNumberOfFloors(), CentralDispatcher
									.getNumberOfElevators(), problem,
							CentralDispatcher.getCurrentTime() / 1000, this
									.getClass().getName());
				}
				int groupSize = ag.getNumberOfPeopleInGroup();
				if (temp[i].getCurrentNumberOfPeopleSpaces() >= groupSize) {
					temp[i].setHasTask(true);
					// CentralDispatcher.getElevatorThreadArray()[i].sleep((long)(groupSize*2000/CentralDispatcher.getTimeFactor()));
					ag.setWaiting(false);
					ag.setCumulativeTimeOfWaiting(System.nanoTime() / 1000000
							- ag.getTimeOfArrival());
					ag.setTimeFirstBoarding(System.nanoTime() / 1000000);
					temp[i].getGroupsOfPassengers().add(ag);
					return;
				}
			}

		}
		// perhaps we don't need the following section
		// we find a tasked elevator stopped on the same floor, that has space
		// and is going the right direction
		// temp = CentralDispatcher.getElevatorArray();
		// for(int i = 0; i < temp.length; i++){
		// //basic/preliminary conditions to add people
		// String direction = "";
		// if( temp[i].isGoingUp()){
		// direction = "up";
		// }
		// else if(temp[i].isGoingDown()){
		// direction = "down";
		// }
		// else{
		// //problem...
		// }
		// if(temp[i].getCurrentFloor() == this.getFloorNumber() &&
		// temp[i].isAvailable() && temp[i].isStoppedOnCurrentFloor() &&
		// temp[i].getCurrentNumberOfPeopleSpaces() >= 1+
		// ag.getNumberOfPeopleInGroup()
		// && ((temp[i].isGoingDown() && ag.getDirection().equals("down")) ||
		// (temp[i].isGoingUp() && ag.getDirection().equals("up")))){
		// //here we check to see if there are people waiting for this elevator
		// already in front the newest group
		// for(int j = 0; j < this.getArrivalGroupArray().size(); j++){
		// if(this.getArrivalGroupArray().get(j).isWaiting() &&
		// this.getArrivalGroupArray().get(j).getDirection().equals(direction));
		// }
		// temp[i].setHasTask(true);
		// int groupSize = ag.getNumberOfPeopleInGroup();
		// CentralDispatcher.getElevatorThreadArray()[i].sleep((long)(groupSize*2000/CentralDispatcher.getTimeFactor()));
		// ag.setWaiting(false);
		// ag.setCumulativeTimeOfWaiting(System.nanoTime()/1000000-ag.getTimeOfArrival());
		// ag.setTimeFirstBoarding(System.nanoTime()/1000000);
		// temp[i].getGroupsOfPassengers().add(ag);
		// temp[i].setHasTask(true);
		// return;
		// }
		// }

		// if we can't find an elevator on the same floor, then we send the
		// Central Dispatcher a new request to find us an elevator to serve
		// these new people waiting!
		// here we only add new elements to the request array if there isn't a
		// redundancy.

		// CentralDispatcher.ra.ralock();

		CentralDispatcher.addToRequestArray(ag);

		countPplWaiting = this.getCurrentNumberWaiting();
		countPplWaitingUp = this.getCurrentNumberWaitingToGoUp();
		countPplWaitingDown = this.getCurrentNumberWaitingToGoDown();

		waiting = countPplWaiting.toString() + " "
				+ countPplWaitingUp.toString() + " "
				+ countPplWaitingDown.toString();

		setChanged();
		notifyObservers(waiting);

	}

}
