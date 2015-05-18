package sample1;

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
                newIndividual.generateIndividual();
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
			for(int i = 0; i < getSize(); i++){
				String line = new String(bw.readLine());
				Individual newIndividual = new Individual(line);
				saveIndividual(i,newIndividual);
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    

    /* Getters */
    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public Individual getFittest() {
        Individual fittest = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < getSize(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
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
    
    public void saveToFile(String filePath){
    	File file = new File(filePath);
		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(getSize() + "\n");
			for(int i = 0; i < getSize(); i++){
				bw.write(individuals[i].toString() + "\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
   
    
}