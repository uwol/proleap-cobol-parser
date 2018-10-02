/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.file.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.proleap.cobol.CobolParser.BlockContainsClauseContext;
import io.proleap.cobol.CobolParser.BlockContainsToContext;
import io.proleap.cobol.CobolParser.CodeSetClauseContext;
import io.proleap.cobol.CobolParser.DataNameContext;
import io.proleap.cobol.CobolParser.DataRecordsClauseContext;
import io.proleap.cobol.CobolParser.ExternalClauseContext;
import io.proleap.cobol.CobolParser.FileDescriptionEntryContext;
import io.proleap.cobol.CobolParser.GlobalClauseContext;
import io.proleap.cobol.CobolParser.LabelRecordsClauseContext;
import io.proleap.cobol.CobolParser.LinageAtContext;
import io.proleap.cobol.CobolParser.LinageClauseContext;
import io.proleap.cobol.CobolParser.LinageFootingAtContext;
import io.proleap.cobol.CobolParser.LinageLinesAtBottomContext;
import io.proleap.cobol.CobolParser.LinageLinesAtTopContext;
import io.proleap.cobol.CobolParser.RecordContainsClauseContext;
import io.proleap.cobol.CobolParser.RecordContainsClauseFormat1Context;
import io.proleap.cobol.CobolParser.RecordContainsClauseFormat2Context;
import io.proleap.cobol.CobolParser.RecordContainsClauseFormat3Context;
import io.proleap.cobol.CobolParser.RecordContainsToContext;
import io.proleap.cobol.CobolParser.ReportClauseContext;
import io.proleap.cobol.CobolParser.ReportNameContext;
import io.proleap.cobol.CobolParser.ValueOfClauseContext;
import io.proleap.cobol.CobolParser.ValuePairContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.datadescription.impl.DataDescriptionEntryContainerImpl;
import io.proleap.cobol.asg.metamodel.data.file.BlockContainsClause;
import io.proleap.cobol.asg.metamodel.data.file.CodeSetClause;
import io.proleap.cobol.asg.metamodel.data.file.DataRecordsClause;
import io.proleap.cobol.asg.metamodel.data.file.ExternalClause;
import io.proleap.cobol.asg.metamodel.data.file.FileDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.file.GlobalClause;
import io.proleap.cobol.asg.metamodel.data.file.LabelRecordsClause;
import io.proleap.cobol.asg.metamodel.data.file.LinageClause;
import io.proleap.cobol.asg.metamodel.data.file.RecordContainsClause;
import io.proleap.cobol.asg.metamodel.data.file.ReportClause;
import io.proleap.cobol.asg.metamodel.data.file.ValueOfClause;
import io.proleap.cobol.asg.metamodel.data.file.ValueOfNameValuePair;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.FileControlEntry;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public class FileDescriptionEntryImpl extends DataDescriptionEntryContainerImpl implements FileDescriptionEntry {

	private final static Logger LOG = LoggerFactory.getLogger(FileDescriptionEntryImpl.class);

	protected BlockContainsClause blockContainsClause;

	protected CodeSetClause codeSetClause;

	protected final DataDescriptionEntryContainerType containerType = DataDescriptionEntryContainerType.FILE_DESCRIPTION_ENTRY;

	protected final FileDescriptionEntryContext ctx;

	protected DataRecordsClause dataRecordsClause;

	protected ExternalClause externalClause;

	protected Call fileCall;

	protected FileControlEntry fileControlEntry;

	protected GlobalClause globalClause;

	protected LabelRecordsClause labelRecordsClause;

	protected LinageClause linageClause;

	protected final String name;

	protected RecordContainsClause recordContainsClause;

	protected ReportClause reportClause;

	protected ValueOfClause valueOfClause;

	public FileDescriptionEntryImpl(final String name, final ProgramUnit programUnit,
			final FileDescriptionEntryContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
		this.name = name;
	}

	@Override
	public BlockContainsClause addBlockContainsClause(final BlockContainsClauseContext ctx) {
		BlockContainsClause result = (BlockContainsClause) getASGElement(ctx);

		if (result == null) {
			result = new BlockContainsClauseImpl(programUnit, ctx);

			/*
			 * from
			 */
			if (ctx.integerLiteral() != null) {
				final IntegerLiteral from = createIntegerLiteral(ctx.integerLiteral());
				result.setFrom(from);
			}

			/*
			 * to
			 */
			if (ctx.blockContainsTo() != null) {
				final BlockContainsToContext blockContainsTo = ctx.blockContainsTo();

				if (blockContainsTo.integerLiteral() != null) {
					final IntegerLiteral to = createIntegerLiteral(blockContainsTo.integerLiteral());
					result.setTo(to);
				}
			}

			/*
			 * unit
			 */
			final BlockContainsClause.Unit unit;

			if (ctx.RECORDS() != null) {
				unit = BlockContainsClause.Unit.RECORDS;
			} else if (ctx.CHARACTERS() != null) {
				unit = BlockContainsClause.Unit.CHARACTERS;
			} else {
				unit = null;
			}

			result.setUnit(unit);

			blockContainsClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CodeSetClause addCodeSetClause(final CodeSetClauseContext ctx) {
		CodeSetClause result = (CodeSetClause) getASGElement(ctx);

		if (result == null) {
			result = new CodeSetClauseImpl(programUnit, ctx);

			final String alphabetName = determineName(ctx.alphabetName());
			result.setAlhpabetName(alphabetName);

			codeSetClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DataRecordsClause addDataRecordsClause(final DataRecordsClauseContext ctx) {
		DataRecordsClause result = (DataRecordsClause) getASGElement(ctx);

		if (result == null) {
			result = new DataRecordsClauseImpl(programUnit, ctx);

			/*
			 * data names
			 */
			for (final DataNameContext dataNameContext : ctx.dataName()) {
				final Call dataCall = createCall(dataNameContext);
				result.addDataCall(dataCall);
			}

			dataRecordsClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ExternalClause addExternalClause(final ExternalClauseContext ctx) {
		ExternalClause result = (ExternalClause) getASGElement(ctx);

		if (result == null) {
			result = new ExternalClauseImpl(programUnit, ctx);

			result.setExternal(true);

			externalClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public GlobalClause addGlobalClause(final GlobalClauseContext ctx) {
		GlobalClause result = (GlobalClause) getASGElement(ctx);

		if (result == null) {
			result = new GlobalClauseImpl(programUnit, ctx);

			result.setGlobal(true);

			globalClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public LabelRecordsClause addLabelRecordsClause(final LabelRecordsClauseContext ctx) {
		LabelRecordsClause result = (LabelRecordsClause) getASGElement(ctx);

		if (result == null) {
			result = new LabelRecordsClauseImpl(programUnit, ctx);

			/*
			 * type
			 */
			final LabelRecordsClause.LabelRecordsClauseType type;

			if (ctx.OMITTED() != null) {
				type = LabelRecordsClause.LabelRecordsClauseType.OMITTED;
			} else if (ctx.STANDARD() != null) {
				type = LabelRecordsClause.LabelRecordsClauseType.STANDARD;
			} else if (!ctx.dataName().isEmpty()) {
				type = LabelRecordsClause.LabelRecordsClauseType.DATA_NAMES;
			} else {
				type = null;
			}

			result.setLabelRecordsClauseType(type);

			/*
			 * data names
			 */
			for (final DataNameContext dataNameContext : ctx.dataName()) {
				final Call dataCall = createCall(dataNameContext);
				result.addDataCall(dataCall);
			}

			labelRecordsClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public LinageClause addLinageClause(final LinageClauseContext ctx) {
		LinageClause result = (LinageClause) getASGElement(ctx);

		if (result == null) {
			result = new LinageClauseImpl(programUnit, ctx);

			/*
			 * number of lines
			 */
			final ValueStmt numberOfLinesValueStmt = createValueStmt(ctx.dataName(), ctx.integerLiteral());
			result.setNumberOfLinesValueStmt(numberOfLinesValueStmt);

			/*
			 * linage at
			 */
			for (final LinageAtContext linageAtContext : ctx.linageAt()) {
				/*
				 * footing at
				 */
				if (linageAtContext.linageFootingAt() != null) {
					final LinageFootingAtContext linageFootingAtContext = linageAtContext.linageFootingAt();
					final ValueStmt footingAtValueStmt;

					if (linageFootingAtContext.dataName() != null) {
						footingAtValueStmt = createCallValueStmt(linageFootingAtContext.dataName());
					} else if (linageFootingAtContext.integerLiteral() != null) {
						footingAtValueStmt = createIntegerLiteralValueStmt(linageFootingAtContext.integerLiteral());
					} else {
						LOG.warn("unknown linage footing at {}", linageFootingAtContext);
						footingAtValueStmt = null;
					}

					result.setFootingAtValueStmt(footingAtValueStmt);
				}

				/*
				 * lines at top
				 */
				if (linageAtContext.linageLinesAtTop() != null) {
					final LinageLinesAtTopContext linageLinesAtTopContext = linageAtContext.linageLinesAtTop();
					final ValueStmt LinesAtTopValueStmt;

					if (linageLinesAtTopContext.dataName() != null) {
						LinesAtTopValueStmt = createCallValueStmt(linageLinesAtTopContext.dataName());
					} else if (linageLinesAtTopContext.integerLiteral() != null) {
						LinesAtTopValueStmt = createIntegerLiteralValueStmt(linageLinesAtTopContext.integerLiteral());
					} else {
						LOG.warn("unknown lines at top at {}", linageLinesAtTopContext);
						LinesAtTopValueStmt = null;
					}

					result.setLinesAtTopValueStmt(LinesAtTopValueStmt);
				}

				/*
				 * lines at bottom
				 */
				if (linageAtContext.linageLinesAtBottom() != null) {
					final LinageLinesAtBottomContext linageLinesAtBottomContext = linageAtContext.linageLinesAtBottom();
					final ValueStmt LinesAtBottomValueStmt;

					if (linageLinesAtBottomContext.dataName() != null) {
						LinesAtBottomValueStmt = createCallValueStmt(linageLinesAtBottomContext.dataName());
					} else if (linageLinesAtBottomContext.integerLiteral() != null) {
						LinesAtBottomValueStmt = createIntegerLiteralValueStmt(
								linageLinesAtBottomContext.integerLiteral());
					} else {
						LOG.warn("unknown lines at bottom at {}", linageLinesAtBottomContext);
						LinesAtBottomValueStmt = null;
					}

					result.setLinesAtBottomValueStmt(LinesAtBottomValueStmt);
				}
			}

			linageClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public RecordContainsClause addRecordContainsClause(final RecordContainsClauseContext ctx) {
		RecordContainsClause result = (RecordContainsClause) getASGElement(ctx);

		if (result == null) {
			result = new RecordContainsClauseImpl(programUnit, ctx);

			if (ctx.recordContainsClauseFormat1() != null) {
				final RecordContainsClauseFormat1Context recordContainsClauseFormat1 = ctx
						.recordContainsClauseFormat1();

				if (recordContainsClauseFormat1.integerLiteral() != null) {
					final IntegerLiteral from = createIntegerLiteral(recordContainsClauseFormat1.integerLiteral());
					result.setFrom(from);
				}
			} else if (ctx.recordContainsClauseFormat2() != null) {
				final RecordContainsClauseFormat2Context recordContainsClauseFormat2 = ctx
						.recordContainsClauseFormat2();

				/*
				 * from
				 */
				if (recordContainsClauseFormat2.integerLiteral() != null) {
					final IntegerLiteral from = createIntegerLiteral(recordContainsClauseFormat2.integerLiteral());
					result.setFrom(from);
				}

				/*
				 * to
				 */
				if (recordContainsClauseFormat2.recordContainsTo() != null) {
					final RecordContainsToContext recordContainsTo = recordContainsClauseFormat2.recordContainsTo();

					if (recordContainsTo.integerLiteral() != null) {
						final IntegerLiteral to = createIntegerLiteral(recordContainsTo.integerLiteral());
						result.setTo(to);
					}
				}

				/*
				 * varying
				 */
				if (recordContainsClauseFormat2.VARYING() != null) {
					result.setVarying(true);
				}

				/*
				 * depending on
				 */
				if (recordContainsClauseFormat2.qualifiedDataName() != null) {
					final Call dependingOnCall = createCall(recordContainsClauseFormat2.qualifiedDataName());
					result.setDependingOnCall(dependingOnCall);
				}
			} else if (ctx.recordContainsClauseFormat3() != null) {
				final RecordContainsClauseFormat3Context recordContainsClauseFormat3 = ctx
						.recordContainsClauseFormat3();

				/*
				 * from
				 */
				if (recordContainsClauseFormat3.integerLiteral() != null) {
					final IntegerLiteral from = createIntegerLiteral(recordContainsClauseFormat3.integerLiteral());
					result.setFrom(from);
				}

				/*
				 * to
				 */
				if (recordContainsClauseFormat3.recordContainsTo() != null) {
					final RecordContainsToContext recordContainsTo = recordContainsClauseFormat3.recordContainsTo();

					if (recordContainsTo.integerLiteral() != null) {
						final IntegerLiteral to = createIntegerLiteral(recordContainsTo.integerLiteral());
						result.setTo(to);
					}
				}
			}

			recordContainsClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ReportClause addReportClause(final ReportClauseContext ctx) {
		ReportClause result = (ReportClause) getASGElement(ctx);

		if (result == null) {
			result = new ReportClauseImpl(programUnit, ctx);

			/*
			 * report names
			 */
			for (final ReportNameContext reportNameContext : ctx.reportName()) {
				final Call reportCall = createCall(reportNameContext);
				result.addReportCall(reportCall);
			}

			reportClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueOfClause addValueOfClause(final ValueOfClauseContext ctx) {
		ValueOfClause result = (ValueOfClause) getASGElement(ctx);

		if (result == null) {
			result = new ValueOfClauseImpl(programUnit, ctx);

			for (final ValuePairContext valuePairContext : ctx.valuePair()) {
				final ValueOfNameValuePair valuePair = new ValueOfNameValuePairImpl();

				final Call systemCall = createCall(valuePairContext.systemName());
				valuePair.setNameCall(systemCall);

				final ValueStmt value = createValueStmt(valuePairContext.qualifiedDataName(),
						valuePairContext.literal());
				valuePair.setValue(value);

				result.addValuePair(valuePair);
			}

			valueOfClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public BlockContainsClause getBlockContainsClause() {
		return blockContainsClause;
	}

	@Override
	public CodeSetClause getCodeSetClause() {
		return codeSetClause;
	}

	@Override
	public DataDescriptionEntryContainerType getContainerType() {
		return containerType;
	}

	@Override
	public DataRecordsClause getDataRecordsClause() {
		return dataRecordsClause;
	}

	@Override
	public ExternalClause getExternalClause() {
		return externalClause;
	}

	@Override
	public Call getFileCall() {
		return fileCall;
	}

	@Override
	public FileControlEntry getFileControlEntry() {
		return fileControlEntry;
	}

	@Override
	public GlobalClause getGlobalClause() {
		return globalClause;
	}

	@Override
	public LabelRecordsClause getLabelRecordsClause() {
		return labelRecordsClause;
	}

	@Override
	public LinageClause getLinageClause() {
		return linageClause;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public RecordContainsClause getRecordContainsClause() {
		return recordContainsClause;
	}

	@Override
	public ReportClause getReportClause() {
		return reportClause;
	}

	@Override
	public ValueOfClause getValueOfClause() {
		return valueOfClause;
	}

	@Override
	public void setFileCall(final Call fileCall) {
		this.fileCall = fileCall;
	}

	@Override
	public void setFileControlEntry(final FileControlEntry fileControlEntry) {
		this.fileControlEntry = fileControlEntry;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}
}
