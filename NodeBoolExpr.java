
public class NodeBoolExpr {
	
	NodeExpr ne;
	NodeRelop re;
	NodeExpr ne2;
	
	public NodeBoolExpr(NodeExpr ne, NodeRelop re, NodeExpr ne2)
	{
		this.ne = ne;
		this.re = re;
		this.ne2 = ne2;
	}
	
	public double eval(Environment env) throws EvalException
	{
		if(re != null && ne != null && ne2 != null)
		{
		if(re.op(ne.eval(env), ne2.eval(env)))
		{
			return 1.0;
		}
		else
		{
		
			return 0.0;
		}
		}
		
		return 0.0;
	
}
}
