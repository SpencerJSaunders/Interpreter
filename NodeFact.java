import java.util.Scanner;

public class NodeFact extends Node {
	
	public String id;
	public NodeAddop op;
	public NodeExpr newExpr;
	public Double var;
	public NodeExpr ne;
	public NodeTerm term;
	public boolean negative;

	
	
	public NodeFact(NodeExpr e)
	{
		super();
		this.term = e.term;
		this.op = e.na;
		this.newExpr = e.ne;
		
		ne = new NodeExpr(term, op, newExpr);
	}
	public NodeFact(NodeExpr e, boolean flag)
	{
		this.ne = e;
		this.negative = flag;
	}


	
	public NodeFact(String id)
	{
		this.id = id;
	}
	
    public Double eval(Environment env) throws EvalException {
    	
    	//if the hashmap contains the id we're looking for then return it
    	if(env.hm.containsKey(id))
    	{
    		Double ov = env.hm.get(id);
    		return ov;
    	}
    	
    	if(this.id != null)
    	{
    		try
    		{
    			var = Double.parseDouble(id);
    			return var;
  	
    		}
    		 catch(NumberFormatException e)
    		{
    			 
    			Scanner scan = new Scanner(System.in);
    			double ourValue = scan.nextDouble();
    			
    			
    			env.put(id, ourValue);
    		
    			return 0.0;
    		}
    		} else if(ne != null && negative == true)
    		{
    			return -this.ne.eval(env);
    		}
    	
    	return this.ne.eval(env);
    }

}
