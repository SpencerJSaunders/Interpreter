
public class NodeBlock extends Node {

	public NodeStmt stmt;
	public NodeBlock block;

	public NodeBlock(NodeStmt stmt, NodeBlock block) {
		this.stmt = stmt;
		this.block = block;
	}
	

	@Override
	public Double eval(Environment env) throws EvalException {
		
		Double r = this.stmt.eval(env);
		
		if (block != null) {
			r = this.block.eval(env);
		}
		
		return r;
	}

}
