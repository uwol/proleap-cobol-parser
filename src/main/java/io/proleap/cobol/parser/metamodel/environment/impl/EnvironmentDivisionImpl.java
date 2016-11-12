/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.CharacterSetClauseContext;
import io.proleap.cobol.Cobol85Parser.CollatingSequenceClauseContext;
import io.proleap.cobol.Cobol85Parser.ConfigurationSectionContext;
import io.proleap.cobol.Cobol85Parser.ConfigurationSectionParagraphContext;
import io.proleap.cobol.Cobol85Parser.DiskSizeClauseContext;
import io.proleap.cobol.Cobol85Parser.EnvironmentDivisionContext;
import io.proleap.cobol.Cobol85Parser.FileControlEntryContext;
import io.proleap.cobol.Cobol85Parser.FileControlParagraphContext;
import io.proleap.cobol.Cobol85Parser.InputOutputSectionContext;
import io.proleap.cobol.Cobol85Parser.InputOutputSectionParagraphContext;
import io.proleap.cobol.Cobol85Parser.IoControlParagraphContext;
import io.proleap.cobol.Cobol85Parser.MemorySizeClauseContext;
import io.proleap.cobol.Cobol85Parser.ObjectComputerClauseContext;
import io.proleap.cobol.Cobol85Parser.ObjectComputerParagraphContext;
import io.proleap.cobol.Cobol85Parser.SegmentLimitClauseContext;
import io.proleap.cobol.Cobol85Parser.SelectClauseContext;
import io.proleap.cobol.Cobol85Parser.SourceComputerParagraphContext;
import io.proleap.cobol.Cobol85Parser.SpecialNamesParagraphContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.CharacterSetClause;
import io.proleap.cobol.parser.metamodel.environment.CollatingSequenceClause;
import io.proleap.cobol.parser.metamodel.environment.ConfigurationSection;
import io.proleap.cobol.parser.metamodel.environment.DiskSizeClause;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.environment.FileControlEntry;
import io.proleap.cobol.parser.metamodel.environment.FileControlParagraph;
import io.proleap.cobol.parser.metamodel.environment.InputOutputSection;
import io.proleap.cobol.parser.metamodel.environment.InputOutputSectionParagraph;
import io.proleap.cobol.parser.metamodel.environment.IoControlParagraph;
import io.proleap.cobol.parser.metamodel.environment.MemorySizeClause;
import io.proleap.cobol.parser.metamodel.environment.ObjectComputerParagraph;
import io.proleap.cobol.parser.metamodel.environment.SegmentLimitClause;
import io.proleap.cobol.parser.metamodel.environment.SelectClause;
import io.proleap.cobol.parser.metamodel.environment.SourceComputerParagraph;
import io.proleap.cobol.parser.metamodel.environment.SpecialNamesParagraph;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public class EnvironmentDivisionImpl extends CobolDivisionImpl implements EnvironmentDivision {

	private final static Logger LOG = LogManager.getLogger(EnvironmentDivisionImpl.class);

	protected ConfigurationSection configurationSection;

	protected final EnvironmentDivisionContext ctx;

	protected List<FileControlEntry> fileControlEntries = new ArrayList<FileControlEntry>();

	protected Map<String, FileControlEntry> fileControlEntriesByName = new HashMap<String, FileControlEntry>();

	protected InputOutputSection inputOutputSection;

	protected SpecialNamesParagraph specialNamesParagraph;

	public EnvironmentDivisionImpl(final ProgramUnit programUnit, final EnvironmentDivisionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public CharacterSetClause addCharacterSetClause(final CharacterSetClauseContext ctx) {
		CharacterSetClause result = (CharacterSetClause) getASGElement(ctx);

		if (result == null) {
			result = new CharacterSetClauseImpl(programUnit, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CollatingSequenceClause addCollatingSequenceClause(final CollatingSequenceClauseContext ctx) {
		CollatingSequenceClause result = (CollatingSequenceClause) getASGElement(ctx);

		if (result == null) {
			result = new CollatingSequenceClauseImpl(programUnit, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ConfigurationSection addConfigurationSection(final ConfigurationSectionContext ctx) {
		ConfigurationSection result = (ConfigurationSection) getASGElement(ctx);

		if (result == null) {
			result = new ConfigurationSectionImpl(programUnit, this, ctx);

			for (final ConfigurationSectionParagraphContext configurationSectionParagraphContext : ctx
					.configurationSectionParagraph()) {
				if (configurationSectionParagraphContext.sourceComputerParagraph() != null) {
					final SourceComputerParagraph sourceComputerParagraph = addSourceComputerParagraph(
							configurationSectionParagraphContext.sourceComputerParagraph());
					result.setSourceComputerParagraph(sourceComputerParagraph);
				} else if (configurationSectionParagraphContext.objectComputerParagraph() != null) {
					final ObjectComputerParagraph objectComputerParagraph = addObjectComputerParagraph(
							configurationSectionParagraphContext.objectComputerParagraph());
					result.setObjectComputerParagraph(objectComputerParagraph);
				} else {
					LOG.warn("unknown configuration section paragraph {}", configurationSectionParagraphContext);
				}
			}

			configurationSection = result;

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DiskSizeClause addDiskSizeClause(final DiskSizeClauseContext ctx) {
		DiskSizeClause result = (DiskSizeClause) getASGElement(ctx);

		if (result == null) {
			result = new DiskSizeClauseImpl(programUnit, this, ctx);

			/*
			 * size value stmt
			 */
			final ValueStmt valueStmt;

			if (ctx.integerLiteral() != null) {
				valueStmt = createIntegerLiteralValueStmt(ctx.integerLiteral());
			} else if (ctx.cobolWord() != null) {
				valueStmt = createCallValueStmt(ctx.cobolWord());
			} else {
				LOG.warn("unknown value stmt {}.", ctx);
				valueStmt = null;
			}

			result.setValueStmt(valueStmt);

			/*
			 * size unit
			 */
			final DiskSizeClause.Unit unit;

			if (ctx.WORDS() != null) {
				unit = DiskSizeClause.Unit.Words;
			} else if (ctx.MODULES() != null) {
				unit = DiskSizeClause.Unit.Modules;
			} else {
				unit = null;
			}

			result.setUnit(unit);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public FileControlEntry addFileControlEntry(final FileControlEntryContext ctx) {
		FileControlEntry result = (FileControlEntry) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new FileControlEntryImpl(name, programUnit, this, ctx);

			final SelectClause selectClause = addSelectClause(ctx.selectClause());
			result.setSelectClause(selectClause);

			registerASGElement(result);

			fileControlEntries.add(result);
			fileControlEntriesByName.put(name, result);
		}

		return result;
	}

	@Override
	public FileControlParagraph addFileControlParagraph(final FileControlParagraphContext ctx) {
		FileControlParagraph result = (FileControlParagraph) getASGElement(ctx);

		if (result == null) {
			result = new FileControlParagraphImpl(programUnit, this, ctx);

			for (final FileControlEntryContext fileControlEntryContext : ctx.fileControlEntry()) {
				final FileControlEntry fileControlEntry = addFileControlEntry(fileControlEntryContext);

				result.addFileControlEntry(fileControlEntry);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public InputOutputSection addInputOutputSection(final InputOutputSectionContext ctx) {
		InputOutputSection result = (InputOutputSection) getASGElement(ctx);

		if (result == null) {
			result = new InputOutputSectionImpl(programUnit, this, ctx);

			for (final InputOutputSectionParagraphContext inputOutputSectionParagraphContext : ctx
					.inputOutputSectionParagraph()) {
				final InputOutputSectionParagraph inputOutputSectionParagraph;

				if (inputOutputSectionParagraphContext.fileControlParagraph() != null) {
					inputOutputSectionParagraph = addFileControlParagraph(
							inputOutputSectionParagraphContext.fileControlParagraph());
				} else if (inputOutputSectionParagraphContext.ioControlParagraph() != null) {
					inputOutputSectionParagraph = addIoControlParagraph(
							inputOutputSectionParagraphContext.ioControlParagraph());
				} else {
					LOG.warn("unknown input output section paragraph {}", inputOutputSectionParagraphContext);
					inputOutputSectionParagraph = null;
				}

				result.addInputOutputSectionParagraph(inputOutputSectionParagraph);
			}

			inputOutputSection = result;

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public IoControlParagraph addIoControlParagraph(final IoControlParagraphContext ctx) {
		IoControlParagraph result = (IoControlParagraph) getASGElement(ctx);

		if (result == null) {
			result = new IoControlParagraphImpl(programUnit, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public MemorySizeClause addMemorySizeClause(final MemorySizeClauseContext ctx) {
		MemorySizeClause result = (MemorySizeClause) getASGElement(ctx);

		if (result == null) {
			result = new MemorySizeClauseImpl(programUnit, this, ctx);

			/*
			 * size value stmt
			 */
			final ValueStmt valueStmt;

			if (ctx.integerLiteral() != null) {
				valueStmt = createIntegerLiteralValueStmt(ctx.integerLiteral());
			} else if (ctx.cobolWord() != null) {
				valueStmt = createCallValueStmt(ctx.cobolWord());
			} else {
				LOG.warn("unknown value stmt {}.", ctx);
				valueStmt = null;
			}

			result.setValueStmt(valueStmt);

			/*
			 * size unit
			 */
			final MemorySizeClause.Unit unit;

			if (ctx.WORDS() != null) {
				unit = MemorySizeClause.Unit.Words;
			} else if (ctx.CHARACTERS() != null) {
				unit = MemorySizeClause.Unit.Characters;
			} else if (ctx.MODULES() != null) {
				unit = MemorySizeClause.Unit.Modules;
			} else {
				unit = null;
			}

			result.setUnit(unit);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ObjectComputerParagraph addObjectComputerParagraph(final ObjectComputerParagraphContext ctx) {
		ObjectComputerParagraph result = (ObjectComputerParagraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ObjectComputerParagraphImpl(name, programUnit, this, ctx);

			for (final ObjectComputerClauseContext objectComputerClause : ctx.objectComputerClause()) {
				if (objectComputerClause.memorySizeClause() != null) {
					final MemorySizeClause memorySizeClause = addMemorySizeClause(
							objectComputerClause.memorySizeClause());
					result.setMemorySizeClause(memorySizeClause);
				}

				if (objectComputerClause.diskSizeClause() != null) {
					final DiskSizeClause diskSizeClause = addDiskSizeClause(objectComputerClause.diskSizeClause());
					result.setDiskSizeClause(diskSizeClause);
				}

				if (objectComputerClause.collatingSequenceClause() != null) {
					final CollatingSequenceClause collatingSequenceClause = addCollatingSequenceClause(
							objectComputerClause.collatingSequenceClause());
					result.setCollatingSequenceClause(collatingSequenceClause);
				}

				if (objectComputerClause.segmentLimitClause() != null) {
					final SegmentLimitClause segmentLimitClause = addSegmentLimitClause(
							objectComputerClause.segmentLimitClause());
					result.setSegmentLimitClause(segmentLimitClause);
				}

				if (objectComputerClause.characterSetClause() != null) {
					final CharacterSetClause characterSetClause = addCharacterSetClause(
							objectComputerClause.characterSetClause());
					result.setCharacterSetClause(characterSetClause);
				}
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SegmentLimitClause addSegmentLimitClause(final SegmentLimitClauseContext ctx) {
		SegmentLimitClause result = (SegmentLimitClause) getASGElement(ctx);

		if (result == null) {
			result = new SegmentLimitClauseImpl(programUnit, this, ctx);

			final IntegerLiteral integerLiteral = addIntegerLiteral(ctx.integerLiteral());
			result.setIntegerLiteral(integerLiteral);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SelectClause addSelectClause(final SelectClauseContext ctx) {
		SelectClause result = (SelectClause) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new SelectClauseImpl(name, programUnit, this, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SourceComputerParagraph addSourceComputerParagraph(final SourceComputerParagraphContext ctx) {
		SourceComputerParagraph result = (SourceComputerParagraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new SourceComputerParagraphImpl(name, programUnit, this, ctx);

			if (ctx.DEBUGGING() != null) {
				result.setDebuggingMode(true);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SpecialNamesParagraph addSpecialNamesParagraph(final SpecialNamesParagraphContext ctx) {
		SpecialNamesParagraph result = (SpecialNamesParagraph) getASGElement(ctx);

		if (result == null) {
			result = new SpecialNamesParagraphImpl(programUnit, this, ctx);

			specialNamesParagraph = result;

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ConfigurationSection getConfigurationSection() {
		return configurationSection;
	}

	@Override
	public List<FileControlEntry> getFileControlEntries() {
		return fileControlEntries;
	}

	@Override
	public FileControlEntry getFileControlEntry(final String name) {
		return fileControlEntriesByName.get(name);
	}

	@Override
	public InputOutputSection getInputOutputSection() {
		return inputOutputSection;
	}

	@Override
	public SpecialNamesParagraph getSpecialNamesParagraph() {
		return specialNamesParagraph;
	}

}
