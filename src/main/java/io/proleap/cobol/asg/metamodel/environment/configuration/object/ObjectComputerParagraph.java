/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.configuration.object;

import io.proleap.cobol.CobolParser.CharacterSetClauseContext;
import io.proleap.cobol.CobolParser.CollatingSequenceClauseContext;
import io.proleap.cobol.CobolParser.DiskSizeClauseContext;
import io.proleap.cobol.CobolParser.MemorySizeClauseContext;
import io.proleap.cobol.CobolParser.SegmentLimitClauseContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.NamedElement;

public interface ObjectComputerParagraph extends CobolDivisionElement, NamedElement {

	CharacterSetClause addCharacterSetClause(CharacterSetClauseContext ctx);

	CollatingSequenceClause addCollatingSequenceClause(CollatingSequenceClauseContext ctx);

	DiskSizeClause addDiskSizeClause(DiskSizeClauseContext ctx);

	MemorySizeClause addMemorySizeClause(MemorySizeClauseContext ctx);

	SegmentLimitClause addSegmentLimitClause(SegmentLimitClauseContext ctx);

	CharacterSetClause getCharacterSetClause();

	CollatingSequenceClause getCollatingSequenceClause();

	DiskSizeClause getDiskSizeClause();

	MemorySizeClause getMemorySizeClause();

	SegmentLimitClause getSegmentLimitClause();

}
