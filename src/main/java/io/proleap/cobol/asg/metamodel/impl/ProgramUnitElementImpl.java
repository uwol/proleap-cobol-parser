/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.cobol.CobolParser.AlphabetNameContext;
import io.proleap.cobol.CobolParser.AndOrConditionContext;
import io.proleap.cobol.CobolParser.ArithmeticExpressionContext;
import io.proleap.cobol.CobolParser.AssignmentNameContext;
import io.proleap.cobol.CobolParser.BooleanLiteralContext;
import io.proleap.cobol.CobolParser.CdNameContext;
import io.proleap.cobol.CobolParser.ClassNameContext;
import io.proleap.cobol.CobolParser.CobolWordContext;
import io.proleap.cobol.CobolParser.ConditionContext;
import io.proleap.cobol.CobolParser.ConditionNameContext;
import io.proleap.cobol.CobolParser.DataDescNameContext;
import io.proleap.cobol.CobolParser.DataNameContext;
import io.proleap.cobol.CobolParser.EnvironmentNameContext;
import io.proleap.cobol.CobolParser.FigurativeConstantContext;
import io.proleap.cobol.CobolParser.FileNameContext;
import io.proleap.cobol.CobolParser.FunctionCallContext;
import io.proleap.cobol.CobolParser.IdentifierContext;
import io.proleap.cobol.CobolParser.IndexNameContext;
import io.proleap.cobol.CobolParser.IntegerLiteralContext;
import io.proleap.cobol.CobolParser.LibraryNameContext;
import io.proleap.cobol.CobolParser.LiteralContext;
import io.proleap.cobol.CobolParser.LocalNameContext;
import io.proleap.cobol.CobolParser.MnemonicNameContext;
import io.proleap.cobol.CobolParser.NumericLiteralContext;
import io.proleap.cobol.CobolParser.PlusMinusContext;
import io.proleap.cobol.CobolParser.ProcedureNameContext;
import io.proleap.cobol.CobolParser.ProgramNameContext;
import io.proleap.cobol.CobolParser.QualifiedDataNameContext;
import io.proleap.cobol.CobolParser.QualifiedDataNameFormat1Context;
import io.proleap.cobol.CobolParser.QualifiedDataNameFormat2Context;
import io.proleap.cobol.CobolParser.QualifiedDataNameFormat3Context;
import io.proleap.cobol.CobolParser.QualifiedDataNameFormat4Context;
import io.proleap.cobol.CobolParser.QualifiedInDataContext;
import io.proleap.cobol.CobolParser.RecordNameContext;
import io.proleap.cobol.CobolParser.RelationConditionContext;
import io.proleap.cobol.CobolParser.ReportNameContext;
import io.proleap.cobol.CobolParser.SpecialRegisterContext;
import io.proleap.cobol.CobolParser.SubscriptContext;
import io.proleap.cobol.CobolParser.SystemNameContext;
import io.proleap.cobol.CobolParser.TableCallContext;
import io.proleap.cobol.asg.metamodel.ASGElement;
import io.proleap.cobol.asg.metamodel.BooleanLiteral;
import io.proleap.cobol.asg.metamodel.FigurativeConstant;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.MnemonicName;
import io.proleap.cobol.asg.metamodel.NumericLiteral;
import io.proleap.cobol.asg.metamodel.NumericLiteral.NumericLiteralType;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.ProgramUnitElement;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.CommunicationDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.call.FileControlEntryCall;
import io.proleap.cobol.asg.metamodel.call.FunctionCall;
import io.proleap.cobol.asg.metamodel.call.IndexCall;
import io.proleap.cobol.asg.metamodel.call.ProcedureCall;
import io.proleap.cobol.asg.metamodel.call.ReportCall;
import io.proleap.cobol.asg.metamodel.call.ReportDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.call.SectionCall;
import io.proleap.cobol.asg.metamodel.call.SpecialRegisterCall;
import io.proleap.cobol.asg.metamodel.call.TableCall;
import io.proleap.cobol.asg.metamodel.call.impl.CallDelegateImpl;
import io.proleap.cobol.asg.metamodel.call.impl.CommunicationDescriptionEntryCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.DataDescriptionEntryCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.EnvironmentCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.FileControlEntryCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.FunctionCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.IndexCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.MnemonicCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.ProcedureCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.ReportCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.ReportDescriptionEntryCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.SectionCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.SpecialRegisterCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.TableCallImpl;
import io.proleap.cobol.asg.metamodel.call.impl.UndefinedCallImpl;
import io.proleap.cobol.asg.metamodel.data.DataDivision;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntry.DataDescriptionEntryType;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryGroup;
import io.proleap.cobol.asg.metamodel.data.datadescription.Index;
import io.proleap.cobol.asg.metamodel.data.datadescription.OccursClause;
import io.proleap.cobol.asg.metamodel.data.datadescription.OccursIndexed;
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
import io.proleap.cobol.asg.metamodel.procedure.Section;
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
import io.proleap.cobol.asg.util.AsgStringUtils;

public class ProgramUnitElementImpl extends CompilationUnitElementImpl implements ProgramUnitElement {

	private static final String HEX_PREFIX = "X\"";

	private final static Logger LOG = LoggerFactory.getLogger(ProgramUnitElementImpl.class);

	protected ProgramUnit programUnit;

	public ProgramUnitElementImpl(final ProgramUnit programUnit, final ParserRuleContext ctx) {
		super(programUnit.getCompilationUnit(), ctx);

		this.programUnit = programUnit;
	}

	protected List<Call> addCallsThrough(final Call firstCall, final Call lastCall, final ParserRuleContext ctx) {
		final List<Call> result = new ArrayList<Call>();

		final String firstCallName = firstCall.getName();
		final String lastCallName = lastCall.getName();

		boolean inThrough = false;

		final List<Paragraph> paragraphs = programUnit.getProcedureDivision().getParagraphs();

		for (final Paragraph paragraph : paragraphs) {
			final String paragraphName = paragraph.getName();

			if (paragraphName.equals(lastCallName)) {
				break;
			} else if (paragraphName.equals(firstCallName)) {
				inThrough = true;
			} else if (inThrough) {
				final ProcedureCall call = new ProcedureCallImpl(paragraphName, paragraph, programUnit, ctx);
				result.add(call);

				linkProcedureCallWithParagraph(call, paragraph);
			}
		}

		return result;
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
			final Boolean value = AsgStringUtils.parseBoolean(ctx.getText());
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

	protected Call createCall(final FunctionCallContext ctx) {
		Call result = (FunctionCall) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final FunctionCall functionCall = new FunctionCallImpl(name, programUnit, ctx);

			result = functionCall;
			registerASGElement(result);
		}

		return result;
	}

	protected Call createCall(final IdentifierContext ctx) {
		final Call result;

		if (ctx.qualifiedDataName() != null) {
			final Call dataCall = createCall(ctx.qualifiedDataName());
			result = new CallDelegateImpl(dataCall, programUnit, ctx);

			registerASGElement(result);
		} else if (ctx.tableCall() != null) {
			final Call tableCall = createCall(ctx.tableCall());
			result = new CallDelegateImpl(tableCall, programUnit, ctx);

			registerASGElement(result);
		} else if (ctx.functionCall() != null) {
			final Call functionCall = createCall(ctx.functionCall());
			result = new CallDelegateImpl(functionCall, programUnit, ctx);

			registerASGElement(result);
		} else if (ctx.specialRegister() != null) {
			final Call specialRegisterCall = createCall(ctx.specialRegister());
			result = new CallDelegateImpl(specialRegisterCall, programUnit, ctx);

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
			} else if (ctx instanceof QualifiedDataNameFormat1Context) {
				result = createCall((QualifiedDataNameFormat1Context) ctx);
			} else if (ctx instanceof QualifiedDataNameFormat2Context) {
				result = createCall((QualifiedDataNameFormat2Context) ctx);
			} else if (ctx instanceof QualifiedDataNameFormat3Context) {
				result = createCall((QualifiedDataNameFormat3Context) ctx);
			} else if (ctx instanceof QualifiedDataNameFormat4Context) {
				result = createCall((QualifiedDataNameFormat4Context) ctx);
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
			final String paragraphName = determineName(ctx);

			if (ctx.inSection() == null) {
				final Section section = findSection(paragraphName);
				final Paragraph paragraph = findParagraph(paragraphName);

				if (paragraph != null) {
					result = createProcedureCall(paragraphName, paragraph, ctx);
				} else if (section != null) {
					result = createSectionCall(paragraphName, section, ctx);
				} else {
					result = createUndefinedCall(ctx);
				}
			} else {
				final String sectionName = determineName(ctx.inSection());
				final Section section = findSection(sectionName);

				if (section == null) {
					result = createUndefinedCall(ctx);
				} else {
					final Paragraph paragraph = section.getParagraph(paragraphName);

					if (paragraph == null) {
						result = createUndefinedCall(ctx);
					} else {
						result = createProcedureCall(paragraphName, paragraph, ctx);
					}
				}
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
		final Call result;

		if (ctx.qualifiedDataNameFormat1() != null) {
			final Call dataCall = createCall(ctx.qualifiedDataNameFormat1());
			result = new CallDelegateImpl(dataCall, programUnit, ctx);

			registerASGElement(result);
		} else if (ctx.qualifiedDataNameFormat2() != null) {
			final Call dataCall = createCall(ctx.qualifiedDataNameFormat2());
			result = new CallDelegateImpl(dataCall, programUnit, ctx);

			registerASGElement(result);
		} else if (ctx.qualifiedDataNameFormat3() != null) {
			final Call dataCall = createCall(ctx.qualifiedDataNameFormat3());
			result = new CallDelegateImpl(dataCall, programUnit, ctx);

			registerASGElement(result);
		} else if (ctx.qualifiedDataNameFormat4() != null) {
			final Call dataCall = createCall(ctx.qualifiedDataNameFormat4());
			result = new CallDelegateImpl(dataCall, programUnit, ctx);

			registerASGElement(result);
		} else {
			result = createDataDescriptionEntryCall(ctx);
		}

		return result;
	}

	protected Call createCall(final QualifiedDataNameFormat1Context ctx) {
		final Call result;
		final boolean isQualifiedData = !ctx.qualifiedInData().isEmpty();

		if (isQualifiedData) {
			final String name = determineName(ctx);
			final List<DataDescriptionEntry> candidateDataDescriptionEntries = findDataDescriptionEntries(name);
			final List<QualifiedInDataContext> parentInDataCtxs = new ArrayList<>(ctx.qualifiedInData());
			Collections.reverse(parentInDataCtxs);

			DataDescriptionEntry validDataDescriptionEntry = null;

			for (final DataDescriptionEntry candidateDataDescriptionEntry : candidateDataDescriptionEntries) {
				final boolean isCandidateQualified = isSameInData(candidateDataDescriptionEntry, parentInDataCtxs);

				if (isCandidateQualified) {
					validDataDescriptionEntry = candidateDataDescriptionEntry;
					break;
				}
			}

			if (validDataDescriptionEntry == null) {
				result = createUndefinedCall(ctx);
			} else {
				result = createDataDescriptionEntryCall(name, validDataDescriptionEntry, ctx);
			}
		} else if (ctx.dataName() != null) {
			result = createCall(ctx.dataName());
		} else if (ctx.conditionName() != null) {
			result = createCall(ctx.conditionName());
		} else {
			result = createDataDescriptionEntryCall(ctx);
		}

		return result;
	}

	protected Call createCall(final QualifiedDataNameFormat2Context ctx) {
		final String paragraphName = determineName(ctx);
		final String sectionName = determineName(ctx.inSection());
		final Section section = findSection(sectionName);

		final Call result;

		if (section == null) {
			result = createUndefinedCall(ctx);
		} else {
			final Paragraph paragraph = section.getParagraph(paragraphName);

			if (paragraph == null) {
				result = createUndefinedCall(ctx);
			} else {
				result = new ProcedureCallImpl(paragraphName, paragraph, programUnit, ctx);
			}
		}

		return result;
	}

	protected Call createCall(final QualifiedDataNameFormat3Context ctx) {
		final Call result = createCall(ctx.textName());
		return result;
	}

	protected Call createCall(final QualifiedDataNameFormat4Context ctx) {
		return createUndefinedCall(ctx);
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
			final SpecialRegisterCall.SpecialRegisterType type;

			if (ctx.ADDRESS() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.ADDRESS_OF;
			} else if (ctx.DATE() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.DATE;
			} else if (ctx.DAY() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.DAY;
			} else if (ctx.DAY_OF_WEEK() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.DAY_OF_WEEK;
			} else if (ctx.DEBUG_CONTENTS() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.DEBUG_CONTENTS;
			} else if (ctx.DEBUG_ITEM() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.DEBUG_ITEM;
			} else if (ctx.DEBUG_LINE() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.DEBUG_LINE;
			} else if (ctx.DEBUG_NAME() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.DEBUG_NAME;
			} else if (ctx.DEBUG_SUB_1() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.DEBUG_SUB_1;
			} else if (ctx.DEBUG_SUB_2() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.DEBUG_SUB_2;
			} else if (ctx.DEBUG_SUB_3() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.DEBUG_SUB_3;
			} else if (ctx.LENGTH() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.LENGTH_OF;
			} else if (ctx.LINAGE_COUNTER() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.LINAGE_COUNTER;
			} else if (ctx.LINE_COUNTER() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.LINE_COUNTER;
			} else if (ctx.PAGE_COUNTER() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.PAGE_COUNTER;
			} else if (ctx.RETURN_CODE() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.RETURN_CODE;
			} else if (ctx.SHIFT_IN() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.SHIFT_IN;
			} else if (ctx.SHIFT_OUT() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.SHIFT_OUT;
			} else if (ctx.SORT_CONTROL() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.SORT_CONTROL;
			} else if (ctx.SORT_CORE_SIZE() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.SORT_CORE_SIZE;
			} else if (ctx.SORT_FILE_SIZE() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.SORT_FILE_SIZE;
			} else if (ctx.SORT_MESSAGE() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.SORT_MESSAGE;
			} else if (ctx.SORT_MODE_SIZE() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.SORT_MODE_SIZE;
			} else if (ctx.SORT_RETURN() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.SORT_RETURN;
			} else if (ctx.TALLY() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.TALLY;
			} else if (ctx.TIME() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.TIME;
			} else if (ctx.WHEN_COMPILED() != null) {
				type = SpecialRegisterCall.SpecialRegisterType.WHEN_COMPILED;
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
			final List<DataDescriptionEntry> dataDescriptionEntries = findDataDescriptionEntries(name);

			if (dataDescriptionEntries.isEmpty()) {
				result = createUndefinedCall(ctx);
			} else {
				final DataDescriptionEntry dataDescriptionEntry = dataDescriptionEntries.get(0);
				final TableCall tableCall = new TableCallImpl(name, dataDescriptionEntry, programUnit, ctx);
				linkDataDescriptionEntryCallWithDataDescriptionEntry(tableCall, dataDescriptionEntry);

				for (final SubscriptContext subscriptContext : ctx.subscript()) {
					tableCall.addSubscript(subscriptContext);
				}

				result = tableCall;
				registerASGElement(result);
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

	protected CallValueStmt createCallValueStmt(final TableCallContext ctx) {
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

			if (index != null) {
				result = createIndexCall(ctx);
			} else {
				final List<DataDescriptionEntry> dataDescriptionEntries = findDataDescriptionEntries(name);

				if (dataDescriptionEntries.isEmpty()) {
					result = createUndefinedCall(ctx);
				} else {
					final DataDescriptionEntry dataDescriptionEntry = dataDescriptionEntries.get(0);
					result = createDataDescriptionEntryCall(name, dataDescriptionEntry, ctx);
				}
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
			final FigurativeConstant.FigurativeConstantType type;

			if (ctx.ALL() != null) {
				type = FigurativeConstant.FigurativeConstantType.ALL;
			} else if (ctx.HIGH_VALUE() != null) {
				type = FigurativeConstant.FigurativeConstantType.HIGH_VALUE;
			} else if (ctx.HIGH_VALUES() != null) {
				type = FigurativeConstant.FigurativeConstantType.HIGH_VALUES;
			} else if (ctx.LOW_VALUE() != null) {
				type = FigurativeConstant.FigurativeConstantType.LOW_VALUE;
			} else if (ctx.LOW_VALUES() != null) {
				type = FigurativeConstant.FigurativeConstantType.LOW_VALUES;
			} else if (ctx.NULL() != null) {
				type = FigurativeConstant.FigurativeConstantType.NULL;
			} else if (ctx.NULLS() != null) {
				type = FigurativeConstant.FigurativeConstantType.NULLS;
			} else if (ctx.QUOTE() != null) {
				type = FigurativeConstant.FigurativeConstantType.QUOTE;
			} else if (ctx.QUOTES() != null) {
				type = FigurativeConstant.FigurativeConstantType.QUOTES;
			} else if (ctx.SPACE() != null) {
				type = FigurativeConstant.FigurativeConstantType.SPACE;
			} else if (ctx.SPACES() != null) {
				type = FigurativeConstant.FigurativeConstantType.SPACES;
			} else if (ctx.ZERO() != null) {
				type = FigurativeConstant.FigurativeConstantType.ZERO;
			} else if (ctx.ZEROS() != null) {
				type = FigurativeConstant.FigurativeConstantType.ZEROS;
			} else if (ctx.ZEROES() != null) {
				type = FigurativeConstant.FigurativeConstantType.ZEROES;
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
			final BigDecimal value = AsgStringUtils.parseBigDecimal(ctx.getText());
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

			final Literal.LiteralType type;

			if (ctx.NONNUMERICLITERAL() != null) {
				final String text = ctx.getText();
				final boolean isHex = text.startsWith(HEX_PREFIX);
				final String nonNumericLiteral = isHex ? text : text.substring(1, text.length() - 1);
				result.setNonNumericLiteral(nonNumericLiteral);

				type = Literal.LiteralType.NON_NUMERIC;
			} else if (ctx.numericLiteral() != null) {
				final NumericLiteral numericLiteral = createNumericLiteral(ctx.numericLiteral());
				result.setNumericLiteral(numericLiteral);

				type = Literal.LiteralType.NUMERIC;
			} else if (ctx.booleanLiteral() != null) {
				final BooleanLiteral booleanLiteral = createBooleanLiteral(ctx.booleanLiteral());
				result.setBooleanLiteral(booleanLiteral);

				type = Literal.LiteralType.BOOLEAN;
			} else if (ctx.figurativeConstant() != null) {
				final FigurativeConstant figurativeConstant = createFigurativeConstant(ctx.figurativeConstant());
				result.setFigurativeConstant(figurativeConstant);

				type = Literal.LiteralType.FIGURATIVE_CONSTANT;
			} else if (ctx.cicsDfhRespLiteral() != null) {
				type = Literal.LiteralType.CICS_DFH_RESP;
			} else if (ctx.cicsDfhValueLiteral() != null) {
				type = Literal.LiteralType.CICS_DFH_VALUE;
			} else {
				type = null;
			}

			result.setLiteralType(type);

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

			final NumericLiteral.NumericLiteralType type;

			if (ctx.integerLiteral() != null) {
				result.setValue(AsgStringUtils.parseBigDecimal(ctx.getText()));
				type = NumericLiteral.NumericLiteralType.INTEGER;
			} else if (ctx.ZERO() != null) {
				result.setValue(BigDecimal.ZERO);
				type = NumericLiteral.NumericLiteralType.INTEGER;
			} else if (ctx.NUMERICLITERAL() != null) {
				result.setValue(AsgStringUtils.parseBigDecimal(ctx.NUMERICLITERAL().getText()));
				type = NumericLiteralType.FLOAT;
			} else {
				type = null;
			}

			result.setNumericLiteralType(type);

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

			final RelationConditionValueStmt.RelationConditionType type;

			if (ctx.relationSignCondition() != null) {
				result.addRelationSignCondition(ctx.relationSignCondition());
				type = RelationConditionValueStmt.RelationConditionType.SIGN;
			} else if (ctx.relationArithmeticComparison() != null) {
				result.addRelationArithmeticComparison(ctx.relationArithmeticComparison());
				type = RelationConditionValueStmt.RelationConditionType.ARITHMETIC;
			} else if (ctx.relationCombinedComparison() != null) {
				result.addRelationCombinedComparison(ctx.relationCombinedComparison());
				type = RelationConditionValueStmt.RelationConditionType.COMBINED;
			} else {
				type = null;
			}

			result.setRelationConditionType(type);

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

	protected Call createSectionCall(final String name, final Section section, final ProcedureNameContext ctx) {
		SectionCall result = (SectionCall) getASGElement(ctx);

		if (result == null) {
			result = new SectionCallImpl(name, section, programUnit, ctx);
			linkSectionCallWithSection(result, section);

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
			} else if (ctx instanceof ConditionContext) {
				result = createConditionValueStmt((ConditionContext) ctx);
			} else if (ctx instanceof DataDescNameContext) {
				result = createCallValueStmt((DataDescNameContext) ctx);
			} else if (ctx instanceof DataNameContext) {
				result = createCallValueStmt((DataNameContext) ctx);
			} else if (ctx instanceof FileNameContext) {
				result = createCallValueStmt((FileNameContext) ctx);
			} else if (ctx instanceof IndexNameContext) {
				result = createCallValueStmt((IndexNameContext) ctx);
			} else if (ctx instanceof IntegerLiteralContext) {
				result = createIntegerLiteralValueStmt((IntegerLiteralContext) ctx);
			} else if (ctx instanceof LibraryNameContext) {
				result = createCallValueStmt((LibraryNameContext) ctx);
			} else if (ctx instanceof LiteralContext) {
				result = createLiteralValueStmt((LiteralContext) ctx);
			} else if (ctx instanceof LocalNameContext) {
				result = createCallValueStmt((LocalNameContext) ctx);
			} else if (ctx instanceof ProgramNameContext) {
				result = createCallValueStmt((ProgramNameContext) ctx);
			} else if (ctx instanceof QualifiedDataNameContext) {
				result = createCallValueStmt((QualifiedDataNameContext) ctx);
			} else if (ctx instanceof RelationConditionContext) {
				result = createRelationConditionValueStmt((RelationConditionContext) ctx);
			} else if (ctx instanceof ReportNameContext) {
				result = createCallValueStmt((ReportNameContext) ctx);
			} else if (ctx instanceof SystemNameContext) {
				result = createCallValueStmt((SystemNameContext) ctx);
			} else if (ctx instanceof TableCallContext) {
				result = createCallValueStmt((TableCallContext) ctx);
			} else {
				LOG.debug("unknown value stmt at {}", ctx);
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

	protected List<DataDescriptionEntry> findDataDescriptionEntries(final String name) {
		final WorkingStorageSection workingStorageSection = findWorkingStorageSection();
		final FileSection fileSection = findFileSection();
		final CommunicationSection communicationSection = findCommunicationSection();
		final LinkageSection linkageSection = findLinkageSection();
		final LocalStorageSection localStorageSection = findLocalStorageSection();

		final List<DataDescriptionEntry> result = new ArrayList<DataDescriptionEntry>();

		if (workingStorageSection != null) {
			result.addAll(workingStorageSection.getDataDescriptionEntries(name));
		}

		if (fileSection != null) {
			for (final FileDescriptionEntry fileDescriptionEntry : fileSection.getFileDescriptionEntries()) {
				result.addAll(fileDescriptionEntry.getDataDescriptionEntries(name));
			}
		}

		if (communicationSection != null) {
			result.addAll(communicationSection.getDataDescriptionEntries(name));
		}

		if (linkageSection != null) {
			result.addAll(linkageSection.getDataDescriptionEntries(name));
		}

		if (localStorageSection != null) {
			result.addAll(localStorageSection.getDataDescriptionEntries(name));
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
				if (DataDescriptionEntryType.GROUP.equals(dataDescriptionEntry.getDataDescriptionEntryType())) {
					final DataDescriptionEntryGroup dataDescriptionEntryGroup = (DataDescriptionEntryGroup) dataDescriptionEntry;

					for (final OccursClause occursClause : dataDescriptionEntryGroup.getOccursClauses()) {
						final OccursIndexed occursIndexed = occursClause.getOccursIndexed();

						if (occursIndexed != null) {
							final Index index = occursIndexed.getIndex(name);

							if (index != null) {
								return index;
							}
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

	protected Paragraph findParagraph(final String name) {
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

	protected Section findSection(final String name) {
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		final Section result;

		if (procedureDivision == null) {
			result = null;
		} else {
			result = procedureDivision.getSection(name);
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

	protected String getSymbol(final String name) {
		return StringUtils.isEmpty(name) ? name : name.toUpperCase();
	}

	private boolean isSameInData(final DataDescriptionEntry candidateDataDescriptionEntry,
			final List<QualifiedInDataContext> parentInDataCtxs) {
		DataDescriptionEntryGroup currentParent = candidateDataDescriptionEntry.getParentDataDescriptionEntryGroup();
		boolean result = true;

		for (final QualifiedInDataContext parentCtx : parentInDataCtxs) {
			if (currentParent == null) {
				result = false;
				break;
			} else {
				final String currentParentSymbol = getSymbol(currentParent.getName());
				final String parentInDataCtxSymbol = getSymbol(determineName(parentCtx));

				if (StringUtils.isEmpty(currentParentSymbol) || !currentParentSymbol.equals(parentInDataCtxSymbol)) {
					result = false;
					break;
				}

				currentParent = currentParent.getParentDataDescriptionEntryGroup();
			}
		}

		return result;
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

	protected void linkSectionCallWithSection(final SectionCall call, final Section section) {
		section.addCall(call);
	}

	protected void registerASGElement(final ASGElement asgElement) {
		programUnit.getProgram().getASGElementRegistry().addASGElement(asgElement);
	}
}
