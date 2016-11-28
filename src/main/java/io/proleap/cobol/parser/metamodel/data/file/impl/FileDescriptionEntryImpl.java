/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.file.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.BlockContainsClauseContext;
import io.proleap.cobol.Cobol85Parser.CodeSetClauseContext;
import io.proleap.cobol.Cobol85Parser.DataNameContext;
import io.proleap.cobol.Cobol85Parser.DataRecordsClauseContext;
import io.proleap.cobol.Cobol85Parser.ExternalClauseContext;
import io.proleap.cobol.Cobol85Parser.FileDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.GlobalClauseContext;
import io.proleap.cobol.Cobol85Parser.LabelRecordsClauseContext;
import io.proleap.cobol.Cobol85Parser.LinageAtContext;
import io.proleap.cobol.Cobol85Parser.LinageClauseContext;
import io.proleap.cobol.Cobol85Parser.LinageFootingAtContext;
import io.proleap.cobol.Cobol85Parser.LinageLinesAtBottomContext;
import io.proleap.cobol.Cobol85Parser.LinageLinesAtTopContext;
import io.proleap.cobol.Cobol85Parser.RecordContainsClauseContext;
import io.proleap.cobol.Cobol85Parser.RecordContainsClauseFormat2Context;
import io.proleap.cobol.Cobol85Parser.ReportClauseContext;
import io.proleap.cobol.Cobol85Parser.ReportNameContext;
import io.proleap.cobol.Cobol85Parser.ValueOfClauseContext;
import io.proleap.cobol.Cobol85Parser.ValuePairContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.datadescription.impl.DataDescriptionEntryContainerImpl;
import io.proleap.cobol.parser.metamodel.data.file.BlockContainsClause;
import io.proleap.cobol.parser.metamodel.data.file.CodeSetClause;
import io.proleap.cobol.parser.metamodel.data.file.DataRecordsClause;
import io.proleap.cobol.parser.metamodel.data.file.ExternalClause;
import io.proleap.cobol.parser.metamodel.data.file.FileDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.file.GlobalClause;
import io.proleap.cobol.parser.metamodel.data.file.LabelRecordsClause;
import io.proleap.cobol.parser.metamodel.data.file.LinageClause;
import io.proleap.cobol.parser.metamodel.data.file.RecordContainsClause;
import io.proleap.cobol.parser.metamodel.data.file.ReportClause;
import io.proleap.cobol.parser.metamodel.data.file.ValueOfClause;
import io.proleap.cobol.parser.metamodel.data.file.ValueOfNameValuePair;
import io.proleap.cobol.parser.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class FileDescriptionEntryImpl extends DataDescriptionEntryContainerImpl implements FileDescriptionEntry {

	private final static Logger LOG = LogManager.getLogger(FileDescriptionEntryImpl.class);

	protected BlockContainsClause blockContainsClause;

	protected CodeSetClause codeSetClause;

	protected final FileDescriptionEntryContext ctx;

	protected DataRecordsClause dataRecordsClause;

	protected ExternalClause externalClause;

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
			final IntegerLiteral from = createIntegerLiteral(ctx.integerLiteral());
			result.setFrom(from);

			/*
			 * to
			 */
			if (ctx.blockContainsTo() != null) {
				final IntegerLiteral to = createIntegerLiteral(ctx.blockContainsTo().integerLiteral());
				result.setTo(to);
			}

			/*
			 * unit
			 */
			final BlockContainsClause.Unit unit;

			if (ctx.RECORDS() != null) {
				unit = BlockContainsClause.Unit.Records;
			} else if (ctx.CHARACTERS() != null) {
				unit = BlockContainsClause.Unit.Characters;
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
				final CallValueStmt dataNameValueStmt = createCallValueStmt(dataNameContext);
				result.addDataNameValueStmt(dataNameValueStmt);
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
			final LabelRecordsClause.Type type;

			if (ctx.OMITTED() != null) {
				type = LabelRecordsClause.Type.Omitted;
			} else if (ctx.STANDARD() != null) {
				type = LabelRecordsClause.Type.Standard;
			} else if (!ctx.dataName().isEmpty()) {
				type = LabelRecordsClause.Type.DataNames;
			} else {
				type = null;
			}

			result.setType(type);

			/*
			 * data names
			 */
			for (final DataNameContext dataNameContext : ctx.dataName()) {
				final CallValueStmt dataNameValueStmt = createCallValueStmt(dataNameContext);
				result.addDataNameValueStmt(dataNameValueStmt);
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
				final IntegerLiteral from = createIntegerLiteral(ctx.recordContainsClauseFormat1().integerLiteral());
				result.setFrom(from);
			} else if (ctx.recordContainsClauseFormat2() != null) {
				final RecordContainsClauseFormat2Context recordContainsClauseFormat2 = ctx
						.recordContainsClauseFormat2();

				/*
				 * from
				 */
				final IntegerLiteral from = createIntegerLiteral(recordContainsClauseFormat2.integerLiteral());
				result.setFrom(from);

				/*
				 * to
				 */
				if (recordContainsClauseFormat2.recordContainsTo() != null) {
					final IntegerLiteral to = createIntegerLiteral(
							recordContainsClauseFormat2.recordContainsTo().integerLiteral());
					result.setTo(to);
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
					final CallValueStmt dependingOnValueStmt = createCallValueStmt(
							recordContainsClauseFormat2.qualifiedDataName());
					result.setDependingOnValueStmt(dependingOnValueStmt);
				}
			} else if (ctx.recordContainsClauseFormat3() != null) {
				/*
				 * from
				 */
				final IntegerLiteral from = createIntegerLiteral(ctx.recordContainsClauseFormat3().integerLiteral());
				result.setFrom(from);

				/*
				 * to
				 */
				final IntegerLiteral to = createIntegerLiteral(
						ctx.recordContainsClauseFormat2().recordContainsTo().integerLiteral());
				result.setTo(to);
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
				final CallValueStmt reportNameValueStmt = createCallValueStmt(reportNameContext);
				result.addReportNameValueStmt(reportNameValueStmt);
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
				final ValueStmt systemName = createCallValueStmt(valuePairContext.systemName());
				final ValueStmt value = createValueStmt(valuePairContext.qualifiedDataName(),
						valuePairContext.literal());

				final ValueOfNameValuePair valuePair = new ValueOfNameValuePairImpl();
				valuePair.setName(systemName);
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
	public DataRecordsClause getDataRecordsClause() {
		return dataRecordsClause;
	}

	@Override
	public ExternalClause getExternalClause() {
		return externalClause;
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

}
