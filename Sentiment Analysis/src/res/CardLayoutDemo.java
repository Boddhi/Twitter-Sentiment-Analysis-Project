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
 
public class CardLayoutDemo implements ItemListener, ActionListener {
    JPanel cards; //a panel that uses CardLayout
    final static String BUTTONPANEL = "Card with JButtons";
    final static String TEXTPANEL = "Card with JTextField";
    final static String LOADPANEL = "Card with JProgressBar";
    
    
    TwitterFeed twitter;
    Population myPop;
    
    
    
    
    JButton startButton = new JButton("Listen In!"); 
    JButton backToStartButton = new JButton("Back");
    JButton submitButton = new JButton("Submit");
    JButton backToTextButton = new JButton("Back"); 
    public static JTextField input = new JTextField(20);

	public CardLayoutDemo(TwitterFeed twitter2, Population myPop2) {
		this.twitter = twitter2;
    	this.myPop = myPop2;
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
        card1.add(startButton);
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
        ProgressBarDemo card3 = new ProgressBarDemo(backToTextButton, twitter, myPop);
        
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1, BUTTONPANEL);
        cards.add(card2, TEXTPANEL);
        cards.add(card3, LOADPANEL);
         
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
        JFrame frame = new JFrame("CardLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Create and set up the content pane.
        CardLayoutDemo demo = new CardLayoutDemo(null, null);
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
		}
	}
}
