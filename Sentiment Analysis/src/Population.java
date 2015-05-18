import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Population {

	Individual[] individuals;

	/*
	 * Constructors
	 */
	// Create a population
	public Population(int populationSize, boolean initialize) {
		individuals = new Individual[populationSize];
		// Initialize population
		if (initialize) {
			// Loop and create individuals
			for (int i = 0; i < getSize(); i++) {
				Individual newIndividual = new Individual();
				newIndividual.generateIndividual("Hello world! Goodbye world.");
				saveIndividual(i, newIndividual);
			}
		}
	}

	public Population(String filePath) {
		File file = new File(filePath);
		FileReader fw;
		try {
			fw = new FileReader(file.getAbsoluteFile());
			BufferedReader bw = new BufferedReader(fw);
			individuals = new Individual[Integer.parseInt(bw.readLine())];
			for (int i = 0; i < getSize(); i++) {
				String line = new String(bw.readLine());
				String[] words = line.split("\\s+");
				Individual newGuy = new Individual();
				for (int j = 0; j < words.length; j += 2) {
					newGuy.setGene(words[j], Integer.parseInt(words[j + 1]));
				}
				saveIndividual(i, newGuy);
				// Individual newIndividual = new Individual(line);
				// saveIndividual(i,newIndividual);
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
				String[] words = line.split("\\s+");
				for (int j = 0; j < words.length; j++) {
					for (int k = 0; k < individuals.length; k++) {
						if (individuals[k].getGene(words[j]) != 0) {
							individuals[k].setGene(words[j],
									individuals[k].getGene(words[j]) + 1);
						} else {
							individuals[k].setGene(words[j], 1);
						}
					}
				}
			}
			len = Integer.parseInt(bw.readLine());
			for (int i = 0; i < len; i++) {
				String line = new String(bw.readLine());
				String[] words = line.split("\\s+");
				for (int j = 0; j < words.length; j++) {
					for (int k = 0; k < individuals.length; k++) {
						if (individuals[k].getGene(words[j]) != 0) {
							individuals[k].setGene(words[j],
									individuals[k].getGene(words[j]) - 1);
						} else {
							individuals[k].setGene(words[j], -1);
						}
					}
				}
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void feed(String input, int value) {
		String line = new String(input);
		String[] words = line.split("\\s+");
		for (int j = 0; j < words.length; j++) {
			for (int k = 0; k < individuals.length; k++) {
				if (individuals[k].getGene(words[j]) != 0) {
					individuals[k].setGene(words[j],
							individuals[k].getGene(words[j]) + value);
				} else {
					individuals[k].setGene(words[j], value);
				}
			}
		}
	}

	/* Getters */
	public Individual getIndividual(int index) {
		return individuals[index];
	}

	public Individual getFittest(int dummy) {
		Individual fittest = individuals[0];
		return fittest;
	}

	public Individual getFittest() {
		Individual fittest = individuals[0];
		// Loop through individuals to find fittest
		for (int i = 0; i < getSize(); i++) {
			// if (fittest.getFitness() <= getIndividual(i).getFitness()) {
			fittest = getIndividual(i);
			// }
		}
		return fittest;
	}

	/* Public methods */
	// Get population size
	public int getSize() {
		return individuals.length;
	}

	// Save individual
	public void saveIndividual(int index, Individual indiv) {
		individuals[index] = indiv;
	}

	public void saveToFile(String filePath) {
		File file = new File(filePath);
		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(getSize() + "\n");
			for (int i = 0; i < getSize(); i++) {
				for (String key : individuals[i].data.keySet()) {
					Integer value = individuals[i].getGene(key);
					bw.write(key + " " + value + " ");
				}
				bw.write("\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}