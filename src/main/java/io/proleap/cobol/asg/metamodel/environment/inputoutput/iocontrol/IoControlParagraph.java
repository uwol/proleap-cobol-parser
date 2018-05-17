/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.iocontrol;

import java.util.List;

import io.proleap.cobol.CobolParser.CommitmentControlClauseContext;
import io.proleap.cobol.CobolParser.MultipleFileClauseContext;
import io.proleap.cobol.CobolParser.RerunClauseContext;
import io.proleap.cobol.CobolParser.SameClauseContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface IoControlParagraph extends CobolDivisionElement {

	CommitmentControlClause addCommitmentControlClause(CommitmentControlClauseContext ctx);

	MultipleFileClause addMultipleFileClause(MultipleFileClauseContext ctx);

	RerunClause addRerunClause(RerunClauseContext ctx);

	SameClause addSameClause(SameClauseContext ctx);

	CommitmentControlClause getCommitmentControlClause();

	Call getFileCall();

	MultipleFileClause getMultipleFileClause();

	RerunClause getRerunClause();

	List<SameClause> getSameClauses();

	void setFileCall(Call fileCall);
}
