
public class NodeWr extends Node{

	NodeExpr ne;
	
	
	public NodeWr(NodeExpr ne)
	{
		this.ne = ne;
	}
	
	public Double eval(Environment env) throws EvalException
	{
		Double val = ne.eval(env);
		System.out.println(val);
		return val;
	}
}
