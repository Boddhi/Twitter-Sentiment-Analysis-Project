package sample1;

public class Tester {

	static Boolean load = true;
	static Boolean save = true;
	
	
	public static void main(String[] args) {
		
		FitnessCalc.setSolution("11000000000010000111010101010000010011001000000000000000");
		Population myPop;
		if(!load) myPop = new Population(50,true);
		else{
			myPop = new Population("Save File");
		}
		
		int generationCount = 0; 
		
		while(myPop.getFittest().getFitness() < FitnessCalc.getMaxFitness()){ 
		  generationCount++; 
		  System.out.println("Generation: "+generationCount+" Fittest: "+myPop.getFittest().getFitness()); 
		  myPop = Algorithm.evolvePopulation(myPop); 
		} 
		
		if(save) myPop.saveToFile("Save File");
		
		System.out.println("Solution found!"); 
		System.out.println("Generation: "+generationCount); 
		System.out.println("Genes:"); 
		System.out.println(myPop.getFittest());
	}

}
