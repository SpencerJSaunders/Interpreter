import java.util.*;

/*
 * This class is a recursive-descent parser,
 * modeled after the programming language's grammar.
 * It constructs and has-a Scanner for the program
 * being parsed.
*/
public class Parser 
{

    private Scanner scanner;
    Double flag = 1.0;

    /*
    * Creates a new scanner for the string to be parsed.
    * Returns the parsed statement
    * Throws SyntaxException if the scanner class throws one.
    * @param program
    */
    
    public Node parse(String program) throws SyntaxException 
    {
		scanner=new Scanner(program);
		scanner.next();
		return parseProg();
		
    }
    
    
    private NodeProg parseProg() throws SyntaxException
    {
    	NodeBlock nb = parseBlock();
    	NodeProg np = new NodeProg(nb);
		return np;  	
    }

	private NodeBlock parseBlock() throws SyntaxException {
		
		NodeStmt stmt = parseStmt();

		Token t = scanner.curr();
		
		NodeBlock block = null;
		if (";".equals(t.lex())) {
			match(";"); // consume the semicolon and move onto the next token
			block = parseBlock();
		}
		
		
		return new NodeBlock(stmt, block);
	}

	
		private NodeStmt parseStmt() throws SyntaxException {
		
		Token t = scanner.curr();
		Token elseToken = new Token("else");
		if("rd".equals(t.lex()))
		{
			match("rd");
			NodeFact fact = parseNodeFact();
			return new NodeStmt(fact);
		}
		
		if("wr".equals(t.lex()))
		{
			match("wr"); 
			NodeExpr nodeExpr = parseExpr();
			return new NodeStmt(nodeExpr);
		}
		
		if("if".equals(t.lex()))
		{
			match("if");
			NodeBoolExpr nbe = parseBoolExpr();
			match("then");
			NodeStmt ns = parseStmt();
			if(scanner.curr().equals(elseToken))
			{
				match("else");
				NodeStmt ns2 = parseStmt();
				return new NodeStmt(nbe, ns, ns2);
			}
			else
			{
				return new NodeStmt(nbe, ns);
			}
		}
		if("while".equals(t.lex()))
		{
			match("while");
			NodeBoolExpr nodeboole = parseBoolExpr();
			match("do");
			NodeStmt ns = parseStmt();
			return new NodeStmt(nodeboole, flag, ns);
		}
		
		if("begin".equals(t.lex()))
		{
			match("begin");
			NodeBlock nb = parseBlock();
			//if(scanner.curr().equals("end"))
			//{
			match("end");
			//}
			
			return new NodeStmt(nb);
		}
		
		else
		{
		NodeAssn assn = parseAssn();
		
		return new NodeStmt(assn);
		}
	}

	private NodeExpr parseExpr() throws SyntaxException {
        NodeTerm term=parseTerm();
        NodeAddop addop=parseAddop();
        if (addop==null)
        {
        	//null, null
            return new NodeExpr(term, null, null);
        }
        NodeExpr subexpr=parseExpr();
        return new NodeExpr(term,addop,subexpr);
    }
	 private NodeAddop parseAddop() throws SyntaxException {
		 
		 Token t = scanner.curr();
		 if(t.lex().equals("+"))
		 {
			 match("+");
			 //pos?
			 return new NodeAddop(scanner.pos(), "+");
		 }
		 if(t.lex().equals("-"))
		 {
			 match("-");
			 
			 return new NodeAddop(scanner.pos(), "-");
		 }
		 
		 return null;
	 }
	 
	 private NodeMulop parseMulop() throws SyntaxException
	 {
		 Token t = scanner.curr();
		 if(t.lex().equals("*"))
		 {
			 match("*");
			 return new NodeMulop(scanner.pos(), "*");
		 }
		 if(t.lex().equals("/"))
		 {
			 match("/");
			 return new NodeMulop(scanner.pos(), "/");
		 }
		 
		 return null;
	 }
	private NodeTerm parseTerm() throws SyntaxException
	{
		NodeFact fact = parseNodeFact();
		NodeMulop mulop = parseMulop();
		if(mulop == null)
		{
			return new NodeTerm(fact);
		}
	    NodeTerm subterm = parseTerm();
	    
	    return new NodeTerm(fact, mulop, subterm);
	}
	
	private NodeBoolExpr parseBoolExpr() throws SyntaxException
	{
		NodeExpr ne = parseExpr();
		NodeRelop nr = parseRelop();
		NodeExpr ne2 = parseExpr();
		
		return new NodeBoolExpr(ne, nr, ne2);
	}
	
	private NodeRelop parseRelop() throws SyntaxException
	{
		Token t = scanner.curr();
		 if(t.lex().equals("<"))
		 {
			 match("<");
			 //pos?
			 return new NodeRelop(scanner.pos(), "<");
		 }
		 if(t.lex().equals("<="))
		 {
			 match("<=");
			 
			 return new NodeRelop(scanner.pos(), "<=");
		 }
		 if(t.lex().equals(">"))
		 {
			 match(">");
			 
			 return new NodeRelop(scanner.pos(), ">");
		 }
		 if(t.lex().equals(">="))
		 {
			 match(">=");
			 return new NodeRelop(scanner.pos(), ">=");
		 }
		 if(t.lex().equals("<>"))
		 {
			 match("<>");
			 return new NodeRelop(scanner.pos(), "<>");
		 }
		 if(t.lex().equals("=="))
		 {
			 match("==");
			 return new NodeRelop(scanner.pos(), "==");
		 }
		 
		 return null;
	}
	
	private NodeFact parseNodeFact() throws SyntaxException
	{	
		NodeFact nf;
		boolean negative = false; 
		String leftP = "(";
		Token t = scanner.curr();
		NodeExpr ne = null;
		NodeExpr ne2 = null;
		
		Token id = new Token("id");
		Token num = new Token("num");
		
		if(t.equals(id))
		{
			match("id");
		}
		if(t.lex().equals("-"))
		{
			match("-");
			//nf = parseNodeFact();
			negative = true;
			ne2 = parseExpr();
		}
		else if(t.equals(num))
		{
			match("num");
		}
		else if(t.lex().equals("("))
		{
			match("(");
			ne = parseExpr();
			if(t.lex().equals(")"))
					{
			match(")");
					}
		}
		 if(ne2 != null)
		{
			return new NodeFact(ne2, negative);
		}
		 else if(ne == null)
		{
			return new NodeFact(t.lex());
		}
		
		else
		{
			return new NodeFact(ne,negative);
		}
		
		
	}

	private NodeAssn parseAssn() throws SyntaxException {
		
		Token id = scanner.curr();
		
		match("id");
		match("=");
		
		NodeExpr ne = parseExpr();
		
		return new NodeAssn(id.lex(), ne);
	}



	private void match(String s) throws SyntaxException {
		scanner.match(new Token(s));
	}

}
