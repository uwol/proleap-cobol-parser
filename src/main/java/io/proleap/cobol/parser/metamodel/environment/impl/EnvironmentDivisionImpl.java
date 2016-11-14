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

import io.proleap.cobol.Cobol85Parser.ConfigurationSectionContext;
import io.proleap.cobol.Cobol85Parser.ConfigurationSectionParagraphContext;
import io.proleap.cobol.Cobol85Parser.EnvironmentDivisionContext;
import io.proleap.cobol.Cobol85Parser.FileControlClauseContext;
import io.proleap.cobol.Cobol85Parser.FileControlEntryContext;
import io.proleap.cobol.Cobol85Parser.FileControlParagraphContext;
import io.proleap.cobol.Cobol85Parser.InputOutputSectionContext;
import io.proleap.cobol.Cobol85Parser.InputOutputSectionParagraphContext;
import io.proleap.cobol.Cobol85Parser.IoControlParagraphContext;
import io.proleap.cobol.Cobol85Parser.ObjectComputerClauseContext;
import io.proleap.cobol.Cobol85Parser.ObjectComputerParagraphContext;
import io.proleap.cobol.Cobol85Parser.SourceComputerParagraphContext;
import io.proleap.cobol.Cobol85Parser.SpecialNamesParagraphContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.environment.configuration.ConfigurationSection;
import io.proleap.cobol.parser.metamodel.environment.configuration.impl.ConfigurationSectionImpl;
import io.proleap.cobol.parser.metamodel.environment.configuration.object.ObjectComputerParagraph;
import io.proleap.cobol.parser.metamodel.environment.configuration.object.impl.ObjectComputerParagraphImpl;
import io.proleap.cobol.parser.metamodel.environment.configuration.source.SourceComputerParagraph;
import io.proleap.cobol.parser.metamodel.environment.configuration.source.impl.SourceComputerParagraphImpl;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.InputOutputSection;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.FileControlEntry;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.FileControlParagraph;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.impl.FileControlEntryImpl;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.filecontrol.impl.FileControlParagraphImpl;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.impl.InputOutputSectionImpl;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.IoControlParagraph;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol.impl.IoControlParagraphImpl;
import io.proleap.cobol.parser.metamodel.environment.specialnames.SpecialNamesParagraph;
import io.proleap.cobol.parser.metamodel.environment.specialnames.impl.SpecialNamesParagraphImpl;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionImpl;

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
	public ConfigurationSection addConfigurationSection(final ConfigurationSectionContext ctx) {
		ConfigurationSection result = (ConfigurationSection) getASGElement(ctx);

		if (result == null) {
			result = new ConfigurationSectionImpl(programUnit, ctx);

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
	public FileControlEntry addFileControlEntry(final FileControlEntryContext ctx) {
		FileControlEntry result = (FileControlEntry) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new FileControlEntryImpl(name, programUnit, ctx);

			result.addSelectClause(ctx.selectClause());

			for (final FileControlClauseContext fileControlClause : ctx.fileControlClause()) {
				if (fileControlClause.assignClause() != null) {
					result.addAssignClause(fileControlClause.assignClause());
				}

				if (fileControlClause.reserveClause() != null) {
					result.addReserveClause(fileControlClause.reserveClause());
				}

				if (fileControlClause.organizationClause() != null) {
					result.addOrganizationClause(fileControlClause.organizationClause());
				}

				if (fileControlClause.paddingCharacterClause() != null) {
					result.addPaddingCharacterClause(fileControlClause.paddingCharacterClause());
				}

				if (fileControlClause.recordDelimiterClause() != null) {
					result.addRecordDelimiterClause(fileControlClause.recordDelimiterClause());
				}

				if (fileControlClause.accessModeClause() != null) {
					result.addAccessModeClause(fileControlClause.accessModeClause());
				}

				if (fileControlClause.recordKeyClause() != null) {
					result.addRecordKeyClause(fileControlClause.recordKeyClause());
				}

				if (fileControlClause.alternateRecordKeyClause() != null) {
					result.addAlternateRecordKeyClause(fileControlClause.alternateRecordKeyClause());
				}

				if (fileControlClause.passwordClause() != null) {
					result.addPasswordClause(fileControlClause.passwordClause());
				}

				if (fileControlClause.fileStatusClause() != null) {
					result.addFileStatusClause(fileControlClause.fileStatusClause());
				}

				if (fileControlClause.relativeKeyClause() != null) {
					result.addRelativeKeyClause(fileControlClause.relativeKeyClause());
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
			result = new FileControlParagraphImpl(programUnit, ctx);

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
			result = new InputOutputSectionImpl(programUnit, ctx);

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
			result = new IoControlParagraphImpl(programUnit, ctx);

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ObjectComputerParagraph addObjectComputerParagraph(final ObjectComputerParagraphContext ctx) {
		ObjectComputerParagraph result = (ObjectComputerParagraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ObjectComputerParagraphImpl(name, programUnit, ctx);

			for (final ObjectComputerClauseContext objectComputerClause : ctx.objectComputerClause()) {
				if (objectComputerClause.memorySizeClause() != null) {
					result.addMemorySizeClause(objectComputerClause.memorySizeClause());
				}

				if (objectComputerClause.diskSizeClause() != null) {
					result.addDiskSizeClause(objectComputerClause.diskSizeClause());
				}

				if (objectComputerClause.collatingSequenceClause() != null) {
					result.addCollatingSequenceClause(objectComputerClause.collatingSequenceClause());
				}

				if (objectComputerClause.segmentLimitClause() != null) {
					result.addSegmentLimitClause(objectComputerClause.segmentLimitClause());
				}

				if (objectComputerClause.characterSetClause() != null) {
					result.addCharacterSetClause(objectComputerClause.characterSetClause());
				}
			}

			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SourceComputerParagraph addSourceComputerParagraph(final SourceComputerParagraphContext ctx) {
		SourceComputerParagraph result = (SourceComputerParagraph) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new SourceComputerParagraphImpl(name, programUnit, ctx);

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
			result = new SpecialNamesParagraphImpl(programUnit, ctx);

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
