

public class Algorithm {

    /* GA parameters */
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    /* Public methods */
    
    // Evolve a population
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.getSize(), false);
        int elitismOffset;
        
        // Keep our best individual
        if (elitism) {
        	elitismOffset = 1;
            newPopulation.saveIndividual(0, pop.getFittest());
        }
        else 
            elitismOffset = 0;

        // Crossover population
        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < pop.getSize(); i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.getSize(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    // Crossover individuals
    private static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual();
        // Loop through genes
        for(String key : indiv1.data.keySet()){
        	Integer value1 = indiv1.data.get(key);
        	
        	if (Math.random() <= uniformRate) {
        		newSol.setGene(key, value1);
            } else {
            	if(indiv2.getGene(key) != 0)
            		newSol.setGene(key, indiv2.getGene(key));
            }
		}
        
        return newSol;
    }

    // Mutate an individual
    private static void mutate(Individual indiv) {
        // Loop through genes
    	for(String key : indiv.data.keySet()){
        	Integer value1 = indiv.data.get(key);
        	
        	if (Math.random() <= mutationRate) {
        		value1 = (int)(value1*(4*Math.random()-2));
        		indiv.setGene(key, value1);
            }
		}
    }

    // Select individuals for crossover
    private static Individual tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.getSize());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        Individual fittest = tournament.getFittest();
        return fittest;
    }
}
