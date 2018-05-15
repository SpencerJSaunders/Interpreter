
public class NodeProg extends Node{
	
	NodeBlock block;
	public NodeProg(NodeBlock block)
	{
		this.block = block;
	}
	
	public Double eval(Environment env) throws EvalException
	{
		Double r = null;
		
		r = this.block.eval(env);
		
		return r;
	}
}
