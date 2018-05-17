/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.screen.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.proleap.cobol.CobolParser.PictureStringContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionAutoClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionBackgroundColorClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionBellClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionBlankClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionBlankWhenZeroClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionBlinkClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionColumnClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionControlClauseContext;
import io.proleap.cobol.CobolParser.ScreenDescriptionEntryContext;
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
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

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

	protected ScreenDescriptionEntry predecessor;

	protected PromptClause promptClause;

	protected RequiredClause requiredClause;

	protected ReverseVideoClause reverseVideoClause;

	protected List<ScreenDescriptionEntry> screenDescriptionEntries = new ArrayList<ScreenDescriptionEntry>();

	protected Map<String, ScreenDescriptionEntry> screenDescriptionEntriesSymbolTable = new HashMap<String, ScreenDescriptionEntry>();

	protected SecureClause secureClause;

	protected SignClause signClause;

	protected SizeClause sizeClause;

	protected ScreenDescriptionEntry successor;

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
			final AutoClause.AutoClauseType type;

			if (ctx.AUTO() != null) {
				type = AutoClause.AutoClauseType.AUTO;
			} else if (ctx.AUTO_SKIP() != null) {
				type = AutoClause.AutoClauseType.AUTO_SKIP;
			} else {
				type = null;
			}

			result.setAutoClauseType(type);

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

			final ValueStmt colorValueStmt = createValueStmt(ctx.identifier(), ctx.integerLiteral());
			result.setColorValueStmt(colorValueStmt);

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
			final BellClause.BellClauseType type;

			if (ctx.BELL() != null) {
				type = BellClause.BellClauseType.BELL;
			} else if (ctx.BEEP() != null) {
				type = BellClause.BellClauseType.BEEP;
			} else {
				type = null;
			}

			result.setBellClauseType(type);

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
			final BlankClause.BlankClauseType type;

			if (ctx.SCREEN() != null) {
				type = BlankClause.BlankClauseType.SCREEN;
			} else if (ctx.LINE() != null) {
				type = BlankClause.BlankClauseType.LINE;
			} else {
				type = null;
			}

			result.setBlankClauseType(type);

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
			final ColumnNumberClause.ColumnNumberClauseType type;

			if (ctx.PLUS() != null) {
				type = ColumnNumberClause.ColumnNumberClauseType.PLUS;
			} else if (ctx.PLUSCHAR() != null) {
				type = ColumnNumberClause.ColumnNumberClauseType.PLUS;
			} else if (ctx.MINUSCHAR() != null) {
				type = ColumnNumberClause.ColumnNumberClauseType.MINUS;
			} else {
				type = ColumnNumberClause.ColumnNumberClauseType.EQUAL;
			}

			result.setColumnNumberClauseType(type);

			// line number
			if (ctx.integerLiteral() != null) {
				final IntegerLiteral lineNumber = createIntegerLiteral(ctx.integerLiteral());
				result.setIntegerLiteral(lineNumber);
			}

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

			if (ctx.identifier() != null) {
				final Call controlCall = createCall(ctx.identifier());
				result.setControlCall(controlCall);
			}

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
			final EraseClause.EraseClauseType type;

			if (ctx.EOL() != null) {
				type = EraseClause.EraseClauseType.EOL;
			} else if (ctx.EOS() != null) {
				type = EraseClause.EraseClauseType.EOS;
			} else {
				type = null;
			}

			result.setEraseClauseType(type);

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

			final ValueStmt colorValueStmt = createValueStmt(ctx.identifier(), ctx.integerLiteral());
			result.setColorValueStmt(colorValueStmt);

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
			final ValueStmt fromValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setFromValueStmt(fromValueStmt);

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
			final FullClause.FullClauseType type;

			if (ctx.FULL() != null) {
				type = FullClause.FullClauseType.FULL;
			} else if (ctx.LENGTH_CHECK() != null) {
				type = FullClause.FullClauseType.LENGTH_CHECK;
			} else {
				type = null;
			}

			result.setFullClauseType(type);

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
			final GridClause.GridClauseType type;

			if (ctx.GRID() != null) {
				type = GridClause.GridClauseType.GRID;
			} else if (ctx.LEFTLINE() != null) {
				type = GridClause.GridClauseType.LEFTLINE;
			} else if (ctx.OVERLINE() != null) {
				type = GridClause.GridClauseType.OVERLINE;
			} else {
				type = null;
			}

			result.setGridClauseType(type);

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
			final LightClause.LightClauseType type;

			if (ctx.HIGHLIGHT() != null) {
				type = LightClause.LightClauseType.HIGHLIGHT;
			} else if (ctx.LOWLIGHT() != null) {
				type = LightClause.LightClauseType.LOWLIGHT;
			} else {
				type = null;
			}

			result.setLightClauseType(type);

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
			final LineNumberClause.LineNumberClauseType type;

			if (ctx.PLUS() != null) {
				type = LineNumberClause.LineNumberClauseType.PLUS;
			} else if (ctx.PLUSCHAR() != null) {
				type = LineNumberClause.LineNumberClauseType.PLUS;
			} else if (ctx.MINUSCHAR() != null) {
				type = LineNumberClause.LineNumberClauseType.MINUS;
			} else {
				type = LineNumberClause.LineNumberClauseType.EQUAL;
			}

			result.setLineNumberClauseType(type);

			// line number
			if (ctx.integerLiteral() != null) {
				final IntegerLiteral lineNumber = createIntegerLiteral(ctx.integerLiteral());
				result.setIntegerLiteral(lineNumber);
			}

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

			// character
			final ValueStmt characterValueStmt = createValueStmt(ctx.identifier(), ctx.literal());
			result.setCharacterValueStmt(characterValueStmt);

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
			final RequiredClause.RequiredClauseType type;

			if (ctx.REQUIRED() != null) {
				type = RequiredClause.RequiredClauseType.REQUIRED;
			} else if (ctx.EMPTY_CHECK() != null) {
				type = RequiredClause.RequiredClauseType.EMPTY_CHECK;
			} else {
				type = null;
			}

			result.setRequiredClauseType(type);

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
		screenDescriptionEntriesSymbolTable.put(getSymbol(name), screenDescriptionEntry);
	}

	@Override
	public SecureClause addSecureClause(final ScreenDescriptionSecureClauseContext ctx) {
		SecureClause result = (SecureClause) getASGElement(ctx);

		if (result == null) {
			result = new SecureClauseImpl(programUnit, ctx);

			// type
			final SecureClause.SecureClauseType type;

			if (ctx.SECURE() != null) {
				type = SecureClause.SecureClauseType.SECURE;
			} else if (ctx.NO_ECHO() != null) {
				type = SecureClause.SecureClauseType.NO_ECHO;
			} else {
				type = null;
			}

			result.setSecureClauseType(type);

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
			final SignClause.SignClauseType type;

			if (ctx.LEADING() != null) {
				type = SignClause.SignClauseType.LEADING;
			} else if (ctx.TRAILING() != null) {
				type = SignClause.SignClauseType.TRAILING;
			} else {
				type = null;
			}

			result.setSignClauseType(type);

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

			final ValueStmt sizeValueStmt = createValueStmt(ctx.identifier(), ctx.integerLiteral());
			result.setSizeValueStmt(sizeValueStmt);

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
			final UsageClause.UsageClauseType type;

			if (ctx.DISPLAY() != null) {
				type = UsageClause.UsageClauseType.DISPLAY;
			} else if (ctx.DISPLAY_1() != null) {
				type = UsageClause.UsageClauseType.DISPLAY_1;
			} else {
				type = null;
			}

			result.setUsageClauseType(type);

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

			if (ctx.identifier() != null) {
				final Call usingCall = createCall(ctx.identifier());
				result.setUsingCall(usingCall);
			}

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

			if (ctx.literal() != null) {
				final Literal literal = createLiteral(ctx.literal());
				result.setLiteral(literal);
			}

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
	public ScreenDescriptionEntry getPredecessor() {
		return predecessor;
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
		return screenDescriptionEntriesSymbolTable.get(getSymbol(name));
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
	public ScreenDescriptionEntry getSuccessor() {
		return successor;
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
	public void setPredecessor(final ScreenDescriptionEntry predecessor) {
		this.predecessor = predecessor;
	}

	@Override
	public void setSuccessor(final ScreenDescriptionEntry successor) {
		this.successor = successor;
	}

	@Override
	public String toString() {
		return "name=[" + name + "]";
	}
}
