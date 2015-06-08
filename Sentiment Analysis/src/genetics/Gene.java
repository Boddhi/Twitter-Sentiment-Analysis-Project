package genetics;

/**
 * Gene class.
 * 
 * @author Tristan Monger
 *
 */
public class Gene {
	public int positiveCount = 0;
	public int appearanceCount = 0; 
	
	/**
	 * Default constructor.
	 */
	public Gene(){
		
	}
	
	/**
	 * Constructor. Sets the default stats as either 1/1 or 0/1.
	 * @param seed either 0 or 1
	 */
	public Gene(int seed){
		update(seed);
	}
	
	/**
	 * Constructor. Builds a Gene with the given data.
	 * @param pC the total positive count
	 * @param aC the total appearance count
	 */
	public Gene(int pC, int aC){
		updateCounts(pC,aC);
	}
	
	/**
	 * Returns the percentage of appearances that were positive.
	 * 
	 * @return the positiveCount percentage
	 */
	public double getSuccessRate(){
		if(positiveCount == 0 && appearanceCount == 0) return -1;
		return (double)positiveCount/(double)appearanceCount;
	}
	
	/**
	 * Returns the Wilson Score (the center of the Wilson Interval) of the Gene, approximating the likely true value of the Gene.
	 * It essentially is a weighted average, where averages based on larger samples are weighted more strongly. <p>
	 * Source: http://en.wikipedia.org/wiki/Binomial_proportion_confidence_interval#Wilson_score_interval
	 * @return the Wilson Score
	 */
	public double getWilsonScore(){
		double mean = getSuccessRate();
		double z = 1.96; //95% confidence interval
		double ans = (mean + z*z/(2*appearanceCount))/(1 + z*z/appearanceCount);
		return ans;
	}
	
	/**
	 * Updates the data with either a success or failure.
	 * 
	 * @param feedback 1 for success, 0 for failure
	 */
	public void update(int feedback){
		this.appearanceCount++;
		if(feedback == 1) this.positiveCount++;
	}	
	
	/**
	 * Returns the number of correct positive predictions the Gene has made.
	 * @return the number of successes
	 */
	public int getPCount(){
		return this.positiveCount;
	}
	
	/**
	 * Returns the number of times the Gene's string has been seen.
	 * @return the number of appearances
	 */
	public int getTCount(){
		return this.appearanceCount;
	}
	
	/**
	 * Adds given values to positive and total tallies.
	 * 
	 * @param pUp the change in positive count
	 * @param tUp the change in total count
	 */
	public void updateCounts(int pUp, int tUp){
		this.positiveCount+=pUp;
		this.appearanceCount+=tUp;
	}
	
	/**
	 * Returns a string showing the success and total counts of the Gene.
	 * @return the String representation of the Gene
	 */
	public String toString(){
		return this.getPCount() + " " + this.getTCount();
	}
	
}
