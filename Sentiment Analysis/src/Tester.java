import java.util.Scanner;

import res.*;

import main.*;

public class Tester {

	static Boolean load = true;
	static Boolean save = true;
	static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		//Individual ant1 = new Individual();
		//ant1.generateIndividual("Hello world! Goodbye world.");
		//System.out.println(ant1.toString());
		//Display window = new Display(null);
		//window.createContentPane();
		
		TwitterFeed twitter = new TwitterFeed();		
		
		
		Population myPop;
		
		if(!load) {
			myPop = new Population(50,true);
			myPop.feed("Training Data");
		}
		else{
			myPop = new Population("New Save File");
		}
		
		CardLayoutDemo card = new CardLayoutDemo(twitter,myPop);
		card.createAndShowGUI();
		
		int ans,change = 0;
		
		for(int i = 0; i < 500; i++){
			String line = s.nextLine();
			line = line.toLowerCase();
			if(line.equals("exit")) break;
			ans = myPop.getFittest(0).test(line);
			if(ans > 0) System.out.println("Positive");
			else if(ans < 0) System.out.println("Negative");
			else System.out.println("Neutral");
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
	}
	
	

}
