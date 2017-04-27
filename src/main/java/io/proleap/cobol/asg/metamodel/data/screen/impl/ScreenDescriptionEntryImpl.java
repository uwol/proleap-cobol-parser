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

import io.proleap.cobol.Cobol85Parser.PictureStringContext;
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
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.call.ScreenDescriptionEntryCall;
import io.proleap.cobol.asg.metamodel.data.screen.AutoClause;
import io.proleap.cobol.asg.metamodel.data.screen.BackgroundColorClause;
import io.proleap.cobol.asg.metamodel.data.screen.BellClause;
import io.proleap.cobol.asg.metamodel.data.screen.BlankClause;
import io.proleap.cobol.asg.metamodel.data.screen.BlankWhenZeroClause;
import io.proleap.cobol.asg.metamodel.data.screen.BlinkClause;
import io.proleap.cobol.asg.metamodel.data.screen.ColumnNumberClause;
import io.proleap.cobol.asg.metamodel.data.screen.ControlClause;
import io.proleap.cobol.asg.metamodel.data.screen.EraseClause;
import io.proleap.cobol.asg.metamodel.data.screen.ForegroundColorClause;
import io.proleap.cobol.asg.metamodel.data.screen.FromClause;
import io.proleap.cobol.asg.metamodel.data.screen.FullClause;
import io.proleap.cobol.asg.metamodel.data.screen.GridClause;
import io.proleap.cobol.asg.metamodel.data.screen.JustifiedClause;
import io.proleap.cobol.asg.metamodel.data.screen.LightClause;
import io.proleap.cobol.asg.metamodel.data.screen.LineNumberClause;
import io.proleap.cobol.asg.metamodel.data.screen.PictureClause;
import io.proleap.cobol.asg.metamodel.data.screen.PromptClause;
import io.proleap.cobol.asg.metamodel.data.screen.RequiredClause;
import io.proleap.cobol.asg.metamodel.data.screen.ReverseVideoClause;
import io.proleap.cobol.asg.metamodel.data.screen.ScreenDescriptionEntry;
import io.proleap.cobol.asg.metamodel.data.screen.SecureClause;
import io.proleap.cobol.asg.metamodel.data.screen.SignClause;
import io.proleap.cobol.asg.metamodel.data.screen.SizeClause;
import io.proleap.cobol.asg.metamodel.data.screen.UnderlineClause;
import io.proleap.cobol.asg.metamodel.data.screen.UsageClause;
import io.proleap.cobol.asg.metamodel.data.screen.UsingClause;
import io.proleap.cobol.asg.metamodel.data.screen.ValueClause;
import io.proleap.cobol.asg.metamodel.data.screen.ZeroFillClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class ScreenDescriptionEntryImpl extends CobolDivisionElementImpl implements ScreenDescriptionEntry {

	protected AutoClause autoClause;

	protected BackgroundColorClause backgroundColorClause;

	protected BellClause bellClause;

	protected BlankClause blankClause;

	protected BlankWhenZeroClause blankWhenZeroClause;

	protected BlinkClause blinkClause;

	protected final List<ScreenDescriptionEntryCall> calls = new ArrayList<ScreenDescriptionEntryCall>();

	protected ColumnNumberClause columnNumberClause;

	protected ControlClause controlClause;

	protected ScreenDescriptionEntryContext ctx;

	protected EraseClause eraseClause;

	protected Boolean filler;

	protected ForegroundColorClause foregroundColorClause;

	protected FromClause fromClause;

	protected FullClause fullClause;

	protected GridClause gridClause;

	protected JustifiedClause justifiedClause;

	protected Integer levelNumber;

	protected LightClause lightClause;

	protected LineNumberClause lineNumberClause;

	protected final String name;

	protected ScreenDescriptionEntry parentScreenDescriptionEntry;

	protected PictureClause pictureClause;

	protected PromptClause promptClause;

	protected RequiredClause requiredClause;

	protected ReverseVideoClause reverseVideoClause;

	protected List<ScreenDescriptionEntry> screenDescriptionEntries = new ArrayList<ScreenDescriptionEntry>();

	protected Map<String, ScreenDescriptionEntry> screenDescriptionEntriesSymbolTable = new HashMap<String, ScreenDescriptionEntry>();

	protected SecureClause secureClause;

	protected SignClause signClause;

	protected SizeClause sizeClause;

	protected UnderlineClause underlineClause;

	protected UsageClause usageClause;

	protected UsingClause usingClause;

	protected ValueClause valueClause;

	protected ZeroFillClause zeroFillClause;

	public ScreenDescriptionEntryImpl(final String name, final ProgramUnit programUnit,
			final ScreenDescriptionEntryContext ctx) {
		super(programUnit, ctx);

		this.name = name;
		this.ctx = ctx;
	}

	@Override
	public AutoClause addAutoClause(final ScreenDescriptionAutoClauseContext ctx) {
		AutoClause result = (AutoClause) getASGElement(ctx);

		if (result == null) {
			result = new AutoClauseImpl(programUnit, ctx);

			// type
			final AutoClause.Type type;

			if (ctx.AUTO() != null) {
				type = AutoClause.Type.AUTO;
			} else if (ctx.AUTO_SKIP() != null) {
				type = AutoClause.Type.AUTO_SKIP;
			} else {
				type = null;
			}

			result.setType(type);

			autoClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public BackgroundColorClause addBackgroundColorClause(final ScreenDescriptionBackgroundColorClauseContext ctx) {
		BackgroundColorClause result = (BackgroundColorClause) getASGElement(ctx);

		if (result == null) {
			result = new BackgroundColorClauseImpl(programUnit, ctx);

			final Call colorCall = createCall(ctx.identifier(), ctx.integerLiteral());
			result.setColorCall(colorCall);

			backgroundColorClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public BellClause addBellClause(final ScreenDescriptionBellClauseContext ctx) {
		BellClause result = (BellClause) getASGElement(ctx);

		if (result == null) {
			result = new BellClauseImpl(programUnit, ctx);

			// type
			final BellClause.Type type;

			if (ctx.BELL() != null) {
				type = BellClause.Type.BELL;
			} else if (ctx.BEEP() != null) {
				type = BellClause.Type.BEEP;
			} else {
				type = null;
			}

			result.setType(type);

			bellClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public BlankClause addBlankClause(final ScreenDescriptionBlankClauseContext ctx) {
		BlankClause result = (BlankClause) getASGElement(ctx);

		if (result == null) {
			result = new BlankClauseImpl(programUnit, ctx);

			// type
			final BlankClause.Type type;

			if (ctx.SCREEN() != null) {
				type = BlankClause.Type.SCREEN;
			} else if (ctx.LINE() != null) {
				type = BlankClause.Type.LINE;
			} else {
				type = null;
			}

			result.setType(type);

			blankClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public BlankWhenZeroClause addBlankWhenZeroClause(final ScreenDescriptionBlankWhenZeroClauseContext ctx) {
		BlankWhenZeroClause result = (BlankWhenZeroClause) getASGElement(ctx);

		if (result == null) {
			result = new BlankWhenZeroClauseImpl(programUnit, ctx);

			result.setBlankWhenZero(true);

			blankWhenZeroClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public BlinkClause addBlinkClause(final ScreenDescriptionBlinkClauseContext ctx) {
		BlinkClause result = (BlinkClause) getASGElement(ctx);

		if (result == null) {
			result = new BlinkClauseImpl(programUnit, ctx);

			result.setBlink(true);

			blinkClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public void addCall(final ScreenDescriptionEntryCall call) {
		calls.add(call);
	}

	@Override
	public ColumnNumberClause addColumnNumberClause(final ScreenDescriptionColumnClauseContext ctx) {
		ColumnNumberClause result = (ColumnNumberClause) getASGElement(ctx);

		if (result == null) {
			result = new ColumnNumberClauseImpl(programUnit, ctx);

			// type
			final ColumnNumberClause.Type type;

			if (ctx.PLUS() != null) {
				type = ColumnNumberClause.Type.PLUS;
			} else if (ctx.PLUSCHAR() != null) {
				type = ColumnNumberClause.Type.PLUS;
			} else if (ctx.MINUSCHAR() != null) {
				type = ColumnNumberClause.Type.MINUS;
			} else {
				type = ColumnNumberClause.Type.EQUAL;
			}

			result.setType(type);

			// line number
			final IntegerLiteral lineNumber = createIntegerLiteral(ctx.integerLiteral());
			result.setIntegerLiteral(lineNumber);

			columnNumberClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ControlClause addControlClause(final ScreenDescriptionControlClauseContext ctx) {
		ControlClause result = (ControlClause) getASGElement(ctx);

		if (result == null) {
			result = new ControlClauseImpl(programUnit, ctx);

			final Call controlCall = createCall(ctx.identifier());
			result.setControlCall(controlCall);

			controlClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public EraseClause addEraseClause(final ScreenDescriptionEraseClauseContext ctx) {
		EraseClause result = (EraseClause) getASGElement(ctx);

		if (result == null) {
			result = new EraseClauseImpl(programUnit, ctx);

			// type
			final EraseClause.Type type;

			if (ctx.EOL() != null) {
				type = EraseClause.Type.EOL;
			} else if (ctx.EOS() != null) {
				type = EraseClause.Type.EOS;
			} else {
				type = null;
			}

			result.setType(type);

			eraseClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ForegroundColorClause addForegroundColorClause(final ScreenDescriptionForegroundColorClauseContext ctx) {
		ForegroundColorClause result = (ForegroundColorClause) getASGElement(ctx);

		if (result == null) {
			result = new ForegroundColorClauseImpl(programUnit, ctx);

			final Call colorCall = createCall(ctx.identifier(), ctx.integerLiteral());
			result.setColorCall(colorCall);

			foregroundColorClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public FromClause addFromClause(final ScreenDescriptionFromClauseContext ctx) {
		FromClause result = (FromClause) getASGElement(ctx);

		if (result == null) {
			result = new FromClauseImpl(programUnit, ctx);

			// from
			final Call fromCall = createCall(ctx.identifier(), ctx.literal());
			result.setFromCall(fromCall);

			// to
			if (ctx.screenDescriptionToClause() != null) {
				result.addTo(ctx.screenDescriptionToClause());
			}

			fromClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public FullClause addFullClause(final ScreenDescriptionFullClauseContext ctx) {
		FullClause result = (FullClause) getASGElement(ctx);

		if (result == null) {
			result = new FullClauseImpl(programUnit, ctx);

			// type
			final FullClause.Type type;

			if (ctx.FULL() != null) {
				type = FullClause.Type.FULL;
			} else if (ctx.LENGTH_CHECK() != null) {
				type = FullClause.Type.LENGTH_CHECK;
			} else {
				type = null;
			}

			result.setType(type);

			fullClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public GridClause addGridClause(final ScreenDescriptionGridClauseContext ctx) {
		GridClause result = (GridClause) getASGElement(ctx);

		if (result == null) {
			result = new GridClauseImpl(programUnit, ctx);

			// type
			final GridClause.Type type;

			if (ctx.GRID() != null) {
				type = GridClause.Type.GRID;
			} else if (ctx.LEFTLINE() != null) {
				type = GridClause.Type.LEFTLINE;
			} else if (ctx.OVERLINE() != null) {
				type = GridClause.Type.OVERLINE;
			} else {
				type = null;
			}

			result.setType(type);

			gridClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public JustifiedClause addJustifiedClause(final ScreenDescriptionJustifiedClauseContext ctx) {
		JustifiedClause result = (JustifiedClause) getASGElement(ctx);

		if (result == null) {
			result = new JustifiedClauseImpl(programUnit, ctx);

			// type
			final JustifiedClause.Justified type;

			if (ctx.RIGHT() != null) {
				type = JustifiedClause.Justified.JUSTIFIED_RIGHT;
			} else {
				type = JustifiedClause.Justified.JUSTIFIED;
			}

			result.setJustified(type);

			justifiedClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public LightClause addLightClause(final ScreenDescriptionLightClauseContext ctx) {
		LightClause result = (LightClause) getASGElement(ctx);

		if (result == null) {
			result = new LightClauseImpl(programUnit, ctx);

			// type
			final LightClause.Type type;

			if (ctx.HIGHLIGHT() != null) {
				type = LightClause.Type.HIGHLIGHT;
			} else if (ctx.LOWLIGHT() != null) {
				type = LightClause.Type.LOWLIGHT;
			} else {
				type = null;
			}

			result.setType(type);

			lightClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public LineNumberClause addLineNumberClause(final ScreenDescriptionLineClauseContext ctx) {
		LineNumberClause result = (LineNumberClause) getASGElement(ctx);

		if (result == null) {
			result = new LineNumberClauseImpl(programUnit, ctx);

			// type
			final LineNumberClause.Type type;

			if (ctx.PLUS() != null) {
				type = LineNumberClause.Type.PLUS;
			} else if (ctx.PLUSCHAR() != null) {
				type = LineNumberClause.Type.PLUS;
			} else if (ctx.MINUSCHAR() != null) {
				type = LineNumberClause.Type.MINUS;
			} else {
				type = LineNumberClause.Type.EQUAL;
			}

			result.setType(type);

			// line number
			final IntegerLiteral lineNumber = createIntegerLiteral(ctx.integerLiteral());
			result.setIntegerLiteral(lineNumber);

			lineNumberClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public PictureClause addPictureClause(final ScreenDescriptionPictureClauseContext ctx) {
		PictureClause result = (PictureClause) getASGElement(ctx);

		if (result == null) {
			result = new PictureClauseImpl(programUnit, ctx);

			final PictureStringContext pictureString = ctx.pictureString();
			result.setPictureString(pictureString.getText());

			pictureClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public PromptClause addPromptClause(final ScreenDescriptionPromptClauseContext ctx) {
		PromptClause result = (PromptClause) getASGElement(ctx);

		if (result == null) {
			result = new PromptClauseImpl(programUnit, ctx);

			// character call
			final Call characterCall = createCall(ctx.identifier(), ctx.literal());
			result.setCharacterCall(characterCall);

			// occurs
			if (ctx.screenDescriptionPromptOccursClause() != null) {
				result.addOccurs(ctx.screenDescriptionPromptOccursClause());
			}

			promptClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public RequiredClause addRequiredClause(final ScreenDescriptionRequiredClauseContext ctx) {
		RequiredClause result = (RequiredClause) getASGElement(ctx);

		if (result == null) {
			result = new RequiredClauseImpl(programUnit, ctx);

			// type
			final RequiredClause.Type type;

			if (ctx.REQUIRED() != null) {
				type = RequiredClause.Type.REQUIRED;
			} else if (ctx.EMPTY_CHECK() != null) {
				type = RequiredClause.Type.EMPTY_CHECK;
			} else {
				type = null;
			}

			result.setType(type);

			requiredClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ReverseVideoClause addReverseVideoClause(final ScreenDescriptionReverseVideoClauseContext ctx) {
		ReverseVideoClause result = (ReverseVideoClause) getASGElement(ctx);

		if (result == null) {
			result = new ReverseVideoClauseImpl(programUnit, ctx);

			result.setReverseVideo(true);

			reverseVideoClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public void addScreenDescriptionEntry(final ScreenDescriptionEntry screenDescriptionEntry) {
		final String name = screenDescriptionEntry.getName();

		screenDescriptionEntries.add(screenDescriptionEntry);
		screenDescriptionEntriesSymbolTable.put(name, screenDescriptionEntry);
	}

	@Override
	public SecureClause addSecureClause(final ScreenDescriptionSecureClauseContext ctx) {
		SecureClause result = (SecureClause) getASGElement(ctx);

		if (result == null) {
			result = new SecureClauseImpl(programUnit, ctx);

			// type
			final SecureClause.Type type;

			if (ctx.SECURE() != null) {
				type = SecureClause.Type.SECURE;
			} else if (ctx.NO_ECHO() != null) {
				type = SecureClause.Type.NO_ECHO;
			} else {
				type = null;
			}

			result.setType(type);

			secureClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SignClause addSignClause(final ScreenDescriptionSignClauseContext ctx) {
		SignClause result = (SignClause) getASGElement(ctx);

		if (result == null) {
			result = new SignClauseImpl(programUnit, ctx);

			// type
			final SignClause.Type type;

			if (ctx.LEADING() != null) {
				type = SignClause.Type.LEADING;
			} else if (ctx.TRAILING() != null) {
				type = SignClause.Type.TRAILING;
			} else {
				type = null;
			}

			result.setType(type);

			signClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SizeClause addSizeClause(final ScreenDescriptionSizeClauseContext ctx) {
		SizeClause result = (SizeClause) getASGElement(ctx);

		if (result == null) {
			result = new SizeClauseImpl(programUnit, ctx);

			final Call sizeCall = createCall(ctx.identifier(), ctx.integerLiteral());
			result.setSizeCall(sizeCall);

			sizeClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public UnderlineClause addUnderlineClause(final ScreenDescriptionUnderlineClauseContext ctx) {
		UnderlineClause result = (UnderlineClause) getASGElement(ctx);

		if (result == null) {
			result = new UnderlineClauseImpl(programUnit, ctx);

			result.setUnderline(true);

			underlineClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public UsageClause addUsageClause(final ScreenDescriptionUsageClauseContext ctx) {
		UsageClause result = (UsageClause) getASGElement(ctx);

		if (result == null) {
			result = new UsageClauseImpl(programUnit, ctx);

			// type
			final UsageClause.Type type;

			if (ctx.DISPLAY() != null) {
				type = UsageClause.Type.DISPLAY;
			} else if (ctx.DISPLAY_1() != null) {
				type = UsageClause.Type.DISPLAY_1;
			} else {
				type = null;
			}

			result.setType(type);

			usageClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public UsingClause addUsingClause(final ScreenDescriptionUsingClauseContext ctx) {
		UsingClause result = (UsingClause) getASGElement(ctx);

		if (result == null) {
			result = new UsingClauseImpl(programUnit, ctx);

			final Call usingCall = createCall(ctx.identifier());
			result.setUsingCall(usingCall);

			usingClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ValueClause addValueClause(final ScreenDescriptionValueClauseContext ctx) {
		ValueClause result = (ValueClause) getASGElement(ctx);

		if (result == null) {
			result = new ValueClauseImpl(programUnit, ctx);

			final Literal literal = createLiteral(ctx.literal());
			result.setLiteral(literal);

			valueClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ZeroFillClause addZeroFillClause(final ScreenDescriptionZeroFillClauseContext ctx) {
		ZeroFillClause result = (ZeroFillClause) getASGElement(ctx);

		if (result == null) {
			result = new ZeroFillClauseImpl(programUnit, ctx);

			result.setZeroFill(true);

			zeroFillClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AutoClause getAutoClause() {
		return autoClause;
	}

	@Override
	public BackgroundColorClause getBackgroundColorClause() {
		return backgroundColorClause;
	}

	@Override
	public BellClause getBellClause() {
		return bellClause;
	}

	@Override
	public BlankClause getBlankClause() {
		return blankClause;
	}

	@Override
	public BlankWhenZeroClause getBlankWhenZeroClause() {
		return blankWhenZeroClause;
	}

	@Override
	public BlinkClause getBlinkClause() {
		return blinkClause;
	}

	@Override
	public List<ScreenDescriptionEntryCall> getCalls() {
		return calls;
	}

	@Override
	public ColumnNumberClause getColumnNumberClause() {
		return columnNumberClause;
	}

	@Override
	public ControlClause getControlClause() {
		return controlClause;
	}

	@Override
	public EraseClause getEraseClause() {
		return eraseClause;
	}

	@Override
	public Boolean getFiller() {
		return filler;
	}

	@Override
	public ForegroundColorClause getForegroundColorClause() {
		return foregroundColorClause;
	}

	@Override
	public FromClause getFromClause() {
		return fromClause;
	}

	@Override
	public FullClause getFullClause() {
		return fullClause;
	}

	@Override
	public GridClause getGridClause() {
		return gridClause;
	}

	@Override
	public JustifiedClause getJustifiedClause() {
		return justifiedClause;
	}

	@Override
	public Integer getLevelNumber() {
		return levelNumber;
	}

	@Override
	public LightClause getLightClause() {
		return lightClause;
	}

	@Override
	public LineNumberClause getLineNumberClause() {
		return lineNumberClause;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ScreenDescriptionEntry getParentScreenDescriptionEntry() {
		return parentScreenDescriptionEntry;
	}

	@Override
	public PictureClause getPictureClause() {
		return pictureClause;
	}

	@Override
	public PromptClause getPromptClause() {
		return promptClause;
	}

	@Override
	public RequiredClause getRequiredClause() {
		return requiredClause;
	}

	@Override
	public ReverseVideoClause getReverseVideoClause() {
		return reverseVideoClause;
	}

	@Override
	public List<ScreenDescriptionEntry> getScreenDescriptionEntries() {
		return screenDescriptionEntries;
	}

	@Override
	public ScreenDescriptionEntry getScreenDescriptionEntry(final String name) {
		return screenDescriptionEntriesSymbolTable.get(name);
	}

	@Override
	public SecureClause getSecureClause() {
		return secureClause;
	}

	@Override
	public SignClause getSignClause() {
		return signClause;
	}

	@Override
	public SizeClause getSizeClause() {
		return sizeClause;
	}

	@Override
	public UnderlineClause getUnderlineClause() {
		return underlineClause;
	}

	@Override
	public UsageClause getUsageClause() {
		return usageClause;
	}

	@Override
	public UsingClause getUsingClause() {
		return usingClause;
	}

	@Override
	public ValueClause getValueClause() {
		return valueClause;
	}

	@Override
	public ZeroFillClause getZeroFillClause() {
		return zeroFillClause;
	}

	@Override
	public void setFiller(final Boolean filler) {
		this.filler = filler;
	}

	@Override
	public void setLevelNumber(final Integer levelNumber) {
		this.levelNumber = levelNumber;
	}

	@Override
	public void setParentScreenDescriptionEntry(final ScreenDescriptionEntry parentScreenDescriptionEntry) {
		this.parentScreenDescriptionEntry = parentScreenDescriptionEntry;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}
}
