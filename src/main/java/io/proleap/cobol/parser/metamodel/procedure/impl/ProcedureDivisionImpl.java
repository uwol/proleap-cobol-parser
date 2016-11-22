/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.AcceptStatementContext;
import io.proleap.cobol.Cobol85Parser.AddStatementContext;
import io.proleap.cobol.Cobol85Parser.AlterProceedToContext;
import io.proleap.cobol.Cobol85Parser.AlterStatementContext;
import io.proleap.cobol.Cobol85Parser.CallByReferenceStatementContext;
import io.proleap.cobol.Cobol85Parser.CallStatementContext;
import io.proleap.cobol.Cobol85Parser.DisplayStatementContext;
import io.proleap.cobol.Cobol85Parser.IdentifierContext;
import io.proleap.cobol.Cobol85Parser.LiteralContext;
import io.proleap.cobol.Cobol85Parser.MoveToStatementContext;
import io.proleap.cobol.Cobol85Parser.MoveToStatementSendingAreaContext;
import io.proleap.cobol.Cobol85Parser.NotOnSizeErrorPhraseContext;
import io.proleap.cobol.Cobol85Parser.OnSizeErrorPhraseContext;
import io.proleap.cobol.Cobol85Parser.ParagraphContext;
import io.proleap.cobol.Cobol85Parser.ParagraphNameContext;
import io.proleap.cobol.Cobol85Parser.PerformStatementContext;
import io.proleap.cobol.Cobol85Parser.ProcedureDivisionContext;
import io.proleap.cobol.Cobol85Parser.StopStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionImpl;
import io.proleap.cobol.parser.metamodel.procedure.NotOnSizeErrorPhrase;
import io.proleap.cobol.parser.metamodel.procedure.OnSizeErrorPhrase;
import io.proleap.cobol.parser.metamodel.procedure.Paragraph;
import io.proleap.cobol.parser.metamodel.procedure.ParagraphName;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.Statement;
import io.proleap.cobol.parser.metamodel.procedure.accept.AcceptStatement;
import io.proleap.cobol.parser.metamodel.procedure.accept.AcceptStatement.Type;
import io.proleap.cobol.parser.metamodel.procedure.accept.impl.AcceptStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.add.AddStatement;
import io.proleap.cobol.parser.metamodel.procedure.add.impl.AddStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.alter.AlterStatement;
import io.proleap.cobol.parser.metamodel.procedure.alter.impl.AlterStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.call.CallStatement;
import io.proleap.cobol.parser.metamodel.procedure.call.impl.CallStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.parser.metamodel.procedure.display.impl.DisplayStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.move.MoveToStatement;
import io.proleap.cobol.parser.metamodel.procedure.move.impl.MoveToStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.perform.PerformStatement;
import io.proleap.cobol.parser.metamodel.procedure.perform.impl.PerformStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.parser.metamodel.procedure.stop.impl.StopStatementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.impl.LiteralValueStmtImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.impl.ValueStmtDelegateImpl;

public class ProcedureDivisionImpl extends CobolDivisionImpl implements ProcedureDivision {

	private final static Logger LOG = LogManager.getLogger(ProcedureDivisionImpl.class);

	protected final ProcedureDivisionContext ctx;

	protected List<Paragraph> paragraphs = new ArrayList<Paragraph>();

	protected Map<String, Paragraph> paragraphsByName = new HashMap<String, Paragraph>();

	protected List<Statement> statements = new ArrayList<Statement>();

	public ProcedureDivisionImpl(final ProgramUnit programUnit, final ProcedureDivisionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public AcceptStatement addAcceptStatement(final AcceptStatementContext ctx) {
		AcceptStatement result = (AcceptStatement) getASGElement(ctx);

		if (result == null) {
			result = new AcceptStatementImpl(programUnit, ctx);

			/*
			 * accept value stmt
			 */
			final CallValueStmt acceptValueStmt = createCallValueStmt(ctx.identifier());
			result.setAcceptValueStmt(acceptValueStmt);

			/*
			 * type
			 */
			final Type type;

			if (ctx.acceptFromDate() != null) {
				result.addAcceptFromDate(ctx.acceptFromDate());
				type = Type.Date;
			} else if (ctx.acceptFromMnemonic() != null) {
				result.addAcceptFromMnemonic(ctx.acceptFromMnemonic());
				type = Type.Mnemonic;
			} else if (ctx.acceptMessageCount() != null) {
				result.addAcceptMessageCount(ctx.acceptMessageCount());
				type = Type.MessageCount;
			} else {
				LOG.warn("unknown type at {}", ctx);
				type = null;
			}

			result.setType(type);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public AddStatement addAddStatement(final AddStatementContext ctx) {
		AddStatement result = (AddStatement) getASGElement(ctx);

		if (result == null) {
			result = new AddStatementImpl(programUnit, ctx);

			/*
			 * add sub statement
			 */
			final AddStatement.Type type;

			if (ctx.addToStatement() != null) {
				result.addAddTo(ctx.addToStatement());
				type = AddStatement.Type.To;
			} else if (ctx.addToGivingStatement() != null) {
				result.addAddToGiving(ctx.addToGivingStatement());
				type = AddStatement.Type.Giving;
			} else if (ctx.addCorrespondingStatement() != null) {
				result.addAddCorresponding(ctx.addCorrespondingStatement());
				type = AddStatement.Type.Corresponding;
			} else {
				LOG.warn("unknown add statement at {}", ctx);
				type = null;
			}

			result.setType(type);

			/*
			 * on size
			 */
			if (ctx.onSizeErrorPhrase() != null) {
				final OnSizeErrorPhrase onSizeErrorPhrase = createOnSizeErrorPhrase(ctx.onSizeErrorPhrase());
				result.setOnSizeErrorPhrase(onSizeErrorPhrase);
			}

			/*
			 * not on size
			 */
			if (ctx.notOnSizeErrorPhrase() != null) {
				final NotOnSizeErrorPhrase notOnSizeErrorPhrase = createNotOnSizeErrorPhrase(
						ctx.notOnSizeErrorPhrase());
				result.setNotOnSizeErrorPhrase(notOnSizeErrorPhrase);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public AlterStatement addAlterStatement(final AlterStatementContext ctx) {
		AlterStatement result = (AlterStatement) getASGElement(ctx);

		if (result == null) {
			result = new AlterStatementImpl(programUnit, ctx);

			for (final AlterProceedToContext alterProceedToContext : ctx.alterProceedTo()) {
				result.addAlterProceedTo(alterProceedToContext);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public CallStatement addCallStatement(final CallStatementContext ctx) {
		CallStatement result = (CallStatement) getASGElement(ctx);

		if (result == null) {
			result = new CallStatementImpl(programUnit, ctx);

			/*
			 * using call by reference
			 */
			for (final CallByReferenceStatementContext callByReferenceStatementContext : ctx
					.callByReferenceStatement()) {
				result.addCallByReferenceStatement(callByReferenceStatementContext);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public DisplayStatement addDisplayStatement(final DisplayStatementContext ctx) {
		DisplayStatement result = (DisplayStatement) getASGElement(ctx);

		if (result == null) {
			result = new DisplayStatementImpl(programUnit, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public MoveToStatement addMoveToStatement(final MoveToStatementContext ctx) {
		MoveToStatement result = (MoveToStatement) getASGElement(ctx);

		if (result == null) {
			result = new MoveToStatementImpl(programUnit, ctx);

			final MoveToStatementSendingAreaContext moveToStatementSendingArea = ctx.moveToStatementSendingArea();
			final List<IdentifierContext> identifierCtxs = ctx.identifier();

			// sending area value statement
			final ValueStmt sendingAreaValueStmt = addValueStmt(moveToStatementSendingArea);
			result.setSendingAreaValueStmt(sendingAreaValueStmt);

			// receiving area calls
			for (final IdentifierContext identifierCtx : identifierCtxs) {
				final Call receivingAreaCall = createCall(identifierCtx);
				result.addReceivingAreaCall(receivingAreaCall);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public Paragraph addParagraph(final ParagraphContext ctx) {
		Paragraph result = (Paragraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ParagraphImpl(name, programUnit, ctx);

			paragraphs.add(result);
			paragraphsByName.put(name, result);

			final ParagraphName paragraphName = addParagraphName(ctx.paragraphName());
			result.addParagraphName(paragraphName);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ParagraphName addParagraphName(final ParagraphNameContext ctx) {
		ParagraphName result = (ParagraphName) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ParagraphNameImpl(name, programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public PerformStatement addPerformStatement(final PerformStatementContext ctx) {
		PerformStatement result = (PerformStatement) getASGElement(ctx);

		if (result == null) {
			result = new PerformStatementImpl(programUnit, ctx);

			/*
			 * perform procedure
			 */
			if (ctx.performProcedureStatement() != null) {
				result.addPerformProcedureStatement(ctx.performProcedureStatement());
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public StopStatement addStopStatement(final StopStatementContext ctx) {
		StopStatement result = (StopStatement) getASGElement(ctx);

		if (result == null) {
			result = new StopStatementImpl(programUnit, ctx);

			registerStatement(result);
		}

		return result;
	}

	public ValueStmt addValueStmt(final LiteralContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new LiteralValueStmtImpl(programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueStmt addValueStmt(final MoveToStatementSendingAreaContext ctx) {
		ValueStmt result = (ValueStmt) getASGElement(ctx);

		if (result == null) {
			/*
			 * then the delegated value stmt
			 */
			final ValueStmt delegatedValueStmt;

			if (ctx.identifier() != null) {
				delegatedValueStmt = createCallValueStmt(ctx.identifier());
			} else if (ctx.literal() != null) {
				delegatedValueStmt = createLiteralValueStmt(ctx.literal());
			} else {
				LOG.warn("unknown value stmt {}.", ctx);
				delegatedValueStmt = null;
			}

			result = new ValueStmtDelegateImpl(delegatedValueStmt, programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	protected NotOnSizeErrorPhrase createNotOnSizeErrorPhrase(final NotOnSizeErrorPhraseContext ctx) {
		NotOnSizeErrorPhrase result = (NotOnSizeErrorPhrase) getASGElement(ctx);

		if (result == null) {
			result = new NotOnSizeErrorPhraseImpl(programUnit, ctx);

			// FIXME add statements

			registerASGElement(result);
		}

		return result;
	}

	protected OnSizeErrorPhrase createOnSizeErrorPhrase(final OnSizeErrorPhraseContext ctx) {
		OnSizeErrorPhrase result = (OnSizeErrorPhrase) getASGElement(ctx);

		if (result == null) {
			result = new OnSizeErrorPhraseImpl(programUnit, ctx);

			// FIXME add statements

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Paragraph getParagraph(final String name) {
		return paragraphsByName.get(name);
	}

	@Override
	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}

	@Override
	public List<Statement> getStatements() {
		return statements;
	}

	protected void registerStatement(final Statement statement) {
		statements.add(statement);
		registerASGElement(statement);
	}
}
