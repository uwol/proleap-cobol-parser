/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.impl;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.AlphabetNameContext;
import io.proleap.cobol.Cobol85Parser.ArithmeticExpressionContext;
import io.proleap.cobol.Cobol85Parser.AssignmentNameContext;
import io.proleap.cobol.Cobol85Parser.CdNameContext;
import io.proleap.cobol.Cobol85Parser.ClassNameContext;
import io.proleap.cobol.Cobol85Parser.CobolWordContext;
import io.proleap.cobol.Cobol85Parser.DataDescNameContext;
import io.proleap.cobol.Cobol85Parser.DataNameContext;
import io.proleap.cobol.Cobol85Parser.EnvironmentNameContext;
import io.proleap.cobol.Cobol85Parser.FileNameContext;
import io.proleap.cobol.Cobol85Parser.IdentifierContext;
import io.proleap.cobol.Cobol85Parser.IndexNameContext;
import io.proleap.cobol.Cobol85Parser.IntegerLiteralContext;
import io.proleap.cobol.Cobol85Parser.LibraryNameContext;
import io.proleap.cobol.Cobol85Parser.LiteralContext;
import io.proleap.cobol.Cobol85Parser.LocalNameContext;
import io.proleap.cobol.Cobol85Parser.MnemonicNameContext;
import io.proleap.cobol.Cobol85Parser.OtherKeywordContext;
import io.proleap.cobol.Cobol85Parser.ProcedureNameContext;
import io.proleap.cobol.Cobol85Parser.ProgramNameContext;
import io.proleap.cobol.Cobol85Parser.QualifiedDataNameContext;
import io.proleap.cobol.Cobol85Parser.RecordNameContext;
import io.proleap.cobol.Cobol85Parser.ReportNameContext;
import io.proleap.cobol.Cobol85Parser.SystemNameContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.Literal;
import io.proleap.cobol.parser.metamodel.MnemonicName;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.ProgramUnitElement;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.call.CommunicationDescriptionEntryCall;
import io.proleap.cobol.parser.metamodel.call.DataDescriptionEntryCall;
import io.proleap.cobol.parser.metamodel.call.ProcedureCall;
import io.proleap.cobol.parser.metamodel.call.impl.CommunicationDescriptionEntryCallImpl;
import io.proleap.cobol.parser.metamodel.call.impl.DataDescriptionEntryCallImpl;
import io.proleap.cobol.parser.metamodel.call.impl.ProcedureCallImpl;
import io.proleap.cobol.parser.metamodel.call.impl.UndefinedCallImpl;
import io.proleap.cobol.parser.metamodel.data.DataDivision;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.communication.CommunicationSection;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.workingstorage.WorkingStorageSection;
import io.proleap.cobol.parser.metamodel.procedure.Paragraph;
import io.proleap.cobol.parser.metamodel.valuestmt.ArithmeticValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.IntegerLiteralValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.TerminalValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.impl.ArithmeticValueStmtImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.impl.CallValueStmtImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.impl.IntegerLiteralValueStmtImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.impl.LiteralValueStmtImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.impl.TerminalValueStmtImpl;
import io.proleap.cobol.parser.util.StringUtils;

public class ProgramUnitElementImpl extends CompilationUnitElementImpl implements ProgramUnitElement {

	private final static Logger LOG = LogManager.getLogger(ProgramUnitElementImpl.class);

	protected ProgramUnit programUnit;

	public ProgramUnitElementImpl(final ProgramUnit programUnit, final ParseTree ctx) {
		super(ctx);

		this.programUnit = programUnit;
	}

	protected ArithmeticValueStmt createArithmeticValueStmt(final ArithmeticExpressionContext ctx) {
		final ArithmeticValueStmt result = new ArithmeticValueStmtImpl(programUnit, ctx);
		return result;
	}

	protected Call createCall(final AlphabetNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
		}

		return result;
	}

	protected Call createCall(final AssignmentNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
		}

		return result;
	}

	protected Call createCall(final CdNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final DataDivision dataDivision = programUnit.getDataDivision();

			if (dataDivision == null) {
				result = new UndefinedCallImpl(name, programUnit, ctx);
			} else {
				final CommunicationSection communicationSection = dataDivision.getCommunicationSection();

				if (communicationSection == null) {
					result = new UndefinedCallImpl(name, programUnit, ctx);
				} else {
					final CommunicationDescriptionEntry communicationDescriptionEntry = communicationSection
							.getCommunicationDescriptionEntry(name);

					if (communicationDescriptionEntry == null) {
						LOG.warn("call to unknown element {}", name);
						result = new UndefinedCallImpl(name, programUnit, ctx);
					} else {
						final CommunicationDescriptionEntryCall call = new CommunicationDescriptionEntryCallImpl(name,
								communicationDescriptionEntry, programUnit, ctx);

						linkCommunicationDescriptionEntryCallWithCommunicationDescriptionEntry(call,
								communicationDescriptionEntry);

						result = call;
					}
				}
			}
		}

		return result;
	}

	protected Call createCall(final ClassNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
		}

		return result;
	}

	protected Call createCall(final CobolWordContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
		}

		return result;
	}

	protected Call createCall(final DataDescNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
		}

		return result;
	}

	protected Call createCall(final DataNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
		}

		return result;
	}

	protected Call createCall(final EnvironmentNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
		}

		return result;
	}

	protected Call createCall(final FileNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
		}

		return result;
	}

	protected Call createCall(final IdentifierContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			final DataDivision dataDivision = programUnit.getDataDivision();

			if (dataDivision == null) {
				result = new UndefinedCallImpl(name, programUnit, ctx);
			} else {
				final WorkingStorageSection workingStorageSection = dataDivision.getWorkingStorageSection();

				if (workingStorageSection == null) {
					result = new UndefinedCallImpl(name, programUnit, ctx);
				} else {
					final DataDescriptionEntry dataDescriptionEntry = workingStorageSection
							.getDataDescriptionEntry(name);

					if (dataDescriptionEntry == null) {
						LOG.warn("call to unknown element {}", name);
						result = new UndefinedCallImpl(name, programUnit, ctx);
					} else {
						final DataDescriptionEntryCall call = new DataDescriptionEntryCallImpl(name,
								dataDescriptionEntry, programUnit, ctx);

						linkDataDescriptionEntryCallWithDataDescriptionEntry(call, dataDescriptionEntry);

						result = call;
					}
				}
			}
		}

		return result;
	}

	protected Call createCall(final IndexNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
		}

		return result;
	}

	protected Call createCall(final IntegerLiteralContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
		}

		return result;
	}

	protected Call createCall(final LibraryNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
		}

		return result;
	}

	protected Call createCall(final LiteralContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
		}

		return result;
	}

	protected Call createCall(final LocalNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
		}

		return result;
	}

	protected Call createCall(final MnemonicNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
		}

		return result;
	}

	protected Call createCall(final OtherKeywordContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
		}

		return result;
	}

	protected Call createCall(final ParseTree... ctxs) {
		Call result = null;

		for (final ParseTree ctx : ctxs) {
			if (result != null) {
				break;
			}

			if (ctx == null) {
				continue;
			}

			if (ctx instanceof IdentifierContext) {
				result = createCall((IdentifierContext) ctx);
			} else if (ctx instanceof LiteralContext) {
				result = createCall((LiteralContext) ctx);
			} else if (ctx instanceof IntegerLiteralContext) {
				result = createCall((IntegerLiteralContext) ctx);
			} else if (ctx instanceof MnemonicNameContext) {
				result = createCall((MnemonicNameContext) ctx);
			} else if (ctx instanceof ProcedureNameContext) {
				result = createCall((ProcedureNameContext) ctx);
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
			} else if (ctx instanceof OtherKeywordContext) {
				result = createCall((OtherKeywordContext) ctx);
			} else if (ctx instanceof ProgramNameContext) {
				result = createCall((ProgramNameContext) ctx);
			} else if (ctx instanceof QualifiedDataNameContext) {
				result = createCall((QualifiedDataNameContext) ctx);
			} else if (ctx instanceof RecordNameContext) {
				result = createCall((RecordNameContext) ctx);
			} else if (ctx instanceof ReportNameContext) {
				result = createCall((ReportNameContext) ctx);
			} else if (ctx instanceof SystemNameContext) {
				result = createCall((SystemNameContext) ctx);
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
			final Paragraph paragraph = programUnit.getProcedureDivision().getParagraph(name);

			if (paragraph == null) {
				result = new UndefinedCallImpl(name, programUnit, ctx);
			} else {
				final ProcedureCall call = new ProcedureCallImpl(name, paragraph, programUnit, ctx);

				linkProcedureCallWithParagraph(call, paragraph);

				result = call;
			}

			registerASGElement(result);
		}

		return result;
	}

	protected Call createCall(final ProgramNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
		}

		return result;
	}

	protected Call createCall(final QualifiedDataNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
		}

		return result;
	}

	protected Call createCall(final RecordNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
		}

		return result;
	}

	protected Call createCall(final ReportNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
		}

		return result;
	}

	protected Call createCall(final SystemNameContext ctx) {
		Call result = (Call) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new UndefinedCallImpl(name, programUnit, ctx);
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

	protected CallValueStmt createCallValueStmt(final LocalNameContext ctx) {
		final Call delegatedCall = createCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, ctx);
		return result;
	}

	protected CallValueStmt createCallValueStmt(final MnemonicNameContext ctx) {
		final Call delegatedCall = createCall(ctx);
		final CallValueStmt result = new CallValueStmtImpl(delegatedCall, programUnit, ctx);
		return result;
	}

	protected CallValueStmt createCallValueStmt(final ProcedureNameContext ctx) {
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
			final String value = ctx.getText();
			result = new LiteralImpl(value, programUnit, ctx);

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

	protected TerminalValueStmt createTerminalValueStmt(final TerminalNode ctx) {
		final TerminalValueStmt result = new TerminalValueStmtImpl(programUnit, ctx);
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

	protected void linkProcedureCallWithParagraph(final ProcedureCall call, final Paragraph paragraph) {
		paragraph.addCall(call);
	}

}
