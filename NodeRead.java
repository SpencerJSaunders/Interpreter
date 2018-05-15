import java.util.Scanner;

public class NodeRead {
	 	public String id;
	    public double value;
	    
	    Scanner scan;
		
	    public NodeRead() {
	    	
	    }
		
	    public Double eval(Environment env) throws EvalException {
	    	scan = new Scanner(System.in);
	    	id = scan.next();
	    	value = scan.nextDouble();
	    	return env.put(id,value);
	    }

	}

