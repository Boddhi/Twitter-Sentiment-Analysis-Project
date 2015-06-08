package main;

import res.*;
import genetics.*;

/**
 * The Main class. It is responsible solely for loading the resources and starting up the GUI.
 * @author Tristan Monger
 *
 */
public class Main {

	final static Boolean load = true;
	
	/**
	 * The loading and initialization of the program occurs here.
	 * @param args
	 */
	public static void main(String[] args) {	
		TwitterFeed twitter = new TwitterFeed();		
	
		Population myPop;

		if(!load) {
			myPop = new Population(50,true);
			myPop.feed(".\\Text Files\\Training Data");
		}
		else{
			myPop = new Population(".\\Text Files\\Save File.txt");
		}
		myPop.feed(".\\Text Files\\Update.txt");
		GraphicsControl display = new GraphicsControl(twitter,myPop);
		display.createAndShowGUI();
	}
}
