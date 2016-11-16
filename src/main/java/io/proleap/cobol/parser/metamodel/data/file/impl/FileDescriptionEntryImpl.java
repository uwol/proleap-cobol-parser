/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.file.impl;

import io.proleap.cobol.Cobol85Parser.BlockContainsClauseContext;
import io.proleap.cobol.Cobol85Parser.DataNameContext;
import io.proleap.cobol.Cobol85Parser.FileDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.LabelRecordsClauseContext;
import io.proleap.cobol.Cobol85Parser.RecordContainsClauseContext;
import io.proleap.cobol.Cobol85Parser.RecordContainsClauseFormat2Context;
import io.proleap.cobol.Cobol85Parser.ValueOfClauseContext;
import io.proleap.cobol.Cobol85Parser.ValuePairContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.file.BlockContainsClause;
import io.proleap.cobol.parser.metamodel.data.file.FileDescriptionEntry;
import io.proleap.cobol.parser.metamodel.data.file.LabelRecordsClause;
import io.proleap.cobol.parser.metamodel.data.file.RecordContainsClause;
import io.proleap.cobol.parser.metamodel.data.file.ValueOfClause;
import io.proleap.cobol.parser.metamodel.data.file.ValueOfNameValuePair;
import io.proleap.cobol.parser.metamodel.data.impl.DataDescriptionEntryContainerImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.CallValueStmt;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class FileDescriptionEntryImpl extends DataDescriptionEntryContainerImpl implements FileDescriptionEntry {

	protected BlockContainsClause blockContainsClause;

	protected final FileDescriptionEntryContext ctx;

	protected Boolean external;

	protected Boolean global;

	protected LabelRecordsClause labelRecordsClause;

	protected final String name;

	protected RecordContainsClause recordContainsClause;

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
			final IntegerLiteral from = addIntegerLiteral(ctx.integerLiteral());
			result.setFrom(from);

			/*
			 * to
			 */
			if (ctx.blockContainsTo() != null) {
				final IntegerLiteral to = addIntegerLiteral(ctx.blockContainsTo().integerLiteral());
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
	public LabelRecordsClause addLabelRecordsClause(final LabelRecordsClauseContext ctx) {
		LabelRecordsClause result = (LabelRecordsClause) getASGElement(ctx);

		if (result == null) {
			result = new LabelRecordsClauseImpl(programUnit, ctx);

			/*
			 * unit
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
	public RecordContainsClause addRecordContainsClause(final RecordContainsClauseContext ctx) {
		RecordContainsClause result = (RecordContainsClause) getASGElement(ctx);

		if (result == null) {
			result = new RecordContainsClauseImpl(programUnit, ctx);

			if (ctx.recordContainsClauseFormat1() != null) {
				final IntegerLiteral from = addIntegerLiteral(ctx.recordContainsClauseFormat1().integerLiteral());
				result.setFrom(from);
			} else if (ctx.recordContainsClauseFormat2() != null) {
				final RecordContainsClauseFormat2Context recordContainsClauseFormat2 = ctx
						.recordContainsClauseFormat2();

				/*
				 * from
				 */
				final IntegerLiteral from = addIntegerLiteral(recordContainsClauseFormat2.integerLiteral());
				result.setFrom(from);

				/*
				 * to
				 */
				if (recordContainsClauseFormat2.recordContainsTo() != null) {
					final IntegerLiteral to = addIntegerLiteral(
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
				final IntegerLiteral from = addIntegerLiteral(ctx.recordContainsClauseFormat3().integerLiteral());
				result.setFrom(from);

				/*
				 * to
				 */
				final IntegerLiteral to = addIntegerLiteral(
						ctx.recordContainsClauseFormat2().recordContainsTo().integerLiteral());
				result.setTo(to);
			}

			recordContainsClause = result;
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
				final ValueStmt value;

				if (valuePairContext.qualifiedDataName() != null) {
					value = createCallValueStmt(valuePairContext.qualifiedDataName());
				} else if (valuePairContext.literal() != null) {
					value = createLiteralValueStmt(valuePairContext.literal());
				} else {
					value = null;
				}

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
	public LabelRecordsClause getLabelRecordsClause() {
		return labelRecordsClause;
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
	public ValueOfClause getValueOfClause() {
		return valueOfClause;
	}

	@Override
	public Boolean isExternal() {
		return external;
	}

	@Override
	public Boolean isGlobal() {
		return global;
	}

	@Override
	public void setExternal(final Boolean external) {
		this.external = external;
	}

	@Override
	public void setGlobal(final Boolean global) {
		this.global = global;
	}

}
