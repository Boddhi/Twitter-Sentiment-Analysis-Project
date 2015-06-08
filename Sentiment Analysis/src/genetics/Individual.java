package genetics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * The Individual class. For use in the genetic processes of the Population and Algorithm classes.
 * @author Tristan Monger
 *
 */
public class Individual implements Comparable<Individual>{

	Map<String, Gene> data = new HashMap<String, Gene>();
	String[] negators = {"not", "least", "isn't", "neither", "nor","never","non", "anti"};

	private double fitness = 0;

	/**
	 * Constructor.
	 */
	public Individual() {
	}

	/**
	 * Creates a new individual from an input string. All the words of the input string are added to the Individual's vocabulary.
	 * @param root the string to build from
	 */
	public void generateIndividual(String root) {
		String[] words = root.split("[^a-zA-Z0-9']");
		for (int i = 0; i < words.length; i++) {
			data.put(words[i].toLowerCase(), new Gene());
		}
	}

	/**
	 * Returns the Wilson score of the gene associated with given key, or 0 if no such gene exists.
	 * @param key
	 * @return gene or 0
	 */
	public double getGeneSuccess(String key) {
		if (data.get(key) != null)
			return data.get(key).getWilsonScore();
		return 0;
	}

	/**
	 * Returns the Gene associated with the given string.
	 * @param key the string key
	 * @return the Gene association
	 */
	public Gene getGene(String key){
		return data.get(key);
	}
	
	/**
	 * Creates a new association of the given string to the given Gene.
	 * @param key the string
	 * @param gene the value to associate with the key
	 */
	public void setGene(String key, Gene gene) {
		data.put(key, gene);
	}

	/**
	 * Updates the Gene associated with the given string with either a success or failure.
	 * @param key the string key
	 * @param value 0 for failure, 1 for success
	 */
	public void updateGene(String key, int value) {
		if (data.get(key) != null) {
			data.get(key).update(value);
		} else {
			data.put(key, new Gene(value));
		}
	}

	/**
	 * Returns the amount of string-gene associations the Individual has.
	 * @return the number of string-gene associations
	 */
	public int size() {
		return data.size();
	}

	/**
	 * Teaches the input new associations between the words in the input and the given value.
	 * @param input the string to teach
	 * @param value the overall sentiment of the given string (1 - positive, 0 - neutral, -1 - negative)
	 */
	public void feed(String input, int value) {
		String line = new String(input);
		String[] words = line.toLowerCase().split("[^a-zA-Z0-9']"); //Removes punctuation, except apostrophes
		Gene cur;
		for (int j = 0; j < words.length; j++) {
			cur = getGene(words[j]);
			if (cur != null) {
				if(value == -1) 
					cur.updateCounts(0, cur.getTCount());
				else if(value == 1) 
					cur.updateCounts((cur.getTCount()-cur.getPCount())/2+5, 5);
				else
					cur.updateCounts(0, 2*cur.getPCount()-cur.getTCount());
				setGene(words[j],cur);
			} else {
				if(value == -1) 
					setGene(words[j], new Gene(2, 10));
				else if(value == 1) 
					setGene(words[j], new Gene(8, 10));
				else
					setGene(words[j], new Gene(10, 20));
			}
		}
	}
	
	/**
	 * For use with user inputs only. <p>
	 * Strongly readjusts data to match String-Gene associations to user's input.
	 * @param input the words to change
	 * @param value the adjustment to make to the Gene values (1 - positive, 0 - neutral, -1 - negative)
	 */
	public void forceFeed(String input, int value){
		String line = new String(input);
		String[] words = line.toLowerCase().split("[^a-zA-Z0-9']"); //Remove punctuation
		Gene cur;
		for (int j = 0; j < words.length; j++) {
			cur = getGene(words[j]);
			if (cur != null) {
				if(value == -1) 
					cur.updateCounts(0, cur.getTCount());
				else if(value == 1) 
					cur.updateCounts((cur.getTCount()-cur.getPCount())/2+5, 5);
				else
					cur.updateCounts(0, 2*cur.getPCount()-cur.getTCount());
				setGene(words[j],cur);
			} else {
				if(value == -1) 
					setGene(words[j], new Gene(2, 10));
				else if(value == 1) 
					setGene(words[j], new Gene(8, 10));
				else
					setGene(words[j], new Gene(10, 20));
			}
		}
	}

	/**
	 * Returns the analysis of a given string.
	 * @param sample the string to analyze
	 * @return the estimation of the strings positiveness
	 */
	public double test(String sample) {
		String[] words = sample.toLowerCase().split("[^a-zA-Z0-9']");
		double sigTotal = 0,total = 0, min = 10, max = 0, wScore,sigCount = 0;
		Boolean flip = false;
		Gene cur;
		for (int i = 0; i < words.length; i++) {
			cur = getGene(words[i]);
			if(Arrays.asList(negators).contains(words[i])){
				flip = true;
			}
			if(cur == null){
				total+= 0.5;
				continue;
			}
			wScore = cur.getWilsonScore();
			if(Math.abs(wScore - 0.5) > 0.1){
				if(flip == true){
					wScore = 1-wScore;
					flip = false;
				}
				sigTotal += wScore;
				sigCount++;
			}
			total+=wScore;
			min = Math.min(min,wScore);
			max = Math.max(max,wScore);
		}
		if(sigCount == 0) {
			return total/words.length;
		}
		return sigTotal/=sigCount;
	}

	/**
	 * Returns the Wilson Score estimation of the quality of data stored by the Individual. <p>
	 * Wilson Score Source: http://en.wikipedia.org/wiki/Binomial_proportion_confidence_interval#Wilson_score_interval
	 * @return the Wilson Score fitness calculation
	 */
	public double getFitness() {
		fitness = data.size();
		double cur, pCount= 0, nCount = 0, tCount = 0, min = 10, max = -2;
		for (String key : data.keySet()) {			
			cur = data.get(key).getWilsonScore();
			min = Math.min(cur, min);
			max = Math.max(cur, max);
			if(cur < 0.4) nCount++;
			else if(cur > 0.6) pCount++;
			tCount++;
		}
		double mean = ((double)(pCount+nCount)/(double)tCount);
		double z = 1.96; //95% confidence interval
		fitness = (mean + z*z/(2*tCount))/(1 + z*z/tCount);
		return fitness;
	}

	/**
	 * Returns the String representation of the word-gene associations of the Individual.
	 * @return the String representation
	 */
	@Override
	public String toString() {
		return data.toString();
	}

	/**
	 * Compares two Individuals by fitness. Returns a negative integer, 0, or positive integer if this Individual's fitness is lesser, equal, or greater than the given Individual.
	 * @return a negative integer, 0, or positive integer if this Individual's fitness is lesser, equal, or greater than the given Individual
	 */
	@Override
	public int compareTo(Individual other) {
		return Double.compare(this.getFitness(), other.getFitness());
	}
}
