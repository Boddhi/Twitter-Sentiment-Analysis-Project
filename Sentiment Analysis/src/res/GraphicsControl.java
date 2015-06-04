package res;
 
/*
 * CardLayoutDemo.java
 *
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import main.*;
 
public class GraphicsControl implements ItemListener, ActionListener {
    JPanel cards; //a panel that uses CardLayout
    final static String BUTTONPANEL = "Card with JButtons";
    final static String TEXTPANEL = "Card with JTextField";
    final static String LOADPANEL = "Card with JProgressBar";
    final static String TRAINPANEL = "Card with Machine Training";
    
    static TwitterFeed twitter;
    static Population myPop;
    
    
    
    
    JButton startButton = new JButton("Listen In!"); 
    JButton backToStartButton = new JButton("Back");
    JButton backToStartButton2 = new JButton("Back");
    JButton submitButton = new JButton("Submit");
    JButton backToTextButton = new JButton("Back"); 
    JButton trainButton = new JButton("Train");
    JButton feedButton = new JButton("Teach");
    
    JTextField feedText = new JTextField(20);
    
    JRadioButton good = new JRadioButton("Good");
    JRadioButton bad = new JRadioButton("Bad");
    JRadioButton neutral = new JRadioButton("Neutral");

    DataMiner card3;
    
    public static JTextField input = new JTextField(20);

	public GraphicsControl(TwitterFeed twitter2, Population myPop2) {
		this.twitter = twitter2;
    	this.myPop = myPop2;
    	
    	//twitter2.seek("Pizza");
	}


	public void addComponentToPane(Container pane) {
        //Put the JComboBox in a JPanel to get a nicer look.
        /*
    	JPanel comboBoxPane = new JPanel(); //use FlowLayout
        String comboBoxItems[] = { BUTTONPANEL, TEXTPANEL , LOADPANEL};
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);
        */ 
        //Create the "cards".
        JPanel card1 = new JPanel();
        
        startButton.addActionListener(this);
        startButton.setActionCommand("start");
        
        trainButton.addActionListener(this);
        trainButton.setActionCommand("train");
        
        card1.add(startButton);
        card1.add(trainButton);
        //card1.add(new JButton("Button 2"));
        //card1.add(new JButton("Button 3"));
         
        JPanel card2 = new JPanel();
        backToStartButton.addActionListener(this);
        backToStartButton.setActionCommand("back1");
        submitButton.addActionListener(this);
        submitButton.setActionCommand("submit");
        card2.add(backToStartButton);
        card2.add(submitButton);
        card2.add(input);
         
        
        backToTextButton = new JButton("Back");
        backToTextButton.setActionCommand("back2");
        backToTextButton.addActionListener(this);
        //System.out.println(twitter == null);
        card3 = new DataMiner(backToTextButton, twitter, myPop);
        //System.out.println(card3.twitter2 == null);
        
        
        JPanel card4 = new JPanel();
        backToStartButton2.addActionListener(this);
        backToStartButton2.setActionCommand("back3");
        
        feedButton.addActionListener(this);
        feedButton.setActionCommand("feed");
        
        ButtonGroup group = new ButtonGroup();
        
        group.add(good);
        group.add(bad);
        group.add(neutral);
        
        
        card4.add(good);
        card4.add(bad);
        card4.add(neutral);
        card4.add(feedText);
        card4.add(feedButton);
        card4.add(backToStartButton2);
        
        
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, BUTTONPANEL);
        cards.add(card2, TEXTPANEL);
        cards.add(card3, LOADPANEL);
        cards.add(card4, TRAINPANEL);
         
      //  pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }
     
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }
     
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ListenIn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setPreferredSize(new Dimension(500,300));
        
        //Create and set up the content pane.
        GraphicsControl demo = new GraphicsControl(twitter, myPop);
        demo.addComponentToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
         
    }
     
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
       // 	for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        	//	System.out.println(info.getClassName());
                /*if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }*/
        //    }
        	UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "start"){
			CardLayout cl = (CardLayout)(cards.getLayout());
	        cl.show(cards, TEXTPANEL);
		}
		else if(e.getActionCommand() == "back1"){
			CardLayout cl = (CardLayout)(cards.getLayout());
	        cl.show(cards, BUTTONPANEL);
		}
		else if(e.getActionCommand() == "submit"){
			CardLayout cl = (CardLayout)(cards.getLayout());
	        cl.show(cards, LOADPANEL);
		}
		else if(e.getActionCommand() == "back2"){
			CardLayout cl = (CardLayout)(cards.getLayout());
	        cl.show(cards, TEXTPANEL);
	        card3.revalidate();
		}
		else if(e.getActionCommand() == "train"){
			CardLayout cl = (CardLayout)(cards.getLayout());
	        cl.show(cards, TRAINPANEL);
		}
		else if(e.getActionCommand() == "back3"){
			CardLayout cl = (CardLayout)(cards.getLayout());
			myPop.saveToFile("New Save File");
	        cl.show(cards, BUTTONPANEL);
		}
		else if(e.getActionCommand() == "feed"){
			if(good.isSelected())myPop.feed(feedText.getText(), 1);
			else if(bad.isSelected())myPop.feed(feedText.getText(), -1);
			else myPop.feed(feedText.getText(), 0);
		}
	}
}
