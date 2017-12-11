/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.sort;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.SortCollatingSequencePhraseContext;
import io.proleap.cobol.Cobol85Parser.SortDuplicatesPhraseContext;
import io.proleap.cobol.Cobol85Parser.SortGivingPhraseContext;
import io.proleap.cobol.Cobol85Parser.SortInputProcedurePhraseContext;
import io.proleap.cobol.Cobol85Parser.SortOnKeyClauseContext;
import io.proleap.cobol.Cobol85Parser.SortOutputProcedurePhraseContext;
import io.proleap.cobol.Cobol85Parser.SortUsingContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.Statement;

/**
 * Creates a sort file from input procedures or by transferring records from
 * another file. After sorting the records in the sort file makes them available
 * in sorted order to an output procedure or an output file.
 */
public interface SortStatement extends Statement {

	CollatingSequence addCollatingSequence(SortCollatingSequencePhraseContext ctx);

	Duplicates addDuplicates(SortDuplicatesPhraseContext ctx);

	GivingPhrase addGivingPhrase(SortGivingPhraseContext ctx);

	InputProcedure addInputProcedure(SortInputProcedurePhraseContext ctx);

	OnKey addOnKey(SortOnKeyClauseContext ctx);

	OutputProcedure addOutputProcedure(SortOutputProcedurePhraseContext ctx);

	UsingPhrase addUsingPhrase(SortUsingContext ctx);

	CollatingSequence getCollatingSequence();

	Duplicates getDuplicates();

	Call getFileCall();

	List<GivingPhrase> getGivingPhrases();

	InputProcedure getInputProcedure();

	List<OnKey> getOnKeys();

	OutputProcedure getOutputProcedure();

	List<UsingPhrase> getUsingPhrases();

	void setFileCall(Call fileCall);

}
