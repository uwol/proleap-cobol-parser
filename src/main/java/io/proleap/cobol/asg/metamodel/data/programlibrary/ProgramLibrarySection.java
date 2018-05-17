/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.programlibrary;

import java.util.List;

import io.proleap.cobol.CobolParser.LibraryDescriptionEntryContext;
import io.proleap.cobol.CobolParser.LibraryDescriptionEntryFormat1Context;
import io.proleap.cobol.CobolParser.LibraryDescriptionEntryFormat2Context;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;

public interface ProgramLibrarySection extends CobolDivisionElement {

	LibraryDescriptionEntryExport addLibraryDescriptionEntryExport(LibraryDescriptionEntryFormat1Context ctx);

	LibraryDescriptionEntryImport addLibraryDescriptionEntryImport(LibraryDescriptionEntryFormat2Context ctx);

	LibraryDescriptionEntry createLibraryDescriptionEntry(LibraryDescriptionEntryContext ctx);

	List<LibraryDescriptionEntry> getLibraryDescriptionEntries();

	LibraryDescriptionEntry getLibraryDescriptionEntry(String name);

}
