package main;
import res.*;
import genetics.*;

public class Main {

	final static Boolean load = true;
	
	public static void main(String[] args) {	
		TwitterFeed twitter = new TwitterFeed();		
	
		Population myPop;
		
		if(!load) {
			myPop = new Population(50,true);
			myPop.feed("Training Data");
		}
		else{
			myPop = new Population("Save File");
		}
		myPop.feed("Update.txt");
		GraphicsControl display = new GraphicsControl(twitter,myPop);
		display.createAndShowGUI();
	}
}
