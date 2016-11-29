package io.proleap.cobol.parser.metamodel.impl;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import io.proleap.cobol.Cobol85Parser.StatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.StatementsContainer;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

public class StatementsContainerImpl extends CobolDivisionElementImpl implements StatementsContainer {

	protected List<Statement> statements = new ArrayList<Statement>();

	public StatementsContainerImpl(final ProgramUnit programUnit, final ParseTree ctx) {
		super(programUnit, ctx);
	}

	@Override
	public Statement addStatement(final StatementContext ctx) {
		// FIXME
		return null;
	}

	@Override
	public List<Statement> getStatements() {
		return statements;
	}
}
