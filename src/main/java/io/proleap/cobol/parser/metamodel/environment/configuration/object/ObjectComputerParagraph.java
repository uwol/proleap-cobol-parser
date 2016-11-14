/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.configuration.object;

import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.NamedElement;

public interface ObjectComputerParagraph extends CobolDivisionElement, NamedElement {

	CharacterSetClause getCharacterSetClause();

	CollatingSequenceClause getCollatingSequenceClause();

	DiskSizeClause getDiskSizeClause();

	MemorySizeClause getMemorySizeClause();

	SegmentLimitClause getSegmentLimitClause();

	void setCharacterSetClause(CharacterSetClause characterSetClause);

	void setCollatingSequenceClause(CollatingSequenceClause collatingSequenceClause);

	void setDiskSizeClause(DiskSizeClause diskSizeClause);

	void setMemorySizeClause(MemorySizeClause memorySizeClause);

	void setSegmentLimitClause(SegmentLimitClause segmentLimitClause);
}
