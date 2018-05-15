
public class NodeStmt extends Node {

	public NodeAssn assn;
	public NodeExpr ne;
	public NodeBoolExpr nbe;
	public NodeBlock nb;
	public String id;
	public NodeStmt ns;
	public NodeStmt ns2;
	public NodeFact nf;
	public NodeRead nr;
	public Double flag = 0.0;
	double value;

	



	public NodeStmt(NodeAssn assn, NodeExpr ne) {
		this.assn = assn;
		this.ne = ne;
	}
	
	public NodeStmt(NodeFact fact)
	{
		this.nf = fact;
	}
	public NodeStmt(String id)
	{
		this.id = id;
	}
	
	public NodeStmt(NodeAssn assn)
	{
		this.assn = assn;
	}
	public NodeStmt(NodeBoolExpr nbe, Double flag, NodeStmt ns)
	{
		this.nbe = nbe;
		this.flag = flag;
		this.ns = ns;
	}
	
	public NodeStmt(NodeBlock nb)
	{
		this.nb = nb;
	}
	
	public NodeStmt(NodeExpr expr)
	{
		this.ne = expr;
	}
	public NodeStmt(NodeBoolExpr nbe)
	{
		this.nbe = nbe;
	}
	public NodeStmt(NodeBoolExpr nbe, NodeStmt ns)
	{
		this.nbe = nbe;
		this.ns = ns;
	}
	public NodeStmt(NodeBoolExpr nbe, NodeStmt ns, NodeStmt ns2)
	{
		this.nbe = nbe;
		this.ns = ns;
		this.ns2 = ns2;
	}
	@Override
	public Double eval(Environment env) throws EvalException {
		
		if(ne != null)
		{
			System.out.println(ne.eval(env));
			return ne.eval(env);
		}
		if(nbe != null && nbe.eval(env) == 1.0 && flag == 0.0)
		{
			return ns.eval(env);
		}
		if(nbe != null && flag == 1.0 && nbe.eval(env) == 1.0)
		{
		
			while(nbe.eval(env) == 1.0 && flag == 1.0)
			{
				value = ns.eval(env);
				if(nbe.eval(env) == 0.0)
			    {
					flag = 0.0;
				}
			}
			return value;
		}
		 
		if(assn != null)
		{
			return assn.eval(env);
		}
		if(nb != null)
		{
			return nb.eval(env);
		}
		if(nf != null)
		{
			return nf.eval(env);
		}
		
		if(ns != null)
		{
			Double check = nbe.eval(env);
			if(check == 1.0)
			{
				return ns.eval(env);
			}
		}
		 if(ns2 != null)
			{
				return ns2.eval(env);
			}
	
		else
		{
			//when wr is called, we print out the evaluation of the expression to the console and then we evaluate expr
			//System.out.println(ne.eval(env));
			//return ne.eval(env);
			
			return 1.0;
		}
	}

}
