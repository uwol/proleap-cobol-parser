package my.test;

import org.cobol85.Cobol85BaseVisitor;

public class MyVisitor extends Cobol85BaseVisitor<Boolean> {

	/*
	 * exemplary callback function for identification division
	 */
	@Override
	public Boolean visitIdentificationDivision(
			final org.cobol85.Cobol85Parser.IdentificationDivisionContext ctx) {
		return visitChildren(ctx);
	}
	
	//20160501 AC
	@Override
	public Boolean visitDisplayStatement(
			final org.cobol85.Cobol85Parser.DisplayStatementContext ctx) {
		System.out.println("Display " +ctx.literal(0).getText());
		return visitChildren(ctx);
	}
	

}
