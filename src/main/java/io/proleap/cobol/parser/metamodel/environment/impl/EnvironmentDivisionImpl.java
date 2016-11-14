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

import io.proleap.cobol.Cobol85Parser.AccessModeClauseContext;
import io.proleap.cobol.Cobol85Parser.AlphabetNameContext;
import io.proleap.cobol.Cobol85Parser.AlternateRecordKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.AssignClauseContext;
import io.proleap.cobol.Cobol85Parser.CharacterSetClauseContext;
import io.proleap.cobol.Cobol85Parser.CollatingSequenceClauseContext;
import io.proleap.cobol.Cobol85Parser.ConfigurationSectionContext;
import io.proleap.cobol.Cobol85Parser.ConfigurationSectionParagraphContext;
import io.proleap.cobol.Cobol85Parser.DiskSizeClauseContext;
import io.proleap.cobol.Cobol85Parser.EnvironmentDivisionContext;
import io.proleap.cobol.Cobol85Parser.FileControlClauseContext;
import io.proleap.cobol.Cobol85Parser.FileControlEntryContext;
import io.proleap.cobol.Cobol85Parser.FileControlParagraphContext;
import io.proleap.cobol.Cobol85Parser.InputOutputSectionContext;
import io.proleap.cobol.Cobol85Parser.InputOutputSectionParagraphContext;
import io.proleap.cobol.Cobol85Parser.IoControlParagraphContext;
import io.proleap.cobol.Cobol85Parser.MemorySizeClauseContext;
import io.proleap.cobol.Cobol85Parser.ObjectComputerClauseContext;
import io.proleap.cobol.Cobol85Parser.ObjectComputerParagraphContext;
import io.proleap.cobol.Cobol85Parser.OrganizationClauseContext;
import io.proleap.cobol.Cobol85Parser.PaddingCharacterClauseContext;
import io.proleap.cobol.Cobol85Parser.PasswordClauseContext;
import io.proleap.cobol.Cobol85Parser.RecordDelimiterClauseContext;
import io.proleap.cobol.Cobol85Parser.RecordKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.ReserveClauseContext;
import io.proleap.cobol.Cobol85Parser.SegmentLimitClauseContext;
import io.proleap.cobol.Cobol85Parser.SelectClauseContext;
import io.proleap.cobol.Cobol85Parser.SourceComputerParagraphContext;
import io.proleap.cobol.Cobol85Parser.SpecialNamesParagraphContext;
import io.proleap.cobol.parser.metamodel.IntegerLiteral;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.AccessModeClause;
import io.proleap.cobol.parser.metamodel.environment.AlternateRecordKeyClause;
import io.proleap.cobol.parser.metamodel.environment.AssignClause;
import io.proleap.cobol.parser.metamodel.environment.CharacterSetClause;
import io.proleap.cobol.parser.metamodel.environment.CollatingSequenceClause;
import io.proleap.cobol.parser.metamodel.environment.ConfigurationSection;
import io.proleap.cobol.parser.metamodel.environment.DiskSizeClause;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.environment.FileControlEntry;
import io.proleap.cobol.parser.metamodel.environment.FileControlParagraph;
import io.proleap.cobol.parser.metamodel.environment.InputOutputSection;
import io.proleap.cobol.parser.metamodel.environment.IoControlParagraph;
import io.proleap.cobol.parser.metamodel.environment.MemorySizeClause;
import io.proleap.cobol.parser.metamodel.environment.ObjectComputerParagraph;
import io.proleap.cobol.parser.metamodel.environment.OrganizationClause;
import io.proleap.cobol.parser.metamodel.environment.PaddingCharacterClause;
import io.proleap.cobol.parser.metamodel.environment.PasswordClause;
import io.proleap.cobol.parser.metamodel.environment.RecordDelimiterClause;
import io.proleap.cobol.parser.metamodel.environment.RecordKeyClause;
import io.proleap.cobol.parser.metamodel.environment.ReserveClause;
import io.proleap.cobol.parser.metamodel.environment.SegmentLimitClause;
import io.proleap.cobol.parser.metamodel.environment.SelectClause;
import io.proleap.cobol.parser.metamodel.environment.SourceComputerParagraph;
import io.proleap.cobol.parser.metamodel.environment.SpecialNamesParagraph;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionImpl;
import io.proleap.cobol.parser.metamodel.valuestmt.IntegerLiteralValueStmt;
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
	public AccessModeClause addAccessModeClause(final AccessModeClauseContext ctx) {
		AccessModeClause result = (AccessModeClause) getASGElement(ctx);

		if (result == null) {
			result = new AccessModeClauseImpl(programUnit, this, ctx);

			final AccessModeClause.Mode mode;

			if (ctx.SEQUENTIAL() != null) {
				mode = AccessModeClause.Mode.Sequential;
			} else if (ctx.RANDOM() != null) {
				mode = AccessModeClause.Mode.Random;
			} else if (ctx.DYNAMIC() != null) {
				mode = AccessModeClause.Mode.Dynamic;
			} else if (ctx.EXCLUSIVE() != null) {
				mode = AccessModeClause.Mode.Exclusive;
			} else {
				LOG.warn("unknown mode {}.", ctx);
				mode = null;
			}

			result.setMode(mode);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AlternateRecordKeyClause addAlternateRecordKeyClause(final AlternateRecordKeyClauseContext ctx) {
		AlternateRecordKeyClause result = (AlternateRecordKeyClause) getASGElement(ctx);

		if (result == null) {
			result = new AlternateRecordKeyClauseImpl(programUnit, this, ctx);

			final ValueStmt valueStmt = createCallValueStmt(ctx.qualifiedDataName());
			result.setValueStmt(valueStmt);

			if (ctx.passwordClause() != null) {
				final PasswordClause passwordClause = addPasswordClause(ctx.passwordClause());
				result.setPasswordClause(passwordClause);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AssignClause addAssignClause(final AssignClauseContext ctx) {
		AssignClause result = (AssignClause) getASGElement(ctx);

		if (result == null) {
			result = new AssignClauseImpl(programUnit, this, ctx);

			final ValueStmt valueStmt;

			if (ctx.assignmentName() != null) {
				valueStmt = createCallValueStmt(ctx.assignmentName());
			} else if (ctx.literal() != null) {
				valueStmt = createLiteralValueStmt(ctx.literal());
			} else {
				LOG.warn("unknown value stmt {}.", ctx);
				valueStmt = null;
			}

			result.setValueStmt(valueStmt);

			registerASGElement(result);
		}

		return result;
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

			for (final AlphabetNameContext alphabetNameContext : ctx.alphabetName()) {
				final String alphabetName = determineName(alphabetNameContext);
				result.addAlphabetName(alphabetName);
			}

			if (ctx.collatingSequenceClauseAlphanumeric() != null) {
				final String alphanumeric = determineName(ctx.collatingSequenceClauseAlphanumeric().alphabetName());
				result.setAlphaNumeric(alphanumeric);
			}

			if (ctx.collatingSequenceClauseNational() != null) {
				final String national = determineName(ctx.collatingSequenceClauseNational().alphabetName());
				result.setNational(national);
			}

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

			for (final FileControlClauseContext fileControlClause : ctx.fileControlClause()) {
				if (fileControlClause.assignClause() != null) {
					final AssignClause assignClause = addAssignClause(fileControlClause.assignClause());
					result.setAssignClause(assignClause);
				}

				if (fileControlClause.reserveClause() != null) {
					final ReserveClause reserveClause = addReserveClause(fileControlClause.reserveClause());
					result.setReserveClause(reserveClause);
				}

				if (fileControlClause.organizationClause() != null) {
					final OrganizationClause organizationClause = addOrganizationClause(
							fileControlClause.organizationClause());
					result.setOrganizationClause(organizationClause);
				}

				if (fileControlClause.paddingCharacterClause() != null) {
					final PaddingCharacterClause paddingCharacterClause = addPaddingCharacterClause(
							fileControlClause.paddingCharacterClause());
					result.setPaddingCharacterClause(paddingCharacterClause);
				}

				if (fileControlClause.recordDelimiterClause() != null) {
					final RecordDelimiterClause recordDelimiterClause = addRecordDelimiterClause(
							fileControlClause.recordDelimiterClause());
					result.setRecordDelimiterClause(recordDelimiterClause);
				}

				if (fileControlClause.accessModeClause() != null) {
					final AccessModeClause accessModeClause = addAccessModeClause(fileControlClause.accessModeClause());
					result.setAccessModeClause(accessModeClause);
				}

				if (fileControlClause.recordKeyClause() != null) {
					final RecordKeyClause recordKeyClause = addRecordKeyClause(fileControlClause.recordKeyClause());
					result.setRecordKeyClause(recordKeyClause);
				}

				if (fileControlClause.alternateRecordKeyClause() != null) {
					final AlternateRecordKeyClause alternateRecordKeyClause = addAlternateRecordKeyClause(
							fileControlClause.alternateRecordKeyClause());
					result.setAlternateRecordKeyClause(alternateRecordKeyClause);
				}

				if (fileControlClause.passwordClause() != null) {
					final PasswordClause passwordClause = addPasswordClause(fileControlClause.passwordClause());
					result.setPasswordClause(passwordClause);
				}
			}

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
				if (inputOutputSectionParagraphContext.fileControlParagraph() != null) {
					final FileControlParagraph fileControlParagraph = addFileControlParagraph(
							inputOutputSectionParagraphContext.fileControlParagraph());
					result.addFileControlParagraph(fileControlParagraph);
				} else if (inputOutputSectionParagraphContext.ioControlParagraph() != null) {
					final IoControlParagraph ioControlParagraph = addIoControlParagraph(
							inputOutputSectionParagraphContext.ioControlParagraph());
					result.addIoControlParagraph(ioControlParagraph);
				} else {
					LOG.warn("unknown input output section paragraph {}", inputOutputSectionParagraphContext);
				}
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
	public OrganizationClause addOrganizationClause(final OrganizationClauseContext ctx) {
		OrganizationClause result = (OrganizationClause) getASGElement(ctx);

		if (result == null) {
			result = new OrganizationClauseImpl(programUnit, this, ctx);

			/*
			 * type
			 */
			final OrganizationClause.Type type;

			if (ctx.LINE() != null) {
				type = OrganizationClause.Type.Line;
			} else if (ctx.RECORD() != null && ctx.BINARY() != null) {
				type = OrganizationClause.Type.RecordBinary;
			} else if (ctx.BINARY() != null) {
				type = OrganizationClause.Type.Binary;
			} else if (ctx.RECORD() != null) {
				type = OrganizationClause.Type.Record;
			} else {
				type = null;
			}

			result.setType(type);

			/*
			 * mode
			 */
			final OrganizationClause.Mode mode;

			if (ctx.SEQUENTIAL() != null) {
				mode = OrganizationClause.Mode.Sequential;
			} else if (ctx.RELATIVE() != null) {
				mode = OrganizationClause.Mode.Relative;
			} else if (ctx.INDEXED() != null) {
				mode = OrganizationClause.Mode.Indexed;
			} else {
				mode = null;
			}

			result.setMode(mode);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public PaddingCharacterClause addPaddingCharacterClause(final PaddingCharacterClauseContext ctx) {
		PaddingCharacterClause result = (PaddingCharacterClause) getASGElement(ctx);

		if (result == null) {
			result = new PaddingCharacterClauseImpl(programUnit, this, ctx);

			final ValueStmt valueStmt;

			if (ctx.qualifiedDataName() != null) {
				valueStmt = createCallValueStmt(ctx.qualifiedDataName());
			} else if (ctx.literal() != null) {
				valueStmt = createLiteralValueStmt(ctx.literal());
			} else {
				LOG.warn("unknown value stmt {}.", ctx);
				valueStmt = null;
			}

			result.setValueStmt(valueStmt);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public PasswordClause addPasswordClause(final PasswordClauseContext ctx) {
		PasswordClause result = (PasswordClause) getASGElement(ctx);

		if (result == null) {
			result = new PasswordClauseImpl(programUnit, this, ctx);

			final ValueStmt valueStmt = createCallValueStmt(ctx.dataName());
			result.setValueStmt(valueStmt);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public RecordDelimiterClause addRecordDelimiterClause(final RecordDelimiterClauseContext ctx) {
		RecordDelimiterClause result = (RecordDelimiterClause) getASGElement(ctx);

		if (result == null) {
			result = new RecordDelimiterClauseImpl(programUnit, this, ctx);

			final ValueStmt valueStmt;

			if (ctx.assignmentName() != null) {
				valueStmt = createCallValueStmt(ctx.assignmentName());
			} else if (ctx.STANDARD_1() != null) {
				valueStmt = createTerminalValueStmt(ctx.STANDARD_1());
			} else if (ctx.IMPLICIT() != null) {
				valueStmt = createTerminalValueStmt(ctx.IMPLICIT());
			} else {
				LOG.warn("unknown value stmt {}.", ctx);
				valueStmt = null;
			}

			result.setValueStmt(valueStmt);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public RecordKeyClause addRecordKeyClause(final RecordKeyClauseContext ctx) {
		RecordKeyClause result = (RecordKeyClause) getASGElement(ctx);

		if (result == null) {
			result = new RecordKeyClauseImpl(programUnit, this, ctx);

			final ValueStmt valueStmt = createCallValueStmt(ctx.qualifiedDataName());
			result.setValueStmt(valueStmt);

			if (ctx.passwordClause() != null) {
				final PasswordClause passwordClause = addPasswordClause(ctx.passwordClause());
				result.setPasswordClause(passwordClause);
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ReserveClause addReserveClause(final ReserveClauseContext ctx) {
		ReserveClause result = (ReserveClause) getASGElement(ctx);

		if (result == null) {
			result = new ReserveClauseImpl(programUnit, this, ctx);

			if (ctx.integerLiteral() != null) {
				final IntegerLiteralValueStmt integerLiteralValueStmt = createIntegerLiteralValueStmt(
						ctx.integerLiteral());
				result.setValueStmt(integerLiteralValueStmt);
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
