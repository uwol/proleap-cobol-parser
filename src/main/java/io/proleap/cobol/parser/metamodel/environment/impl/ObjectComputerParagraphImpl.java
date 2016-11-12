/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.environment.impl;

import io.proleap.cobol.Cobol85Parser.ObjectComputerParagraphContext;
import io.proleap.cobol.parser.metamodel.CobolDivision;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.environment.CharacterSetClause;
import io.proleap.cobol.parser.metamodel.environment.CollatingSequenceClause;
import io.proleap.cobol.parser.metamodel.environment.DiskSizeClause;
import io.proleap.cobol.parser.metamodel.environment.MemorySizeClause;
import io.proleap.cobol.parser.metamodel.environment.ObjectComputerParagraph;
import io.proleap.cobol.parser.metamodel.environment.SegmentLimitClause;

public class ObjectComputerParagraphImpl extends ConfigurationSectionParagraphImpl implements ObjectComputerParagraph {

	protected CharacterSetClause characterSetClause;

	protected CollatingSequenceClause collatingSequenceClause;

	protected final ObjectComputerParagraphContext ctx;

	protected DiskSizeClause diskSizeClause;

	protected MemorySizeClause memorySizeClause;

	protected String name;

	protected SegmentLimitClause segmentLimitClause;

	public ObjectComputerParagraphImpl(final String name, final ProgramUnit programUnit, final CobolDivision scope,
			final ObjectComputerParagraphContext ctx) {
		super(programUnit, scope, ctx);

		this.name = name;
		this.ctx = ctx;
	}

	@Override
	public CharacterSetClause getCharacterSetClause() {
		return characterSetClause;
	}

	@Override
	public CollatingSequenceClause getCollatingSequenceClause() {
		return collatingSequenceClause;
	}

	@Override
	public DiskSizeClause getDiskSizeClause() {
		return diskSizeClause;
	}

	@Override
	public MemorySizeClause getMemorySizeClause() {
		return memorySizeClause;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public SegmentLimitClause getSegmentLimitClause() {
		return segmentLimitClause;
	}

	@Override
	public void setCharacterSetClause(final CharacterSetClause characterSetClause) {
		this.characterSetClause = characterSetClause;
	}

	@Override
	public void setCollatingSequenceClause(final CollatingSequenceClause collatingSequenceClause) {
		this.collatingSequenceClause = collatingSequenceClause;
	}

	@Override
	public void setDiskSizeClause(final DiskSizeClause diskSizeClause) {
		this.diskSizeClause = diskSizeClause;
	}

	@Override
	public void setMemorySizeClause(final MemorySizeClause memorySizeClause) {
		this.memorySizeClause = memorySizeClause;
	}

	@Override
	public void setSegmentLimitClause(final SegmentLimitClause segmentLimitClause) {
		this.segmentLimitClause = segmentLimitClause;
	}

}
