import java.util.HashMap;
import java.util.Map;


public class StringExperiments {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Map<String,Integer> m1 = new HashMap<String,Integer>();
		
		
		
		// value iterator
		//Iterator iterator = mapA.values();
		
		m1.put("Zara", 8);
	    m1.put("Mahnaz", 31);
	    m1.put("Ayan", 12);
	    m1.put("Daisy", 14);
	    String key1 = "Ayan";
	    if(m1.get(key1) != null){
	    	m1.put(key1, m1.get(key1) + 1);
	    }
	    else{
	    	m1.put(key1, 1);
	    }
	    
	    
	    for(String key : m1.keySet()){
			Integer value = m1.get(key);
			System.out.println(key + " " + value);
		}
	    		
	}

}
