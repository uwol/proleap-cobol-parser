/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.proleap.cobol.Cobol85Parser.ScreenDescriptionAutoClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionBackgroundColorClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionBellClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionBlankClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionBlankWhenZeroClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionBlinkClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionColumnClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionControlClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionEntryContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionEraseClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionForegroundColorClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionFromClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionFullClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionGridClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionJustifiedClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionLightClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionLineClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionPictureClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionPromptClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionRequiredClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionReverseVideoClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionSecureClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionSignClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionSizeClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionUnderlineClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionUsageClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionUsingClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionValueClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenDescriptionZeroFillClauseContext;
import io.proleap.cobol.Cobol85Parser.ScreenSectionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.screen.ScreenDescriptionEntriesSymbolTableEntry;
import io.proleap.cobol.asg.metamodel.data.screen.ScreenDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.screen.ScreenSection;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.util.StringUtils;

public class ScreenSectionImpl extends CobolDivisionElementImpl implements ScreenSection {

	protected final ScreenSectionContext ctx;

	protected List<ScreenDescriptionEntry> screenDescriptionEntries = new ArrayList<ScreenDescriptionEntry>();

	protected Map<String, ScreenDescriptionEntriesSymbolTableEntry> screenDescriptionEntriesSymbolTable = new HashMap<String, ScreenDescriptionEntriesSymbolTableEntry>();

	public ScreenSectionImpl(final ProgramUnit programUnit, final ScreenSectionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ScreenDescriptionEntry addScreenDescriptionEntry(final ScreenDescriptionEntryContext ctx) {
		ScreenDescriptionEntry result = (ScreenDescriptionEntry) getASGElement(ctx);

		if (result == null) {
			final String name = determineName(ctx);
			result = new ScreenDescriptionEntryImpl(name, programUnit, ctx);

			/*
			 * level number
			 */
			final Integer levelNumber;

			if (ctx.INTEGERLITERAL() != null) {
				levelNumber = StringUtils.parseInteger(ctx.INTEGERLITERAL().getText());
			} else {
				levelNumber = null;
			}

			result.setLevelNumber(levelNumber);

			// filler
			if (ctx.FILLER() != null) {
				result.setFiller(true);
			}

			// blank
			for (final ScreenDescriptionBlankClauseContext blankClauseContext : ctx.screenDescriptionBlankClause()) {
				result.addBlankClause(blankClauseContext);
			}

			// bell
			for (final ScreenDescriptionBellClauseContext bellClauseContext : ctx.screenDescriptionBellClause()) {
				result.addBellClause(bellClauseContext);
			}

			// blink
			for (final ScreenDescriptionBlinkClauseContext blinkClauseContext : ctx.screenDescriptionBlinkClause()) {
				result.addBlinkClause(blinkClauseContext);
			}

			// erase
			for (final ScreenDescriptionEraseClauseContext eraseClauseContext : ctx.screenDescriptionEraseClause()) {
				result.addEraseClause(eraseClauseContext);
			}

			// light
			for (final ScreenDescriptionLightClauseContext lightClauseContext : ctx.screenDescriptionLightClause()) {
				result.addLightClause(lightClauseContext);
			}

			// grid
			for (final ScreenDescriptionGridClauseContext gridClauseContext : ctx.screenDescriptionGridClause()) {
				result.addGridClause(gridClauseContext);
			}

			// reverse video
			for (final ScreenDescriptionReverseVideoClauseContext reverseVideoClauseContext : ctx
					.screenDescriptionReverseVideoClause()) {
				result.addReverseVideoClause(reverseVideoClauseContext);
			}

			// underline
			for (final ScreenDescriptionUnderlineClauseContext underlineClauseContext : ctx
					.screenDescriptionUnderlineClause()) {
				result.addUnderlineClause(underlineClauseContext);
			}

			// size
			for (final ScreenDescriptionSizeClauseContext sizeClauseContext : ctx.screenDescriptionSizeClause()) {
				result.addSizeClause(sizeClauseContext);
			}

			// line
			for (final ScreenDescriptionLineClauseContext lineClauseContext : ctx.screenDescriptionLineClause()) {
				result.addLineNumberClause(lineClauseContext);
			}

			// column
			for (final ScreenDescriptionColumnClauseContext columnClauseContext : ctx.screenDescriptionColumnClause()) {
				result.addColumnNumberClause(columnClauseContext);
			}

			// foreground color
			for (final ScreenDescriptionForegroundColorClauseContext foregroundColorClauseContext : ctx
					.screenDescriptionForegroundColorClause()) {
				result.addForegroundColorClause(foregroundColorClauseContext);
			}

			// background color
			for (final ScreenDescriptionBackgroundColorClauseContext backgroundColorClauseContext : ctx
					.screenDescriptionBackgroundColorClause()) {
				result.addBackgroundColorClause(backgroundColorClauseContext);
			}

			// control
			for (final ScreenDescriptionControlClauseContext controlClauseContext : ctx
					.screenDescriptionControlClause()) {
				result.addControlClause(controlClauseContext);
			}

			// value
			for (final ScreenDescriptionValueClauseContext valueClauseContext : ctx.screenDescriptionValueClause()) {
				result.addValueClause(valueClauseContext);
			}

			// picture
			for (final ScreenDescriptionPictureClauseContext pictureClauseContext : ctx
					.screenDescriptionPictureClause()) {
				result.addPictureClause(pictureClauseContext);
			}

			// from
			for (final ScreenDescriptionFromClauseContext fromClauseContext : ctx.screenDescriptionFromClause()) {
				result.addFromClause(fromClauseContext);
			}

			// using
			for (final ScreenDescriptionUsingClauseContext usingClauseContext : ctx.screenDescriptionUsingClause()) {
				result.addUsingClause(usingClauseContext);
			}

			// usage
			for (final ScreenDescriptionUsageClauseContext usageClauseContext : ctx.screenDescriptionUsageClause()) {
				result.addUsageClause(usageClauseContext);
			}

			// blank when zero
			for (final ScreenDescriptionBlankWhenZeroClauseContext blankWhenZeroClauseContext : ctx
					.screenDescriptionBlankWhenZeroClause()) {
				result.addBlankWhenZeroClause(blankWhenZeroClauseContext);
			}

			// justified
			for (final ScreenDescriptionJustifiedClauseContext justifiedClauseContext : ctx
					.screenDescriptionJustifiedClause()) {
				result.addJustifiedClause(justifiedClauseContext);
			}

			// sign
			for (final ScreenDescriptionSignClauseContext signClauseContext : ctx.screenDescriptionSignClause()) {
				result.addSignClause(signClauseContext);
			}

			// auto
			for (final ScreenDescriptionAutoClauseContext autoClauseContext : ctx.screenDescriptionAutoClause()) {
				result.addAutoClause(autoClauseContext);
			}

			// secure
			for (final ScreenDescriptionSecureClauseContext secureClauseContext : ctx.screenDescriptionSecureClause()) {
				result.addSecureClause(secureClauseContext);
			}

			// required
			for (final ScreenDescriptionRequiredClauseContext requiredClauseContext : ctx
					.screenDescriptionRequiredClause()) {
				result.addRequiredClause(requiredClauseContext);
			}

			// prompt
			for (final ScreenDescriptionPromptClauseContext promptClauseContext : ctx.screenDescriptionPromptClause()) {
				result.addPromptClause(promptClauseContext);
			}

			// full
			for (final ScreenDescriptionFullClauseContext fullClauseContext : ctx.screenDescriptionFullClause()) {
				result.addFullClause(fullClauseContext);
			}

			// zero fill
			for (final ScreenDescriptionZeroFillClauseContext zeroFillClauseContext : ctx
					.screenDescriptionZeroFillClause()) {
				result.addZeroFillClause(zeroFillClauseContext);
			}

			registerASGElement(result);

			screenDescriptionEntries.add(result);
			assureScreenDescriptionEntriesSymbolTableEntry(name).addScreenDescriptionEntry(result);
		}

		return result;
	}

	protected ScreenDescriptionEntriesSymbolTableEntry assureScreenDescriptionEntriesSymbolTableEntry(
			final String name) {
		ScreenDescriptionEntriesSymbolTableEntry screenDescriptionEntriesSymbolTableEntry = screenDescriptionEntriesSymbolTable
				.get(getSymbol(name));

		if (screenDescriptionEntriesSymbolTableEntry == null) {
			screenDescriptionEntriesSymbolTableEntry = new ScreenDescriptionEntriesSymbolTableEntryImpl();
			screenDescriptionEntriesSymbolTable.put(getSymbol(name), screenDescriptionEntriesSymbolTableEntry);
		}

		return screenDescriptionEntriesSymbolTableEntry;
	}

	@Override
	public List<ScreenDescriptionEntry> getRootScreenDescriptionEntries() {
		final List<ScreenDescriptionEntry> result = new ArrayList<ScreenDescriptionEntry>();

		for (final ScreenDescriptionEntry screenDescriptionEntry : screenDescriptionEntries) {
			if (screenDescriptionEntry.getParentScreenDescriptionEntry() == null) {
				result.add(screenDescriptionEntry);
			}
		}

		return result;
	}

	@Override
	public List<ScreenDescriptionEntry> getScreenDescriptionEntries() {
		return screenDescriptionEntries;
	}

	@Override
	public List<ScreenDescriptionEntry> getScreenDescriptionEntries(final String name) {
		return screenDescriptionEntriesSymbolTable.get(getSymbol(name)) == null ? null
				: screenDescriptionEntriesSymbolTable.get(getSymbol(name)).getScreenDescriptionEntries();
	}

	@Override
	public ScreenDescriptionEntry getScreenDescriptionEntry(final String name) {
		return screenDescriptionEntriesSymbolTable.get(getSymbol(name)) == null ? null
				: screenDescriptionEntriesSymbolTable.get(getSymbol(name)).getScreenDescriptionEntry();
	}
}
