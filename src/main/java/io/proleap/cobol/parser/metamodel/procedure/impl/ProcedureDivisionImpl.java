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
import io.proleap.cobol.Cobol85Parser.CallByContentStatementContext;
import io.proleap.cobol.Cobol85Parser.CallByReferenceStatementContext;
import io.proleap.cobol.Cobol85Parser.CallByValueStatementContext;
import io.proleap.cobol.Cobol85Parser.CallStatementContext;
import io.proleap.cobol.Cobol85Parser.CancelCallContext;
import io.proleap.cobol.Cobol85Parser.CancelStatementContext;
import io.proleap.cobol.Cobol85Parser.CloseFileContext;
import io.proleap.cobol.Cobol85Parser.CloseStatementContext;
import io.proleap.cobol.Cobol85Parser.ComputeStatementContext;
import io.proleap.cobol.Cobol85Parser.ComputeStoreContext;
import io.proleap.cobol.Cobol85Parser.ContinueStatementContext;
import io.proleap.cobol.Cobol85Parser.DeleteStatementContext;
import io.proleap.cobol.Cobol85Parser.DisableStatementContext;
import io.proleap.cobol.Cobol85Parser.DisplayStatementContext;
import io.proleap.cobol.Cobol85Parser.IdentifierContext;
import io.proleap.cobol.Cobol85Parser.InvalidKeyPhraseContext;
import io.proleap.cobol.Cobol85Parser.LiteralContext;
import io.proleap.cobol.Cobol85Parser.MoveToStatementContext;
import io.proleap.cobol.Cobol85Parser.MoveToStatementSendingAreaContext;
import io.proleap.cobol.Cobol85Parser.NotInvalidKeyPhraseContext;
import io.proleap.cobol.Cobol85Parser.NotOnExceptionClauseContext;
import io.proleap.cobol.Cobol85Parser.NotOnOverflowPhraseContext;
import io.proleap.cobol.Cobol85Parser.NotOnSizeErrorPhraseContext;
import io.proleap.cobol.Cobol85Parser.OnExceptionClauseContext;
import io.proleap.cobol.Cobol85Parser.OnOverflowPhraseContext;
import io.proleap.cobol.Cobol85Parser.OnSizeErrorPhraseContext;
import io.proleap.cobol.Cobol85Parser.ParagraphContext;
import io.proleap.cobol.Cobol85Parser.ParagraphNameContext;
import io.proleap.cobol.Cobol85Parser.PerformStatementContext;
import io.proleap.cobol.Cobol85Parser.ProcedureDivisionContext;
import io.proleap.cobol.Cobol85Parser.StopStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionImpl;
import io.proleap.cobol.parser.metamodel.procedure.InvalidKeyPhrase;
import io.proleap.cobol.parser.metamodel.procedure.NotInvalidKeyPhrase;
import io.proleap.cobol.parser.metamodel.procedure.NotOnExceptionClause;
import io.proleap.cobol.parser.metamodel.procedure.NotOnOverflowPhrase;
import io.proleap.cobol.parser.metamodel.procedure.NotOnSizeErrorPhrase;
import io.proleap.cobol.parser.metamodel.procedure.OnExceptionClause;
import io.proleap.cobol.parser.metamodel.procedure.OnOverflowPhrase;
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
import io.proleap.cobol.parser.metamodel.procedure.cancel.CancelStatement;
import io.proleap.cobol.parser.metamodel.procedure.cancel.impl.CancelStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.close.CloseStatement;
import io.proleap.cobol.parser.metamodel.procedure.close.impl.CloseStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.compute.ComputeStatement;
import io.proleap.cobol.parser.metamodel.procedure.compute.impl.ComputeStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.contin.ContinueStatement;
import io.proleap.cobol.parser.metamodel.procedure.contin.impl.ContinueStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.delete.DeleteStatement;
import io.proleap.cobol.parser.metamodel.procedure.delete.impl.DeleteStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.disable.DisableStatement;
import io.proleap.cobol.parser.metamodel.procedure.disable.impl.DisableStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.parser.metamodel.procedure.display.impl.DisplayStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.move.MoveToStatement;
import io.proleap.cobol.parser.metamodel.procedure.move.impl.MoveToStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.perform.PerformStatement;
import io.proleap.cobol.parser.metamodel.procedure.perform.impl.PerformStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.parser.metamodel.procedure.stop.impl.StopStatementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.impl.LiteralValueStmtImpl;

public class ProcedureDivisionImpl extends CobolDivisionImpl implements ProcedureDivision {

	private final static Logger LOG = LogManager.getLogger(ProcedureDivisionImpl.class);

	protected final ProcedureDivisionContext ctx;

	protected List<Paragraph> paragraphs = new ArrayList<Paragraph>();

	protected Map<String, Paragraph> paragraphsSymbolTable = new HashMap<String, Paragraph>();

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

			// accept call
			final Call acceptCall = createCall(ctx.identifier());
			result.setAcceptCall(acceptCall);

			// type
			final Type type;

			if (ctx.acceptFromDateStatement() != null) {
				result.addAcceptFromDate(ctx.acceptFromDateStatement());
				type = Type.Date;
			} else if (ctx.acceptFromMnemonicStatement() != null) {
				result.addAcceptFromMnemonic(ctx.acceptFromMnemonicStatement());
				type = Type.Mnemonic;
			} else if (ctx.acceptMessageCountStatement() != null) {
				result.addAcceptMessageCount(ctx.acceptMessageCountStatement());
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

			// add sub statement
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

			// on size
			if (ctx.onSizeErrorPhrase() != null) {
				final OnSizeErrorPhrase onSizeErrorPhrase = createOnSizeErrorPhrase(ctx.onSizeErrorPhrase());
				result.setOnSizeErrorPhrase(onSizeErrorPhrase);
			}

			// not on size
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

			// called program
			final Call programCall;

			if (ctx.literal() != null) {
				programCall = createCall(ctx.literal());
			} else if (ctx.identifier() != null) {
				programCall = createCall(ctx.identifier());
			} else {
				LOG.warn("unknown program call at {}", ctx);
				programCall = null;
			}

			result.setProgramCall(programCall);

			// using call by reference
			for (final CallByReferenceStatementContext callByReferenceStatementContext : ctx
					.callByReferenceStatement()) {
				result.addCallByReferenceStatement(callByReferenceStatementContext);
			}

			// using call by value
			for (final CallByValueStatementContext callByValueStatementContext : ctx.callByValueStatement()) {
				result.addCallByValueStatement(callByValueStatementContext);
			}

			// using call by content
			for (final CallByContentStatementContext callByContentStatementContext : ctx.callByContentStatement()) {
				result.addCallByContentStatement(callByContentStatementContext);
			}

			// giving
			if (ctx.callGivingPhrase() != null) {
				result.addGivingPhrase(ctx.callGivingPhrase());
			}

			// on overflow
			if (ctx.onOverflowPhrase() != null) {
				final OnOverflowPhrase onOverflowPhrase = createOnOverflowPhrase(ctx.onOverflowPhrase());
				result.setOnOverflowPhrase(onOverflowPhrase);
			}

			// on exception
			if (ctx.onExceptionClause() != null) {
				final OnExceptionClause onExceptionClause = createOnExceptionClause(ctx.onExceptionClause());
				result.setOnExceptionClause(onExceptionClause);
			}

			// not on exception
			if (ctx.notOnExceptionClause() != null) {
				final NotOnExceptionClause notOnExceptionClause = createNotOnExceptionClause(
						ctx.notOnExceptionClause());
				result.setNotOnExceptionClause(notOnExceptionClause);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public CancelStatement addCancelStatement(final CancelStatementContext ctx) {
		CancelStatement result = (CancelStatement) getASGElement(ctx);

		if (result == null) {
			result = new CancelStatementImpl(programUnit, ctx);

			for (final CancelCallContext cancelCallContext : ctx.cancelCall()) {
				result.addCancelCall(cancelCallContext);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public CloseStatement addCloseStatement(final CloseStatementContext ctx) {
		CloseStatement result = (CloseStatement) getASGElement(ctx);

		if (result == null) {
			result = new CloseStatementImpl(programUnit, ctx);

			for (final CloseFileContext closeFileContext : ctx.closeFile()) {
				result.addCloseFile(closeFileContext);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ComputeStatement addComputeStatement(final ComputeStatementContext ctx) {
		ComputeStatement result = (ComputeStatement) getASGElement(ctx);

		if (result == null) {
			result = new ComputeStatementImpl(programUnit, ctx);

			// store calls
			for (final ComputeStoreContext computeStoreContext : ctx.computeStore()) {
				result.addStore(computeStoreContext);
			}

			// arithmetic expression
			final ArithmeticValueStmt arithmeticExpression = createArithmeticValueStmt(ctx.arithmeticExpression());
			result.setArithmeticExpression(arithmeticExpression);

			// on size error
			if (ctx.onSizeErrorPhrase() != null) {
				final OnSizeErrorPhrase onSizeErrorPhrase = createOnSizeErrorPhrase(ctx.onSizeErrorPhrase());
				result.setOnSizeErrorPhrase(onSizeErrorPhrase);
			}

			// not on size error
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
	public ContinueStatement addContinueStatement(final ContinueStatementContext ctx) {
		ContinueStatement result = (ContinueStatement) getASGElement(ctx);

		if (result == null) {
			result = new ContinueStatementImpl(programUnit, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public DeleteStatement addDeleteStatement(final DeleteStatementContext ctx) {
		DeleteStatement result = (DeleteStatement) getASGElement(ctx);

		if (result == null) {
			result = new DeleteStatementImpl(programUnit, ctx);

			// file
			final Call fileCall = createCall(ctx.fileName());
			result.setFileCall(fileCall);

			if (ctx.RECORD() != null) {
				result.setRecord(true);
			}

			// invalid key
			if (ctx.invalidKeyPhrase() != null) {
				final InvalidKeyPhrase invalidKeyPhrase = createInvalidKeyPhrase(ctx.invalidKeyPhrase());
				result.setInvalidKeyPhrase(invalidKeyPhrase);
			}

			// not invalid key
			if (ctx.notInvalidKeyPhrase() != null) {
				final NotInvalidKeyPhrase notInvalidKeyPhrase = createNotInvalidKeyPhrase(ctx.notInvalidKeyPhrase());
				result.setNotInvalidKeyPhrase(notInvalidKeyPhrase);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public DisableStatement addDisableStatement(final DisableStatementContext ctx) {
		DisableStatement result = (DisableStatement) getASGElement(ctx);

		if (result == null) {
			result = new DisableStatementImpl(programUnit, ctx);

			// type
			final DisableStatement.Type type;

			if (ctx.INPUT() != null) {
				type = DisableStatement.Type.Input;
			} else if (ctx.I_O() != null) {
				type = DisableStatement.Type.InputOutput;
			} else if (ctx.OUTPUT() != null) {
				type = DisableStatement.Type.Output;
			} else {
				type = null;
			}

			result.setType(type);

			// terminal
			if (ctx.TERMINAL() != null) {
				result.setTerminal(true);
			}

			// cd name
			final Call cdNameCall = createCall(ctx.cdName());
			result.setCommunicationDescriptionCall(cdNameCall);

			// key
			final Call keyCall;

			if (ctx.identifier() != null) {
				keyCall = createCall(ctx.identifier());
			} else if (ctx.literal() != null) {
				keyCall = createCall(ctx.literal());
			} else {
				keyCall = null;
			}

			result.setKeyCall(keyCall);

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
			result.addSendingAreaValueStmt(moveToStatementSendingArea);

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
			paragraphsSymbolTable.put(name, result);

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

			// perform procedure
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

	protected InvalidKeyPhrase createInvalidKeyPhrase(final InvalidKeyPhraseContext ctx) {
		InvalidKeyPhrase result = (InvalidKeyPhrase) getASGElement(ctx);

		if (result == null) {
			result = new InvalidKeyPhraseImpl(programUnit, ctx);

			// FIXME add statements

			registerASGElement(result);
		}

		return result;
	}

	protected NotInvalidKeyPhrase createNotInvalidKeyPhrase(final NotInvalidKeyPhraseContext ctx) {
		NotInvalidKeyPhrase result = (NotInvalidKeyPhrase) getASGElement(ctx);

		if (result == null) {
			result = new NotInvalidKeyPhraseImpl(programUnit, ctx);

			// FIXME add statements

			registerASGElement(result);
		}

		return result;
	}

	protected NotOnExceptionClause createNotOnExceptionClause(final NotOnExceptionClauseContext ctx) {
		NotOnExceptionClause result = (NotOnExceptionClause) getASGElement(ctx);

		if (result == null) {
			result = new NotOnExceptionClauseImpl(programUnit, ctx);

			// FIXME add statements

			registerASGElement(result);
		}

		return result;
	}

	protected NotOnOverflowPhrase createNotOnOverflowPhrase(final NotOnOverflowPhraseContext ctx) {
		NotOnOverflowPhrase result = (NotOnOverflowPhrase) getASGElement(ctx);

		if (result == null) {
			result = new NotOnOverflowPhraseImpl(programUnit, ctx);

			// FIXME add statements

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

	protected OnExceptionClause createOnExceptionClause(final OnExceptionClauseContext ctx) {
		OnExceptionClause result = (OnExceptionClause) getASGElement(ctx);

		if (result == null) {
			result = new OnExceptionClauseImpl(programUnit, ctx);

			// FIXME add statements

			registerASGElement(result);
		}

		return result;
	}

	protected OnOverflowPhrase createOnOverflowPhrase(final OnOverflowPhraseContext ctx) {
		OnOverflowPhrase result = (OnOverflowPhrase) getASGElement(ctx);

		if (result == null) {
			result = new OnOverflowPhraseImpl(programUnit, ctx);

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
		return paragraphsSymbolTable.get(name);
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
