/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wlauseol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.cobol.CobolParser.AcceptStatementContext;
import io.proleap.cobol.CobolParser.AddStatementContext;
import io.proleap.cobol.CobolParser.AlterProceedToContext;
import io.proleap.cobol.CobolParser.AlterStatementContext;
import io.proleap.cobol.CobolParser.AtEndPhraseContext;
import io.proleap.cobol.CobolParser.CallStatementContext;
import io.proleap.cobol.CobolParser.CancelCallContext;
import io.proleap.cobol.CobolParser.CancelStatementContext;
import io.proleap.cobol.CobolParser.CdNameContext;
import io.proleap.cobol.CobolParser.CloseFileContext;
import io.proleap.cobol.CobolParser.CloseStatementContext;
import io.proleap.cobol.CobolParser.ComputeStatementContext;
import io.proleap.cobol.CobolParser.ComputeStoreContext;
import io.proleap.cobol.CobolParser.ContinueStatementContext;
import io.proleap.cobol.CobolParser.DeleteStatementContext;
import io.proleap.cobol.CobolParser.DisableStatementContext;
import io.proleap.cobol.CobolParser.DisplayOperandContext;
import io.proleap.cobol.CobolParser.DisplayStatementContext;
import io.proleap.cobol.CobolParser.DivideStatementContext;
import io.proleap.cobol.CobolParser.EnableStatementContext;
import io.proleap.cobol.CobolParser.EntryStatementContext;
import io.proleap.cobol.CobolParser.EvaluateAlsoSelectContext;
import io.proleap.cobol.CobolParser.EvaluateStatementContext;
import io.proleap.cobol.CobolParser.EvaluateWhenPhraseContext;
import io.proleap.cobol.CobolParser.ExecCicsStatementContext;
import io.proleap.cobol.CobolParser.ExecSqlImsStatementContext;
import io.proleap.cobol.CobolParser.ExecSqlStatementContext;
import io.proleap.cobol.CobolParser.ExhibitOperandContext;
import io.proleap.cobol.CobolParser.ExhibitStatementContext;
import io.proleap.cobol.CobolParser.ExitStatementContext;
import io.proleap.cobol.CobolParser.GenerateStatementContext;
import io.proleap.cobol.CobolParser.GoToStatementContext;
import io.proleap.cobol.CobolParser.GobackStatementContext;
import io.proleap.cobol.CobolParser.IdentifierContext;
import io.proleap.cobol.CobolParser.IfStatementContext;
import io.proleap.cobol.CobolParser.InitializeStatementContext;
import io.proleap.cobol.CobolParser.InitiateStatementContext;
import io.proleap.cobol.CobolParser.InspectStatementContext;
import io.proleap.cobol.CobolParser.InvalidKeyPhraseContext;
import io.proleap.cobol.CobolParser.MergeGivingPhraseContext;
import io.proleap.cobol.CobolParser.MergeOnKeyClauseContext;
import io.proleap.cobol.CobolParser.MergeStatementContext;
import io.proleap.cobol.CobolParser.MergeUsingContext;
import io.proleap.cobol.CobolParser.MoveStatementContext;
import io.proleap.cobol.CobolParser.MultiplyStatementContext;
import io.proleap.cobol.CobolParser.NotAtEndPhraseContext;
import io.proleap.cobol.CobolParser.NotInvalidKeyPhraseContext;
import io.proleap.cobol.CobolParser.NotOnExceptionClauseContext;
import io.proleap.cobol.CobolParser.NotOnOverflowPhraseContext;
import io.proleap.cobol.CobolParser.NotOnSizeErrorPhraseContext;
import io.proleap.cobol.CobolParser.OnExceptionClauseContext;
import io.proleap.cobol.CobolParser.OnOverflowPhraseContext;
import io.proleap.cobol.CobolParser.OnSizeErrorPhraseContext;
import io.proleap.cobol.CobolParser.OpenExtendStatementContext;
import io.proleap.cobol.CobolParser.OpenIOStatementContext;
import io.proleap.cobol.CobolParser.OpenInputStatementContext;
import io.proleap.cobol.CobolParser.OpenOutputStatementContext;
import io.proleap.cobol.CobolParser.OpenStatementContext;
import io.proleap.cobol.CobolParser.PerformStatementContext;
import io.proleap.cobol.CobolParser.PurgeStatementContext;
import io.proleap.cobol.CobolParser.ReadStatementContext;
import io.proleap.cobol.CobolParser.ReceiveStatementContext;
import io.proleap.cobol.CobolParser.ReleaseStatementContext;
import io.proleap.cobol.CobolParser.ReportNameContext;
import io.proleap.cobol.CobolParser.ReturnStatementContext;
import io.proleap.cobol.CobolParser.RewriteStatementContext;
import io.proleap.cobol.CobolParser.SearchStatementContext;
import io.proleap.cobol.CobolParser.SearchWhenContext;
import io.proleap.cobol.CobolParser.SendStatementContext;
import io.proleap.cobol.CobolParser.SetStatementContext;
import io.proleap.cobol.CobolParser.SetToStatementContext;
import io.proleap.cobol.CobolParser.SortGivingPhraseContext;
import io.proleap.cobol.CobolParser.SortOnKeyClauseContext;
import io.proleap.cobol.CobolParser.SortStatementContext;
import io.proleap.cobol.CobolParser.SortUsingContext;
import io.proleap.cobol.CobolParser.StartStatementContext;
import io.proleap.cobol.CobolParser.StatementContext;
import io.proleap.cobol.CobolParser.StopStatementContext;
import io.proleap.cobol.CobolParser.StringSendingPhraseContext;
import io.proleap.cobol.CobolParser.StringStatementContext;
import io.proleap.cobol.CobolParser.SubtractStatementContext;
import io.proleap.cobol.CobolParser.TerminateStatementContext;
import io.proleap.cobol.CobolParser.UnstringStatementContext;
import io.proleap.cobol.CobolParser.WriteAtEndOfPagePhraseContext;
import io.proleap.cobol.CobolParser.WriteNotAtEndOfPagePhraseContext;
import io.proleap.cobol.CobolParser.WriteStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.AtEndPhrase;
import io.proleap.cobol.asg.metamodel.procedure.InvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotAtEndPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotInvalidKeyPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotOnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.NotOnOverflowPhrase;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnExceptionClause;
import io.proleap.cobol.asg.metamodel.procedure.OnOverflowPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptStatement;
import io.proleap.cobol.asg.metamodel.procedure.accept.AcceptStatement.AcceptType;
import io.proleap.cobol.asg.metamodel.procedure.accept.impl.AcceptStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.add.AddStatement;
import io.proleap.cobol.asg.metamodel.procedure.add.impl.AddStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.alter.AlterStatement;
import io.proleap.cobol.asg.metamodel.procedure.alter.impl.AlterStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.call.CallStatement;
import io.proleap.cobol.asg.metamodel.procedure.call.impl.CallStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.cancel.CancelStatement;
import io.proleap.cobol.asg.metamodel.procedure.cancel.impl.CancelStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.close.CloseStatement;
import io.proleap.cobol.asg.metamodel.procedure.close.impl.CloseStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.compute.ComputeStatement;
import io.proleap.cobol.asg.metamodel.procedure.compute.impl.ComputeStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.continuestmt.ContinueStatement;
import io.proleap.cobol.asg.metamodel.procedure.continuestmt.impl.ContinueStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.delete.DeleteStatement;
import io.proleap.cobol.asg.metamodel.procedure.delete.impl.DeleteStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.disable.DisableStatement;
import io.proleap.cobol.asg.metamodel.procedure.disable.impl.DisableStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.display.DisplayStatement;
import io.proleap.cobol.asg.metamodel.procedure.display.impl.DisplayStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.divide.DivideStatement;
import io.proleap.cobol.asg.metamodel.procedure.divide.impl.DivideStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.enable.EnableStatement;
import io.proleap.cobol.asg.metamodel.procedure.enable.impl.EnableStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.entry.EntryStatement;
import io.proleap.cobol.asg.metamodel.procedure.entry.impl.EntryStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.EvaluateStatement;
import io.proleap.cobol.asg.metamodel.procedure.evaluate.impl.EvaluateStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.execcics.ExecCicsStatement;
import io.proleap.cobol.asg.metamodel.procedure.execcics.impl.ExecCicsStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.execsql.ExecSqlStatement;
import io.proleap.cobol.asg.metamodel.procedure.execsql.impl.ExecSqlStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.execsqlims.ExecSqlImsStatement;
import io.proleap.cobol.asg.metamodel.procedure.execsqlims.impl.ExecSqlImsStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.exhibit.ExhibitStatement;
import io.proleap.cobol.asg.metamodel.procedure.exhibit.impl.ExhibitStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.exit.ExitStatement;
import io.proleap.cobol.asg.metamodel.procedure.exit.impl.ExitStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.generate.GenerateStatement;
import io.proleap.cobol.asg.metamodel.procedure.generate.impl.GenerateStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.goback.GobackStatement;
import io.proleap.cobol.asg.metamodel.procedure.goback.impl.GobackStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.GoToStatement;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.impl.GoToStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.ifstmt.IfStatement;
import io.proleap.cobol.asg.metamodel.procedure.ifstmt.impl.IfStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.AtEndPhraseImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.InvalidKeyPhraseImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.NotAtEndPhraseImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.NotInvalidKeyPhraseImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.NotOnExceptionClauseImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.NotOnOverflowPhraseImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.NotOnSizeErrorPhraseImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.OnExceptionClauseImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.OnOverflowPhraseImpl;
import io.proleap.cobol.asg.metamodel.procedure.impl.OnSizeErrorPhraseImpl;
import io.proleap.cobol.asg.metamodel.procedure.initialize.InitializeStatement;
import io.proleap.cobol.asg.metamodel.procedure.initialize.impl.InitializeStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.initiate.InitiateStatement;
import io.proleap.cobol.asg.metamodel.procedure.initiate.impl.InitiateStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.inspect.InspectStatement;
import io.proleap.cobol.asg.metamodel.procedure.inspect.impl.InspectStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.merge.MergeStatement;
import io.proleap.cobol.asg.metamodel.procedure.merge.impl.MergeStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveStatement;
import io.proleap.cobol.asg.metamodel.procedure.move.impl.MoveStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.multiply.MultiplyStatement;
import io.proleap.cobol.asg.metamodel.procedure.multiply.impl.MultiplyStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.open.OpenStatement;
import io.proleap.cobol.asg.metamodel.procedure.open.impl.OpenStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.perform.PerformStatement;
import io.proleap.cobol.asg.metamodel.procedure.perform.impl.PerformStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.purge.PurgeStatement;
import io.proleap.cobol.asg.metamodel.procedure.purge.impl.PurgeStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.read.ReadStatement;
import io.proleap.cobol.asg.metamodel.procedure.read.impl.ReadStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.receive.ReceiveStatement;
import io.proleap.cobol.asg.metamodel.procedure.receive.impl.ReceiveStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.release.ReleaseStatement;
import io.proleap.cobol.asg.metamodel.procedure.release.impl.ReleaseStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.returnstmt.ReturnStatement;
import io.proleap.cobol.asg.metamodel.procedure.returnstmt.impl.ReturnStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.rewrite.RewriteStatement;
import io.proleap.cobol.asg.metamodel.procedure.rewrite.impl.RewriteStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.search.SearchStatement;
import io.proleap.cobol.asg.metamodel.procedure.search.impl.SearchStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.send.SendStatement;
import io.proleap.cobol.asg.metamodel.procedure.send.impl.SendStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.set.SetStatement;
import io.proleap.cobol.asg.metamodel.procedure.set.impl.SetStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.sort.SortStatement;
import io.proleap.cobol.asg.metamodel.procedure.sort.impl.SortStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.start.StartStatement;
import io.proleap.cobol.asg.metamodel.procedure.start.impl.StartStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement;
import io.proleap.cobol.asg.metamodel.procedure.stop.StopStatement.StopType;
import io.proleap.cobol.asg.metamodel.procedure.stop.impl.StopStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.string.StringStatement;
import io.proleap.cobol.asg.metamodel.procedure.string.impl.StringStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.subtract.SubtractStatement;
import io.proleap.cobol.asg.metamodel.procedure.subtract.impl.SubtractStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.terminate.TerminateStatement;
import io.proleap.cobol.asg.metamodel.procedure.terminate.impl.TerminateStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.unstring.UnstringStatement;
import io.proleap.cobol.asg.metamodel.procedure.unstring.impl.UnstringStatementImpl;
import io.proleap.cobol.asg.metamodel.procedure.write.AtEndOfPagePhrase;
import io.proleap.cobol.asg.metamodel.procedure.write.NotAtEndOfPagePhrase;
import io.proleap.cobol.asg.metamodel.procedure.write.WriteStatement;
import io.proleap.cobol.asg.metamodel.procedure.write.impl.AtEndOfPagePhraseImpl;
import io.proleap.cobol.asg.metamodel.procedure.write.impl.NotAtEndOfPagePhraseImpl;
import io.proleap.cobol.asg.metamodel.procedure.write.impl.WriteStatementImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.util.TagUtils;
import io.proleap.cobol.preprocessor.CobolPreprocessor;

public class ScopeImpl extends CobolDivisionElementImpl implements Scope {

	private final static Logger LOG = LoggerFactory.getLogger(ScopeImpl.class);

	protected List<Statement> statements = new ArrayList<Statement>();

	public ScopeImpl(final ProgramUnit programUnit, final ParserRuleContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public AcceptStatement addAcceptStatement(final AcceptStatementContext ctx) {
		AcceptStatement result = (AcceptStatement) getASGElement(ctx);

		if (result == null) {
			result = new AcceptStatementImpl(programUnit, this, ctx);

			// accept call
			if (ctx.identifier() != null) {
				final Call acceptCall = createCall(ctx.identifier());
				result.setAcceptCall(acceptCall);
			}

			// type
			final AcceptType type;

			if (ctx.acceptFromDateStatement() != null) {
				result.addAcceptFromDateStatement(ctx.acceptFromDateStatement());
				type = AcceptType.DATE;
			} else if (ctx.acceptFromMnemonicStatement() != null) {
				result.addAcceptFromMnemonicStatement(ctx.acceptFromMnemonicStatement());
				type = AcceptType.MNEMONIC;
			} else if (ctx.acceptMessageCountStatement() != null) {
				result.addAcceptMessageCountStatement(ctx.acceptMessageCountStatement());
				type = AcceptType.MESSAGE_COUNT;
			} else if (ctx.acceptFromEscapeKeyStatement() != null) {
				result.addAcceptFromEscapeKeyStatement(ctx.acceptFromEscapeKeyStatement());
				type = AcceptType.FROM_ESCAPE_KEY;
			} else {
				type = AcceptType.NO_FROM;
			}

			result.setAcceptType(type);

			// on exception
			if (ctx.onExceptionClause() != null) {
				final OnExceptionClause onException = createOnException(ctx.onExceptionClause());
				result.setOnExceptionClause(onException);
			}

			// not on exception
			if (ctx.notOnExceptionClause() != null) {
				final NotOnExceptionClause notOnException = createNotOnExceptionClause(ctx.notOnExceptionClause());
				result.setNotOnExceptionClause(notOnException);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public AddStatement addAddStatement(final AddStatementContext ctx) {
		AddStatement result = (AddStatement) getASGElement(ctx);

		if (result == null) {
			result = new AddStatementImpl(programUnit, this, ctx);

			// add add statement
			final AddStatement.AddType type;

			if (ctx.addToStatement() != null) {
				result.addAddToStatement(ctx.addToStatement());
				type = AddStatement.AddType.TO;
			} else if (ctx.addToGivingStatement() != null) {
				result.addAddToGivingStatement(ctx.addToGivingStatement());
				type = AddStatement.AddType.TO_GIVING;
			} else if (ctx.addCorrespondingStatement() != null) {
				result.addAddCorrespondingStatement(ctx.addCorrespondingStatement());
				type = AddStatement.AddType.CORRESPONDING;
			} else {
				LOG.warn("unknown add statement at {}", ctx);
				type = null;
			}

			result.setAddType(type);

			// on size
			if (ctx.onSizeErrorPhrase() != null) {
				final OnSizeErrorPhrase onSizeError = createOnSizeErrorPhrase(ctx.onSizeErrorPhrase());
				result.setOnSizeErrorPhrase(onSizeError);
			}

			// not on size
			if (ctx.notOnSizeErrorPhrase() != null) {
				final NotOnSizeErrorPhrase notOnSizeError = createNotOnSizeErrorPhrase(ctx.notOnSizeErrorPhrase());
				result.setNotOnSizePhrase(notOnSizeError);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public AlterStatement addAlterStatement(final AlterStatementContext ctx) {
		AlterStatement result = (AlterStatement) getASGElement(ctx);

		if (result == null) {
			result = new AlterStatementImpl(programUnit, this, ctx);

			for (final AlterProceedToContext alterProceedToContext : ctx.alterProceedTo()) {
				result.addProceedTo(alterProceedToContext);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public CallStatement addCallStatement(final CallStatementContext ctx) {
		CallStatement result = (CallStatement) getASGElement(ctx);

		if (result == null) {
			result = new CallStatementImpl(programUnit, this, ctx);

			// called program
			final ValueStmt programValueStmt = createValueStmt(ctx.literal(), ctx.identifier());
			result.setProgramValueStmt(programValueStmt);

			// using
			if (ctx.callUsingPhrase() != null) {
				result.addUsingPhrase(ctx.callUsingPhrase());
			}

			// giving
			if (ctx.callGivingPhrase() != null) {
				result.addGivingPhrase(ctx.callGivingPhrase());
			}

			// on overflow
			if (ctx.onOverflowPhrase() != null) {
				final OnOverflowPhrase onOverflow = createOnOverflowPhrase(ctx.onOverflowPhrase());
				result.setOnOverflowPhrase(onOverflow);
			}

			// on exception
			if (ctx.onExceptionClause() != null) {
				final OnExceptionClause onException = createOnException(ctx.onExceptionClause());
				result.setOnExceptionClause(onException);
			}

			// not on exception
			if (ctx.notOnExceptionClause() != null) {
				final NotOnExceptionClause notOnException = createNotOnExceptionClause(ctx.notOnExceptionClause());
				result.setNotOnExceptionClause(notOnException);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public CancelStatement addCancelStatement(final CancelStatementContext ctx) {
		CancelStatement result = (CancelStatement) getASGElement(ctx);

		if (result == null) {
			result = new CancelStatementImpl(programUnit, this, ctx);

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
			result = new CloseStatementImpl(programUnit, this, ctx);

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
			result = new ComputeStatementImpl(programUnit, this, ctx);

			// store calls
			for (final ComputeStoreContext computeStoreContext : ctx.computeStore()) {
				result.addStore(computeStoreContext);
			}

			// arithmetic expression
			final ArithmeticValueStmt arithmeticExpression = createArithmeticValueStmt(ctx.arithmeticExpression());
			result.setArithmeticExpression(arithmeticExpression);

			// on size error
			if (ctx.onSizeErrorPhrase() != null) {
				final OnSizeErrorPhrase onSizeError = createOnSizeErrorPhrase(ctx.onSizeErrorPhrase());
				result.setOnSizeErrorPhrase(onSizeError);
			}

			// not on size error
			if (ctx.notOnSizeErrorPhrase() != null) {
				final NotOnSizeErrorPhrase notOnSizeError = createNotOnSizeErrorPhrase(ctx.notOnSizeErrorPhrase());
				result.setNotOnSizeErrorPhrase(notOnSizeError);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ContinueStatement addContinueStatement(final ContinueStatementContext ctx) {
		ContinueStatement result = (ContinueStatement) getASGElement(ctx);

		if (result == null) {
			result = new ContinueStatementImpl(programUnit, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public DeleteStatement addDeleteStatement(final DeleteStatementContext ctx) {
		DeleteStatement result = (DeleteStatement) getASGElement(ctx);

		if (result == null) {
			result = new DeleteStatementImpl(programUnit, this, ctx);

			// file
			final Call fileCall = createCall(ctx.fileName());
			result.setFileCall(fileCall);

			if (ctx.RECORD() != null) {
				result.setRecord(true);
			}

			// invalid key
			if (ctx.invalidKeyPhrase() != null) {
				final InvalidKeyPhrase invalidKey = createInvalidKeyPhrase(ctx.invalidKeyPhrase());
				result.setInvalidKeyPhrase(invalidKey);
			}

			// not invalid key
			if (ctx.notInvalidKeyPhrase() != null) {
				final NotInvalidKeyPhrase notInvalidKey = createNotInvalidKeyPhrase(ctx.notInvalidKeyPhrase());
				result.setNotInvalidKeyPhrase(notInvalidKey);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public DisableStatement addDisableStatement(final DisableStatementContext ctx) {
		DisableStatement result = (DisableStatement) getASGElement(ctx);

		if (result == null) {
			result = new DisableStatementImpl(programUnit, this, ctx);

			// type
			final DisableStatement.DisableType type;

			if (ctx.INPUT() != null) {
				type = DisableStatement.DisableType.INPUT;
			} else if (ctx.I_O() != null) {
				type = DisableStatement.DisableType.INPUT_OUTPUT;
			} else if (ctx.OUTPUT() != null) {
				type = DisableStatement.DisableType.OUTPUT;
			} else {
				type = null;
			}

			result.setDisableType(type);

			// terminal
			if (ctx.TERMINAL() != null) {
				result.setTerminal(true);
			}

			// cd name
			final Call cdNameCall = createCall(ctx.cdName());
			result.setCommunicationDescriptionCall(cdNameCall);

			// key
			final ValueStmt keyValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setKeyValueStmt(keyValueStmt);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public DisplayStatement addDisplayStatement(final DisplayStatementContext ctx) {
		DisplayStatement result = (DisplayStatement) getASGElement(ctx);

		if (result == null) {
			result = new DisplayStatementImpl(programUnit, this, ctx);

			// operands
			for (final DisplayOperandContext displayOperandContext : ctx.displayOperand()) {
				result.addOperand(displayOperandContext);
			}

			// at
			if (ctx.displayAt() != null) {
				result.addAt(ctx.displayAt());
			}

			// upon
			if (ctx.displayUpon() != null) {
				result.addUpon(ctx.displayUpon());
			}

			// with
			if (ctx.displayWith() != null) {
				result.addWith(ctx.displayWith());
			}

			// on exception
			if (ctx.onExceptionClause() != null) {
				final OnExceptionClause onException = createOnException(ctx.onExceptionClause());
				result.setOnExceptionClause(onException);
			}

			// not on exception
			if (ctx.notOnExceptionClause() != null) {
				final NotOnExceptionClause notOnException = createNotOnExceptionClause(ctx.notOnExceptionClause());
				result.setNotOnExceptionClause(notOnException);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public DivideStatement addDivideStatement(final DivideStatementContext ctx) {
		DivideStatement result = (DivideStatement) getASGElement(ctx);

		if (result == null) {
			result = new DivideStatementImpl(programUnit, this, ctx);

			// operand
			final ValueStmt operandValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setOperandValueStmt(operandValueStmt);

			// giving
			final DivideStatement.DivideType type;

			if (ctx.divideIntoStatement() != null) {
				result.addDivideIntoStatement(ctx.divideIntoStatement());
				type = DivideStatement.DivideType.INTO;
			} else if (ctx.divideIntoGivingStatement() != null) {
				result.addDivideIntoGivingStatement(ctx.divideIntoGivingStatement());
				type = DivideStatement.DivideType.INTO_GIVING;
			} else if (ctx.divideByGivingStatement() != null) {
				result.addDivideByGivingStatement(ctx.divideByGivingStatement());
				type = DivideStatement.DivideType.BY_GIVING;
			} else {
				type = null;
			}

			result.setDivideType(type);

			// remainder
			if (ctx.divideRemainder() != null) {
				result.addRemainder(ctx.divideRemainder());
			}

			// on size
			if (ctx.onSizeErrorPhrase() != null) {
				final OnSizeErrorPhrase onSizeError = createOnSizeErrorPhrase(ctx.onSizeErrorPhrase());
				result.setOnSizeErrorPhrase(onSizeError);
			}

			// not on size
			if (ctx.notOnSizeErrorPhrase() != null) {
				final NotOnSizeErrorPhrase notOnSizeError = createNotOnSizeErrorPhrase(ctx.notOnSizeErrorPhrase());
				result.setNotOnSizeErrorPhrase(notOnSizeError);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public EnableStatement addEnableStatement(final EnableStatementContext ctx) {
		EnableStatement result = (EnableStatement) getASGElement(ctx);

		if (result == null) {
			result = new EnableStatementImpl(programUnit, this, ctx);

			// type
			final EnableStatement.EnableType type;

			if (ctx.INPUT() != null) {
				type = EnableStatement.EnableType.INPUT;
			} else if (ctx.I_O() != null) {
				type = EnableStatement.EnableType.INPUT_OUTPUT;
			} else if (ctx.OUTPUT() != null) {
				type = EnableStatement.EnableType.OUTPUT;
			} else {
				type = null;
			}

			result.setEnableType(type);

			// terminal
			if (ctx.TERMINAL() != null) {
				result.setTerminal(true);
			}

			// cd name
			final Call cdNameCall = createCall(ctx.cdName());
			result.setCommunicationDescriptionCall(cdNameCall);

			// key
			final ValueStmt keyValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setKeyValueStmt(keyValueStmt);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public EntryStatement addEntryStatement(final EntryStatementContext ctx) {
		EntryStatement result = (EntryStatement) getASGElement(ctx);

		if (result == null) {
			result = new EntryStatementImpl(programUnit, this, ctx);

			// entry
			final ValueStmt entryValueStmt = createValueStmt(ctx.literal());
			result.setEntryValueStmt(entryValueStmt);

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
	public EvaluateStatement addEvaluateStatement(final EvaluateStatementContext ctx) {
		EvaluateStatement result = (EvaluateStatement) getASGElement(ctx);

		if (result == null) {
			result = new EvaluateStatementImpl(programUnit, this, ctx);

			// select
			result.addSelect(ctx.evaluateSelect());

			// also selects
			for (final EvaluateAlsoSelectContext evaluateAlsoSelectContext : ctx.evaluateAlsoSelect()) {
				result.addAlsoSelect(evaluateAlsoSelectContext);
			}

			// when
			for (final EvaluateWhenPhraseContext evaluateWhenPhraseContext : ctx.evaluateWhenPhrase()) {
				result.addWhenPhrase(evaluateWhenPhraseContext);
			}

			// when other
			if (ctx.evaluateWhenOther() != null) {
				result.addWhenOther(ctx.evaluateWhenOther());
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ExecCicsStatement addExecCicsStatement(final ExecCicsStatementContext ctx) {
		ExecCicsStatement result = (ExecCicsStatement) getASGElement(ctx);

		if (result == null) {
			result = new ExecCicsStatementImpl(programUnit, this, ctx);

			final String execCicsText = TagUtils.getUntaggedText(ctx.EXECCICSLINE(), CobolPreprocessor.EXEC_CICS_TAG,
					CobolPreprocessor.EXEC_END_TAG);
			result.setExecCicsText(execCicsText);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ExecSqlImsStatement addExecSqlImsStatement(final ExecSqlImsStatementContext ctx) {
		ExecSqlImsStatement result = (ExecSqlImsStatement) getASGElement(ctx);

		if (result == null) {
			result = new ExecSqlImsStatementImpl(programUnit, this, ctx);

			final String execSqlImsText = TagUtils.getUntaggedText(ctx.EXECSQLIMSLINE(),
					CobolPreprocessor.EXEC_SQLIMS_TAG, CobolPreprocessor.EXEC_END_TAG);
			result.setExecSqlImsText(execSqlImsText);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ExecSqlStatement addExecSqlStatement(final ExecSqlStatementContext ctx) {
		ExecSqlStatement result = (ExecSqlStatement) getASGElement(ctx);

		if (result == null) {
			result = new ExecSqlStatementImpl(programUnit, this, ctx);

			final String execSqlText = TagUtils.getUntaggedText(ctx.EXECSQLLINE(), CobolPreprocessor.EXEC_SQL_TAG,
					CobolPreprocessor.EXEC_END_TAG);
			result.setExecSqlText(execSqlText);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ExhibitStatement addExhibitStatement(final ExhibitStatementContext ctx) {
		ExhibitStatement result = (ExhibitStatement) getASGElement(ctx);

		if (result == null) {
			result = new ExhibitStatementImpl(programUnit, this, ctx);

			// operands
			for (final ExhibitOperandContext exhibitOperandContext : ctx.exhibitOperand()) {
				result.addOperand(exhibitOperandContext);
			}

			// named
			if (ctx.NAMED() != null) {
				result.setNamed(true);
			}

			// changed
			if (ctx.CHANGED() != null) {
				result.setChanged(true);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ExitStatement addExitStatement(final ExitStatementContext ctx) {
		ExitStatement result = (ExitStatement) getASGElement(ctx);

		if (result == null) {
			result = new ExitStatementImpl(programUnit, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public GenerateStatement addGenerateStatement(final GenerateStatementContext ctx) {
		GenerateStatement result = (GenerateStatement) getASGElement(ctx);

		if (result == null) {
			result = new GenerateStatementImpl(programUnit, this, ctx);

			final Call reportDescriptionCall = createCall(ctx.reportName());
			result.setReportDescriptionCall(reportDescriptionCall);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public GobackStatement addGobackStatement(final GobackStatementContext ctx) {
		GobackStatement result = (GobackStatement) getASGElement(ctx);

		if (result == null) {
			result = new GobackStatementImpl(programUnit, this, ctx);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public GoToStatement addGoToStatement(final GoToStatementContext ctx) {
		GoToStatement result = (GoToStatement) getASGElement(ctx);

		if (result == null) {
			result = new GoToStatementImpl(programUnit, this, ctx);

			// type
			final GoToStatement.GoToType type;

			if (ctx.goToStatementSimple() != null) {
				result.addSimple(ctx.goToStatementSimple());
				type = GoToStatement.GoToType.SIMPLE;
			} else if (ctx.goToDependingOnStatement() != null) {
				result.addDependingOnPhrase(ctx.goToDependingOnStatement());
				type = GoToStatement.GoToType.DEPENDING_ON;
			} else {
				type = null;
			}

			result.setGoToType(type);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public IfStatement addIfStatement(final IfStatementContext ctx) {
		IfStatement result = (IfStatement) getASGElement(ctx);

		if (result == null) {
			result = new IfStatementImpl(programUnit, this, ctx);

			// condition
			final ConditionValueStmt condition = createConditionValueStmt(ctx.condition());
			result.setCondition(condition);

			// then
			if (ctx.ifThen() != null) {
				result.addThen(ctx.ifThen());
			}

			// else
			if (ctx.ifElse() != null) {
				result.addElse(ctx.ifElse());
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public InitializeStatement addInitializeStatement(final InitializeStatementContext ctx) {
		InitializeStatement result = (InitializeStatement) getASGElement(ctx);

		if (result == null) {
			result = new InitializeStatementImpl(programUnit, this, ctx);

			// data item calls
			for (final IdentifierContext identifierContext : ctx.identifier()) {
				final Call dataItemCall = createCall(identifierContext);
				result.addDataItemCall(dataItemCall);
			}

			// replacing
			if (ctx.initializeReplacingPhrase() != null) {
				result.addReplacingPhrase(ctx.initializeReplacingPhrase());
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public InitiateStatement addInitiateStatement(final InitiateStatementContext ctx) {
		InitiateStatement result = (InitiateStatement) getASGElement(ctx);

		if (result == null) {
			result = new InitiateStatementImpl(programUnit, this, ctx);

			for (final ReportNameContext reportNameContext : ctx.reportName()) {
				final Call reportCall = createCall(reportNameContext);
				result.addReportCall(reportCall);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public InspectStatement addInspectStatement(final InspectStatementContext ctx) {
		InspectStatement result = (InspectStatement) getASGElement(ctx);

		if (result == null) {
			result = new InspectStatementImpl(programUnit, this, ctx);

			// data item call
			if (ctx.identifier() != null) {
				final Call dataItemCall = createCall(ctx.identifier());
				result.setDataItemCall(dataItemCall);
			}

			// type
			final InspectStatement.InspectType type;

			if (ctx.inspectTallyingPhrase() != null) {
				result.addTallying(ctx.inspectTallyingPhrase());
				type = InspectStatement.InspectType.TALLYING;
			} else if (ctx.inspectReplacingPhrase() != null) {
				result.addReplacing(ctx.inspectReplacingPhrase());
				type = InspectStatement.InspectType.REPLACING;
			} else if (ctx.inspectTallyingReplacingPhrase() != null) {
				result.addTallyingReplacing(ctx.inspectTallyingReplacingPhrase());
				type = InspectStatement.InspectType.TALLYING_REPLACING;
			} else if (ctx.inspectConvertingPhrase() != null) {
				result.addConverting(ctx.inspectConvertingPhrase());
				type = InspectStatement.InspectType.CONVERTING;
			} else {
				type = null;
			}

			result.setInspectType(type);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public MergeStatement addMergeStatement(final MergeStatementContext ctx) {
		MergeStatement result = (MergeStatement) getASGElement(ctx);

		if (result == null) {
			result = new MergeStatementImpl(programUnit, this, ctx);

			// file
			final Call fileCall = createCall(ctx.fileName());
			result.setFileCall(fileCall);

			// on key
			for (final MergeOnKeyClauseContext mergeOnKeyClauseContext : ctx.mergeOnKeyClause()) {
				result.addOnKey(mergeOnKeyClauseContext);
			}

			// collating sequence
			if (ctx.mergeCollatingSequencePhrase() != null) {
				result.addCollatingSequencePhrase(ctx.mergeCollatingSequencePhrase());
			}

			// using
			for (final MergeUsingContext mergeUsingContext : ctx.mergeUsing()) {
				result.addUsingPhrase(mergeUsingContext);
			}

			// output procedure
			if (ctx.mergeOutputProcedurePhrase() != null) {
				result.addOutputProcedurePhrase(ctx.mergeOutputProcedurePhrase());
			}

			// giving
			for (final MergeGivingPhraseContext mergeGivingPhraseContext : ctx.mergeGivingPhrase()) {
				result.addGiving(mergeGivingPhraseContext);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public MoveStatement addMoveStatement(final MoveStatementContext ctx) {
		MoveStatement result = (MoveStatement) getASGElement(ctx);

		if (result == null) {
			result = new MoveStatementImpl(programUnit, this, ctx);

			// type
			final MoveStatement.MoveType type;

			if (ctx.moveToStatement() != null) {
				type = MoveStatement.MoveType.MOVE_TO;
				result.addMoveToStatement(ctx.moveToStatement());
			} else if (ctx.moveCorrespondingToStatement() != null) {
				type = MoveStatement.MoveType.MOVE_CORRESPONDING;
				result.addMoveCorrespondingToStatement(ctx.moveCorrespondingToStatement());
			} else {
				type = null;
			}

			result.setMoveType(type);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public MultiplyStatement addMultiplyStatement(final MultiplyStatementContext ctx) {
		MultiplyStatement result = (MultiplyStatement) getASGElement(ctx);

		if (result == null) {
			result = new MultiplyStatementImpl(programUnit, this, ctx);

			// operand
			final ValueStmt operandValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setOperandValueStmt(operandValueStmt);

			// type
			final MultiplyStatement.MultiplyType type;

			if (ctx.multiplyRegular() != null) {
				result.addByPhrase(ctx.multiplyRegular());
				type = MultiplyStatement.MultiplyType.BY;
			} else if (ctx.multiplyGiving() != null) {
				result.addGivingPhrase(ctx.multiplyGiving());
				type = MultiplyStatement.MultiplyType.BY_GIVING;
			} else {
				type = null;
			}

			result.setMultiplyType(type);

			// on size error
			if (ctx.onSizeErrorPhrase() != null) {
				final OnSizeErrorPhrase onSizeError = createOnSizeErrorPhrase(ctx.onSizeErrorPhrase());
				result.setOnSizeErrorPhrase(onSizeError);
			}

			// not on size error
			if (ctx.notOnSizeErrorPhrase() != null) {
				final NotOnSizeErrorPhrase notOnSizeError = createNotOnSizeErrorPhrase(ctx.notOnSizeErrorPhrase());
				result.setNotOnSizeErrorPhrase(notOnSizeError);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public OpenStatement addOpenStatement(final OpenStatementContext ctx) {
		OpenStatement result = (OpenStatement) getASGElement(ctx);

		if (result == null) {
			result = new OpenStatementImpl(programUnit, this, ctx);

			// input
			for (final OpenInputStatementContext openInputStatementContext : ctx.openInputStatement()) {
				result.addInputPhrase(openInputStatementContext);
			}

			// output
			for (final OpenOutputStatementContext openOutputStatementContext : ctx.openOutputStatement()) {
				result.addOutputPhrase(openOutputStatementContext);
			}

			// input / output
			for (final OpenIOStatementContext openIOStatementContext : ctx.openIOStatement()) {
				result.addInputOutputPhrase(openIOStatementContext);
			}

			// extend
			for (final OpenExtendStatementContext openExtendStatementContext : ctx.openExtendStatement()) {
				result.addExtendPhrase(openExtendStatementContext);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public PerformStatement addPerformStatement(final PerformStatementContext ctx) {
		PerformStatement result = (PerformStatement) getASGElement(ctx);

		if (result == null) {
			result = new PerformStatementImpl(programUnit, this, ctx);

			final PerformStatement.PerformStatementType type;

			if (ctx.performInlineStatement() != null) {
				type = PerformStatement.PerformStatementType.INLINE;

				result.addPerformInlineStatement(ctx.performInlineStatement());
			} else if (ctx.performProcedureStatement() != null) {
				type = PerformStatement.PerformStatementType.PROCEDURE;

				result.addPerformProcedureStatement(ctx.performProcedureStatement());
			} else {
				type = null;
			}

			result.setPerformStatementType(type);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public PurgeStatement addPurgeStatement(final PurgeStatementContext ctx) {
		PurgeStatement result = (PurgeStatement) getASGElement(ctx);

		if (result == null) {
			result = new PurgeStatementImpl(programUnit, this, ctx);

			for (final CdNameContext cdNameContext : ctx.cdName()) {
				final Call cdNameCall = createCall(cdNameContext);
				result.addCommunicationDescriptionEntryCall(cdNameCall);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ReadStatement addReadStatement(final ReadStatementContext ctx) {
		ReadStatement result = (ReadStatement) getASGElement(ctx);

		if (result == null) {
			result = new ReadStatementImpl(programUnit, this, ctx);

			// file
			final Call fileCall = createCall(ctx.fileName());
			result.setFileCall(fileCall);

			// next record
			if (ctx.RECORD() != null) {
				result.setNextRecord(true);
			}

			// into
			if (ctx.readInto() != null) {
				result.addInto(ctx.readInto());
			}

			// with
			if (ctx.readWith() != null) {
				result.addWith(ctx.readWith());
			}

			// key
			if (ctx.readKey() != null) {
				result.addKey(ctx.readKey());
			}

			// invalid key
			if (ctx.invalidKeyPhrase() != null) {
				final InvalidKeyPhrase invalidKey = createInvalidKeyPhrase(ctx.invalidKeyPhrase());
				result.setInvalidKeyPhrase(invalidKey);
			}

			// not invalid key
			if (ctx.notInvalidKeyPhrase() != null) {
				final NotInvalidKeyPhrase notInvalidKey = createNotInvalidKeyPhrase(ctx.notInvalidKeyPhrase());
				result.setNotInvalidKeyPhrase(notInvalidKey);
			}

			// at end
			if (ctx.atEndPhrase() != null) {
				final AtEndPhrase atEnd = createAtEndPhrase(ctx.atEndPhrase());
				result.setAtEnd(atEnd);
			}

			// not at end
			if (ctx.notAtEndPhrase() != null) {
				final NotAtEndPhrase notAtEnd = createNotAtEndPhrase(ctx.notAtEndPhrase());
				result.setNotAtEndPhrase(notAtEnd);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ReceiveStatement addReceiveStatement(final ReceiveStatementContext ctx) {
		ReceiveStatement result = (ReceiveStatement) getASGElement(ctx);

		if (result == null) {
			result = new ReceiveStatementImpl(programUnit, this, ctx);

			// type
			final ReceiveStatement.ReceiveType type;

			if (ctx.receiveFromStatement() != null) {
				result.addReceiveFromStatement(ctx.receiveFromStatement());
				type = ReceiveStatement.ReceiveType.FROM;
			} else if (ctx.receiveIntoStatement() != null) {
				result.addReceiveIntoStatement(ctx.receiveIntoStatement());
				type = ReceiveStatement.ReceiveType.INTO;
			} else {
				type = null;
			}

			result.setReceiveType(type);

			// on exception
			if (ctx.onExceptionClause() != null) {
				final OnExceptionClause onException = createOnException(ctx.onExceptionClause());
				result.setOnExceptionClause(onException);
			}

			// not on exeption
			if (ctx.notOnExceptionClause() != null) {
				final NotOnExceptionClause notOnException = createNotOnExceptionClause(ctx.notOnExceptionClause());
				result.setNotOnExceptionClause(notOnException);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ReleaseStatement addReleaseStatement(final ReleaseStatementContext ctx) {
		ReleaseStatement result = (ReleaseStatement) getASGElement(ctx);

		if (result == null) {
			result = new ReleaseStatementImpl(programUnit, this, ctx);

			// record
			if (ctx.recordName() != null) {
				final Call recordCall = createCall(ctx.recordName());
				result.setRecordCall(recordCall);
			}

			// content
			if (ctx.qualifiedDataName() != null) {
				final Call contentCall = createCall(ctx.qualifiedDataName());
				result.setContentCall(contentCall);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public ReturnStatement addReturnStatement(final ReturnStatementContext ctx) {
		ReturnStatement result = (ReturnStatement) getASGElement(ctx);

		if (result == null) {
			result = new ReturnStatementImpl(programUnit, this, ctx);

			// file call
			final Call fileCall = createCall(ctx.fileName());
			result.addFileCall(fileCall);

			// into
			if (ctx.returnInto() != null) {
				result.addInto(ctx.returnInto());
			}

			// at end
			if (ctx.atEndPhrase() != null) {
				final AtEndPhrase atEnd = createAtEndPhrase(ctx.atEndPhrase());
				result.setAtEndPhrase(atEnd);
			}

			// not at end
			if (ctx.notAtEndPhrase() != null) {
				final NotAtEndPhrase notAtEnd = createNotAtEndPhrase(ctx.notAtEndPhrase());
				result.setNotAtEndPhrase(notAtEnd);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public RewriteStatement addRewriteStatement(final RewriteStatementContext ctx) {
		RewriteStatement result = (RewriteStatement) getASGElement(ctx);

		if (result == null) {
			result = new RewriteStatementImpl(programUnit, this, ctx);

			// record
			final Call recordCall = createCall(ctx.recordName());
			result.setRecordCall(recordCall);

			// from
			if (ctx.rewriteFrom() != null) {
				result.addFrom(ctx.rewriteFrom());
			}

			// invalid key
			if (ctx.invalidKeyPhrase() != null) {
				final InvalidKeyPhrase invalidKey = createInvalidKeyPhrase(ctx.invalidKeyPhrase());
				result.setInvalidKeyPhrase(invalidKey);
			}

			// not invalid key
			if (ctx.notInvalidKeyPhrase() != null) {
				final NotInvalidKeyPhrase notInvalidKey = createNotInvalidKeyPhrase(ctx.notInvalidKeyPhrase());
				result.setNotInvalidKeyPhrase(notInvalidKey);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public SearchStatement addSearchStatement(final SearchStatementContext ctx) {
		SearchStatement result = (SearchStatement) getASGElement(ctx);

		if (result == null) {
			result = new SearchStatementImpl(programUnit, this, ctx);

			// data call
			final Call dataCall = createCall(ctx.qualifiedDataName());
			result.setDataCall(dataCall);

			// varying
			if (ctx.searchVarying() != null) {
				result.addVaryingPhrase(ctx.searchVarying());
			}

			// at end
			if (ctx.atEndPhrase() != null) {
				final AtEndPhrase atEnd = createAtEndPhrase(ctx.atEndPhrase());
				result.setAtEndPhrase(atEnd);
			}

			// when
			for (final SearchWhenContext searchWhenContext : ctx.searchWhen()) {
				result.addWhenPhrase(searchWhenContext);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public SendStatement addSendStatement(final SendStatementContext ctx) {
		SendStatement result = (SendStatement) getASGElement(ctx);

		if (result == null) {
			result = new SendStatementImpl(programUnit, this, ctx);

			// type
			final SendStatement.SendType type;

			if (ctx.sendStatementSync() != null) {
				result.addSync(ctx.sendStatementSync());
				type = SendStatement.SendType.SYNC;
			} else if (ctx.sendStatementAsync() != null) {
				result.addAsync(ctx.sendStatementAsync());
				type = SendStatement.SendType.ASYNC;
			} else {
				type = null;
			}

			result.setSendType(type);

			// on exception
			if (ctx.onExceptionClause() != null) {
				final OnExceptionClause onException = createOnException(ctx.onExceptionClause());
				result.setOnExceptionClause(onException);
			}

			// not on exeption
			if (ctx.notOnExceptionClause() != null) {
				final NotOnExceptionClause notOnException = createNotOnExceptionClause(ctx.notOnExceptionClause());
				result.setNotOnExceptionClause(notOnException);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public SetStatement addSetStatement(final SetStatementContext ctx) {
		SetStatement result = (SetStatement) getASGElement(ctx);

		if (result == null) {
			result = new SetStatementImpl(programUnit, this, ctx);

			// type
			final SetStatement.SetType type;

			if (!ctx.setToStatement().isEmpty()) {
				type = SetStatement.SetType.TO;

				for (final SetToStatementContext setToStatementContext : ctx.setToStatement()) {
					result.addSetTo(setToStatementContext);
				}
			} else if (ctx.setUpDownByStatement() != null) {
				result.addSetBy(ctx.setUpDownByStatement());
				type = SetStatement.SetType.BY;
			} else {
				type = null;
			}

			result.setSetType(type);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public SortStatement addSortStatement(final SortStatementContext ctx) {
		SortStatement result = (SortStatement) getASGElement(ctx);

		if (result == null) {
			result = new SortStatementImpl(programUnit, this, ctx);

			// file
			final Call fileCall = createCall(ctx.fileName());
			result.setFileCall(fileCall);

			// on key
			for (final SortOnKeyClauseContext sortOnKeyClauseContext : ctx.sortOnKeyClause()) {
				result.addOnKey(sortOnKeyClauseContext);
			}

			// duplicates
			if (ctx.sortDuplicatesPhrase() != null) {
				result.addDuplicates(ctx.sortDuplicatesPhrase());
			}

			// collating sequence
			if (ctx.sortCollatingSequencePhrase() != null) {
				result.addCollatingSequence(ctx.sortCollatingSequencePhrase());
			}

			// using
			for (final SortUsingContext sortUsingContext : ctx.sortUsing()) {
				result.addUsingPhrase(sortUsingContext);
			}

			// input procedure
			if (ctx.sortInputProcedurePhrase() != null) {
				result.addInputProcedure(ctx.sortInputProcedurePhrase());
			}

			// giving
			for (final SortGivingPhraseContext sortGivingPhraseContext : ctx.sortGivingPhrase()) {
				result.addGivingPhrase(sortGivingPhraseContext);
			}

			// output procedure
			if (ctx.sortOutputProcedurePhrase() != null) {
				result.addOutputProcedure(ctx.sortOutputProcedurePhrase());
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public StartStatement addStartStatement(final StartStatementContext ctx) {
		StartStatement result = (StartStatement) getASGElement(ctx);

		if (result == null) {
			result = new StartStatementImpl(programUnit, this, ctx);

			// file call
			final Call fileCall = createCall(ctx.fileName());
			result.setFileCall(fileCall);

			// key
			if (ctx.startKey() != null) {
				result.addKey(ctx.startKey());
			}

			// invalid key
			if (ctx.invalidKeyPhrase() != null) {
				final InvalidKeyPhrase invalidKey = createInvalidKeyPhrase(ctx.invalidKeyPhrase());
				result.setInvalidKeyPhrase(invalidKey);
			}

			// not invalid key
			if (ctx.notInvalidKeyPhrase() != null) {
				final NotInvalidKeyPhrase notInvalidKey = createNotInvalidKeyPhrase(ctx.notInvalidKeyPhrase());
				result.setNotInvalidKeyPhrase(notInvalidKey);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public Statement addStatement(final StatementContext ctx) {
		return null;
	}

	@Override
	public StopStatement addStopStatement(final StopStatementContext ctx) {
		StopStatement result = (StopStatement) getASGElement(ctx);

		if (result == null) {
			result = new StopStatementImpl(programUnit, this, ctx);

			// type
			final StopType type;

			if (ctx.RUN() != null) {
				type = StopType.STOP_RUN;
			} else if (ctx.literal() != null) {
				type = StopType.STOP_RUN_AND_DISPLAY;
			} else if (ctx.stopStatementGiving() != null) {
				type = StopType.STOP_RUN_GIVING;
			} else {
				LOG.warn("unknown stop statement at {}", ctx);
				type = null;
			}

			result.setStopType(type);

			// display value stmt
			if (ctx.literal() != null) {
				final ValueStmt displayValueStmt = createValueStmt(ctx.literal());
				result.setDisplayValueStmt(displayValueStmt);
			}

			// giving
			if (ctx.stopStatementGiving() != null) {
				result.addStopStatementGiving(ctx.stopStatementGiving());
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public StringStatement addStringStatement(final StringStatementContext ctx) {
		StringStatement result = (StringStatement) getASGElement(ctx);

		if (result == null) {
			result = new StringStatementImpl(programUnit, this, ctx);

			// sending
			for (final StringSendingPhraseContext stringSendingPhraseContext : ctx.stringSendingPhrase()) {
				result.addSendings(stringSendingPhraseContext);
			}

			// into
			result.addIntoPhrase(ctx.stringIntoPhrase());

			// with pointer
			if (ctx.stringWithPointerPhrase() != null) {
				result.addWithPointerPhrase(ctx.stringWithPointerPhrase());
			}

			// on overflow
			if (ctx.onOverflowPhrase() != null) {
				final OnOverflowPhrase onOverflow = createOnOverflowPhrase(ctx.onOverflowPhrase());
				result.setOnOverflowPhrase(onOverflow);
			}

			// not on overflow
			if (ctx.notOnOverflowPhrase() != null) {
				final NotOnOverflowPhrase notOnOverflow = createNotOnOverflowPhrase(ctx.notOnOverflowPhrase());
				result.setNotOnOverflowPhrase(notOnOverflow);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public SubtractStatement addSubtractStatement(final SubtractStatementContext ctx) {
		SubtractStatement result = (SubtractStatement) getASGElement(ctx);

		if (result == null) {
			result = new SubtractStatementImpl(programUnit, this, ctx);

			// type
			final SubtractStatement.SubtractType type;

			if (ctx.subtractFromStatement() != null) {
				result.addSubtractFromStatement(ctx.subtractFromStatement());
				type = SubtractStatement.SubtractType.FROM;
			} else if (ctx.subtractFromGivingStatement() != null) {
				result.addSubtractFromGivingStatement(ctx.subtractFromGivingStatement());
				type = SubtractStatement.SubtractType.FROM_GIVING;
			} else if (ctx.subtractCorrespondingStatement() != null) {
				result.addSubtractCorrespondingStatement(ctx.subtractCorrespondingStatement());
				type = SubtractStatement.SubtractType.CORRESPONDING;
			} else {
				type = null;
			}

			result.setSubtractType(type);

			// on size error
			if (ctx.onSizeErrorPhrase() != null) {
				final OnSizeErrorPhrase onSizeError = createOnSizeErrorPhrase(ctx.onSizeErrorPhrase());
				result.setOnSizeErrorPhrase(onSizeError);
			}

			// not on size error
			if (ctx.notOnSizeErrorPhrase() != null) {
				final NotOnSizeErrorPhrase notOnSizeError = createNotOnSizeErrorPhrase(ctx.notOnSizeErrorPhrase());
				result.setNotOnSizeErrorPhrase(notOnSizeError);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public TerminateStatement addTerminateStatement(final TerminateStatementContext ctx) {
		TerminateStatement result = (TerminateStatement) getASGElement(ctx);

		if (result == null) {
			result = new TerminateStatementImpl(programUnit, this, ctx);

			final Call reportCall = createCall(ctx.reportName());
			result.setReportCall(reportCall);

			registerStatement(result);
		}

		return result;
	}

	@Override
	public UnstringStatement addUnstringStatement(final UnstringStatementContext ctx) {
		UnstringStatement result = (UnstringStatement) getASGElement(ctx);

		if (result == null) {
			result = new UnstringStatementImpl(programUnit, this, ctx);

			// sending
			result.addSending(ctx.unstringSendingPhrase());

			// into
			result.addIntoPhrase(ctx.unstringIntoPhrase());

			// with pointer
			if (ctx.unstringWithPointerPhrase() != null) {
				result.addWithPointerPhrase(ctx.unstringWithPointerPhrase());
			}

			// tallying
			if (ctx.unstringTallyingPhrase() != null) {
				result.addTallyingPhrase(ctx.unstringTallyingPhrase());
			}

			// on overflow
			if (ctx.onOverflowPhrase() != null) {
				final OnOverflowPhrase onOverflow = createOnOverflowPhrase(ctx.onOverflowPhrase());
				result.setOnOverflowPhrase(onOverflow);
			}

			// not on overflow
			if (ctx.notOnOverflowPhrase() != null) {
				final NotOnOverflowPhrase notOnOverflow = createNotOnOverflowPhrase(ctx.notOnOverflowPhrase());
				result.setNotOnOverflowPhrase(notOnOverflow);
			}

			registerStatement(result);
		}

		return result;
	}

	@Override
	public WriteStatement addWriteStatement(final WriteStatementContext ctx) {
		WriteStatement result = (WriteStatement) getASGElement(ctx);

		if (result == null) {
			result = new WriteStatementImpl(programUnit, this, ctx);

			// record
			final Call recordCall = createCall(ctx.recordName());
			result.setRecordCall(recordCall);

			// from
			if (ctx.writeFromPhrase() != null) {
				result.addFrom(ctx.writeFromPhrase());
			}

			// advancing
			if (ctx.writeAdvancingPhrase() != null) {
				result.addAdvancingPhrase(ctx.writeAdvancingPhrase());
			}

			// at end of page
			if (ctx.writeAtEndOfPagePhrase() != null) {
				final AtEndOfPagePhrase atEndOfPagePhrase = createAtEndOfPagePhrase(ctx.writeAtEndOfPagePhrase());
				result.setAtEndOfPagePhrase(atEndOfPagePhrase);
			}

			// not at end of page
			if (ctx.writeNotAtEndOfPagePhrase() != null) {
				final NotAtEndOfPagePhrase notAtEndOfPagePhrase = createNotAtEndOfPagePhrase(
						ctx.writeNotAtEndOfPagePhrase());
				result.setNotAtEndOfPagePhrase(notAtEndOfPagePhrase);
			}

			// invalid key
			if (ctx.invalidKeyPhrase() != null) {
				final InvalidKeyPhrase invalidKey = createInvalidKeyPhrase(ctx.invalidKeyPhrase());
				result.setInvalidKeyPhrase(invalidKey);
			}

			// not invalid key
			if (ctx.notInvalidKeyPhrase() != null) {
				final NotInvalidKeyPhrase notInvalidKey = createNotInvalidKeyPhrase(ctx.notInvalidKeyPhrase());
				result.setNotInvalidKeyPhrase(notInvalidKey);
			}

			registerStatement(result);
		}

		return result;
	}

	protected AtEndOfPagePhrase createAtEndOfPagePhrase(final WriteAtEndOfPagePhraseContext ctx) {
		AtEndOfPagePhrase result = (AtEndOfPagePhrase) getASGElement(ctx);

		if (result == null) {
			result = new AtEndOfPagePhraseImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected AtEndPhrase createAtEndPhrase(final AtEndPhraseContext ctx) {
		AtEndPhrase result = (AtEndPhrase) getASGElement(ctx);

		if (result == null) {
			result = new AtEndPhraseImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected InvalidKeyPhrase createInvalidKeyPhrase(final InvalidKeyPhraseContext ctx) {
		InvalidKeyPhrase result = (InvalidKeyPhrase) getASGElement(ctx);

		if (result == null) {
			result = new InvalidKeyPhraseImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected NotAtEndOfPagePhrase createNotAtEndOfPagePhrase(final WriteNotAtEndOfPagePhraseContext ctx) {
		NotAtEndOfPagePhrase result = (NotAtEndOfPagePhrase) getASGElement(ctx);

		if (result == null) {
			result = new NotAtEndOfPagePhraseImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected NotAtEndPhrase createNotAtEndPhrase(final NotAtEndPhraseContext ctx) {
		NotAtEndPhrase result = (NotAtEndPhrase) getASGElement(ctx);

		if (result == null) {
			result = new NotAtEndPhraseImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected NotInvalidKeyPhrase createNotInvalidKeyPhrase(final NotInvalidKeyPhraseContext ctx) {
		NotInvalidKeyPhrase result = (NotInvalidKeyPhrase) getASGElement(ctx);

		if (result == null) {
			result = new NotInvalidKeyPhraseImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected NotOnExceptionClause createNotOnExceptionClause(final NotOnExceptionClauseContext ctx) {
		NotOnExceptionClause result = (NotOnExceptionClause) getASGElement(ctx);

		if (result == null) {
			result = new NotOnExceptionClauseImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected NotOnOverflowPhrase createNotOnOverflowPhrase(final NotOnOverflowPhraseContext ctx) {
		NotOnOverflowPhrase result = (NotOnOverflowPhrase) getASGElement(ctx);

		if (result == null) {
			result = new NotOnOverflowPhraseImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected NotOnSizeErrorPhrase createNotOnSizeErrorPhrase(final NotOnSizeErrorPhraseContext ctx) {
		NotOnSizeErrorPhrase result = (NotOnSizeErrorPhrase) getASGElement(ctx);

		if (result == null) {
			result = new NotOnSizeErrorPhraseImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected OnExceptionClause createOnException(final OnExceptionClauseContext ctx) {
		OnExceptionClause result = (OnExceptionClause) getASGElement(ctx);

		if (result == null) {
			result = new OnExceptionClauseImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected OnOverflowPhrase createOnOverflowPhrase(final OnOverflowPhraseContext ctx) {
		OnOverflowPhrase result = (OnOverflowPhrase) getASGElement(ctx);

		if (result == null) {
			result = new OnOverflowPhraseImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected OnSizeErrorPhrase createOnSizeErrorPhrase(final OnSizeErrorPhraseContext ctx) {
		OnSizeErrorPhrase result = (OnSizeErrorPhrase) getASGElement(ctx);

		if (result == null) {
			result = new OnSizeErrorPhraseImpl(programUnit, ctx);

			for (final StatementContext statementContext : ctx.statement()) {
				result.addStatement(statementContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public List<Statement> getStatements() {
		return statements;
	}

	protected void registerStatement(final Statement statement) {
		assert statement != null;
		assert statement.getCtx() != null;

		registerASGElement(statement);
		statements.add(statement);
	}
}
