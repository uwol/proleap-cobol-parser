/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.configuration.object;

import io.proleap.cobol.Cobol85Parser.CharacterSetClauseContext;
import io.proleap.cobol.Cobol85Parser.CollatingSequenceClauseContext;
import io.proleap.cobol.Cobol85Parser.DiskSizeClauseContext;
import io.proleap.cobol.Cobol85Parser.MemorySizeClauseContext;
import io.proleap.cobol.Cobol85Parser.SegmentLimitClauseContext;
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
