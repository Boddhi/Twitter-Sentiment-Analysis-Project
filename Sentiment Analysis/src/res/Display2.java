import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import java.net.URL;

import res.ButtonListener;
import res.ImagePanel;
import res.KeyboardListener;

/**

 * 
 * @author Tristan Monger
 */

public class Display2 {

	// Window variables
	public JFrame window = new JFrame("Trash Smash");
	public JPanel infoPane;
	public final static int WIDTH = 1024;
	public final static int HEIGHT = 768;

	// Listeners
	private ButtonListener l;
	private KeyboardListener kl;

	// Button variables
	private ImageIcon sButton, qButton, iButton;

	/**
	 * Constructor for graphics main, opens the window
	 * 
	 * @param k
	 */
	public Display2(KeyboardListener k) {
		l = new ButtonListener();
		kl = k;
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.setResizable(false);
		window.pack();
		window.setFocusable(true);
		window.addKeyListener(kl);
		init();
	}

	/**
	 * Loads images for use in menus.
	 */
	private void init() {
		sButton = new ImageIcon("UI/stbutton.png");
		qButton = new ImageIcon("UI/qbutton.png");
	}

	/**
	 * Generates the Main Menu (and clears any other display)
	 */
	public JPanel createContentPane() {
		window.setVisible(false);
		window.getContentPane().removeAll();
		//window.setLayout(null);

		ImagePanel imgPanel = new ImagePanel("UI/MenuMockup.png");

		JPanel contentPane = new JPanel();
		JPanel buttonsPane = new JPanel();
		
		//imgPanel.setLayout(null);
		//contentPane.setLayout(null);
		
		contentPane.setLayout(new OverlayLayout(contentPane));
		

		JButton startButton = new JButton();
		JButton quitButton = new JButton();		
		
		startButton.setIcon(sButton);
		startButton.setBorder(null);
		startButton.addActionListener(l);
		startButton.setActionCommand("start");
		//startButton.setLocation(458, 620);
		//startButton.setLocation(50, 50);
		
		quitButton.setIcon(qButton);
		quitButton.setBorder(null);		
		quitButton.addActionListener(l);
		quitButton.setActionCommand("quit");
		
		buttonsPane.setLayout(new BoxLayout(buttonsPane, BoxLayout.X_AXIS));
		buttonsPane.add(Box.createRigidArea(new Dimension(0, 0)));
		buttonsPane.add(startButton);
		
		
		
		
		
		
		contentPane.add(startButton);
		//contentPane.add(buttonsPane);
		//contentPane.add(startButton);
		contentPane.add(imgPanel);
		
		
		//window.add(startButton);
		//window.add(imgPanel);
		
		window.setContentPane(contentPane);
		//window.paint(null);
		window.setVisible(true);
		return contentPane;
	}

	/**
	 * Removes the game pane
	 */
	public void menuStart() {
		window.getGraphics().dispose();
	}
}
