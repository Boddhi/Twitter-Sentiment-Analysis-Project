package genetics;

/**
 * Genetic Algorithm class.
 * @author Tristan Monger
 *
 */
public class Algorithm {

    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 2;
    private static final boolean elitism = true;
    
    /**
     * Evolves a given Population.
     * @param pop the Population to evolve
     * @return the evolved Population
     */
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.getSize(), false);
        int elitismOffset;
        
        if (elitism) { //saves the fittest Individual
        	elitismOffset = 1;
            newPopulation.saveIndividual(0, pop.getFittest());
        }
        else 
            elitismOffset = 0;

        // Breeds Individuals from within the population
        for (int i = elitismOffset; i < pop.getSize(); i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutates population
        for (int i = elitismOffset; i < newPopulation.getSize(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    /**
     * Breeds two Individuals, mixing their Genes to create a new Individual.
     * @param indiv1 an Individual to breed
     * @param indiv2 the Individual breeding partner
     * @return the child Individual result
     */
    private static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newKid = new Individual();
        for(String key : indiv1.data.keySet()){
        	int posCount = indiv1.data.get(key).getPCount();
        	int totCount = indiv1.data.get(key).getTCount();
        	
        	if (Math.random() <= uniformRate) {
        		newKid.setGene(key, new Gene(posCount,totCount));
            } else {
            	if(indiv2.getGene(key) != null)
            		newKid.setGene(key, new Gene(indiv2.data.get(key).getPCount(),indiv2.data.get(key).getTCount()));
            }
		}
        
        return newKid;
    }

    /**
     * Mutates the Genes of an Individual at random.
     * @param indiv the Individual to mutate
     */
    private static void mutate(Individual indiv) {
    	for(String key : indiv.data.keySet()){
        	if (Math.random() <= mutationRate) {
        		int totCount = indiv.data.get(key).getTCount();
            	int posCount = indiv.data.get(key).getPCount();
            	
            	int tChange = (int)(Math.random()*(totCount-posCount));
            	int pChange = (int)(Math.random()*(totCount-posCount));
            	          		
        		indiv.setGene(key, new Gene(posCount+pChange,totCount+tChange));
            }
		}
    }

    /**
     * Randomly selects Individuals from a larger Population to breed.
     * @param pop the Population to select from
     * @return the fittest of the random subPopulation
     */
    private static Individual tournamentSelection(Population pop) {
        Population tournament = new Population(tournamentSize, false);
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.getSize());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        return tournament.getFittest();
    }
}
