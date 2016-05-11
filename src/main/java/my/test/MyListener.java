package my.test;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.NotNull;
import org.cobol85.Cobol85BaseListener;
import org.cobol85.Cobol85Parser;

public class MyListener extends Cobol85BaseListener {
	
	private int iflevel=0;
	private String last_cond="";
	
	private String myGetText(ParserRuleContext ctx) {
		String l=String.valueOf(ctx.getStart().getLine());
		int a = ctx.start.getStartIndex();
	    int b = ctx.stop.getStopIndex();
	    Interval interval = new Interval(a,b);
	    String line=ctx.start.getInputStream().getText(interval);
	    
		return line;
	}
	
	@Override public void enterIfStatement(@NotNull Cobol85Parser.IfStatementContext ctx) { 
		//System.out.println("enter if");
		iflevel++;
		last_cond=myGetText(ctx.condition());
	}
	
	@Override public void exitIfStatement(@NotNull Cobol85Parser.IfStatementContext ctx) {
		//System.out.println("exit if");
		iflevel--;
	}
	
	@Override public void exitDisplayStatement(@NotNull Cobol85Parser.DisplayStatementContext ctx) { 
		//System.out.println("exit display");
		
		String line=myGetText(ctx);
	    System.out.println("original line:"+line);
		
		if(iflevel>0){
			System.out.println("\nDISPLAY dentro if (livello "+iflevel+" condizione "+last_cond+")");
			
			System.out.println("  "+ctx.getText());
		} else {
			System.out.println("\nDISPLAY fuori if");
			System.out.println("  "+ctx.getText());
		}
	}


}
