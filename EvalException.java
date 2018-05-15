// (C) 2013 Jim Buffenbarger
// All rights reserved.

public class EvalException extends Exception {

    private Double pos;
    private String msg;

    public EvalException(Double pos, String msg) {
	this.pos=pos;
	this.msg=msg;
    }

    public String toString() {
	return "eval error"
	    +", pos="+pos
	    +", "+msg;
    }

}
