package start;


public class ElevatorSimulationMainController {

	private int runNumber;
	public static int[] numberOfElevators = {13,16};
	public static int numberOfFloors = 23;
	private volatile long lengthOfSim;
	private int[] timeFactor = {10,20,20};
	private int[] totalServedArray;
	private volatile MemoryMgt mgmt;
	private int[] numberOfEmptySpacesToUseGoingToVariable = { 2, 3 }; // 1
	private int[] numberOfEmptySpacesToUsePassingByVariable = { 2, 3 };// 2
	private int[] numberOfEmptySpacesToUseSameFloorVariable = { 2, 3 }; // 3
	private int[] capacityThresholdVariable = { 2, 3 }; // 4
	private int[] numberOfFloorsDifference = { 2, 3 }; // 5
	private int[] maxDistanceForUntaskedVariable = { 5, 6 }; // 6
	private int[] countUntaskedVariable = { 0, 1 }; // 7
	private int[] appendDistanceVariable = { 5, 6 }; // 8
	private int[] distanceAlreadyGoingVariable = { 4, 5}; // 9
	private int[] capacityVariable = {9,10};

	public static Integer numberOfRunsTotal = null;
	
	public int getRunNumber() {
		return runNumber;
	}
	
	public Integer getTotalNumberOfRuns(){
		
		Integer x = null;
		
		x = this.getNumberOfElevators().length*
				this.getTimeFactor().length*
				this.getNumberOfEmptySpacesToUseGoingToVariable().length*
				this.getNumberOfEmptySpacesToUsePassingByVariable().length*
				this.getNumberOfEmptySpacesToUseSameFloorVariable().length*
				this.getCapacityThresholdVariable().length*
				this.getNumberOfFloorsDifference().length*
				this.getMaxDistanceForUntaskedVariable().length*
				this.getCountUntaskedVariable().length*
				this.getAppendDistanceVariable().length*
				this.getDistanceAlreadyGoingVariable().length*
				this.getCapacityVariable().length;
		
		return x;
		
	}

	public void setRunNumber(int runNumber) {
		this.runNumber = runNumber;
	}

	public int[] getCapacityVariable() {
		return capacityVariable;
	}

	public void setCapacityVariable(int[] capacity) {
		this.capacityVariable = capacity;
	}
	public int[] getNumberOfEmptySpacesToUseGoingToVariable() {
		return numberOfEmptySpacesToUseGoingToVariable;
	}

	public void setNumberOfEmptySpacesToUseGoingToVariable(
			int[] numberOfEmptySpacesToUseGoingToVariable) {
		this.numberOfEmptySpacesToUseGoingToVariable = numberOfEmptySpacesToUseGoingToVariable;
	}

	public int[] getNumberOfEmptySpacesToUsePassingByVariable() {
		return numberOfEmptySpacesToUsePassingByVariable;
	}

	public void setNumberOfEmptySpacesToUsePassingByVariable(
			int[] numberOfEmptySpacesToUsePassingByVariable) {
		this.numberOfEmptySpacesToUsePassingByVariable = numberOfEmptySpacesToUsePassingByVariable;
	}

	public int[] getNumberOfEmptySpacesToUseSameFloorVariable() {
		return numberOfEmptySpacesToUseSameFloorVariable;
	}

	public void setNumberOfEmptySpacesToUseSameFloorVariable(
			int[] numberOfEmptySpacesToUseSameFloorVariable) {
		this.numberOfEmptySpacesToUseSameFloorVariable = numberOfEmptySpacesToUseSameFloorVariable;
	}

	public int[] getCapacityThresholdVariable() {
		return capacityThresholdVariable;
	}

	public void setCapacityThresholdVariable(int[] capacityThresholdVariable) {
		this.capacityThresholdVariable = capacityThresholdVariable;
	}

	public int[] getNumberOfFloorsDifference() {
		return numberOfFloorsDifference;
	}

	public void setNumberOfFloorsDifference(int[] numberOfFloorsDifference) {
		this.numberOfFloorsDifference = numberOfFloorsDifference;
	}

	public int[] getMaxDistanceForUntaskedVariable() {
		return maxDistanceForUntaskedVariable;
	}

	public void setMaxDistanceForUntaskedVariable(
			int[] maxDistanceForUntaskedVariable) {
		this.maxDistanceForUntaskedVariable = maxDistanceForUntaskedVariable;
	}

	public int[] getCountUntaskedVariable() {
		return countUntaskedVariable;
	}

	public void setCountUntaskedVariable(int[] countUntaskedVariable) {
		this.countUntaskedVariable = countUntaskedVariable;
	}

	public int[] getAppendDistanceVariable() {
		return appendDistanceVariable;
	}

	public void setAppendDistanceVariable(int[] appendDistanceVariable) {
		this.appendDistanceVariable = appendDistanceVariable;
	}

	public int[] getDistanceAlreadyGoingVariable() {
		return distanceAlreadyGoingVariable;
	}

	public void setDistanceAlreadyGoingVariable(
			int[] distanceAlreadyGoingVariable) {
		this.distanceAlreadyGoingVariable = distanceAlreadyGoingVariable;
	}

	public int[] getNumberOfElevators() {
		return numberOfElevators;
	}

	public void setNumberOfElevators(int[] numberOfElevators) {
		this.numberOfElevators = numberOfElevators;
	}

	public int getNumberOfFloors() {
		return numberOfFloors;
	}

	public void setNumberOfFloors(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}

	public long getLengthOfSim() {
		return lengthOfSim;
	}

	public void setLengthOfSim(long lengthOfSim) {
		this.lengthOfSim = lengthOfSim;
	}

	public int[] getTimeFactor() {
		return timeFactor;
	}

	public void setTimeFactor(int[] timeFactor) {
		this.timeFactor = timeFactor;
	}

	public int[] getTotalServedArray() {
		return totalServedArray;
	}

	public void setTotalServedArray(int[] totalServedArray) {
		this.totalServedArray = totalServedArray;
	}

	public MemoryMgt getMgmt() {
		return mgmt;
	}

	public void setMgmt(MemoryMgt mgmt) {
		this.mgmt = mgmt;
	}

	public ElevatorSimulationMainController() throws InterruptedException {

		numberOfRunsTotal = getTotalNumberOfRuns();
		totalServedArray = new int[timeFactor.length];
		mgmt = new MemoryMgt(0);

		CentralDispatcher cd = null;

		int k = 0;
		for (int i = 0; i < numberOfElevators.length; i++) {
			for (int j = 0; j < distanceAlreadyGoingVariable.length; j++) {
				for (int j2 = 0; j2 < appendDistanceVariable.length; j2++) {
					for (int l = 0; l < countUntaskedVariable.length; l++) {
						for (int l2 = 0; l2 < maxDistanceForUntaskedVariable.length; l2++) {
							for (int m = 0; m < numberOfFloorsDifference.length; m++) {
								for (int m2 = 0; m2 < numberOfEmptySpacesToUseGoingToVariable.length; m2++) {
									for (int n = 0; n < numberOfEmptySpacesToUsePassingByVariable.length; n++) {
										for (int n2 = 0; n2 < numberOfEmptySpacesToUseSameFloorVariable.length; n2++) {
											for (int o = 0; o < capacityThresholdVariable.length; o++) {
												for (int o2 = 0; o2 < capacityVariable.length; o2++) {
													for (int p = 0; p < timeFactor.length; p++) {
														k++;
														cd = new CentralDispatcher(
																k,
																numberOfFloors,
																numberOfElevators[i],
																capacityVariable[o2],
																distanceAlreadyGoingVariable[j],
																appendDistanceVariable[j2],
																countUntaskedVariable[l],
																maxDistanceForUntaskedVariable[l2],
																numberOfFloorsDifference[m],
																numberOfEmptySpacesToUseGoingToVariable[m2],
																numberOfEmptySpacesToUsePassingByVariable[n],
																numberOfEmptySpacesToUseSameFloorVariable[n2],
																capacityThresholdVariable[o],
																timeFactor[p]);
									
														CentralDispatcher.getEca().dispose();
														this.getMgmt()
																.cleanMemory();
														cd = null;
														this.getMgmt()
																.cleanMemory();
														Thread.sleep(2000);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

	}
}
