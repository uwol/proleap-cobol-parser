/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.linkage.impl;

import io.proleap.cobol.Cobol85Parser.LinkageSectionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.data.datadescription.impl.DataDescriptionEntryContainerImpl;
import io.proleap.cobol.asg.metamodel.data.linkage.LinkageSection;

public class LinkageSectionImpl extends DataDescriptionEntryContainerImpl implements LinkageSection {

	protected final LinkageSectionContext ctx;

	public LinkageSectionImpl(final ProgramUnit programUnit, final LinkageSectionContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

}
