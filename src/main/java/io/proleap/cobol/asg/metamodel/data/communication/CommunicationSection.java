/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.communication;

import java.util.List;

import io.proleap.cobol.CobolParser.CommunicationDescriptionEntryContext;
import io.proleap.cobol.CobolParser.CommunicationDescriptionEntryFormat1Context;
import io.proleap.cobol.CobolParser.CommunicationDescriptionEntryFormat2Context;
import io.proleap.cobol.CobolParser.CommunicationDescriptionEntryFormat3Context;
import io.proleap.cobol.asg.metamodel.data.datadescription.DataDescriptionEntryContainer;

public interface CommunicationSection extends DataDescriptionEntryContainer {

	CommunicationDescriptionEntryInput addCommunicationDescriptionEntryInput(
			CommunicationDescriptionEntryFormat1Context ctx);

	CommunicationDescriptionEntryInputOutput addCommunicationDescriptionEntryInputOutput(
			CommunicationDescriptionEntryFormat3Context ctx);

	CommunicationDescriptionEntryOutput addCommunicationDescriptionEntryOutput(
			CommunicationDescriptionEntryFormat2Context ctx);

	CommunicationDescriptionEntry createCommunicationDescriptionEntry(
			CommunicationDescriptionEntryContext communicationDescriptionEntryContext);

	List<CommunicationDescriptionEntry> getCommunicationDescriptionEntries();

	List<CommunicationDescriptionEntry> getCommunicationDescriptionEntries(String name);

	CommunicationDescriptionEntry getCommunicationDescriptionEntry(String name);

}
