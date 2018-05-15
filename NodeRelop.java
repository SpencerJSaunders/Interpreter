
public class NodeRelop {
	Double pos;
	String relop;
	public NodeRelop(Double pos, String relop) {
		this.pos= pos;
		this.relop = relop;
}
	
	 public boolean op(double o1, double o2) throws EvalException {
			if (relop.equals("<"))
			    return (o1 < o2);
			if (relop.equals("<="))
			    return (o1 <= o2);
			if(relop.equals(">"))
			{
				return (o1 > o2);
			}
			if(relop.equals(">="))
			{
				return (o1 >= o2);
			}
			if(relop.equals("<>"))
			{
				return (o1 != o2);
			}
			if(relop.equals("=="))
			{
				return (o1 == o2);
			}
			throw new EvalException(pos,"bogus relop: " + relop);
		    }
}
