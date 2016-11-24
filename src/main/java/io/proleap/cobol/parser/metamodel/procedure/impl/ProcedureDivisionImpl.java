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
import io.proleap.cobol.Cobol85Parser.CdNameContext;
import io.proleap.cobol.Cobol85Parser.CloseFileContext;
import io.proleap.cobol.Cobol85Parser.CloseStatementContext;
import io.proleap.cobol.Cobol85Parser.ComputeStatementContext;
import io.proleap.cobol.Cobol85Parser.ComputeStoreContext;
import io.proleap.cobol.Cobol85Parser.ContinueStatementContext;
import io.proleap.cobol.Cobol85Parser.DeleteStatementContext;
import io.proleap.cobol.Cobol85Parser.DisableStatementContext;
import io.proleap.cobol.Cobol85Parser.DisplayOperandContext;
import io.proleap.cobol.Cobol85Parser.DisplayStatementContext;
import io.proleap.cobol.Cobol85Parser.DivideStatementContext;
import io.proleap.cobol.Cobol85Parser.EnableStatementContext;
import io.proleap.cobol.Cobol85Parser.EntryStatementContext;
import io.proleap.cobol.Cobol85Parser.ExitStatementContext;
import io.proleap.cobol.Cobol85Parser.GobackStatementContext;
import io.proleap.cobol.Cobol85Parser.IdentifierContext;
import io.proleap.cobol.Cobol85Parser.InitiateStatementContext;
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
import io.proleap.cobol.Cobol85Parser.ProcedureDeclarativeContext;
import io.proleap.cobol.Cobol85Parser.ProcedureDeclarativesContext;
import io.proleap.cobol.Cobol85Parser.ProcedureDivisionContext;
import io.proleap.cobol.Cobol85Parser.PurgeStatementContext;
import io.proleap.cobol.Cobol85Parser.ReleaseStatementContext;
import io.proleap.cobol.Cobol85Parser.ReportNameContext;
import io.proleap.cobol.Cobol85Parser.StopStatementContext;
import io.proleap.cobol.Cobol85Parser.TerminateStatementContext;
import io.proleap.cobol.Cobol85Parser.WriteStatementContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionImpl;
import io.proleap.cobol.parser.metamodel.procedure.InvalidKey;
import io.proleap.cobol.parser.metamodel.procedure.NotInvalidKey;
import io.proleap.cobol.parser.metamodel.procedure.NotOnException;
import io.proleap.cobol.parser.metamodel.procedure.NotOnOverflow;
import io.proleap.cobol.parser.metamodel.procedure.NotOnSizeError;
import io.proleap.cobol.parser.metamodel.procedure.OnException;
import io.proleap.cobol.parser.metamodel.procedure.OnOverflow;
import io.proleap.cobol.parser.metamodel.procedure.OnSizeError;
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
import io.proleap.cobol.parser.metamodel.procedure.continuestmt.ContinueStatement;
import io.proleap.cobol.parser.metamodel.procedure.continuestmt.impl.ContinueStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.declaratives.Declaratives;
import io.proleap.cobol.parser.metamodel.procedure.declaratives.impl.DeclarativesImpl;
import io.proleap.cobol.parser.metamodel.procedure.delete.DeleteStatement;
import io.proleap.cobol.parser.metamodel.procedure.delete.impl.DeleteStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.disable.DisableStatement;
import io.proleap.cobol.parser.metamodel.procedure.disable.impl.DisableStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.parser.metamodel.procedure.display.impl.DisplayStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.divide.DivideStatement;
import io.proleap.cobol.parser.metamodel.procedure.divide.impl.DivideStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.enable.EnableStatement;
import io.proleap.cobol.parser.metamodel.procedure.enable.impl.EnableStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.entry.EntryStatement;
import io.proleap.cobol.parser.metamodel.procedure.entry.impl.EntryStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.exit.ExitStatement;
import io.proleap.cobol.parser.metamodel.procedure.exit.impl.ExitStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.goback.GobackStatement;
import io.proleap.cobol.parser.metamodel.procedure.goback.impl.GobackStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.initiate.InitiateStatement;
import io.proleap.cobol.parser.metamodel.procedure.initiate.impl.InitiateStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.move.MoveToStatement;
import io.proleap.cobol.parser.metamodel.procedure.move.impl.MoveToStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.perform.PerformStatement;
import io.proleap.cobol.parser.metamodel.procedure.perform.impl.PerformStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.purge.PurgeStatement;
import io.proleap.cobol.parser.metamodel.procedure.purge.impl.PurgeStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.release.ReleaseStatement;
import io.proleap.cobol.parser.metamodel.procedure.release.impl.ReleaseStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.parser.metamodel.procedure.stop.impl.StopStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.terminate.TerminateStatement;
import io.proleap.cobol.parser.metamodel.procedure.terminate.impl.TerminateStatementImpl;
import io.proleap.cobol.parser.metamodel.procedure.write.WriteStatement;
import io.proleap.cobol.parser.metamodel.procedure.write.impl.WriteStatementImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.impl.LiteralValueStmtImpl;

public class ProcedureDivisionImpl extends CobolDivisionImpl implements ProcedureDivision {

	private final static Logger LOG = LogManager.getLogger(ProcedureDivisionImpl.class);

	protected final ProcedureDivisionContext ctx;

	protected Declaratives declaratives;

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
				final OnSizeError onSizeError = createOnSizeError(ctx.onSizeErrorPhrase());
				result.setOnSizeError(onSizeError);
			}

			// not on size
			if (ctx.notOnSizeErrorPhrase() != null) {
				final NotOnSizeError notOnSizeError = createNotOnSizeError(ctx.notOnSizeErrorPhrase());
				result.setNotOnSize(notOnSizeError);
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
			final Call programCall = createCall(ctx.literal(), ctx.identifier());
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
				result.addGiving(ctx.callGivingPhrase());
			}

			// on overflow
			if (ctx.onOverflowPhrase() != null) {
				final OnOverflow onOverflow = createOnOverflow(ctx.onOverflowPhrase());
				result.setOnOverflow(onOverflow);
			}

			// on exception
			if (ctx.onExceptionClause() != null) {
				final OnException onException = createOnException(ctx.onExceptionClause());
				result.setOnException(onException);
			}

			// not on exception
			if (ctx.notOnExceptionClause() != null) {
				final NotOnException notOnException = createNotOnException(ctx.notOnExceptionClause());
				result.setNotOnException(notOnException);
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
				final OnSizeError onSizeError = createOnSizeError(ctx.onSizeErrorPhrase());
				result.setOnSizeError(onSizeError);
			}

			// not on size error
			if (ctx.notOnSizeErrorPhrase() != null) {
				final NotOnSizeError notOnSizeError = createNotOnSizeError(ctx.notOnSizeErrorPhrase());
				result.setNotOnSizeError(notOnSizeError);
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
	public Declaratives addDeclaratives(final ProcedureDeclarativesContext ctx) {
		Declaratives result = (Declaratives) getASGElement(ctx);

		if (result == null) {
			result = new DeclarativesImpl(programUnit, ctx);

			// declaratives
			for (final ProcedureDeclarativeContext procedureDeclarativeContext : ctx.procedureDeclarative()) {
				result.addDeclarative(procedureDeclarativeContext);
			}

			declaratives = result;
			registerASGElement(result);
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
				final InvalidKey invalidKey = createInvalidKey(ctx.invalidKeyPhrase());
				result.setInvalidKey(invalidKey);
			}

			// not invalid key
			if (ctx.notInvalidKeyPhrase() != null) {
				final NotInvalidKey notInvalidKey = createNotInvalidKey(ctx.notInvalidKeyPhrase());
				result.setNotInvalidKey(notInvalidKey);
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
			final Call keyCall = createCall(ctx.identifier(), ctx.literal());
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

			// operands
			for (final DisplayOperandContext displayOperandContext : ctx.displayOperand()) {
				result.addOperand(displayOperandContext);
			}

			// upon
			if (ctx.displayUpon() != null) {
				result.addUpon(ctx.displayUpon());
			}

			// with
			if (ctx.displayWith() != null) {
				result.addWith(ctx.displayWith());
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public DivideStatement addDivideStatement(final DivideStatementContext ctx) {
		DivideStatement result = (DivideStatement) getASGElement(ctx);

		if (result == null) {
			result = new DivideStatementImpl(programUnit, ctx);

			// divisor
			final Call divisorCall = createCall(ctx.identifier(), ctx.literal());
			result.setDivisorCall(divisorCall);

			// giving
			final DivideStatement.Type type;

			if (ctx.divideIntoStatement() != null) {
				result.addInto(ctx.divideIntoStatement());
				type = DivideStatement.Type.Into;
			} else if (ctx.divideIntoGivingStatement() != null) {
				result.addIntoGiving(ctx.divideIntoGivingStatement());
				type = DivideStatement.Type.IntoGiving;
			} else if (ctx.divideIntoByGivingStatement() != null) {
				result.addIntoByGiving(ctx.divideIntoByGivingStatement());
				type = DivideStatement.Type.IntoByGiving;
			} else {
				type = null;
			}

			result.setType(type);

			// remainder
			if (ctx.divideRemainder() != null) {
				result.addRemainder(ctx.divideRemainder());
			}

			// on size
			if (ctx.onSizeErrorPhrase() != null) {
				final OnSizeError onSizeError = createOnSizeError(ctx.onSizeErrorPhrase());
				result.setOnSizeError(onSizeError);
			}

			// not on size
			if (ctx.notOnSizeErrorPhrase() != null) {
				final NotOnSizeError notOnSizeError = createNotOnSizeError(ctx.notOnSizeErrorPhrase());
				result.setNotOnSizeError(notOnSizeError);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public EnableStatement addEnableStatement(final EnableStatementContext ctx) {
		EnableStatement result = (EnableStatement) getASGElement(ctx);

		if (result == null) {
			result = new EnableStatementImpl(programUnit, ctx);

			// type
			final EnableStatement.Type type;

			if (ctx.INPUT() != null) {
				type = EnableStatement.Type.Input;
			} else if (ctx.I_O() != null) {
				type = EnableStatement.Type.InputOutput;
			} else if (ctx.OUTPUT() != null) {
				type = EnableStatement.Type.Output;
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
			final Call keyCall = createCall(ctx.identifier(), ctx.literal());
			result.setKeyCall(keyCall);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public EntryStatement addEntryStatement(final EntryStatementContext ctx) {
		EntryStatement result = (EntryStatement) getASGElement(ctx);

		if (result == null) {
			result = new EntryStatementImpl(programUnit, ctx);

			// entry
			final Call entryCall = createCall(ctx.literal());
			result.setEntryCall(entryCall);

			// using
			for (final IdentifierContext identifierContext : ctx.identifier()) {
				final Call usingCall = createCall(identifierContext);
				result.addUsingCall(usingCall);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ExitStatement addExitStatement(final ExitStatementContext ctx) {
		ExitStatement result = (ExitStatement) getASGElement(ctx);

		if (result == null) {
			result = new ExitStatementImpl(programUnit, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public GobackStatement addGobackStatement(final GobackStatementContext ctx) {
		GobackStatement result = (GobackStatement) getASGElement(ctx);

		if (result == null) {
			result = new GobackStatementImpl(programUnit, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public InitiateStatement addInitiateStatement(final InitiateStatementContext ctx) {
		InitiateStatement result = (InitiateStatement) getASGElement(ctx);

		if (result == null) {
			result = new InitiateStatementImpl(programUnit, ctx);

			for (final ReportNameContext reportNameContext : ctx.reportName()) {
				final Call reportCall = createCall(reportNameContext);
				result.addReportCall(reportCall);
			}

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
	public PurgeStatement addPurgeStatement(final PurgeStatementContext ctx) {
		PurgeStatement result = (PurgeStatement) getASGElement(ctx);

		if (result == null) {
			result = new PurgeStatementImpl(programUnit, ctx);

			for (final CdNameContext cdNameContext : ctx.cdName()) {
				final Call communicationDescriptionEntryCall = createCall(cdNameContext);
				result.addCommunicationDescriptionEntryCall(communicationDescriptionEntryCall);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ReleaseStatement addReleaseStatement(final ReleaseStatementContext ctx) {
		ReleaseStatement result = (ReleaseStatement) getASGElement(ctx);

		if (result == null) {
			result = new ReleaseStatementImpl(programUnit, ctx);

			// record
			final Call recordCall = createCall(ctx.recordName());
			result.setRecordCall(recordCall);

			// content
			final Call contentCall = createCall(ctx.qualifiedDataName());
			result.setContentCall(contentCall);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public StopStatement addStopStatement(final StopStatementContext ctx) {
		StopStatement result = (StopStatement) getASGElement(ctx);

		if (result == null) {
			result = new StopStatementImpl(programUnit, ctx);

			if (ctx.literal() != null) {
				final Call displayCall = createCall(ctx.literal());
				result.setDisplayCall(displayCall);
			}

			// type
			final StopStatement.Type type;

			if (ctx.RUN() != null) {
				type = StopStatement.Type.StopRun;
			} else if (ctx.literal() != null) {
				type = StopStatement.Type.StopRunAndDisplay;
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
	public TerminateStatement addTerminateStatement(final TerminateStatementContext ctx) {
		TerminateStatement result = (TerminateStatement) getASGElement(ctx);

		if (result == null) {
			result = new TerminateStatementImpl(programUnit, ctx);

			final Call reportCall = createCall(ctx.reportName());
			result.setReportCall(reportCall);

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
	public WriteStatement addWriteStatement(final WriteStatementContext ctx) {
		WriteStatement result = (WriteStatement) getASGElement(ctx);

		if (result == null) {
			result = new WriteStatementImpl(programUnit, ctx);

			// record
			final Call recordCall = createCall(ctx.recordName());
			result.setRecordCall(recordCall);

			// from
			if (ctx.writeFromPhrase() != null) {
				result.addFrom(ctx.writeFromPhrase());
			}

			// advancing
			if (ctx.writeAdvancingPhrase() != null) {
				result.addAdvancing(ctx.writeAdvancingPhrase());
			}

			// at end of page
			if (ctx.writeAtEndOfPagePhrase() != null) {
				result.addAtEndOfPage(ctx.writeAtEndOfPagePhrase());
			}

			// not at end of page
			if (ctx.writeNotAtEndOfPagePhrase() != null) {
				result.addNotAtEndOfPage(ctx.writeNotAtEndOfPagePhrase());
			}

			// invalid key
			if (ctx.invalidKeyPhrase() != null) {
				final InvalidKey invalidKey = createInvalidKey(ctx.invalidKeyPhrase());
				result.setInvalidKey(invalidKey);
			}

			// not invalid key
			if (ctx.notInvalidKeyPhrase() != null) {
				final NotInvalidKey notInvalidKey = createNotInvalidKey(ctx.notInvalidKeyPhrase());
				result.setNotInvalidKey(notInvalidKey);
			}

			registerStatement(result);
		}

		return result;
	}

	protected InvalidKey createInvalidKey(final InvalidKeyPhraseContext ctx) {
		InvalidKey result = (InvalidKey) getASGElement(ctx);

		if (result == null) {
			result = new InvalidKeyImpl(programUnit, ctx);

			// FIXME add statements

			registerASGElement(result);
		}

		return result;
	}

	protected NotInvalidKey createNotInvalidKey(final NotInvalidKeyPhraseContext ctx) {
		NotInvalidKey result = (NotInvalidKey) getASGElement(ctx);

		if (result == null) {
			result = new NotInvalidKeyImpl(programUnit, ctx);

			// FIXME add statements

			registerASGElement(result);
		}

		return result;
	}

	protected NotOnException createNotOnException(final NotOnExceptionClauseContext ctx) {
		NotOnException result = (NotOnException) getASGElement(ctx);

		if (result == null) {
			result = new NotOnExceptionImpl(programUnit, ctx);

			// FIXME add statements

			registerASGElement(result);
		}

		return result;
	}

	protected NotOnOverflow createNotOnOverflow(final NotOnOverflowPhraseContext ctx) {
		NotOnOverflow result = (NotOnOverflow) getASGElement(ctx);

		if (result == null) {
			result = new NotOnOverflowImpl(programUnit, ctx);

			// FIXME add statements

			registerASGElement(result);
		}

		return result;
	}

	protected NotOnSizeError createNotOnSizeError(final NotOnSizeErrorPhraseContext ctx) {
		NotOnSizeError result = (NotOnSizeError) getASGElement(ctx);

		if (result == null) {
			result = new NotOnSizeErrorImpl(programUnit, ctx);

			// FIXME add statements

			registerASGElement(result);
		}

		return result;
	}

	protected OnException createOnException(final OnExceptionClauseContext ctx) {
		OnException result = (OnException) getASGElement(ctx);

		if (result == null) {
			result = new OnExceptionImpl(programUnit, ctx);

			// FIXME add statements

			registerASGElement(result);
		}

		return result;
	}

	protected OnOverflow createOnOverflow(final OnOverflowPhraseContext ctx) {
		OnOverflow result = (OnOverflow) getASGElement(ctx);

		if (result == null) {
			result = new OnOverflowImpl(programUnit, ctx);

			// FIXME add statements

			registerASGElement(result);
		}

		return result;
	}

	protected OnSizeError createOnSizeError(final OnSizeErrorPhraseContext ctx) {
		OnSizeError result = (OnSizeError) getASGElement(ctx);

		if (result == null) {
			result = new OnSizeErrorImpl(programUnit, ctx);

			// FIXME add statements

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Declaratives getDeclaratives() {
		return declaratives;
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
