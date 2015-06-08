package res;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.SwingWorker;


/**
 * Task class. Runs a twitter query and analysis in background and beeps upon completion.
 * @author Tristan Monger
 *
 */
public class Task extends SwingWorker<Void, Void> {
	
	Boolean queried = false;
	Boolean calculated = false;
	public int good, bad, neutral;
//	public String[] results;
	 
    @Override
    /**
     * Performs the loading and analysis of the twitter response to the given query.
     */
    public Void doInBackground() {
    	good = 0;
    	bad =0;
    	neutral= 0;
        Random random = new Random();
        int progress = 0;

        setProgress(0);
        int threshold1, threshold2;
        
        threshold1 = (int)(Math.random()*50);
        threshold2 = (int)(Math.random()*40) + threshold1;
        
        while (progress < 100) {
            try {
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException ignore) {}
            if(progress > threshold1 && !queried){
            	DataMiner.results = DataMiner.twitter2.seek(GraphicsControl.input.getText(), 500);
            	queried = true;
            }
            
            if(progress > threshold2 && !calculated){
            	for(int i = 0; i < DataMiner.results.length; i++){
            		if(DataMiner.myPop2.getFittest().test(DataMiner.results[i]) >= 0.6) good++;
            		else if(DataMiner.myPop2.getFittest().test(DataMiner.results[i]) <= 0.4) bad++;
            		else neutral++;
            	}
            	calculated = true;
            }
            
            progress += random.nextInt(10);
            setProgress(Math.min(progress, 100));
        }
        
        String output = "";
        if(good > bad){
        	output+= "POSITIVE";
    		DataMiner.response.setForeground(Color.GREEN);
    	}
    	else if(bad > good){
    		output+="NEGATIVE";
    		DataMiner.response.setForeground(Color.RED);
    	}
    	else{
    		output+="NEUTRAL";
    		DataMiner.response.setForeground(Color.BLACK);
    	}
        DataMiner.response.setText(output);
        DataMiner.response.setVisible(true);
        
        queried = false;
        calculated = false;
        return null;
    }
    
    @Override
    public void done() {
        Toolkit.getDefaultToolkit().beep();
    }
}
