package start;
import java.lang.reflect.Method;
import java.util.Hashtable;


public class ElevatorSimulationMainController {



	private static int runNumber;
	private static int[] totalServedArray;
	private static volatile MemoryMgt mgmt;
	private static volatile long lengthOfSim;
	public static int[] numberOfElevators = {4,12};
	public static int[] numberOfFloors = {12};
	private static int[] timeFactor = {43,33,43};
	private static int[] numberOfEmptySpacesToUseGoingToVariable = { 2, 3 }; // 1
	private static int[] numberOfEmptySpacesToUsePassingByVariable = { 2, 3 };// 2
	private static int[] numberOfEmptySpacesToUseSameFloorVariable = { 2, 3 }; // 3
	private static int[] capacityThresholdVariable = { 2, 3 }; // 4
	private static int[] numberOfFloorsDifference = { 2, 3 }; // 5
	private static int[] maxDistanceForUntaskedVariable = { 5, 6 }; // 6
	private static int[] countUntaskedVariable = { 0, 1 }; // 7
	private static int[] appendDistanceVariable = { 5, 6 }; // 8
	private static int[] distanceAlreadyGoingVariable = { 4, 5}; // 9
	private static int[] capacityVariable = {9,10};

	public static Integer numberOfRunsTotal = null;
	
	public int getRunNumber() {
		return runNumber;
	}

	public static Integer getNumberOfRunsTotal() {
		return numberOfRunsTotal;
	}

	public static void setNumberOfRunsTotal(Integer numberOfRunsTotal) {
		ElevatorSimulationMainController.numberOfRunsTotal = numberOfRunsTotal;
	}

	public static void setNumberOfElevators(int[] numberOfElevators) {
		ElevatorSimulationMainController.numberOfElevators = numberOfElevators;
	}

	public static void setNumberOfFloors(int[] numberOfFloors) {
		ElevatorSimulationMainController.numberOfFloors = numberOfFloors;
	}

	public void setRunNumber(int runNumber) {
		ElevatorSimulationMainController.runNumber = runNumber;
	}

	public int[] getCapacityVariable() {
		return capacityVariable;
	}

	public void setCapacityVariable(int[] capacity) {
		ElevatorSimulationMainController.capacityVariable = capacity;
	}
	public int[] getNumberOfEmptySpacesToUseGoingToVariable() {
		return numberOfEmptySpacesToUseGoingToVariable;
	}

	public void setNumberOfEmptySpacesToUseGoingToVariable(
			int[] numberOfEmptySpacesToUseGoingToVariable) {
		ElevatorSimulationMainController.numberOfEmptySpacesToUseGoingToVariable = numberOfEmptySpacesToUseGoingToVariable;
	}

	public int[] getNumberOfEmptySpacesToUsePassingByVariable() {
		return numberOfEmptySpacesToUsePassingByVariable;
	}

	public void setNumberOfEmptySpacesToUsePassingByVariable(
			int[] numberOfEmptySpacesToUsePassingByVariable) {
		ElevatorSimulationMainController.numberOfEmptySpacesToUsePassingByVariable = numberOfEmptySpacesToUsePassingByVariable;
	}

	public int[] getNumberOfEmptySpacesToUseSameFloorVariable() {
		return numberOfEmptySpacesToUseSameFloorVariable;
	}

	public void setNumberOfEmptySpacesToUseSameFloorVariable(
			int[] numberOfEmptySpacesToUseSameFloorVariable) {
		ElevatorSimulationMainController.numberOfEmptySpacesToUseSameFloorVariable = numberOfEmptySpacesToUseSameFloorVariable;
	}

	public int[] getCapacityThresholdVariable() {
		return capacityThresholdVariable;
	}

	public void setCapacityThresholdVariable(int[] capacityThresholdVariable) {
		ElevatorSimulationMainController.capacityThresholdVariable = capacityThresholdVariable;
	}

	public int[] getNumberOfFloorsDifference() {
		return numberOfFloorsDifference;
	}

	public void setNumberOfFloorsDifference(int[] numberOfFloorsDifference) {
		ElevatorSimulationMainController.numberOfFloorsDifference = numberOfFloorsDifference;
	}

	public int[] getMaxDistanceForUntaskedVariable() {
		return maxDistanceForUntaskedVariable;
	}

	public void setMaxDistanceForUntaskedVariable(
			int[] maxDistanceForUntaskedVariable) {
		ElevatorSimulationMainController.maxDistanceForUntaskedVariable = maxDistanceForUntaskedVariable;
	}

	public int[] getCountUntaskedVariable() {
		return countUntaskedVariable;
	}

	public void setCountUntaskedVariable(int[] countUntaskedVariable) {
		ElevatorSimulationMainController.countUntaskedVariable = countUntaskedVariable;
	}

	public int[] getAppendDistanceVariable() {
		return appendDistanceVariable;
	}

	public void setAppendDistanceVariable(int[] appendDistanceVariable) {
		ElevatorSimulationMainController.appendDistanceVariable = appendDistanceVariable;
	}

	public int[] getDistanceAlreadyGoingVariable() {
		return distanceAlreadyGoingVariable;
	}

	public void setDistanceAlreadyGoingVariable(
			int[] distanceAlreadyGoingVariable) {
		ElevatorSimulationMainController.distanceAlreadyGoingVariable = distanceAlreadyGoingVariable;
	}

	public int[] getNumberOfElevators() {
		return numberOfElevators;
	}

	public int[] getNumberOfFloors() {
		return numberOfFloors;
	}


	public long getLengthOfSim() {
		return lengthOfSim;
	}

	public void setLengthOfSim(long lengthOfSim) {
		ElevatorSimulationMainController.lengthOfSim = lengthOfSim;
	}

	public int[] getTimeFactor() {
		return timeFactor;
	}

	public void setTimeFactor(int[] timeFactor) {
		ElevatorSimulationMainController.timeFactor = timeFactor;
	}

	public int[] getTotalServedArray() {
		return totalServedArray;
	}

	public void setTotalServedArray(int[] totalServedArray) {
		ElevatorSimulationMainController.totalServedArray = totalServedArray;
	}

	public static MemoryMgt getMgmt() {
		return mgmt;
	}

	public void setMgmt(MemoryMgt mgmt) {
		ElevatorSimulationMainController.mgmt = mgmt;
	}
	
	
	
	public ElevatorSimulationMainController(int[] numberOfElevators,
			int[] numberOfFloors, int[] timeFactor,
			int[] numberOfEmptySpacesToUseGoingToVariable,
			int[] numberOfEmptySpacesToUsePassingByVariable,
			int[] numberOfEmptySpacesToUseSameFloorVariable,
			int[] capacityThresholdVariable, int[] numberOfFloorsDifference,
			int[] maxDistanceForUntaskedVariable, int[] countUntaskedVariable,
			int[] appendDistanceVariable, int[] distanceAlreadyGoingVariable,
			int[] capacityVariable) {

		ElevatorSimulationMainController.numberOfElevators = numberOfElevators;
		ElevatorSimulationMainController.numberOfFloors =numberOfFloors;
		ElevatorSimulationMainController.timeFactor =timeFactor;
		ElevatorSimulationMainController.numberOfEmptySpacesToUseGoingToVariable =numberOfEmptySpacesToUseGoingToVariable;
		ElevatorSimulationMainController.numberOfEmptySpacesToUsePassingByVariable=numberOfEmptySpacesToUsePassingByVariable;
		ElevatorSimulationMainController.numberOfEmptySpacesToUseSameFloorVariable=numberOfEmptySpacesToUseSameFloorVariable;
		ElevatorSimulationMainController.capacityThresholdVariable=capacityThresholdVariable;
		ElevatorSimulationMainController.numberOfFloorsDifference =numberOfFloorsDifference;
		ElevatorSimulationMainController.maxDistanceForUntaskedVariable=maxDistanceForUntaskedVariable;
		ElevatorSimulationMainController.countUntaskedVariable=countUntaskedVariable;
		ElevatorSimulationMainController.appendDistanceVariable=appendDistanceVariable;
		ElevatorSimulationMainController.distanceAlreadyGoingVariable=distanceAlreadyGoingVariable;
		ElevatorSimulationMainController.capacityVariable=capacityVariable;
		
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
														Thread.sleep(1000);
														cd = new CentralDispatcher(
																k,
																numberOfFloors[0],
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
														cd.kickOff();
														CentralDispatcher.getEca().dispose();
														ElevatorSimulationMainController.getMgmt()
																.cleanMemory();
														cd = null;
														ElevatorSimulationMainController.getMgmt()
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
}
