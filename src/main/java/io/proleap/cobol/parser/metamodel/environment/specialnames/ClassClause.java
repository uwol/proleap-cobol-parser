/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.specialnames;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.ClassClauseThroughContext;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public interface ClassClause extends CobolDivisionElement {

	enum Type {
		AlphaNumeric, National
	}

	ClassClauseThrough addClassClauseThrough(ClassClauseThroughContext ctx);

	List<ClassClauseThrough> getClassClauseThroughs();

	ValueStmt getClassNameValueStmt();

	Type getType();

	void setClassNameValueStmt(ValueStmt classNameValueStmt);

	void setType(Type type);
}
