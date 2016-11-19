/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.ConfigurationSectionContext;
import io.proleap.cobol.Cobol85Parser.ConfigurationSectionParagraphContext;
import io.proleap.cobol.Cobol85Parser.EnvironmentDivisionContext;
import io.proleap.cobol.Cobol85Parser.InputOutputSectionContext;
import io.proleap.cobol.Cobol85Parser.InputOutputSectionParagraphContext;
import io.proleap.cobol.Cobol85Parser.SpecialNameClauseContext;
import io.proleap.cobol.Cobol85Parser.SpecialNamesParagraphContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.EnvironmentDivision;
import io.proleap.cobol.parser.metamodel.environment.configuration.ConfigurationSection;
import io.proleap.cobol.parser.metamodel.environment.configuration.impl.ConfigurationSectionImpl;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.InputOutputSection;
import io.proleap.cobol.parser.metamodel.environment.inputoutput.impl.InputOutputSectionImpl;
import io.proleap.cobol.parser.metamodel.environment.specialnames.SpecialNamesParagraph;
import io.proleap.cobol.parser.metamodel.environment.specialnames.impl.SpecialNamesParagraphImpl;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionImpl;

public class EnvironmentDivisionImpl extends CobolDivisionImpl implements EnvironmentDivision {

	private final static Logger LOG = LogManager.getLogger(EnvironmentDivisionImpl.class);

	protected ConfigurationSection configurationSection;

	protected final EnvironmentDivisionContext ctx;

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
					result.addSourceComputerParagraph(configurationSectionParagraphContext.sourceComputerParagraph());
				} else if (configurationSectionParagraphContext.objectComputerParagraph() != null) {
					result.addObjectComputerParagraph(configurationSectionParagraphContext.objectComputerParagraph());
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
	public InputOutputSection addInputOutputSection(final InputOutputSectionContext ctx) {
		InputOutputSection result = (InputOutputSection) getASGElement(ctx);

		if (result == null) {
			result = new InputOutputSectionImpl(programUnit, ctx);

			for (final InputOutputSectionParagraphContext inputOutputSectionParagraphContext : ctx
					.inputOutputSectionParagraph()) {
				if (inputOutputSectionParagraphContext.fileControlParagraph() != null) {
					result.addFileControlParagraph(inputOutputSectionParagraphContext.fileControlParagraph());
				} else if (inputOutputSectionParagraphContext.ioControlParagraph() != null) {
					result.addIoControlParagraph(inputOutputSectionParagraphContext.ioControlParagraph());
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
	public SpecialNamesParagraph addSpecialNamesParagraph(final SpecialNamesParagraphContext ctx) {
		SpecialNamesParagraph result = (SpecialNamesParagraph) getASGElement(ctx);

		if (result == null) {
			result = new SpecialNamesParagraphImpl(programUnit, ctx);

			for (final SpecialNameClauseContext specialNameClauseContext : ctx.specialNameClause()) {
				if (specialNameClauseContext.classClause() != null) {
					result.addClassClause(specialNameClauseContext.classClause());
				} else if (specialNameClauseContext.channelClause() != null) {
					result.addChannelClause(specialNameClauseContext.channelClause());
				} else if (specialNameClauseContext.currencySignClause() != null) {
					result.addCurrencySignClause(specialNameClauseContext.currencySignClause());
				} else if (specialNameClauseContext.decimalPointClause() != null) {
					result.addDecimalPointClause(specialNameClauseContext.decimalPointClause());
				} else if (specialNameClauseContext.defaultDisplaySignClause() != null) {
					result.addDefaultDisplaySignClause(specialNameClauseContext.defaultDisplaySignClause());
				} else if (specialNameClauseContext.odtClause() != null) {
					result.addOdtClause(specialNameClauseContext.odtClause());
				} else if (specialNameClauseContext.reserveNetworkClause() != null) {
					result.addReserveNetworkClause(specialNameClauseContext.reserveNetworkClause());
				} else if (specialNameClauseContext.symbolicCharactersClause() != null) {
					result.addSymbolicCharactersClause(specialNameClauseContext.symbolicCharactersClause());
				} else if (specialNameClauseContext.alphabetClause() != null) {
					result.createAlphabetClause(specialNameClauseContext.alphabetClause());
				} else {
					LOG.warn("unknown special name clause {}", specialNameClauseContext);
				}
			}

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
	public InputOutputSection getInputOutputSection() {
		return inputOutputSection;
	}

	@Override
	public SpecialNamesParagraph getSpecialNamesParagraph() {
		return specialNamesParagraph;
	}

}
