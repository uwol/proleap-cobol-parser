/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.inputoutput.iocontrol;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.CommitmentControlClauseContext;
import io.proleap.cobol.Cobol85Parser.MultipleFileClauseContext;
import io.proleap.cobol.Cobol85Parser.RerunClauseContext;
import io.proleap.cobol.Cobol85Parser.SameClauseContext;
import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.valuestmt.ValueStmt;

public interface IoControlParagraph extends CobolDivisionElement {

	CommitmentControlClause addCommitmentControlClause(CommitmentControlClauseContext ctx);

	MultipleFileClause addMultipleFileClause(MultipleFileClauseContext ctx);

	RerunClause addRerunClause(RerunClauseContext ctx);

	SameClause addSameClause(SameClauseContext ctx);

	CommitmentControlClause getCommitmentControlClause();

	ValueStmt getFileNameValueStmt();

	MultipleFileClause getMultipleFileClause();

	RerunClause getRerunClause();

	List<SameClause> getSameClauses();

	void setFileNameValueStmt(ValueStmt fileNameValueStmt);
}
