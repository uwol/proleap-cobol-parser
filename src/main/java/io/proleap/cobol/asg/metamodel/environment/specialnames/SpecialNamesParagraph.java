/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames;

import java.util.List;

import io.proleap.cobol.CobolParser.AlphabetClauseContext;
import io.proleap.cobol.CobolParser.AlphabetClauseFormat1Context;
import io.proleap.cobol.CobolParser.AlphabetClauseFormat2Context;
import io.proleap.cobol.CobolParser.ChannelClauseContext;
import io.proleap.cobol.CobolParser.ClassClauseContext;
import io.proleap.cobol.CobolParser.CurrencySignClauseContext;
import io.proleap.cobol.CobolParser.DecimalPointClauseContext;
import io.proleap.cobol.CobolParser.DefaultDisplaySignClauseContext;
import io.proleap.cobol.CobolParser.OdtClauseContext;
import io.proleap.cobol.CobolParser.ReserveNetworkClauseContext;
import io.proleap.cobol.CobolParser.SymbolicCharactersClauseContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface SpecialNamesParagraph extends CobolDivisionElement {

	AlphabetClauseAlphanumeric addAlphabetClauseAlphanumeric(AlphabetClauseFormat1Context ctx);

	AlphabetClauseNational addAlphabetClauseNational(AlphabetClauseFormat2Context ctx);

	ChannelClause addChannelClause(ChannelClauseContext ctx);

	ClassClause addClassClause(ClassClauseContext ctx);

	CurrencySignClause addCurrencySignClause(CurrencySignClauseContext ctx);

	DecimalPointClause addDecimalPointClause(DecimalPointClauseContext ctx);

	DefaultDisplaySignClause addDefaultDisplaySignClause(DefaultDisplaySignClauseContext ctx);

	OdtClause addOdtClause(OdtClauseContext ctx);

	ReserveNetworkClause addReserveNetworkClause(ReserveNetworkClauseContext ctx);

	SymbolicCharactersClause addSymbolicCharactersClause(SymbolicCharactersClauseContext ctx);

	AlphabetClause createAlphabetClause(AlphabetClauseContext ctx);

	List<AlphabetClause> getAlphabetClauses();

	ChannelClause getChannelClause();

	ClassClause getClassClause();

	CurrencySignClause getCurrencySignClause();

	DecimalPointClause getDecimalPointClause();

	DefaultDisplaySignClause getDefaultDisplaySignClause();

	OdtClause getOdtClause();

	ReserveNetworkClause getReserveNetworkClause();

	SymbolicCharactersClause getSymbolicCharactersClause();
}
