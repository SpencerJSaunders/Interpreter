import java.util.HashMap;

// This class provides a stubbed-out environment.
// You are expected to implement the methods.
// Accessing an undefined variable should throw an exception.

public class Environment {

	public HashMap<String, Double> hm = new HashMap<String, Double>();
	
	public Environment()
	{
		hm = new HashMap<String, Double>();
	}
    public Double put(String var, Double val) { 
       hm.put(var, val);
       return 1.0;
      
    }
    public Double get(String var, Double val) throws EvalException { 
    
    	return hm.get(var);
    }
    public Double getKey(String key) throws EvalException
    {
    	return hm.get(key);
    }
    
    public String toString() {
    	
    	return hm.toString();
    }
    
}
