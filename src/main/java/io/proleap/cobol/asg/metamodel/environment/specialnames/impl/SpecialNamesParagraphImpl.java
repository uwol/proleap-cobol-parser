/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.Cobol85Parser.AlphabetClauseContext;
import io.proleap.cobol.Cobol85Parser.AlphabetClauseFormat1Context;
import io.proleap.cobol.Cobol85Parser.AlphabetClauseFormat2Context;
import io.proleap.cobol.Cobol85Parser.AlphabetLiteralsContext;
import io.proleap.cobol.Cobol85Parser.ChannelClauseContext;
import io.proleap.cobol.Cobol85Parser.ClassClauseContext;
import io.proleap.cobol.Cobol85Parser.ClassClauseThroughContext;
import io.proleap.cobol.Cobol85Parser.CurrencySignClauseContext;
import io.proleap.cobol.Cobol85Parser.DecimalPointClauseContext;
import io.proleap.cobol.Cobol85Parser.DefaultDisplaySignClauseContext;
import io.proleap.cobol.Cobol85Parser.OdtClauseContext;
import io.proleap.cobol.Cobol85Parser.ReserveNetworkClauseContext;
import io.proleap.cobol.Cobol85Parser.SpecialNamesParagraphContext;
import io.proleap.cobol.Cobol85Parser.SymbolicCharactersClauseContext;
import io.proleap.cobol.asg.metamodel.IntegerLiteral;
import io.proleap.cobol.asg.metamodel.Literal;
import io.proleap.cobol.asg.metamodel.MnemonicName;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.environment.specialnames.AlphabetClause;
import io.proleap.cobol.asg.metamodel.environment.specialnames.AlphabetClauseAlphanumeric;
import io.proleap.cobol.asg.metamodel.environment.specialnames.AlphabetClauseNational;
import io.proleap.cobol.asg.metamodel.environment.specialnames.ChannelClause;
import io.proleap.cobol.asg.metamodel.environment.specialnames.ClassClause;
import io.proleap.cobol.asg.metamodel.environment.specialnames.CurrencySignClause;
import io.proleap.cobol.asg.metamodel.environment.specialnames.DecimalPointClause;
import io.proleap.cobol.asg.metamodel.environment.specialnames.DefaultDisplaySignClause;
import io.proleap.cobol.asg.metamodel.environment.specialnames.OdtClause;
import io.proleap.cobol.asg.metamodel.environment.specialnames.ReserveNetworkClause;
import io.proleap.cobol.asg.metamodel.environment.specialnames.SpecialNamesParagraph;
import io.proleap.cobol.asg.metamodel.environment.specialnames.SymbolicCharactersClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class SpecialNamesParagraphImpl extends CobolDivisionElementImpl implements SpecialNamesParagraph {

	private final static Logger LOG = LogManager.getLogger(SpecialNamesParagraphImpl.class);

	protected List<AlphabetClause> alphabetClauses = new ArrayList<AlphabetClause>();

	protected ChannelClause channelClause;

	protected ClassClause classClause;

	protected final SpecialNamesParagraphContext ctx;

	protected CurrencySignClause currencySignClause;

	protected DecimalPointClause decimalPointClause;

	protected DefaultDisplaySignClause defaultDisplaySignClause;

	protected OdtClause odtClause;

	protected ReserveNetworkClause reserveNetworkClause;

	protected SymbolicCharactersClause symbolicCharactersClause;

	public SpecialNamesParagraphImpl(final ProgramUnit programUnit, final SpecialNamesParagraphContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public AlphabetClauseAlphanumeric addAlphabetClauseAlphanumeric(final AlphabetClauseFormat1Context ctx) {
		AlphabetClauseAlphanumeric result = (AlphabetClauseAlphanumeric) getASGElement(ctx);

		if (result == null) {
			result = new AlphabetClauseAlphanumericImpl(programUnit, ctx);

			/*
			 * alphabet name
			 */
			final Call alphabetCall = createCall(ctx.alphabetName());
			result.setAlphabetCall(alphabetCall);

			/*
			 * type
			 */
			final AlphabetClauseAlphanumeric.AlphabetClauseAlphanumericType alphabetClauseAlphanumericType;

			if (ctx.EBCDIC() != null) {
				alphabetClauseAlphanumericType = AlphabetClauseAlphanumeric.AlphabetClauseAlphanumericType.EBCDIC;
			} else if (ctx.ASCII() != null) {
				alphabetClauseAlphanumericType = AlphabetClauseAlphanumeric.AlphabetClauseAlphanumericType.ASCII;
			} else if (ctx.STANDARD_1() != null) {
				alphabetClauseAlphanumericType = AlphabetClauseAlphanumeric.AlphabetClauseAlphanumericType.STANDARD_1;
			} else if (ctx.STANDARD_2() != null) {
				alphabetClauseAlphanumericType = AlphabetClauseAlphanumeric.AlphabetClauseAlphanumericType.STANDARD_2;
			} else if (ctx.NATIVE() != null) {
				alphabetClauseAlphanumericType = AlphabetClauseAlphanumeric.AlphabetClauseAlphanumericType.NATIVE;
			} else {
				alphabetClauseAlphanumericType = null;
			}

			result.setAlphabetClauseAlphanumericType(alphabetClauseAlphanumericType);

			/*
			 * character set, collating sequence
			 */
			if (ctx.cobolWord() != null) {
				final Call characterSetCall = createCall(ctx.cobolWord());
				result.addCharacterSetCall(characterSetCall);
			} else if (!ctx.alphabetLiterals().isEmpty()) {
				final List<AlphabetLiteralsContext> alphabetLiteralsContexts = ctx.alphabetLiterals();

				for (final AlphabetLiteralsContext alphabetLiteralsContext : alphabetLiteralsContexts) {
					result.addCharacterSetCalls(alphabetLiteralsContext);
				}
			}

			alphabetClauses.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AlphabetClauseNational addAlphabetClauseNational(final AlphabetClauseFormat2Context ctx) {
		AlphabetClauseNational result = (AlphabetClauseNational) getASGElement(ctx);

		if (result == null) {
			result = new AlphabetClauseNationalImpl(programUnit, ctx);

			/*
			 * alphabet name
			 */
			final Call alphabetCall = createCall(ctx.alphabetName());
			result.setAlphabetCall(alphabetCall);

			/*
			 * type
			 */
			final AlphabetClauseNational.AlphabetClauseNationalType alphabetClauseNationalType;

			if (ctx.NATIVE() != null) {
				alphabetClauseNationalType = AlphabetClauseNational.AlphabetClauseNationalType.NATIVE;
			} else if (ctx.CCSVERSION() != null) {
				alphabetClauseNationalType = AlphabetClauseNational.AlphabetClauseNationalType.CCS_VERSION;
			} else {
				LOG.warn("unknown type at {}", ctx);
				alphabetClauseNationalType = null;
			}

			result.setAlphabetClauseType(alphabetClauseNationalType);

			/*
			 * literal
			 */
			if (ctx.literal() != null) {
				final Literal ccsVersionLiteral = createLiteral(ctx.literal());
				result.setCcsVersionLiteral(ccsVersionLiteral);
			}

			alphabetClauses.add(result);
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ChannelClause addChannelClause(final ChannelClauseContext ctx) {
		ChannelClause result = (ChannelClause) getASGElement(ctx);

		if (result == null) {
			result = new ChannelClauseImpl(programUnit, ctx);

			final IntegerLiteral integerLiteral = createIntegerLiteral(ctx.integerLiteral());
			result.setIntegerLiteral(integerLiteral);

			final MnemonicName mnemonicName = createMnemonicName(ctx.mnemonicName());
			result.setMnemonicName(mnemonicName);

			channelClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ClassClause addClassClause(final ClassClauseContext ctx) {
		ClassClause result = (ClassClause) getASGElement(ctx);

		if (result == null) {
			result = new ClassClauseImpl(programUnit, ctx);

			/*
			 * class name
			 */
			final Call classCall = createCall(ctx.className());
			result.setClassCall(classCall);

			/*
			 * type
			 */
			final ClassClause.Type type;

			if (ctx.ALPHANUMERIC() != null) {
				type = ClassClause.Type.ALPHA_NUMERIC;
			} else if (ctx.NATIONAL() != null) {
				type = ClassClause.Type.NATIONAL;
			} else {
				type = null;
			}

			result.setType(type);

			/*
			 * class through
			 */
			for (final ClassClauseThroughContext classClauseThroughContext : ctx.classClauseThrough()) {
				result.addClassThrough(classClauseThroughContext);
			}

			classClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public CurrencySignClause addCurrencySignClause(final CurrencySignClauseContext ctx) {
		CurrencySignClause result = (CurrencySignClause) getASGElement(ctx);

		if (result == null) {
			result = new CurrencySignClauseImpl(programUnit, ctx);

			final Literal currencyLiteral = createLiteral(ctx.literal(0));
			result.setCurrencyLiteral(currencyLiteral);

			if (ctx.literal().size() > 1) {
				final Literal pictureSymbolLiteral = createLiteral(ctx.literal(1));
				result.setPictureSymbolLiteral(pictureSymbolLiteral);
			}

			currencySignClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DecimalPointClause addDecimalPointClause(final DecimalPointClauseContext ctx) {
		DecimalPointClause result = (DecimalPointClause) getASGElement(ctx);

		if (result == null) {
			result = new DecimalPointClauseImpl(programUnit, ctx);

			decimalPointClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DefaultDisplaySignClause addDefaultDisplaySignClause(final DefaultDisplaySignClauseContext ctx) {
		DefaultDisplaySignClause result = (DefaultDisplaySignClause) getASGElement(ctx);

		if (result == null) {
			result = new DefaultDisplaySignClauseImpl(programUnit, ctx);

			/*
			 * type
			 */
			final DefaultDisplaySignClause.Type type;

			if (ctx.LEADING() != null) {
				type = DefaultDisplaySignClause.Type.LEADING;
			} else if (ctx.TRAILING() != null) {
				type = DefaultDisplaySignClause.Type.TRAILING;
			} else {
				type = null;
			}

			result.setType(type);

			defaultDisplaySignClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public OdtClause addOdtClause(final OdtClauseContext ctx) {
		OdtClause result = (OdtClause) getASGElement(ctx);

		if (result == null) {
			result = new OdtClauseImpl(programUnit, ctx);

			final MnemonicName mnemonicName = createMnemonicName(ctx.mnemonicName());
			result.setMnemonicName(mnemonicName);

			odtClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public ReserveNetworkClause addReserveNetworkClause(final ReserveNetworkClauseContext ctx) {
		ReserveNetworkClause result = (ReserveNetworkClause) getASGElement(ctx);

		if (result == null) {
			result = new ReserveNetworkClauseImpl(programUnit, ctx);

			reserveNetworkClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public SymbolicCharactersClause addSymbolicCharactersClause(final SymbolicCharactersClauseContext ctx) {
		SymbolicCharactersClause result = (SymbolicCharactersClause) getASGElement(ctx);

		if (result == null) {
			result = new SymbolicCharactersClauseImpl(programUnit, ctx);

			/*
			 * type
			 */
			final SymbolicCharactersClause.Type type;

			if (ctx.ALPHANUMERIC() != null) {
				type = SymbolicCharactersClause.Type.ALPHA_NUMERIC;
			} else if (ctx.NATIONAL() != null) {
				type = SymbolicCharactersClause.Type.NATIONAL;
			} else {
				type = null;
			}

			result.setType(type);

			/*
			 * alphabet
			 */

			symbolicCharactersClause = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public AlphabetClause createAlphabetClause(final AlphabetClauseContext ctx) {
		final AlphabetClause result;

		if (ctx.alphabetClauseFormat1() != null) {
			result = addAlphabetClauseAlphanumeric(ctx.alphabetClauseFormat1());
		} else if (ctx.alphabetClauseFormat2() != null) {
			result = addAlphabetClauseNational(ctx.alphabetClauseFormat2());
		} else {
			LOG.warn("unknown data description entry {}", ctx);
			result = null;
		}

		return result;
	}

	@Override
	public List<AlphabetClause> getAlphabetClauses() {
		return alphabetClauses;
	}

	@Override
	public ChannelClause getChannelClause() {
		return channelClause;
	}

	@Override
	public ClassClause getClassClause() {
		return classClause;
	}

	@Override
	public CurrencySignClause getCurrencySignClause() {
		return currencySignClause;
	}

	@Override
	public DecimalPointClause getDecimalPointClause() {
		return decimalPointClause;
	}

	@Override
	public DefaultDisplaySignClause getDefaultDisplaySignClause() {
		return defaultDisplaySignClause;
	}

	@Override
	public OdtClause getOdtClause() {
		return odtClause;
	}

	@Override
	public ReserveNetworkClause getReserveNetworkClause() {
		return reserveNetworkClause;
	}

	@Override
	public SymbolicCharactersClause getSymbolicCharactersClause() {
		return symbolicCharactersClause;
	}

}
