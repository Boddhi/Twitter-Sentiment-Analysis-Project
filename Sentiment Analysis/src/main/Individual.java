package main;
import java.util.HashMap;
import java.util.Map;

public class Individual {

	static int defaultGeneLength = 64;
	private byte[] genes = new byte[defaultGeneLength];

	Map<String, Integer> data = new HashMap<String, Integer>();

	// Cache
	private int fitness = 0;

	public Individual() {
	}

	// Create a random individual
	public void generateIndividual(String root) {
		String[] words = root.split("\\W+");
		for (int i = 0; i < words.length; i++) {
			data.put(words[i].toLowerCase(), 0);
		}
	}

	public void generateIndividual() {

	}

	/* Getters and setters */
	public static void setDefaultGeneLength(int length) {
		defaultGeneLength = length;
	}

	public int getGene(String key) {
		if (data.get(key) != null)
			return data.get(key);
		return 0;
	}

	public void setGene(String key, int value) {
		data.put(key, value);
	}

	public void updateGene(String key, int value) {
		if (data.get(key) != null) {
			data.put(key, data.get(key) + value);
		} else {
			data.put(key, 1);
		}
	}

	/* Public methods */
	public int size() {
		return data.size();
	}

	public void feed(String input, int value) {
		String line = new String(input);
		String[] words = line.split("\\s+");
		for (int j = 0; j < words.length; j++) {
			if (getGene(words[j]) != 0) {
				setGene(words[j],getGene(words[j]) + value);
			} else {
				setGene(words[j], value);
			}
		}
	}

	public int test(String sample) {
		String[] words = sample.split("\\s+");
		int total = 0;
		for (int i = 0; i < words.length; i++) {
			total += getGene(words[i]);
		}
		return total;
	}

	/*
	 * public int getFitness() { if (fitness == 0) { fitness =
	 * FitnessCalc.getFitness(this); } return fitness; }
	 */

	@Override
	public String toString() {
		return data.toString();
	}
}
