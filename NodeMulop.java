
public class NodeMulop extends Node{

	Double pos;
	String mulop;
	public NodeMulop(Double pos, String mulop) {
		this.pos= pos;
		this.mulop = mulop;
}
	
	 public double op(double o1, double o2) throws EvalException {
			if (mulop.equals("*"))
			    return o1*o2;
			if (mulop.equals("/"))
			    return o1/o2;
			throw new EvalException(pos,"bogus addop: " + mulop);
		    }

}