package res;
 
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import genetics.*;
 

/**
 * Graphics Control Object. Manages the GUI of the Sentiment Analysis program.
 * @author Tristan Monger
 *
 */
public class GraphicsControl implements  ActionListener {
	
    JPanel cards; 
    
    final static String STARTPANEL = "Main Panel";
    final static String INPUTPANEL = "Twitter Query Input Panel";
    final static String LOADPANEL = "Twitter Query Execution Panel";
    final static String TRAINPANEL = "Machine Training Panel";
    final static String TESTPANEL = "Machine Testing Panel";
    
    static TwitterFeed twitter;
    static Population myPop;
    
    JButton startButton = new JButton("Listen In!"); 
    JButton backToStartButton = new JButton("Back");
    JButton backToStartButton2 = new JButton("Back");
    JButton submitButton = new JButton("Submit");
    JButton backToTextButton = new JButton("Back"); 
    JButton trainButton = new JButton("Train");
    JButton feedButton = new JButton("Teach");
    JButton sandBoxButton = new JButton("Sandbox");
    JButton backToTrainButton = new JButton("Back");
    JButton testButton = new JButton("Test");
    
    JTextField feedText = new JTextField(20);
    JTextField outputText = new JTextField(20);
    JTextField testResult = new JTextField(10);
    JTextField sampleInput = new JTextField(25); 
    public static JTextField input = new JTextField(20);
    
    JRadioButton good = new JRadioButton("Good");
    JRadioButton bad = new JRadioButton("Bad");
    JRadioButton neutral = new JRadioButton("Neutral");

    DataMiner card3;
    
    static JFrame frame;
    

    /**
     * Constructor.
     * @param twitter2 the TwitterFeed object to use
     * @param myPop2 the Population object to query with
     */
	public GraphicsControl(TwitterFeed twitter2, Population myPop2) {
		twitter = twitter2;
    	myPop = myPop2;
	}


	/**
	 * Adds all the JComponents (Buttons, Text, etc.) to the content pane.
	 * @param pane the Container into which to place the JComponents
	 */
	public void addComponentsToPane(Container pane) {
        JPanel card1 = new JPanel();
        card1.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        startButton.addActionListener(this);
        startButton.setActionCommand("start");
        c.gridx = 1;
        c.gridy = 0;
        card1.add(startButton, c);
        
        trainButton.addActionListener(this);
        trainButton.setActionCommand("train");
        c.gridx = 1;
        c.gridy = 1;
        
        card1.add(trainButton, c);
         
        JPanel card2 = new JPanel();
        card2.setLayout(new GridBagLayout());
        
        backToStartButton.addActionListener(this);
        backToStartButton.setActionCommand("back1");
        c.gridx = 0;
        c.gridy = 0;
        card2.add(backToStartButton, c);
        
        submitButton.addActionListener(this);
        submitButton.setActionCommand("submit");
        c.gridx = 1;
        c.gridy = 1;
        card2.add(submitButton, c);
        
        c.gridx = 1;
        c.gridy = 0;
        input.setText("Enter search term...");
        card2.add(input, c);
         
        
        backToTextButton = new JButton("Back");
        backToTextButton.setActionCommand("back2");
        backToTextButton.addActionListener(this);
        
        card3 = new DataMiner(backToTextButton, twitter, myPop);
        
        
        JPanel card4 = new JPanel();
        card4.setLayout(new GridBagLayout());
        

        ButtonGroup group = new ButtonGroup();
        group.add(good);
        group.add(bad);
        group.add(neutral);
        c.gridx = 2;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        c.gridwidth = (int) 0.5;
        card4.add(good, c);
        c.gridx = 0;
        c.anchor = GridBagConstraints.EAST;
        card4.add(bad, c);
        c.gridx=1;
        c.anchor = GridBagConstraints.CENTER;
        card4.add(neutral, c);
        
        c.gridx = 1;
        c.gridy = 2;
        feedText.setText("Type a new word here...");
        card4.add(feedText, c);
        
        c.gridx = 1;
        c.gridy = 0;
        feedButton.addActionListener(this);
        feedButton.setActionCommand("feed");
        card4.add(feedButton, c);
        
        outputText.setText("Taught!");
        outputText.setVisible(false);
        outputText.setEditable(false);
        outputText.setHorizontalAlignment(SwingConstants.CENTER);
        c.gridx = 1;
        c.gridy = 3;
        card4.add(outputText,c);
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        backToStartButton2.addActionListener(this);
        backToStartButton2.setActionCommand("back3");
        card4.add(backToStartButton2, c);
        
        c.gridx = 0;
        c.gridy = 3;
        sandBoxButton = new JButton("Sandbox");
        sandBoxButton.setActionCommand("sandbox");
        sandBoxButton.addActionListener(this);
        card4.add(sandBoxButton,c);
        
        
        JPanel card5 = new JPanel();
        card5.setLayout(new GridBagLayout());
        
        backToTrainButton.addActionListener(this);
        backToTrainButton.setActionCommand("back4");
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        card5.add(backToTrainButton, c);
        
        testButton.addActionListener(this);
        testButton.setActionCommand("test");
        c.gridx = 1;
        c.gridy = 1;
        card5.add(testButton, c); 
        
        c.gridx = 1;
        c.gridy = 0;
        sampleInput.setText("Enter a word or phrase...");
        card5.add(sampleInput, c);
        
        c.gridx = 1;
        c.gridy = 3;
        testResult.setVisible(false);
        testResult.setEditable(false);
        testResult.setHorizontalAlignment(SwingConstants.CENTER);
        card5.add(testResult, c);
        
        cards = new JPanel(new CardLayout());
        cards.add(card1, STARTPANEL);
        cards.add(card2, INPUTPANEL);
        cards.add(card3, LOADPANEL);
        cards.add(card4, TRAINPANEL);
        cards.add(card5, TESTPANEL);
        
        pane.add(cards, BorderLayout.CENTER);
    }
     
    /**
     * Creates the GUI and shows it. 
     */
    public void createAndShowGUI() {
        frame = new JFrame("ListenIn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400,200));
        
        GraphicsControl demo = new GraphicsControl(twitter, myPop);
        demo.addComponentsToPane(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);  
    }
    
	@Override
	/**
	 * Invoked on button clicks.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "start" || e.getActionCommand() == "back2"){
			CardLayout cl = (CardLayout)(cards.getLayout());
	        cl.show(cards, INPUTPANEL);
	        card3.revalidate();
		}
		else if(e.getActionCommand() == "back1"){
			CardLayout cl = (CardLayout)(cards.getLayout());
	        cl.show(cards, STARTPANEL);
		}
		else if(e.getActionCommand() == "submit"){
			CardLayout cl = (CardLayout)(cards.getLayout());
	        cl.show(cards, LOADPANEL);
		}
		else if(e.getActionCommand() == "train" || e.getActionCommand() == "back4"){
			CardLayout cl = (CardLayout)(cards.getLayout());
	        cl.show(cards, TRAINPANEL);
	        sampleInput.setText("Enter a word or phrase...");
	        testResult.setVisible(false);
		}
		else if(e.getActionCommand() == "back3"){
			CardLayout cl = (CardLayout)(cards.getLayout());
			myPop.saveToFile(".\\Text Files\\Save File.txt");
			outputText.setVisible(false);
	        cl.show(cards, STARTPANEL);
		}
		else if(e.getActionCommand() == "sandbox"){
			CardLayout cl = (CardLayout)(cards.getLayout());
	        cl.show(cards, TESTPANEL);
		}
		else if(e.getActionCommand() == "test"){
			double response = myPop.getFittest().test(sampleInput.getText());
			if(response >= 0.6){
				testResult.setText("POSITIVE");
			}
			else if(response <= 0.4){
				testResult.setText("NEGATIVE");
			}
			else{
				testResult.setText("NEUTRAL");
			}
			testResult.setVisible(true);
			frame.revalidate();
			frame.repaint();
		}
		else if(e.getActionCommand() == "feed"){
			if(!feedText.getText().equals("Type a new word here...")){
				outputText.setVisible(true);
				frame.revalidate();
				frame.repaint();
				if(good.isSelected())myPop.feed(feedText.getText(), 1);
				else if(bad.isSelected())myPop.feed(feedText.getText(), -1);
				else myPop.feed(feedText.getText(), 0);
				myPop = Algorithm.evolvePopulation(myPop);
			}
		}
	}
}
