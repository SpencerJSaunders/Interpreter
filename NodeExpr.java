
public class NodeExpr extends Node {
	
	public NodeTerm term;
	public NodeAddop na;
	public NodeExpr ne;
	
	public NodeExpr(NodeTerm term, NodeAddop na, NodeExpr ne)
	{
		this.term = term;
		this.na = na;
		this.ne = ne;
	}

	
	
	public Double eval(Environment env) throws EvalException {
	
		if(na != null)
		{
			return ((Double) this.na.op(term.eval(env), ne.eval(env)));
		}
		else
		{
			return term.eval(env);
		}
	}

}
