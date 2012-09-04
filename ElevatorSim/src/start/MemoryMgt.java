package start;



/**
 * Class MemoryMgt contains methods to produce heap statistics as well as manage heap cleanup. 
 * @author Alex Wegener
 * August 18, 2010
 *
 */
public class MemoryMgt {
	
	/**
	 * Holds the numerical value for a byte
	 */
	int b = 1;
	/**
	 * Holds the numerical value for a kilobyte
	 */
	int kb = 1024;
	/**
	 * Holds the numerical value for a megabyte
	 */
	int mb = 1024*1024;
	/**
	 * Holds the current unit value.  Used as a conversion factor when calculating space.
	 */
	int currentUnitValue = -1;
	/**
	 * Holds the current unit name
	 */
	String currentUnitName = "";
	
	/**
	 * Constructor for MemoryMgt.  Calls manageMode to set the currentMode for Heap Analysis
	 * @param mode - Possible modes are 0, 1, and 2.  The modes in order stand for b, kb, and mb. 
	 */
	public MemoryMgt(int mode){
		// Set the current mode
		manageMode(mode);
	}
	/**
	 * Private method manageMode sets the necessary data members to their appropriate values
	 * based on the int mode parameter.
	 * @param mode - Possible options
	 */
	private void manageMode(int mode){
		switch (mode){
		// byte mode
		case 0:
			currentUnitValue = b;
			currentUnitName = "b";
			break;
		// kb mode
		case 1:
			currentUnitValue = kb;
			currentUnitName = "kb";
			break;
		// mb mode
		case 2:
			currentUnitValue = mb;
			currentUnitName = "mb";
			break;
		default:
			System.out.println("Error In manageMode: Invalid mode parameter");
		}
	}
	/**
	 * Getter function for the currentUnitValue
	 * @return - the int currentUnitValue
	 */
	public int getCurrentUnitValue(){
		return currentUnitValue;
	}
	/**
	 * Getter function for the currentUnitName
	 * @return - the String currentUnitName
	 */
	public String getCurrentUnitName(){
		return currentUnitName;
	}
	/**
	 * Setter method to set the current mode in the MemoryMgt class
	 * @param mode - int mode with possible values 0 (b), 1 (kb), and 2(mb)
	 */
	public void setMode(int mode){
		manageMode(mode);
	}
	/**
	 * getUsed returns a long of the amount of current heap space used.
	 * @return - the amount of current heap space used
	 */
	public long getUsedLong(){
		Runtime runtime = Runtime.getRuntime();
		return ((runtime.totalMemory() - runtime.freeMemory())/currentUnitValue);
	}
	/**
	 * Converts the current ammount of heap space used into an integer.
	 * @return int convertedLong
	 */
	public int getUsedInt(){
		Runtime runtime = Runtime.getRuntime();
		long convertLong = ((runtime.totalMemory() - runtime.freeMemory())/currentUnitValue);
		return ((int)convertLong);

	}
	/**
	 * Allows for a simple check of the current percentage.
	 * @return a rounded integer of the current percentage float.
	 */
	public int getCurrentPercentage(){
		Runtime runtime = Runtime.getRuntime();
		long top = runtime.totalMemory() - runtime.freeMemory();
		long bottom = runtime.totalMemory();
		float floatTop = ((float)top);
		float floatBottom = ((float)bottom);
		float createdPercentage = (float) ((floatTop/floatBottom)*100.0);
		return Math.round(createdPercentage);
	}
	/**
	 * Print Function which calculates the amount of memory currently used.
	 */
	/* XXXXXXXXXXXXXXXXXXXXX Printing Methods XXXXXXXXXXXXXXXXXXXX */
	public void printUsedMemory(){
		//Getting the runtime reference from system
		Runtime runtime = Runtime.getRuntime();
		
		//Print out heap statistic
		System.out.println("##### Heap Statistics ["+ currentUnitName + "] #####");
		System.out.println("Used Memory:" + (runtime.totalMemory() - runtime.freeMemory()) / currentUnitValue);
	}
	
	/**
	 * Print function which calculates the amount of free memory.
	 */
	public void printFreeMemory(){
		//Getting the runtime reference from system
		Runtime runtime = Runtime.getRuntime();
		
		//Print out heap statistic
		System.out.println("##### Heap Statistics ["+ currentUnitName + "] #####");
		System.out.println("Free Memory:" + runtime.freeMemory() / currentUnitValue);
	}
	/**
	 * Print function which calculates the total amount of memory.
	 */
	public void printTotalMemory(){
		//Getting the runtime reference from system
		Runtime runtime = Runtime.getRuntime();
		
		//Print out heap statistic
		System.out.println("##### Heap Statistics ["+ currentUnitName + "] #####");
		System.out.println("Total Memory:" + runtime.totalMemory() / currentUnitValue);
	}
	/**
	 * Print function which calculates the maximum amount of memory.
	 */
	public void printMaxMemory(){
		//Getting the runtime reference from system
		Runtime runtime = Runtime.getRuntime();
		
		//Print out heap statistic
		System.out.println("##### Heap Statistics ["+ currentUnitName + "] #####");
		System.out.println("Max Memory:" + runtime.maxMemory() / currentUnitValue);
	}
	/**
	 * Print function which calculates all possible statistics.
	 */
	public void printCompleteStatistics(){
		//Getting the runtime reference from system
		Runtime runtime = Runtime.getRuntime();
		
		//Print out all heap statistics
		System.out.println("##### Heap Statistics ["+ currentUnitName + "] #####");
		System.out.println("Used Memory:" + (runtime.totalMemory() - runtime.freeMemory()) / currentUnitValue);
		System.out.println("Free Memory:" + runtime.freeMemory() / currentUnitValue);
		System.out.println("Total Memory:" + runtime.totalMemory() / currentUnitValue);
		System.out.println("Max Memory:" + runtime.maxMemory() / currentUnitValue);

	}
	
	/**
	 * Most complete method of cleaning the heap space.  Near instantaneous implementation when called.
	 */
	/* XXXXXXXXXXXXXXXXXXX Heap Cleaning methods XXXXXXXXXXXXXXXXXXXX */
	public void cleanMemory(){
		this.runFinializationOnObjects();
		this.runGarbageCollection();
	}
	/**
	 * Forces the objects that are unreachable to run their finalization method if they have one.
	 * Allows those objects to be collected.
	 */
	public void runFinializationOnObjects(){
		Runtime runtime = Runtime.getRuntime();
		runtime.runFinalization();
	}
	/**
	 * Manual call to the garbage collector
	 */
	public void runGarbageCollection(){
		Runtime runtime = Runtime.getRuntime();
		runtime.gc();
	}
	/**
	 * isFull is a method to allow the user to enter in a percentage and test whether the current
	 * heap size is equal or exceeds that limit.
	 * 
	 * An example of percentage: if the int 75 was the param value for percentage then 
	 * isFull would return true if the heap size was at 75 percent full or higher.
	 * @param percentage - the int percentage used within the test.
	 * @return - the boolean isFull which indicates (depending on the percentage) whether or not the heap is full.
	 */
	/* XXXXXXXXXXXXXXXXXXXXX Runtime Testing Methods XXXXXXXXXXXXXXXXXXXXXX */
	public boolean isFull(int percentage){
		boolean isFull = false;
		Runtime runtime = Runtime.getRuntime();
		long top = runtime.totalMemory() - runtime.freeMemory();
		long bottom = runtime.totalMemory();
		float floatTop = ((float)top);
		float floatBottom = ((float)bottom);
		float createdPercentage = (float) ((floatTop/floatBottom)*100.0);
		int testedPercentage = Math.round(createdPercentage);
		if(testedPercentage>=percentage){
			isFull = true;
		}
		return isFull;
	}
}