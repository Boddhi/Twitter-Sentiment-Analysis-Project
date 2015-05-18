import java.util.Scanner;



public class Tester {

	static Boolean load = true;
	static Boolean save = true;
	static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		//Individual ant1 = new Individual();
		//ant1.generateIndividual("Hello world! Goodbye world.");
		//System.out.println(ant1.toString());
		Population myPop;
		if(!load) myPop = new Population(50,true);
		else{
			myPop = new Population("New Save File");
			myPop.feed("Training Data");
		}
		
		int ans,change = 0;
		
		for(int i = 0; i < 500; i++){
			String line = s.nextLine();
			line = line.toLowerCase();
			if(line.equals("exit")) break;
			ans = myPop.getFittest(0).test(line);
			if(ans > 0) System.out.println("Positive");
			else if(ans < 0) System.out.println("Negative");
			else System.out.println("Not Sure");
			System.out.println("Correct? (Y/N)");
			String response = s.nextLine();
			if(response.toLowerCase().equals("n")){
				System.out.println("What is correct? (-1,+1,0)");
				String resp = s.nextLine();
				change = Integer.parseInt(resp);
				if(change == 0) change = ans>0?-1:1;
				myPop.getFittest(0).feed(line, change);
			}
			else{
				if(ans !=0)change = ans>0?1:-1;
				else change = 0;
				myPop.getFittest(0).feed(line, change);
				
			}
		}
		
		if(save) myPop.saveToFile("New Save File");
		
		/*
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
		*/
	}

}
