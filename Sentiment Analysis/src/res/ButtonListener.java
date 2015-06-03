package res;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Button listener for main menu, performs actions when buttons are pressed, not part of update or render threads
 * @author Ben Pinhorn
 * @version 1.0
 */
public class ButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("test")) {
		//	Tester.test();
		}
		
		if(e.getActionCommand().equals("train")) {
		//	Tester.train();
		}
	}
}
