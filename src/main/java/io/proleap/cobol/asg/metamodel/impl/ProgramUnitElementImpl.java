/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import org.antlr.v4.runtime.ParserRuleContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.AlphabetNameContext;
import io.proleap.cobol.Cobol85Parser.AndOrConditionContext;
import io.proleap.cobol.Cobol85Parser.ArithmeticExpressionContext;
import io.proleap.cobol.Cobol85Parser.AssignmentNameContext;
import io.proleap.cobol.Cobol85Parser.BooleanLiteralContext;
import io.proleap.cobol.Cobol85Parser.CdNameContext;
import io.proleap.cobol.Cobol85Parser.ClassNameContext;
import io.proleap.cobol.Cobol85Parser.CobolWordContext;
import io.proleap.cobol.Cobol85Parser.ConditionContext;
import io.proleap.cobol.Cobol85Parser.ConditionNameContext;
import io.proleap.cobol.Cobol85Parser.DataDescNameContext;
import io.proleap.cobol.Cobol85Parser.DataNameContext;
import io.proleap.cobol.Cobol85Parser.EnvironmentNameContext;
import io.proleap.cobol.Cobol85Parser.FigurativeConstantContext;
import io.proleap.cobol.Cobol85Parser.FileNameContext;
import io.proleap.cobol.Cobol85Parser.IdentifierContext;
import io.proleap.cobol.Cobol85Parser.IndexNameContext;
import io.proleap.cobol.Cobol85Parser.IntegerLiteralContext;
import io.proleap.cobol.Cobol85Parser.LibraryNameContext;
import io.proleap.cobol.Cobol85Parser.LiteralContext;
import io.proleap.cobol.Cobol85Parser.LocalNameContext;
import io.proleap.cobol.Cobol85Parser.MnemonicNameContext;
import io.proleap.cobol.Cobol85Parser.NumericLiteralContext;
import io.proleap.cobol.Cobol85Parser.PlusMinusContext;
import io.proleap.cobol.Cobol85Parser.ProcedureNameContext;
import io.proleap.cobol.Cobol85Parser.ProgramNameContext;
import io.proleap.cobol.Cobol85Parser.QualifiedDataNameContext;
import io.proleap.cobol.Cobol85Parser.RecordNameContext;
import io.proleap.cobol.Cobol85Parser.RelationConditionContext;
import io.proleap.cobol.Cobol85Parser.ReportNameContext;
import io.proleap.cobol.Cobol85Parser.SpecialRegisterContext;
import io.proleap.cobol.Cobol85Parser.SubscriptContext;
import io.proleap.cobol.Cobol85Parser.SystemNameContext;
import io.proleap.cobol.Cobol85Parser.TableCallContext;
import io.proleap.cobol.asg.metamodel.ASGElement;
import io.proleap.cobol.asg.metamodel.BooleanLiteral;
import io.proleap.cobol.asg.metamodel.FigurativeConstant;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.MnemonicName;
import io.proleap.cobol.asg.metamodel.NumericLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.ProgramUnitElement;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.CommunicationDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.call.FileControlEntryCall;
import io.proleap.cobol.asg.metamodel.call.IndexCall;
import io.proleap.cobol.asg.metamodel.call.ProcedureCall;
import io.proleap.cobol.asg.metamodel.call.ReportCall;
import io.proleap.cobol.asg.metamodel.call.ReportDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.call.SpecialRegisterCall;
import io.proleap.cobol.asg.metamodel.call.TableCall;
import io.proleap.cobol.asg.metamodel.call.impl.CallDelegateImpl;
import io.proleap.cobol.asg.metamodel.call.impl.CommunicationDescriptionEntryCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.DataDescriptionEntryCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.EnvironmentCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.FileControlEntryCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.IndexCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.MnemonicCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.ProcedureCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.ReportCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.ReportDescriptionEntryCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.SpecialRegisterCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.TableCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.UndefinedCallImpl;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.datadescription.Index;
import io.proleap.cobol.asg.metamodel.data.datadescription.OccursClause;
import io.proleap.cobol.asg.metamodel.data.file.FileDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.file.FileSection;
import io.proleap.cobol.asg.metamodel.data.linkage.LinkageSection;
import io.proleap.cobol.asg.metamodel.data.localstorage.LocalStorageSection;
import io.proleap.cobol.asg.metamodel.data.report.ReportDescription;
import io.proleap.cobol.asg.metamodel.data.report.ReportDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.report.ReportSection;
import io.proleap.cobol.asg.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.asg.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.InputOutputSection;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlEntry;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlParagraph;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.BooleanLiteralValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.IntegerLiteralValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.RelationConditionValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ArithmeticValueStmtImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.BooleanLiteralValueStmtImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.CallValueStmtImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ConditionValueStmtImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.IntegerLiteralValueStmtImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.LiteralValueStmtImpl;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.RelationConditionValueStmtImpl;
import io.proleap.cobol.asg.util.StringUtils;

public class ProgramUnitElementImpl extends CompilationUnitElementImpl implements ProgramUnitElement {

	private static final String HEX_PREFIX = "X\"";

	private final static Logger LOG = LogManager.getLogger(ProgramUnitElementImpl.class);

	protected ProgramUnit programUnit;

	public ProgramUnitElementImpl(final ProgramUnit programUnit, final ParserRuleContext ctx) {
		super(programUnit.getCompilationUnit(), ctx);

		this.programUnit = programUnit;
	}

	protected ArithmeticValueStmt createArithmeticValueStmt(final ArithmeticExpressionContext ctx) {
		ArithmeticValueStmt result = (ArithmeticValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new ArithmeticValueStmtImpl(programUnit, ctx);

			// mult divs
			result.addMultDivs(ctx.multDivs());

			// plus minus
			for (final PlusMinusContext plusMinusContext : ctx.plusMinus()) {
				result.addPlusMinus(plusMinusContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected BooleanLiteral createBooleanLiteral(final BooleanLiteralContext ctx) {
		BooleanLiteral result = (BooleanLiteral) getASGElement(ctx);

		if (result == null) {
			final Boolean value = StringUtils.parseBoolean(ctx.getText());
			result = new BooleanLiteralImpl(value, programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	protected BooleanLiteralValueStmt createBooleanLiteralValueStmt(final BooleanLiteralContext ctx) {
		final BooleanLiteral booleanLiteral = createBooleanLiteral(ctx);
		final BooleanLiteralValueStmt result = new BooleanLiteralValueStmtImpl(programUnit, ctx);
		result.setBooleanLiteral(booleanLiteral);
		return result;
	}

	protected Call createCall(final AlphabetNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			result = createUndefinedCall(ctx);
		}

		return result;
	}

	protected Call createCall(final AssignmentNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			result = createUndefinedCall(ctx);
		}

		return result;
	}

	protected Call createCall(final CdNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final CommunicationDescriptionEntry communicationDescriptionEntry = findCommunicationDescriptionEntry(name);

			if (communicationDescriptionEntry == null) {
				LOG.warn("call to unknown communication description element {}", name);
				result = createUndefinedCall(ctx);
			} else {
				result = createCommunicationDescriptionEntryCall(name, communicationDescriptionEntry, ctx);
			}
		}

		return result;
	}

	protected Call createCall(final ClassNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			result = createUndefinedCall(ctx);
		}

		return result;
	}

	protected Call createCall(final CobolWordContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			result = createUndefinedCall(ctx);
		}

		return result;
	}

	protected Call createCall(final ConditionNameContext ctx) {
		final Call result = createDataDescriptionEntryCall(ctx);
		return result;
	}

	protected Call createCall(final DataDescNameContext ctx) {
		final Call result = createDataDescriptionEntryCall(ctx);
		return result;
	}

	protected Call createCall(final DataNameContext ctx) {
		final Call result = createDataDescriptionEntryCall(ctx);
		return result;
	}

	protected Call createCall(final EnvironmentNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new EnvironmentCallImpl(name, programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	protected Call createCall(final FileNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final FileControlEntry fileControlEntry = findFileControlEntry(name);

			if (fileControlEntry == null) {
				result = createDataDescriptionEntryCall(ctx);
			} else {
				result = createFileControlEntryCall(name, fileControlEntry, ctx);
			}
		}

		return result;
	}

	protected Call createCall(final IdentifierContext ctx) {
		final Call result;

		if (ctx.specialRegister() != null) {
			final Call specialRegisterCall = createCall(ctx.specialRegister());
			result = new CallDelegateImpl(specialRegisterCall, programUnit, ctx);

			registerASGElement(result);
		} else if (ctx.tableCall() != null) {
			final Call tableCall = createCall(ctx.tableCall());
			result = new CallDelegateImpl(tableCall, programUnit, ctx);

			registerASGElement(result);
		} else {
			result = createDataDescriptionEntryCall(ctx);
		}

		return result;
	}

	protected Call createCall(final LibraryNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			result = createUndefinedCall(ctx);
		}

		return result;
	}

	protected Call createCall(final LocalNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			result = createUndefinedCall(ctx);
		}

		return result;
	}

	protected Call createCall(final MnemonicNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new MnemonicCallImpl(name, programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	protected Call createCall(final ParserRuleContext... ctxs) {
		Call result = null;

		for (final ParserRuleContext ctx : ctxs) {
			if (result != null) {
				break;
			}

			if (ctx == null) {
				continue;
			}

			if (ctx instanceof IdentifierContext) {
				result = createCall((IdentifierContext) ctx);
			} else if (ctx instanceof CdNameContext) {
				result = createCall((CdNameContext) ctx);
			} else if (ctx instanceof AlphabetNameContext) {
				result = createCall((AlphabetNameContext) ctx);
			} else if (ctx instanceof AssignmentNameContext) {
				result = createCall((AssignmentNameContext) ctx);
			} else if (ctx instanceof ClassNameContext) {
				result = createCall((ClassNameContext) ctx);
			} else if (ctx instanceof CobolWordContext) {
				result = createCall((CobolWordContext) ctx);
			} else if (ctx instanceof ConditionNameContext) {
				result = createCall((ConditionNameContext) ctx);
			} else if (ctx instanceof DataDescNameContext) {
				result = createCall((DataDescNameContext) ctx);
			} else if (ctx instanceof DataNameContext) {
				result = createCall((DataNameContext) ctx);
			} else if (ctx instanceof EnvironmentNameContext) {
				result = createCall((EnvironmentNameContext) ctx);
			} else if (ctx instanceof FileNameContext) {
				result = createCall((FileNameContext) ctx);
			} else if (ctx instanceof LibraryNameContext) {
				result = createCall((LibraryNameContext) ctx);
			} else if (ctx instanceof LocalNameContext) {
				result = createCall((LocalNameContext) ctx);
			} else if (ctx instanceof MnemonicNameContext) {
				result = createCall((MnemonicNameContext) ctx);
			} else if (ctx instanceof ProcedureNameContext) {
				result = createCall((ProcedureNameContext) ctx);
			} else if (ctx instanceof ProgramNameContext) {
				result = createCall((ProgramNameContext) ctx);
			} else if (ctx instanceof QualifiedDataNameContext) {
				result = createCall((QualifiedDataNameContext) ctx);
			} else if (ctx instanceof RecordNameContext) {
				result = createCall((RecordNameContext) ctx);
			} else if (ctx instanceof ReportNameContext) {
				result = createCall((ReportNameContext) ctx);
			} else if (ctx instanceof SpecialRegisterContext) {
				result = createCall((SpecialRegisterContext) ctx);
			} else if (ctx instanceof SystemNameContext) {
				result = createCall((SystemNameContext) ctx);
			} else if (ctx instanceof SpecialRegisterContext) {
				result = createCall((SpecialRegisterContext) ctx);
			} else if (ctx instanceof TableCallContext) {
				result = createCall((TableCallContext) ctx);
			} else {
				LOG.warn("unknown call at {}", ctx);
			}
		}

		return result;
	}

	protected Call createCall(final ProcedureNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final Paragraph paragraph = findProcedure(name);

			if (paragraph == null) {
				result = createUndefinedCall(ctx);
			} else {
				result = createProcedureCall(name, paragraph, ctx);
			}
		}

		return result;
	}

	protected Call createCall(final ProgramNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			result = createUndefinedCall(ctx);
		}

		return result;
	}

	protected Call createCall(final QualifiedDataNameContext ctx) {
		final Call result = createDataDescriptionEntryCall(ctx);
		return result;
	}

	protected Call createCall(final RecordNameContext ctx) {
		final Call result = createDataDescriptionEntryCall(ctx);
		return result;
	}

	protected Call createCall(final ReportNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final ReportDescription report = findReportDescription(name);

			if (report == null) {
				result = createUndefinedCall(ctx);
			} else {
				result = createReportCall(name, report, ctx);
			}
		}

		return result;
	}

	protected Call createCall(final SpecialRegisterContext ctx) {
		SpecialRegisterCall result = (SpecialRegisterCall) getASGElement(ctx);

		if (result == null) {
			final SpecialRegisterCall.Type type;

			if (ctx.ADDRESS() != null) {
				type = SpecialRegisterCall.Type.ADDRESS_OF;
			} else if (ctx.DATE() != null) {
				type = SpecialRegisterCall.Type.DATE;
			} else if (ctx.DAY() != null) {
				type = SpecialRegisterCall.Type.DAY;
			} else if (ctx.DAY_OF_WEEK() != null) {
				type = SpecialRegisterCall.Type.DAY_OF_WEEK;
			} else if (ctx.DEBUG_CONTENTS() != null) {
				type = SpecialRegisterCall.Type.DEBUG_CONTENTS;
			} else if (ctx.DEBUG_ITEM() != null) {
				type = SpecialRegisterCall.Type.DEBUG_ITEM;
			} else if (ctx.DEBUG_LINE() != null) {
				type = SpecialRegisterCall.Type.DEBUG_LINE;
			} else if (ctx.DEBUG_NAME() != null) {
				type = SpecialRegisterCall.Type.DEBUG_NAME;
			} else if (ctx.DEBUG_SUB_1() != null) {
				type = SpecialRegisterCall.Type.DEBUG_SUB_1;
			} else if (ctx.DEBUG_SUB_2() != null) {
				type = SpecialRegisterCall.Type.DEBUG_SUB_2;
			} else if (ctx.DEBUG_SUB_3() != null) {
				type = SpecialRegisterCall.Type.DEBUG_SUB_3;
			} else if (ctx.LENGTH() != null) {
				type = SpecialRegisterCall.Type.LENGTH_OF;
			} else if (ctx.LINAGE_COUNTER() != null) {
				type = SpecialRegisterCall.Type.LINAGE_COUNTER;
			} else if (ctx.LINE_COUNTER() != null) {
				type = SpecialRegisterCall.Type.LINE_COUNTER;
			} else if (ctx.PAGE_COUNTER() != null) {
				type = SpecialRegisterCall.Type.PAGE_COUNTER;
			} else if (ctx.RETURN_CODE() != null) {
				type = SpecialRegisterCall.Type.RETURN_CODE;
			} else if (ctx.SHIFT_IN() != null) {
				type = SpecialRegisterCall.Type.SHIFT_IN;
			} else if (ctx.SHIFT_OUT() != null) {
				type = SpecialRegisterCall.Type.SHIFT_OUT;
			} else if (ctx.SORT_CONTROL() != null) {
				type = SpecialRegisterCall.Type.SORT_CONTROL;
			} else if (ctx.SORT_CORE_SIZE() != null) {
				type = SpecialRegisterCall.Type.SORT_CORE_SIZE;
			} else if (ctx.SORT_FILE_SIZE() != null) {
				type = SpecialRegisterCall.Type.SORT_FILE_SIZE;
			} else if (ctx.SORT_MESSAGE() != null) {
				type = SpecialRegisterCall.Type.SORT_MESSAGE;
			} else if (ctx.SORT_MODE_SIZE() != null) {
				type = SpecialRegisterCall.Type.SORT_MODE_SIZE;
			} else if (ctx.SORT_RETURN() != null) {
				type = SpecialRegisterCall.Type.SORT_RETURN;
			} else if (ctx.TALLY() != null) {
				type = SpecialRegisterCall.Type.TALLY;
			} else if (ctx.TIME() != null) {
				type = SpecialRegisterCall.Type.TIME;
			} else if (ctx.WHEN_COMPILED() != null) {
				type = SpecialRegisterCall.Type.WHEN_COMPILED;
			} else {
				type = null;
			}

			result = new SpecialRegisterCallImpl(type, programUnit, ctx);

			if (ctx.identifier() != null) {
				final Call identifierCall = createCall(ctx.identifier());
				result.setIdentifierCall(identifierCall);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected Call createCall(final SystemNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			result = createUndefinedCall(ctx);
		}

		return result;
	}

	protected Call createCall(final TableCallContext ctx) {
		Call result = (TableCall) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final DataDescriptionEntry dataDescriptionEntry = findDataDescriptionEntry(name);

			if (dataDescriptionEntry != null) {
				final TableCall tableCall = new TableCallImpl(name, dataDescriptionEntry, programUnit, ctx);

				for (final SubscriptContext subscriptContext : ctx.subscript()) {
					tableCall.addSubscript(subscriptContext);
				}

				result = tableCall;
				registerASGElement(result);
			} else {
				LOG.warn("call to unknown data element {}", name);
				result = createUndefinedCall(ctx);
			}
		}

		return result;
	}

	protected CallValueStmt createCallValueStmt(final AlphabetNameContext ctx) {
		final Call delegatedCall = createCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, ctx);
		return result;
	}

	protected CallValueStmt createCallValueStmt(final AssignmentNameContext ctx) {
		final Call delegatedCall = createCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, ctx);
		return result;
	}

	protected CallValueStmt createCallValueStmt(final ClassNameContext ctx) {
		final Call delegatedCall = createCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, ctx);
		return result;
	}

	protected CallValueStmt createCallValueStmt(final CobolWordContext ctx) {
		final Call delegatedCall = createCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, ctx);
		return result;
	}

	protected CallValueStmt createCallValueStmt(final DataDescNameContext ctx) {
		final Call delegatedCall = createCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, ctx);
		return result;
	}

	protected CallValueStmt createCallValueStmt(final DataNameContext ctx) {
		final Call delegatedCall = createCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, ctx);
		return result;
	}

	protected CallValueStmt createCallValueStmt(final FileNameContext ctx) {
		final Call delegatedCall = createCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, ctx);
		return result;
	}

	protected CallValueStmt createCallValueStmt(final IdentifierContext ctx) {
		final Call delegatedCall = createCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, ctx);
		return result;
	}

	protected CallValueStmt createCallValueStmt(final IndexNameContext ctx) {
		final Call delegatedCall = createCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, ctx);
		return result;
	}

	protected CallValueStmt createCallValueStmt(final LibraryNameContext ctx) {
		final Call delegatedCall = createCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, ctx);
		return result;
	}

	protected CallValueStmt createCallValueStmt(final LocalNameContext ctx) {
		final Call delegatedCall = createCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, ctx);
		return result;
	}

	protected CallValueStmt createCallValueStmt(final ProgramNameContext ctx) {
		final Call delegatedCall = createCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, ctx);
		return result;
	}

	protected CallValueStmt createCallValueStmt(final QualifiedDataNameContext ctx) {
		final Call delegatedCall = createCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, ctx);
		return result;
	}

	protected CallValueStmt createCallValueStmt(final ReportNameContext ctx) {
		final Call delegatedCall = createCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, ctx);
		return result;
	}

	protected CallValueStmt createCallValueStmt(final SystemNameContext ctx) {
		final Call delegatedCall = createCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, ctx);
		return result;
	}

	protected CommunicationDescriptionEntryCall createCommunicationDescriptionEntryCall(final String name,
			final CommunicationDescriptionEntry communicationDescriptionEntry, final CdNameContext ctx) {
		CommunicationDescriptionEntryCall result = (CommunicationDescriptionEntryCall) getASGElement(ctx);

		if (result == null) {
			result = new CommunicationDescriptionEntryCallImpl(name, communicationDescriptionEntry, programUnit, ctx);
			linkCommunicationDescriptionEntryCallWithCommunicationDescriptionEntry(result,
					communicationDescriptionEntry);

			registerASGElement(result);
		}

		return result;
	}

	protected ConditionValueStmt createConditionValueStmt(final ConditionContext ctx) {
		ConditionValueStmt result = (ConditionValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new ConditionValueStmtImpl(programUnit, ctx);

			// combinable condition
			result.addCombinableCondition(ctx.combinableCondition());

			// and or condition
			for (final AndOrConditionContext andOrConditionContext : ctx.andOrCondition()) {
				result.addAndOrCondition(andOrConditionContext);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected Call createDataDescriptionEntryCall(final ParserRuleContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final Index index = findIndex(name);
			final DataDescriptionEntry dataDescriptionEntry = findDataDescriptionEntry(name);

			if (index != null) {
				result = createIndexCall(ctx);
			} else if (dataDescriptionEntry != null) {
				result = createDataDescriptionEntryCall(name, dataDescriptionEntry, ctx);
			} else {
				LOG.warn("call to unknown data element {}", name);
				result = createUndefinedCall(ctx);
			}
		}

		return result;
	}

	protected DataDescriptionEntryCall createDataDescriptionEntryCall(final String name,
			final DataDescriptionEntry dataDescriptionEntry, final ParserRuleContext ctx) {
		DataDescriptionEntryCall result = (DataDescriptionEntryCall) getASGElement(ctx);

		if (result == null) {
			result = new DataDescriptionEntryCallImpl(name, dataDescriptionEntry, programUnit, ctx);
			linkDataDescriptionEntryCallWithDataDescriptionEntry(result, dataDescriptionEntry);

			registerASGElement(result);
		}

		return result;
	}

	protected FigurativeConstant createFigurativeConstant(final FigurativeConstantContext ctx) {
		FigurativeConstant result = (FigurativeConstant) getASGElement(ctx);

		if (result == null) {
			/*
			 * type
			 */
			final FigurativeConstant.Type type;

			if (ctx.ALL() != null) {
				type = FigurativeConstant.Type.ALL;
			} else if (ctx.HIGH_VALUE() != null) {
				type = FigurativeConstant.Type.HIGH_VALUE;
			} else if (ctx.HIGH_VALUES() != null) {
				type = FigurativeConstant.Type.HIGH_VALUES;
			} else if (ctx.LOW_VALUE() != null) {
				type = FigurativeConstant.Type.LOW_VALUE;
			} else if (ctx.LOW_VALUES() != null) {
				type = FigurativeConstant.Type.LOW_VALUES;
			} else if (ctx.NULL() != null) {
				type = FigurativeConstant.Type.NULL;
			} else if (ctx.NULLS() != null) {
				type = FigurativeConstant.Type.NULLS;
			} else if (ctx.QUOTE() != null) {
				type = FigurativeConstant.Type.QUOTE;
			} else if (ctx.QUOTES() != null) {
				type = FigurativeConstant.Type.QUOTES;
			} else if (ctx.SPACE() != null) {
				type = FigurativeConstant.Type.SPACE;
			} else if (ctx.SPACES() != null) {
				type = FigurativeConstant.Type.SPACES;
			} else if (ctx.ZERO() != null) {
				type = FigurativeConstant.Type.ZERO;
			} else if (ctx.ZEROS() != null) {
				type = FigurativeConstant.Type.ZEROS;
			} else if (ctx.ZEROES() != null) {
				type = FigurativeConstant.Type.ZEROES;
			} else {
				type = null;
			}

			result = new FigurativeConstantImpl(type, programUnit, ctx);

			/*
			 * literal
			 */
			if (ctx.literal() != null) {
				final Literal literal = createLiteral(ctx.literal());
				result.setLiteral(literal);
			}

			registerASGElement(result);
		}

		return result;
	}

	protected FileControlEntryCall createFileControlEntryCall(final String name,
			final FileControlEntry fileControlEntry, final FileNameContext ctx) {
		FileControlEntryCall result = (FileControlEntryCall) getASGElement(ctx);

		if (result == null) {
			result = new FileControlEntryCallImpl(name, fileControlEntry, programUnit, ctx);
			linkFileControlEntryCallWithFileControlEntry(result, fileControlEntry);

			registerASGElement(result);
		}

		return result;
	}

	protected IndexCall createIndexCall(final ParserRuleContext ctx) {
		IndexCall result = (IndexCall) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final Index index = findIndex(name);

			if (index == null) {
				createDataDescriptionEntryCall(ctx);
			} else {
				result = new IndexCallImpl(name, index, programUnit, ctx);
				linkIndexCallWithIndex(result, index);

				registerASGElement(result);
			}
		}

		return result;
	}

	protected IntegerLiteral createIntegerLiteral(final IntegerLiteralContext ctx) {
		IntegerLiteral result = (IntegerLiteral) getASGElement(ctx);

		if (result == null) {
			final Integer value = StringUtils.parseInteger(ctx.getText());
			result = new IntegerLiteralImpl(value, programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	protected IntegerLiteralValueStmt createIntegerLiteralValueStmt(final IntegerLiteralContext ctx) {
		final IntegerLiteral integerLiteral = createIntegerLiteral(ctx);
		final IntegerLiteralValueStmt result = new IntegerLiteralValueStmtImpl(programUnit, ctx);
		result.setIntegerLiteral(integerLiteral);
		return result;
	}

	protected Literal createLiteral(final LiteralContext ctx) {
		Literal result = (Literal) getASGElement(ctx);

		if (result == null) {
			result = new LiteralImpl(programUnit, ctx);

			final Literal.Type type;

			if (ctx.NONNUMERICLITERAL() != null) {
				final String text = ctx.getText();
				final boolean isHex = text.startsWith(HEX_PREFIX);
				final String nonNumericLiteral = isHex ? text : text.substring(1, text.length() - 1);
				result.setNonNumericLiteral(nonNumericLiteral);

				type = Literal.Type.NON_NUMERIC;
			} else if (ctx.numericLiteral() != null) {
				final NumericLiteral numericLiteral = createNumericLiteral(ctx.numericLiteral());
				result.setNumericLiteral(numericLiteral);

				type = Literal.Type.NUMERIC;
			} else if (ctx.booleanLiteral() != null) {
				final BooleanLiteral booleanLiteral = createBooleanLiteral(ctx.booleanLiteral());
				result.setBooleanLiteral(booleanLiteral);

				type = Literal.Type.BOOLEAN;
			} else if (ctx.figurativeConstant() != null) {
				final FigurativeConstant figurativeConstant = createFigurativeConstant(ctx.figurativeConstant());
				result.setFigurativeConstant(figurativeConstant);

				type = Literal.Type.FIGURATIVE_CONSTANT;
			} else if (ctx.cicsDfhRespLiteral() != null) {
				type = Literal.Type.CICS_DFH_RESP;
			} else if (ctx.cicsDfhValueLiteral() != null) {
				type = Literal.Type.CICS_DFH_VALUE;
			} else {
				type = null;
			}

			result.setType(type);

			registerASGElement(result);
		}

		return result;
	}

	protected LiteralValueStmt createLiteralValueStmt(final LiteralContext ctx) {
		final Literal literal = createLiteral(ctx);
		final LiteralValueStmt result = new LiteralValueStmtImpl(programUnit, ctx);
		result.setLiteral(literal);
		return result;
	}

	protected MnemonicName createMnemonicName(final MnemonicNameContext ctx) {
		MnemonicName result = (MnemonicName) getASGElement(ctx);

		if (result == null) {
			final String value = ctx.getText();
			result = new MnemonicNameImpl(value, programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	protected NumericLiteral createNumericLiteral(final NumericLiteralContext ctx) {
		NumericLiteral result = (NumericLiteral) getASGElement(ctx);

		if (result == null) {
			result = new NumericLiteralImpl(programUnit, ctx);

			final NumericLiteral.Type type;

			if (ctx.integerLiteral() != null) {
				final Integer integerValue = StringUtils.parseInteger(ctx.getText());
				result.setIntegerValue(integerValue);

				type = NumericLiteral.Type.INTEGER;
			} else if (ctx.ZERO() != null) {
				result.setIntegerValue(0);

				type = NumericLiteral.Type.INTEGER;
			} else if (ctx.NUMERICLITERAL() != null) {
				final Double doubleValue = StringUtils.parseDouble(ctx.NUMERICLITERAL().getText());
				result.setDoubleValue(doubleValue);

				type = NumericLiteral.Type.DOUBLE;
			} else {
				type = null;
			}

			result.setType(type);

			registerASGElement(result);
		}

		return result;
	}

	protected ProcedureCall createProcedureCall(final String name, final Paragraph paragraph,
			final ProcedureNameContext ctx) {
		ProcedureCall result = (ProcedureCall) getASGElement(ctx);

		if (result == null) {
			result = new ProcedureCallImpl(name, paragraph, programUnit, ctx);
			linkProcedureCallWithParagraph(result, paragraph);

			registerASGElement(result);
		}

		return result;
	}

	protected RelationConditionValueStmt createRelationConditionValueStmt(final RelationConditionContext ctx) {
		RelationConditionValueStmt result = (RelationConditionValueStmt) getASGElement(ctx);

		if (result == null) {
			result = new RelationConditionValueStmtImpl(programUnit, ctx);

			final RelationConditionValueStmt.Type type;

			if (ctx.relationSignCondition() != null) {
				result.addRelationSignCondition(ctx.relationSignCondition());
				type = RelationConditionValueStmt.Type.SIGN;
			} else if (ctx.relationArithmeticComparison() != null) {
				result.addRelationArithmeticComparison(ctx.relationArithmeticComparison());
				type = RelationConditionValueStmt.Type.ARITHMETIC;
			} else if (ctx.relationCombinedComparison() != null) {
				result.addRelationCombinedComparison(ctx.relationCombinedComparison());
				type = RelationConditionValueStmt.Type.COMBINED;
			} else {
				type = null;
			}

			result.setType(type);

			registerASGElement(result);
		}

		return result;
	}

	protected ReportCall createReportCall(final String name, final ReportDescription report,
			final ReportNameContext ctx) {
		ReportCall result = (ReportCall) getASGElement(ctx);

		if (result == null) {
			result = new ReportCallImpl(name, report, programUnit, ctx);
			linkReportCallWithReport(result, report);

			registerASGElement(result);
		}

		return result;
	}

	protected ReportDescriptionEntryCall createReportDescriptionEntryCall(final String name,
			final ReportDescriptionEntry reportDescriptionEntry, final ReportNameContext ctx) {
		ReportDescriptionEntryCall result = (ReportDescriptionEntryCall) getASGElement(ctx);

		if (result == null) {
			result = new ReportDescriptionEntryCallImpl(name, reportDescriptionEntry, programUnit, ctx);
			linkReportDescriptionEntryCallWithReportDescriptionEntry(result, reportDescriptionEntry);

			registerASGElement(result);
		}

		return result;
	}

	protected Call createUndefinedCall(final ParserRuleContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	protected ValueStmt createValueStmt(final ParserRuleContext... ctxs) {
		ValueStmt result = null;

		for (final ParserRuleContext ctx : ctxs) {
			if (result != null) {
				break;
			}

			if (ctx == null) {
				continue;
			}

			if (ctx instanceof IdentifierContext) {
				result = createCallValueStmt((IdentifierContext) ctx);
			} else if (ctx instanceof AlphabetNameContext) {
				result = createCallValueStmt((AlphabetNameContext) ctx);
			} else if (ctx instanceof ArithmeticExpressionContext) {
				result = createArithmeticValueStmt((ArithmeticExpressionContext) ctx);
			} else if (ctx instanceof AssignmentNameContext) {
				result = createCallValueStmt((AssignmentNameContext) ctx);
			} else if (ctx instanceof ClassNameContext) {
				result = createCallValueStmt((ClassNameContext) ctx);
			} else if (ctx instanceof CobolWordContext) {
				result = createCallValueStmt((CobolWordContext) ctx);
			} else if (ctx instanceof DataDescNameContext) {
				result = createCallValueStmt((DataDescNameContext) ctx);
			} else if (ctx instanceof DataNameContext) {
				result = createCallValueStmt((DataNameContext) ctx);
			} else if (ctx instanceof FileNameContext) {
				result = createCallValueStmt((FileNameContext) ctx);
			} else if (ctx instanceof IndexNameContext) {
				result = createCallValueStmt((IndexNameContext) ctx);
			} else if (ctx instanceof LibraryNameContext) {
				result = createCallValueStmt((LibraryNameContext) ctx);
			} else if (ctx instanceof LocalNameContext) {
				result = createCallValueStmt((LocalNameContext) ctx);
			} else if (ctx instanceof ProgramNameContext) {
				result = createCallValueStmt((ProgramNameContext) ctx);
			} else if (ctx instanceof QualifiedDataNameContext) {
				result = createCallValueStmt((QualifiedDataNameContext) ctx);
			} else if (ctx instanceof ReportNameContext) {
				result = createCallValueStmt((ReportNameContext) ctx);
			} else if (ctx instanceof SystemNameContext) {
				result = createCallValueStmt((SystemNameContext) ctx);
			} else if (ctx instanceof ConditionContext) {
				result = createConditionValueStmt((ConditionContext) ctx);
			} else if (ctx instanceof RelationConditionContext) {
				result = createRelationConditionValueStmt((RelationConditionContext) ctx);
			} else if (ctx instanceof IntegerLiteralContext) {
				result = createIntegerLiteralValueStmt((IntegerLiteralContext) ctx);
			} else if (ctx instanceof LiteralContext) {
				result = createLiteralValueStmt((LiteralContext) ctx);
			} else {
				LOG.warn("unknown value stmt at {}", ctx);
			}
		}

		return result;
	}

	protected CommunicationDescriptionEntry findCommunicationDescriptionEntry(final String name) {
		final CommunicationSection communicationSection = findCommunicationSection();
		final CommunicationDescriptionEntry result;

		if (communicationSection == null) {
			result = null;
		} else {
			result = communicationSection.getCommunicationDescriptionEntry(name);
		}

		return result;
	}

	protected CommunicationSection findCommunicationSection() {
		final DataDivision dataDivision = programUnit.getDataDivision();
		final CommunicationSection result;

		if (dataDivision == null) {
			result = null;
		} else {
			result = dataDivision.getCommunicationSection();
		}

		return result;
	}

	protected DataDescriptionEntry findDataDescriptionEntry(final String name) {
		final WorkingStorageSection workingStorageSection = findWorkingStorageSection();
		final DataDescriptionEntry workingStorageSectionResult;

		if (workingStorageSection == null) {
			workingStorageSectionResult = null;
		} else {
			workingStorageSectionResult = workingStorageSection.getDataDescriptionEntry(name);
		}

		final CommunicationSection communicationSection = findCommunicationSection();
		final DataDescriptionEntry communicationSectionResult;

		if (communicationSection == null) {
			communicationSectionResult = null;
		} else {
			communicationSectionResult = communicationSection.getDataDescriptionEntry(name);
		}

		final LinkageSection linkageSection = findLinkageSection();
		final DataDescriptionEntry linkageSectionResult;

		if (linkageSection == null) {
			linkageSectionResult = null;
		} else {
			linkageSectionResult = linkageSection.getDataDescriptionEntry(name);
		}

		final LocalStorageSection localStorageSection = findLocalStorageSection();
		final DataDescriptionEntry localStorageSectionResult;

		if (localStorageSection == null) {
			localStorageSectionResult = null;
		} else {
			localStorageSectionResult = localStorageSection.getDataDescriptionEntry(name);
		}

		final FileSection fileSection = findFileSection();
		final DataDescriptionEntry fileSectionResult;

		if (fileSection == null) {
			fileSectionResult = null;
		} else {
			DataDescriptionEntry dataDescriptionEntryFound = null;

			for (final FileDescriptionEntry fileDescriptionEntry : fileSection.getFileDescriptionEntries()) {
				final DataDescriptionEntry dataDescriptionEntry = fileDescriptionEntry.getDataDescriptionEntry(name);

				if (dataDescriptionEntry != null) {
					dataDescriptionEntryFound = dataDescriptionEntry;
					break;
				}
			}

			fileSectionResult = dataDescriptionEntryFound;
		}

		final DataDescriptionEntry result;

		if (workingStorageSectionResult != null) {
			result = workingStorageSectionResult;
		} else if (fileSectionResult != null) {
			result = fileSectionResult;
		} else if (communicationSectionResult != null) {
			result = communicationSectionResult;
		} else if (linkageSectionResult != null) {
			result = linkageSectionResult;
		} else if (localStorageSectionResult != null) {
			result = localStorageSectionResult;
		} else {
			result = null;
		}

		return result;
	}

	protected FileControlEntry findFileControlEntry(final String name) {
		final FileControlParagraph fileControlParagraph = findFileControlParagraph();
		final FileControlEntry result;

		if (fileControlParagraph == null) {
			result = null;
		} else {
			result = fileControlParagraph.getFileControlEntry(name);
		}

		return result;
	}

	protected FileControlParagraph findFileControlParagraph() {
		final InputOutputSection inputOutputSection = findInputOutputSection();
		final FileControlParagraph result;

		if (inputOutputSection == null) {
			result = null;
		} else {
			result = inputOutputSection.getFileControlParagraph();
		}

		return result;
	}

	protected FileDescriptionEntry findFileDescriptionEntry(final String name) {
		final FileSection fileSection = findFileSection();
		final FileDescriptionEntry result;

		if (fileSection == null) {
			result = null;
		} else {
			result = fileSection.getFileDescriptionEntry(name);
		}

		return result;
	}

	protected FileSection findFileSection() {
		final DataDivision dataDivision = programUnit.getDataDivision();
		final FileSection result;

		if (dataDivision == null) {
			result = null;
		} else {
			result = dataDivision.getFileSection();
		}

		return result;
	}

	protected Index findIndex(final String name) {
		final WorkingStorageSection workingStorageSection = findWorkingStorageSection();

		if (workingStorageSection != null) {
			for (final DataDescriptionEntry dataDescriptionEntry : workingStorageSection.getDataDescriptionEntries()) {
				if (DataDescriptionEntry.Type.GROUP.equals(dataDescriptionEntry.getType())) {
					final DataDescriptionEntryGroup dataDescriptionEntryGroup = (DataDescriptionEntryGroup) dataDescriptionEntry;

					for (final OccursClause occursClause : dataDescriptionEntryGroup.getOccursClauses()) {
						final Index index = occursClause.getIndex(name);

						if (index != null) {
							return index;
						}
					}
				}
			}
		}

		return null;
	}

	protected InputOutputSection findInputOutputSection() {
		final EnvironmentDivision environmentDivision = programUnit.getEnvironmentDivision();
		final InputOutputSection result;

		if (environmentDivision == null) {
			result = null;
		} else {
			result = environmentDivision.getInputOutputSection();
		}

		return result;
	}

	protected LinkageSection findLinkageSection() {
		final DataDivision dataDivision = programUnit.getDataDivision();
		final LinkageSection result;

		if (dataDivision == null) {
			result = null;
		} else {
			result = dataDivision.getLinkageSection();
		}

		return result;
	}

	protected LocalStorageSection findLocalStorageSection() {
		final DataDivision dataDivision = programUnit.getDataDivision();
		final LocalStorageSection result;

		if (dataDivision == null) {
			result = null;
		} else {
			result = dataDivision.getLocalStorageSection();
		}

		return result;
	}

	protected Paragraph findProcedure(final String name) {
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		final Paragraph result;

		if (procedureDivision == null) {
			result = null;
		} else {
			result = procedureDivision.getParagraph(name);
		}

		return result;
	}

	protected ReportDescription findReportDescription(final String name) {
		final ReportSection reportSection = findReportSection();
		final ReportDescription result;

		if (reportSection == null) {
			result = null;
		} else {
			result = reportSection.getReportDescription(name);
		}

		return result;
	}

	protected ReportDescriptionEntry findReportDescriptionEntry(final String name) {
		final ReportDescription reportDescription = findReportDescription(name);
		final ReportDescriptionEntry result;

		if (reportDescription == null) {
			result = null;
		} else {
			result = reportDescription.getReportDescriptionEntry();
		}

		return result;
	}

	protected ReportSection findReportSection() {
		final DataDivision dataDivision = programUnit.getDataDivision();
		final ReportSection result;

		if (dataDivision == null) {
			result = null;
		} else {
			result = dataDivision.getReportSection();
		}

		return result;
	}

	protected WorkingStorageSection findWorkingStorageSection() {
		final DataDivision dataDivision = programUnit.getDataDivision();
		final WorkingStorageSection result;

		if (dataDivision == null) {
			result = null;
		} else {
			result = dataDivision.getWorkingStorageSection();
		}

		return result;
	}

	protected ASGElement getASGElement(final ParserRuleContext ctx) {
		final ASGElement result = programUnit.getProgram().getASGElementRegistry().getASGElement(ctx);
		return result;
	}

	@Override
	public ProgramUnit getProgramUnit() {
		return programUnit;
	}

	protected void linkCommunicationDescriptionEntryCallWithCommunicationDescriptionEntry(
			final CommunicationDescriptionEntryCall call,
			final CommunicationDescriptionEntry communicationDescriptionEntry) {
		communicationDescriptionEntry.addCall(call);
	}

	protected void linkDataDescriptionEntryCallWithDataDescriptionEntry(final DataDescriptionEntryCall call,
			final DataDescriptionEntry dataDescriptionEntry) {
		dataDescriptionEntry.addCall(call);
	}

	protected void linkFileControlEntryCallWithFileControlEntry(final FileControlEntryCall call,
			final FileControlEntry fileControlEntry) {
		fileControlEntry.addCall(call);
	}

	protected void linkIndexCallWithIndex(final IndexCall call, final Index index) {
		index.addCall(call);
	}

	protected void linkProcedureCallWithParagraph(final ProcedureCall call, final Paragraph paragraph) {
		paragraph.addCall(call);
	}

	protected void linkReportCallWithReport(final ReportCall reportCall, final ReportDescription report) {
		report.addCall(reportCall);
	}

	protected void linkReportDescriptionEntryCallWithReportDescriptionEntry(final ReportDescriptionEntryCall call,
			final ReportDescriptionEntry reportDescriptionEntry) {
		reportDescriptionEntry.addCall(call);
	}

	protected void registerASGElement(final ASGElement asgElement) {
		programUnit.getProgram().getASGElementRegistry().addASGElement(asgElement);
	}

}
