import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import java.net.URL;

import res.ButtonListener;
import res.ImagePanel;
import res.KeyboardListener;

/**
 * Main graphics class for Trash Smash, generates window, starts render thread,
 * creates main menu
 * 
 * @author Ben Pinhorn
 * @author Brian Chen
 * @author Tristan Monger
 */

public class Display {

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
	public Display(KeyboardListener k) {
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

	public void createContentPane() {
		
	}

}
