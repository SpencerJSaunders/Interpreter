
public class NodeTerm extends Node {
	
	public NodeFact fact;
	public NodeMulop mulop;
	public NodeTerm term;
	public NodeTerm(NodeFact fact, NodeMulop mulop, NodeTerm term)
	{
		this.fact = fact;
		this.mulop = mulop;
		this.term = term;
	}
	
	public NodeTerm(NodeFact fact)
	{
		this.fact = fact;
	}
	
    public Double eval(Environment env) throws EvalException {
    	
    	if(mulop != null)
    	{
    		return ((Double) mulop.op(fact.eval(env), term.eval(env)));
    	}
    	else
    	{
    		return this.fact.eval(env);
    	}
    	
    	
    	
    }

}
