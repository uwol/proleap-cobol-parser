/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.sort;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.SortCollatingSequencePhraseContext;
import io.proleap.cobol.Cobol85Parser.SortDuplicatesPhraseContext;
import io.proleap.cobol.Cobol85Parser.SortGivingPhraseContext;
import io.proleap.cobol.Cobol85Parser.SortInputProcedurePhraseContext;
import io.proleap.cobol.Cobol85Parser.SortOnKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.SortOutputProcedurePhraseContext;
import io.proleap.cobol.Cobol85Parser.SortUsingContext;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.Statement;

/**
 * Creates a sort file from input procedures or by transferring records from
 * another file. After sorting the records in the sort file makes them available
 * in sorted order to an output procedure or an output file.
 */
public interface SortStatement extends Statement {

	CollatingSequence addCollatingSequence(SortCollatingSequencePhraseContext ctx);

	Duplicates addDuplicates(SortDuplicatesPhraseContext ctx);

	Givings addGiving(SortGivingPhraseContext ctx);

	InputProcedure addInputProcedure(SortInputProcedurePhraseContext ctx);

	OnKey addOnKey(SortOnKeyClauseContext ctx);

	OutputProcedure addOutputProcedure(SortOutputProcedurePhraseContext ctx);

	Using addUsing(SortUsingContext ctx);

	CollatingSequence getCollatingSequence();

	Duplicates getDuplicates();

	Call getFileCall();

	List<Givings> getGivings();

	InputProcedure getInputProcedure();

	List<OnKey> getOnKeys();

	OutputProcedure getOutputProcedure();

	List<Using> getUsings();

	void setFileCall(Call fileCall);

}
