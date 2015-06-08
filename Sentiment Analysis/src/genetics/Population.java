package genetics;
import java.io.*;
import java.util.Arrays;

/**
 * Population class.
 * 
 * @author Tristan Monger
 *
 */
public class Population {

	Individual[] individuals;

	/**
	 * Constructor. Builds a population of given size and, if called for, initializes the individuals with a basic string.
	 * @param populationSize the number of individuals to put in the population
	 * @param initialize if true - seeds the individuals with a short string
	 */
	public Population(int populationSize, boolean initialize) {
		individuals = new Individual[populationSize];
		if (initialize) {
			for (int i = 0; i < getSize(); i++) {
				Individual newIndividual = new Individual();
				newIndividual.generateIndividual("Hello world! Goodbye world.");
				saveIndividual(i, newIndividual);
			}
		}
	}

	/**
	 * Loads an existing population from a text file.
	 * @param filePath
	 */
	public Population(String filePath) {
		File file = new File(filePath);
		FileReader fw;
		try {
			fw = new FileReader(file.getAbsoluteFile());
			BufferedReader bw = new BufferedReader(fw);
			individuals = new Individual[Integer.parseInt(bw.readLine())];
			for (int i = 0; i < getSize(); i++) {
				String line = new String(bw.readLine());
				String[] words = line.split("[^a-zA-Z0-9']");
				Individual newGuy = new Individual();
				for (int j = 0; j < words.length; j += 3) {
					newGuy.setGene(words[j], new Gene(Integer.parseInt(words[j + 1]),Integer.parseInt(words[j + 2])));
				}
				saveIndividual(i, newGuy);
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Teaches the population data from a text file.
	 * @param filePath
	 */
	public void feed(String filePath) {
		File file = new File(filePath);
		FileReader fw;
		int len;
		try {
			fw = new FileReader(file.getAbsoluteFile());
			BufferedReader bw = new BufferedReader(fw);
			len = Integer.parseInt(bw.readLine());
			for (int i = 0; i < len; i++) {
				String line = new String(bw.readLine());
				String[] words = line.toLowerCase().split("[^a-zA-Z0-9']");
				for (int j = 0; j < words.length; j++) {
					for (int k = 0; k < individuals.length; k++) {
						individuals[k].feed(words[j], 1);
					}
				}
			}
			len = Integer.parseInt(bw.readLine());
			for (int i = 0; i < len; i++) {
				String line = new String(bw.readLine());
				String[] words = line.toLowerCase().split("[^a-zA-Z0-9']");
				for (int j = 0; j < words.length; j++) {
					for (int k = 0; k < individuals.length; k++) {
						individuals[k].feed(words[j], -1);
					}
				}
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Teaches the population the value to associate with the words of a given String.
	 * @param input the string to teach
	 * @param value the value to associate
	 */
	public void feed(String input, int value) {
		String line = new String(input);
		String[] words = line.split("[^a-zA-Z0-9']");
		for (int j = 0; j < words.length; j++) {
			for (int k = 0; k < individuals.length; k++) {
				individuals[k].feed(words[j], value);
			}
		}
	}

	/**
	 * Returns the Individual stored at the given index.
	 * @param index the index to query
	 * @return the Individual at the index
	 */
	public Individual getIndividual(int index) {
		return individuals[index];
	}

	public Individual getFittest(int dummy) {
		Individual fittest = individuals[0];
		return fittest;
	}

	/**
	 * Finds the fittest individual in the population.
	 * @return the fittest individual
	 */
	public Individual getFittest() {
		Arrays.sort(individuals);
		return individuals[individuals.length-1];
	}

	/**
	 * Returns the number of Individuals in the population.
	 * @return the population size
	 */
	public int getSize() {
		return individuals.length;
	}

	/**
	 * Assigns an element of the individuals array to the given individual.
	 * @param index the index of the element to assign
	 * @param indiv the value of the individual to store
	 */
	public void saveIndividual(int index, Individual indiv) {
		individuals[index] = indiv;
	}

	/**
	 * Saves the population to a text file.
	 * @param filePath
	 */
	public void saveToFile(String filePath) {
		File file = new File(filePath);
		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(getSize() + "\n");
			for (int i = 0; i < getSize(); i++) {
				for (String key : individuals[i].data.keySet()) {
					if(key.length() < 1) continue;
					bw.write(key + " " + individuals[i].data.get(key).toString() + " ");
				}
				bw.write("\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sorts the array of individuals by fitness.
	 */
	public void sort(){
		Arrays.sort(individuals);
	}
	
}