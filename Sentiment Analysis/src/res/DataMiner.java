package res;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import genetics.*;

import java.beans.*;

/**
 * The DataMiner class. Responsible for performing calculations and analyzing data.
 * 
 * @author Tristan Monger
 * 
 */
@SuppressWarnings("serial")
public class DataMiner extends JPanel implements ActionListener, PropertyChangeListener {

	public static TwitterFeed twitter2;
	public static Population myPop2;

	public int good, bad, neutral;

	private JProgressBar progressBar;
	public static JButton startButton;
	public JButton backButton;
	public Boolean running = false;
	private Task task;
	public static JTextField response = new JTextField();

	public static String[] results;

	/**
	 * Constructor.
	 * 
	 * @param backButton the button that exits the DataMiner
	 * @param t the TwitterFeed to query with
	 * @param pop the Population to use for twitter analysis
	 */
	public DataMiner(JButton backButton, TwitterFeed t, Population pop) {
		super(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		twitter2 = t;
		myPop2 = pop;

		startButton = new JButton("Start");
		startButton.setActionCommand("start");
		startButton.addActionListener(this);

		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);

		response.setText("Unknown...");
		response.setVisible(true);
		response.setEditable(false);

		JPanel panel = new JPanel(new GridBagLayout());
		c.gridx = 0;
		c.gridy = 0;
		panel.add(backButton, c);
		c.gridx = 1;
		c.gridy = 0;
		panel.add(startButton, c);
		c.gridx = 1;
		c.gridy = 1;
		panel.add(progressBar, c);
		c.gridx = 1;
		c.gridy = 2;
		panel.add(response, c);

		add(panel);
	}

	/**
	 * Invoked when an action occurs.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "start") {
			startButton.setEnabled(false);
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			task = new Task();
			task.addPropertyChangeListener(this);
			task.execute();
			this.repaint();
			this.revalidate();
		}
	}

	/**
	 * Acts when the Task progress property changes.
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			progressBar.setValue(progress);
			if (task.getProgress() == 100) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				startButton.setEnabled(true);
				response.setVisible(true);
			}
			myPop2 = Algorithm.evolvePopulation(myPop2);
		}
	}
}
