/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.communication;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.CommunicationDescriptionEntryContext;
import io.proleap.cobol.parser.metamodel.data.datadescription.DataDescriptionEntryContainer;

public interface CommunicationSection extends DataDescriptionEntryContainer {

	CommunicationDescriptionEntry addCommunicationDescriptionEntry(CommunicationDescriptionEntryContext ctx);

	List<CommunicationDescriptionEntry> getCommunicationDescriptionEntries();

	CommunicationDescriptionEntry getCommunicationDescriptionEntry(String name);

}
