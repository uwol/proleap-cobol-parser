/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen;

import java.util.List;

import io.proleap.cobol.CobolParser.ScreenDescriptionAutoClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionBackgroundColorClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionBellClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionBlankClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionBlankWhenZeroClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionBlinkClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionColumnClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionControlClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionEraseClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionForegroundColorClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionFromClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionFullClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionGridClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionJustifiedClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionLightClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionLineClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionPictureClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionPromptClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionRequiredClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionReverseVideoClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionSecureClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionSignClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionSizeClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionUnderlineClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionUsageClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionUsingClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionValueClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionZeroFillClauseContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.Declaration;
import io.proleap.cobol.asg.metamodel.call.ScreenDescriptionEntryCall;

public interface ScreenDescriptionEntry extends CobolDivisionElement, Declaration {

	AutoClause addAutoClause(ScreenDescriptionAutoClauseContext ctx);

	BackgroundColorClause addBackgroundColorClause(ScreenDescriptionBackgroundColorClauseContext ctx);

	BellClause addBellClause(ScreenDescriptionBellClauseContext ctx);

	BlankClause addBlankClause(ScreenDescriptionBlankClauseContext ctx);

	BlankWhenZeroClause addBlankWhenZeroClause(ScreenDescriptionBlankWhenZeroClauseContext ctx);

	BlinkClause addBlinkClause(ScreenDescriptionBlinkClauseContext ctx);

	void addCall(ScreenDescriptionEntryCall call);

	ColumnNumberClause addColumnNumberClause(ScreenDescriptionColumnClauseContext ctx);

	ControlClause addControlClause(ScreenDescriptionControlClauseContext ctx);

	EraseClause addEraseClause(ScreenDescriptionEraseClauseContext ctx);

	ForegroundColorClause addForegroundColorClause(ScreenDescriptionForegroundColorClauseContext ctx);

	FromClause addFromClause(ScreenDescriptionFromClauseContext ctx);

	FullClause addFullClause(ScreenDescriptionFullClauseContext ctx);

	GridClause addGridClause(ScreenDescriptionGridClauseContext ctx);

	JustifiedClause addJustifiedClause(ScreenDescriptionJustifiedClauseContext ctx);

	LightClause addLightClause(ScreenDescriptionLightClauseContext ctx);

	LineNumberClause addLineNumberClause(ScreenDescriptionLineClauseContext ctx);

	PictureClause addPictureClause(ScreenDescriptionPictureClauseContext ctx);

	PromptClause addPromptClause(ScreenDescriptionPromptClauseContext ctx);

	RequiredClause addRequiredClause(ScreenDescriptionRequiredClauseContext ctx);

	ReverseVideoClause addReverseVideoClause(ScreenDescriptionReverseVideoClauseContext ctx);

	void addScreenDescriptionEntry(ScreenDescriptionEntry screenDescriptionEntry);

	SecureClause addSecureClause(ScreenDescriptionSecureClauseContext ctx);

	SignClause addSignClause(ScreenDescriptionSignClauseContext ctx);

	SizeClause addSizeClause(ScreenDescriptionSizeClauseContext ctx);

	UnderlineClause addUnderlineClause(ScreenDescriptionUnderlineClauseContext ctx);

	UsageClause addUsageClause(ScreenDescriptionUsageClauseContext ctx);

	UsingClause addUsingClause(ScreenDescriptionUsingClauseContext ctx);

	ValueClause addValueClause(ScreenDescriptionValueClauseContext ctx);

	ZeroFillClause addZeroFillClause(ScreenDescriptionZeroFillClauseContext ctx);

	AutoClause getAutoClause();

	BackgroundColorClause getBackgroundColorClause();

	BellClause getBellClause();

	BlankClause getBlankClause();

	BlankWhenZeroClause getBlankWhenZeroClause();

	BlinkClause getBlinkClause();

	List<ScreenDescriptionEntryCall> getCalls();

	ColumnNumberClause getColumnNumberClause();

	ControlClause getControlClause();

	EraseClause getEraseClause();

	Boolean getFiller();

	ForegroundColorClause getForegroundColorClause();

	FromClause getFromClause();

	FullClause getFullClause();

	GridClause getGridClause();

	JustifiedClause getJustifiedClause();

	Integer getLevelNumber();

	LightClause getLightClause();

	LineNumberClause getLineNumberClause();

	ScreenDescriptionEntry getParentScreenDescriptionEntry();

	PictureClause getPictureClause();

	ScreenDescriptionEntry getPredecessor();

	PromptClause getPromptClause();

	RequiredClause getRequiredClause();

	ReverseVideoClause getReverseVideoClause();

	List<ScreenDescriptionEntry> getScreenDescriptionEntries();

	ScreenDescriptionEntry getScreenDescriptionEntry(String name);

	SecureClause getSecureClause();

	SignClause getSignClause();

	SizeClause getSizeClause();

	ScreenDescriptionEntry getSuccessor();

	UnderlineClause getUnderlineClause();

	UsageClause getUsageClause();

	UsingClause getUsingClause();

	ValueClause getValueClause();

	ZeroFillClause getZeroFillClause();

	void setFiller(Boolean filler);

	void setLevelNumber(Integer levelNumber);

	void setParentScreenDescriptionEntry(ScreenDescriptionEntry parentScreenDescriptionEntry);

	void setPredecessor(ScreenDescriptionEntry predecessor);

	void setSuccessor(ScreenDescriptionEntry successor);

}
