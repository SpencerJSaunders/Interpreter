public class NodeAssn extends Node 
{

    public String id;
    public NodeExpr expr;
	
    public NodeAssn(String id, NodeExpr expr) {
    	this.id=id;
    	this.expr = expr;
    }
	
    public Double eval(Environment env) throws EvalException {

    	return env.put(id,expr.eval(env));
    }

}
